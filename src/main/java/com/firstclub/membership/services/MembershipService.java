package com.firstclub.membership.services;

import java.util.List;

import com.firstclub.membership.dtos.SubscriptionRequest;
import com.firstclub.membership.models.MembershipPlan;
import com.firstclub.membership.models.UserMembership;

public interface MembershipService {
	List<MembershipPlan> getAllPlans();
	UserMembership subscribe(SubscriptionRequest request);
	String cancelSubscription(Long userId);
	UserMembership getActiveUserMembership(Long userId);
	UserMembership changePlan(Long userId, Long newPlanId);
}