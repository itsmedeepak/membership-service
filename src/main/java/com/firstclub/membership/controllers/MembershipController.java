package com.firstclub.membership.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.firstclub.membership.dtos.SubscriptionRequest;
import com.firstclub.membership.models.MembershipPlan;
import com.firstclub.membership.models.UserMembership;
import com.firstclub.membership.services.MembershipService;

@RestController
@RequestMapping("/api/v1/memberships")
public class MembershipController {

    private final MembershipService membershipService;

    public MembershipController(MembershipService membershipService) {
        this.membershipService = membershipService;
    }

    @GetMapping("/plans")
    public ResponseEntity<List<MembershipPlan>> getAllPlans() {
        return ResponseEntity.ok(membershipService.getAllPlans());
    }

    @PostMapping("/subscribe")
    public ResponseEntity<UserMembership> subscribe(@RequestBody SubscriptionRequest request) {
        UserMembership newSubscription = membershipService.subscribe(request);
        return new ResponseEntity<>(newSubscription, HttpStatus.CREATED);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<UserMembership> getUserMembership(@PathVariable Long userId) {
        return ResponseEntity.ok(membershipService.getActiveUserMembership(userId));
    }

    @PutMapping("/user/{userId}/change-plan")
    public ResponseEntity<UserMembership> changePlan(
            @PathVariable Long userId,
            @RequestParam Long newPlanId) {
        return ResponseEntity.ok(membershipService.changePlan(userId, newPlanId));
    }

    @DeleteMapping("/user/{userId}/cancel")
    public ResponseEntity<String> cancelSubscription(@PathVariable Long userId) {
        String result = membershipService.cancelSubscription(userId);
        return ResponseEntity.ok(result);
    }
}
