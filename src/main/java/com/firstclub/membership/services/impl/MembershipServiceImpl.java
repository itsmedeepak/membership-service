package com.firstclub.membership.services.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.firstclub.membership.dtos.SubscriptionRequest;
import com.firstclub.membership.exceptions.InvalidRequestException;
import com.firstclub.membership.exceptions.ResourceNotFoundException;
import com.firstclub.membership.exceptions.DatabaseException;
import com.firstclub.membership.models.MembershipPlan;
import com.firstclub.membership.models.User;
import com.firstclub.membership.models.UserMembership;
import com.firstclub.membership.repo.MembershipPlanRepository;
import com.firstclub.membership.repo.UserMembershipRepository;
import com.firstclub.membership.repo.UserRepository;
import com.firstclub.membership.services.MembershipService;

@Service
public class MembershipServiceImpl implements MembershipService {

    private final UserRepository userRepository;
    private final MembershipPlanRepository planRepository;
    private final UserMembershipRepository userMembershipRepository;

    public MembershipServiceImpl(UserRepository userRepository,
                                 MembershipPlanRepository planRepository,
                                 UserMembershipRepository userMembershipRepository) {
        this.userRepository = userRepository;
        this.planRepository = planRepository;
        this.userMembershipRepository = userMembershipRepository;
    }

    @Override
    public List<MembershipPlan> getAllPlans() {
        try {
            return planRepository.findAll();
        } catch (DataAccessException ex) {
            throw new DatabaseException("Failed to fetch membership plans.", ex);
        }
    }

    @Override
    @Transactional
    public UserMembership subscribe(SubscriptionRequest request) {
        if (request.getUserId() == null || request.getPlanId() == null) {
            throw new InvalidRequestException("User ID and Plan ID are required.");
        }

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + request.getUserId()));

        MembershipPlan plan = planRepository.findById(request.getPlanId())
                .orElseThrow(() -> new ResourceNotFoundException("Plan not found with id: " + request.getPlanId()));

        try {
            Optional<UserMembership> existingMembershipOpt =
                    userMembershipRepository.findByUser_UserIdAndActive(user.getUserId(), true);

            existingMembershipOpt.ifPresent(existing -> {
                existing.setActive(false);
                userMembershipRepository.save(existing);
            });

            UserMembership newMembership = new UserMembership();
            newMembership.setUser(user);
            newMembership.setPlan(plan);
            newMembership.setActive(true);
            newMembership.setStartDate(LocalDate.now());
            newMembership.setEndDate(LocalDate.now().plusDays(plan.getDurationInDays()));

            return userMembershipRepository.save(newMembership);
        } catch (DataAccessException ex) {
            throw new DatabaseException("Failed to subscribe user to membership.", ex);
        }
    }

    @Override
    @Transactional
    public UserMembership changePlan(Long userId, Long newPlanId) {
        if (userId == null || newPlanId == null) {
            throw new InvalidRequestException("User ID and new plan ID must be provided.");
        }

        UserMembership activeMembership = findActiveMembership(userId);

        MembershipPlan newPlan = planRepository.findById(newPlanId)
                .orElseThrow(() -> new ResourceNotFoundException("Plan not found with id: " + newPlanId));

        if (activeMembership.getPlan().getPlanId().equals(newPlan.getPlanId())) {
            throw new InvalidRequestException("Cannot change to the same plan.");
        }

        try {
            activeMembership.setPlan(newPlan);
            return userMembershipRepository.save(activeMembership);
        } catch (DataAccessException ex) {
            throw new DatabaseException("Failed to change the plan for user.", ex);
        }
    }

    @Override
    @Transactional
    public String cancelSubscription(Long userId) {
        UserMembership activeMembership = findActiveMembership(userId);

        try {
            activeMembership.setActive(false);
            userMembershipRepository.save(activeMembership);
            return "Subscription cancelled successfully.";
        } catch (DataAccessException ex) {
            throw new DatabaseException("Failed to cancel subscription.", ex);
        }
    }

    @Override
    public UserMembership getActiveUserMembership(Long userId) {
        return findActiveMembership(userId);
    }

    private UserMembership findActiveMembership(Long userId) {
        if (userId == null) {
            throw new InvalidRequestException("User ID is required.");
        }

        return userMembershipRepository.findByUser_UserIdAndActive(userId, true)
                .orElseThrow(() -> new ResourceNotFoundException("No active membership found for user id: " + userId));
    }
}
