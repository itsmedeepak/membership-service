package com.firstclub.membership.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.firstclub.membership.models.UserMembership;

public interface UserMembershipRepository extends JpaRepository <UserMembership, Long> {
	Optional<UserMembership> findByUser_UserIdAndActive(Long userId, boolean active);
}
