package com.firstclub.membership.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.firstclub.membership.models.User;

public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findByEmail(String email);

}
