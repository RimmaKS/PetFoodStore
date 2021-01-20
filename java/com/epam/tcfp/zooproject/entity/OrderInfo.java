package com.epam.tcfp.zooproject.entity;

import java.io.Serializable;
import java.math.BigDecimal;

public class OrderInfo implements Serializable{
	private static final long serialVersionUID = 1L;
	private long orderid;
	private String orderStatus;
	private long userid;
	private BigDecimal totalCost;
	
	private String productTitle; 
	private BigDecimal productPrice;
	private long productQuantity;
	
	
	public OrderInfo() {		
	}


	public long getOrderid() {
		return orderid;
	}


	public void setOrderid(long orderid) {
		this.orderid = orderid;
	}


	public String getOrderStatus() {
		return orderStatus;
	}


	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}


	public long getUserid() {
		return userid;
	}


	public void setUserid(long userid) {
		this.userid = userid;
	}


	public BigDecimal getTotalCost() {
		return totalCost;
	}


	public void setTotalCost(BigDecimal totalCost) {
		this.totalCost = totalCost;
	}


	public String getProductTitle() {
		return productTitle;
	}


	public void setProductTitle(String productTitle) {
		this.productTitle = productTitle;
	}


	public BigDecimal getProductPrice() {
		return productPrice;
	}


	public void setProductPrice(BigDecimal productPrice) {
		this.productPrice = productPrice;
	}


	public long getProductQuantity() {
		return productQuantity;
	}


	public void setProductQuantity(long productQuantity) {
		this.productQuantity = productQuantity;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((orderStatus == null) ? 0 : orderStatus.hashCode());
		result = prime * result + (int) (orderid ^ (orderid >>> 32));
		result = prime * result + ((productPrice == null) ? 0 : productPrice.hashCode());
		result = prime * result + (int) (productQuantity ^ (productQuantity >>> 32));
		result = prime * result + ((productTitle == null) ? 0 : productTitle.hashCode());
		result = prime * result + ((totalCost == null) ? 0 : totalCost.hashCode());
		result = prime * result + (int) (userid ^ (userid >>> 32));
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
		OrderInfo other = (OrderInfo) obj;
		if (orderStatus == null) {
			if (other.orderStatus != null)
				return false;
		} else if (!orderStatus.equals(other.orderStatus))
			return false;
		if (orderid != other.orderid)
			return false;
		if (productPrice == null) {
			if (other.productPrice != null)
				return false;
		} else if (!productPrice.equals(other.productPrice))
			return false;
		if (productQuantity != other.productQuantity)
			return false;
		if (productTitle == null) {
			if (other.productTitle != null)
				return false;
		} else if (!productTitle.equals(other.productTitle))
			return false;
		if (totalCost == null) {
			if (other.totalCost != null)
				return false;
		} else if (!totalCost.equals(other.totalCost))
			return false;
		if (userid != other.userid)
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "OrderInfo [orderid=" + orderid + ", orderStatus=" + orderStatus + ", userid=" + userid + ", totalCost="
				+ totalCost + ", productTitle=" + productTitle + ", productPrice=" + productPrice + ", productQuantity="
				+ productQuantity + "]";
	}
	
	
}
