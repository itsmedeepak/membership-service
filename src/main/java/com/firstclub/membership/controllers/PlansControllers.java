package com.firstclub.membership.controllers;

import com.firstclub.membership.models.MembershipPlan;
import com.firstclub.membership.services.MembershipPlanService;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/plans")
public class PlansControllers {

    private final MembershipPlanService planService;

    public PlansControllers(MembershipPlanService planService) {
        this.planService = planService;
    }

    @PostMapping
    public ResponseEntity<MembershipPlan> createPlan(@RequestBody MembershipPlan plan) {
        MembershipPlan created = planService.createPlan(plan);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @GetMapping("/{planId}")
    public ResponseEntity<MembershipPlan> getPlanById(@PathVariable Long planId) {
        return ResponseEntity.ok(planService.getPlanById(planId));
    }

    @GetMapping
    public ResponseEntity<List<MembershipPlan>> getAllPlans() {
        return ResponseEntity.ok(planService.getAllPlans());
    }

    @PutMapping("/{planId}")
    public ResponseEntity<MembershipPlan> updatePlan(@PathVariable Long planId, @RequestBody MembershipPlan plan) {
        MembershipPlan updated = planService.updatePlan(planId, plan);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{planId}")
    public ResponseEntity<String> deletePlan(@PathVariable Long planId) {
        planService.deletePlan(planId);
        return ResponseEntity.ok("Plan deleted successfully.");
    }
}
