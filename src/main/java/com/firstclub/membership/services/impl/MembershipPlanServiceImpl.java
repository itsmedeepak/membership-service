package com.firstclub.membership.services.impl;

import com.firstclub.membership.models.MembershipPlan;
import com.firstclub.membership.repo.MembershipPlanRepository;
import com.firstclub.membership.services.MembershipPlanService;
import com.firstclub.membership.exceptions.ResourceNotFoundException;
import com.firstclub.membership.exceptions.InvalidRequestException;
import com.firstclub.membership.exceptions.DatabaseException;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MembershipPlanServiceImpl implements MembershipPlanService {

    private final MembershipPlanRepository planRepository;

    public MembershipPlanServiceImpl(MembershipPlanRepository planRepository) {
        this.planRepository = planRepository;
    }

    @Override
    public MembershipPlan createPlan(MembershipPlan plan) {
        if (plan == null || plan.getPlan() == null || plan.getPrice() == null || plan.getDurationInDays() <= 0) {
            throw new InvalidRequestException("Invalid plan details provided.");
        }

        try {
            return planRepository.save(plan);
        } catch (DataAccessException ex) {
            throw new DatabaseException("Failed to save membership plan.", ex);
        }
    }

    @Override
    public MembershipPlan getPlanById(Long planId) {
        if (planId == null || planId <= 0) {
            throw new InvalidRequestException("Invalid plan ID.");
        }

        return planRepository.findById(planId)
                .orElseThrow(() -> new ResourceNotFoundException("Membership Plan not found with id: " + planId));
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
    public MembershipPlan updatePlan(Long planId, MembershipPlan updatedPlan) {
        if (updatedPlan == null) {
            throw new InvalidRequestException("Updated plan data must not be null.");
        }

        MembershipPlan existing = getPlanById(planId);

        if (updatedPlan.getPlan() != null) {
            existing.setPlan(updatedPlan.getPlan());
        }

        if (updatedPlan.getPrice() != null) {
            existing.setPrice(updatedPlan.getPrice());
        }

        if (updatedPlan.getDurationInDays() > 0) {
            existing.setDurationInDays(updatedPlan.getDurationInDays());
        }

        try {
            return planRepository.save(existing);
        } catch (DataAccessException ex) {
            throw new DatabaseException("Failed to update membership plan.", ex);
        }
    }

    @Override
    public void deletePlan(Long planId) {
        MembershipPlan existing = getPlanById(planId);

        try {
            planRepository.delete(existing);
        } catch (DataAccessException ex) {
            throw new DatabaseException("Failed to delete membership plan.", ex);
        }
    }
}
