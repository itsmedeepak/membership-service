package com.firstclub.membership.services;

import com.firstclub.membership.models.MembershipPlan;

import java.util.List;

public interface MembershipPlanService {

    MembershipPlan createPlan(MembershipPlan plan);

    MembershipPlan getPlanById(Long planId);

    List<MembershipPlan> getAllPlans();

    MembershipPlan updatePlan(Long planId, MembershipPlan updatedPlan);

    void deletePlan(Long planId);
}
