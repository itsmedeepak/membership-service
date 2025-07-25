package com.firstclub.membership.models;

import java.math.BigDecimal;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "membership_plan")
public class MembershipPlan {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long planId;
	private String plan;
	private String tier;
	private int tierLevel;
	private BigDecimal price;
	private Integer durationInDays;
	private boolean freeDelivery;
	private int extraDiscount;
	private boolean exclusiveDeals;
	private boolean prioritySupport;

	public MembershipPlan() {

	}

	public MembershipPlan(String plan, String tier, int tierLevel, BigDecimal price, Integer durationInDays,
			boolean freeDelivery, int extraDiscount, boolean exclusiveDeals, boolean prioritySupport) {
		this.plan = plan;
		this.tier = tier;
		this.tierLevel = tierLevel;
		this.price = price;
		this.durationInDays = durationInDays;
		this.freeDelivery = freeDelivery;
		this.extraDiscount = extraDiscount;
		this.exclusiveDeals = exclusiveDeals;
		this.prioritySupport = prioritySupport;
	}

	public Long getPlanId() {
		return planId;
	}

	public void setPlanId(Long planId) {
		this.planId = planId;
	}

	public String getPlan() {
		return plan;
	}

	public void setPlan(String plan) {
		this.plan = plan;
	}

	public String getTier() {
		return tier;
	}

	public void setTier(String tier) {
		this.tier = tier;
	}

	public int getTierLevel() {
		return tierLevel;
	}

	public void setTierLevel(int tierLevel) {
		this.tierLevel = tierLevel;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Integer getDurationInDays() {
		return durationInDays;
	}

	public void setDurationInDays(Integer durationInDays) {
		this.durationInDays = durationInDays;
	}

	public boolean isFreeDelivery() {
		return freeDelivery;
	}

	public void setFreeDelivery(boolean freeDelivery) {
		this.freeDelivery = freeDelivery;
	}

	public int getExtraDiscount() {
		return extraDiscount;
	}

	public void setExtraDiscount(int extraDiscount) {
		this.extraDiscount = extraDiscount;
	}

	public boolean isExclusiveDeals() {
		return exclusiveDeals;
	}

	public void setExclusiveDeals(boolean exclusiveDeals) {
		this.exclusiveDeals = exclusiveDeals;
	}

	public boolean isPrioritySupport() {
		return prioritySupport;
	}

	public void setPrioritySupport(boolean prioritySupport) {
		this.prioritySupport = prioritySupport;
	}

	@Override
	public int hashCode() {
		return Objects.hash(planId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MembershipPlan other = (MembershipPlan) obj;
		return Objects.equals(planId, other.planId);
	}

}
