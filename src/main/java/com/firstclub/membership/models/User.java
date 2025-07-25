package com.firstclub.membership.models;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userId;
	private String name;

	@Column(unique = true)
	private String email;
	
	private String phone;

	@OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "membershipId")

	@JsonBackReference
	private UserMembership userMembership;

	public User() {

	}

	public User(String name, String email, String phone, UserMembership userMembership) {
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.userMembership = userMembership;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public UserMembership getUserMembership() {
		return userMembership;
	}

	public void setUserMembership(UserMembership userMembership) {
		this.userMembership = userMembership;
	}

	@Override
	public int hashCode() {
		return Objects.hash(userId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(userId, other.userId);
	}

}
