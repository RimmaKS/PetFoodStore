package com.epam.tcfp.zooproject.entity;

import java.math.BigDecimal;

public class OrderItem {
	private long id;
	private long orderId; 
	private long productId; 
	private BigDecimal price;  	
	private long quantity;
	
	public OrderItem() {		
	}
	
	public OrderItem(long orderId, long productId, BigDecimal price, long quantity) {
		this.orderId = orderId;
		this.productId = productId;
		this.price = price;
		this.quantity = quantity;		
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getOrderId() {
		return orderId;
	}
	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}
	public long getProductId() {
		return productId;
	}
	public void setProductId(long productId) {
		this.productId = productId;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public long getQuantity() {
		return quantity;
	}
	public void setQuantity(long quantity) {
		this.quantity = quantity;
	}

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + (int) (orderId ^ (orderId >>> 32));
		result = prime * result + ((price == null) ? 0 : price.hashCode());
		result = prime * result + (int) (productId ^ (productId >>> 32));
		result = prime * result + (int) (quantity ^ (quantity >>> 32));
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
		OrderItem other = (OrderItem) obj;
		if (id != other.id)
			return false;
		if (orderId != other.orderId)
			return false;
		if (price == null) {
			if (other.price != null)
				return false;
		} else if (!price.equals(other.price))
			return false;
		if (productId != other.productId)
			return false;
		if (quantity != other.quantity)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "OrderItem [id=" + id + ", orderId=" + orderId + ", productId=" + productId + ", price=" + price
				+ ", quantity=" + quantity + "]";
	}
	
	
}
