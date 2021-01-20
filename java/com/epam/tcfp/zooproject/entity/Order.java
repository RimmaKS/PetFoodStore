package com.epam.tcfp.zooproject.entity;

import java.io.Serializable;
import java.math.BigDecimal;

public class Order implements Serializable {
	private static final long serialVersionUID = 1L;
	private long id;
	private long userId; 
	private long statusId; 
	private BigDecimal totalCost;
	private long deliveryAddressId;

	public Order() {
	}

	public Order(long id, long userId, long statusId, BigDecimal totalCost, long deliveryAddressId) {
		super();
		this.id = id;
		this.userId = userId;
		this.statusId = statusId;
		this.totalCost = totalCost;
		this.deliveryAddressId = deliveryAddressId;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public long getStatusId() {
		return statusId;
	}

	public void setStatusId(long statusId) {
		this.statusId = statusId;
	}

	public BigDecimal getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(BigDecimal totalCost) {
		this.totalCost = totalCost;
	}

	public long getDeliveryAddressId() {
		return deliveryAddressId;
	}

	public void setDeliveryAddressId(long deliveryAddressId) {
		this.deliveryAddressId = deliveryAddressId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (deliveryAddressId ^ (deliveryAddressId >>> 32));
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + (int) (statusId ^ (statusId >>> 32));
		result = prime * result + ((totalCost == null) ? 0 : totalCost.hashCode());
		result = prime * result + (int) (userId ^ (userId >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		if (deliveryAddressId != other.deliveryAddressId)
			return false;
		if (id != other.id)
			return false;
		if (statusId != other.statusId)
			return false;
		if (totalCost == null) {
			if (other.totalCost != null)
				return false;
		} else if (!totalCost.equals(other.totalCost))
			return false;
		if (userId != other.userId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", userId=" + userId + ", statusId=" + statusId + ", totalCost=" + totalCost
				+ ", deliveryAddressId=" + deliveryAddressId + "]";
	}	
}
