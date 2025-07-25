package com.firstclub.membership.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.firstclub.membership.models.MembershipPlan;

public interface MembershipPlanRepository extends JpaRepository <MembershipPlan, Long> {
	
}
