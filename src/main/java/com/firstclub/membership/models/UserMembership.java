package com.firstclub.membership.models;

import java.time.LocalDate;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "user_membership")
public class UserMembership {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long membershipId;

	@OneToOne
	@JoinColumn(name = "user_id", referencedColumnName = "userId")
	@JsonManagedReference
	private User user;

	@ManyToOne
	@JoinColumn(name = "plan_id", referencedColumnName = "planId")
	private MembershipPlan plan;

	private boolean active;

	private LocalDate startDate;

	private LocalDate endDate;

	public UserMembership() {

	}

	public UserMembership(User user, MembershipPlan plan, LocalDate startDate, LocalDate endDate, boolean active) {
		this.user = user;
		this.plan = plan;
		this.startDate = startDate;
		this.endDate = endDate;
		this.active = active;
	}

	public Long getMembershipId() {
		return membershipId;
	}

	public void setMembershipId(Long membershipId) {
		this.membershipId = membershipId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public MembershipPlan getPlan() {
		return plan;
	}

	public void setPlan(MembershipPlan plan) {
		this.plan = plan;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	@Override
	public int hashCode() {
		return Objects.hash(membershipId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserMembership other = (UserMembership) obj;
		return Objects.equals(membershipId, other.membershipId);
	}

}
