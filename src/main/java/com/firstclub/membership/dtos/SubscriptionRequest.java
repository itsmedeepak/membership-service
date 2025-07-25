package com.firstclub.membership.dtos;

import java.util.Objects;

public class SubscriptionRequest {
	private Long userId;
	private Long planId;
	private Long tierId;
	
	public SubscriptionRequest(Long userId, Long planId, Long tierId) {
		this.userId = userId;
		this.planId = planId;
		this.tierId = tierId;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getPlanId() {
		return planId;
	}
	public void setPlanId(Long planId) {
		this.planId = planId;
	}
	public Long getTierId() {
		return tierId;
	}
	public void setTierId(Long tierId) {
		this.tierId = tierId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(planId, tierId, userId);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SubscriptionRequest other = (SubscriptionRequest) obj;
		return Objects.equals(planId, other.planId) && Objects.equals(tierId, other.tierId)
				&& Objects.equals(userId, other.userId);
	}
	
	
}
