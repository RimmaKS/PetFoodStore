package com.epam.tcfp.zooproject.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ShoppingCart implements Serializable {
	private static final long serialVersionUID = 1L;
	private List<Product> items;
	private BigDecimal totalCost;

	public ShoppingCart() {
		this.items = new ArrayList<>();
	}

	public List<Product> getItems() {
		return this.items;
	}

	public void addItem(Product item) {
		this.items.add(item);
	}

	public void removeItem(Product item) {
		this.items.remove(item);
	}

	public BigDecimal getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(BigDecimal totalCost) {
		this.totalCost = totalCost;
	}

	public void setItems(List<Product> items) {
		this.items = items;
	}

	public void calculateTotalCost() {
		BigDecimal itemsCost = new BigDecimal("0.00");
		if (this.items == null) {
			this.totalCost = itemsCost;
		}
		else {
		for(Product item : this.items) {
			itemsCost = itemsCost.add(item.getPrice().multiply(new BigDecimal(item.getQuantity())));
		}
			this.totalCost = itemsCost;
		}
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((items == null) ? 0 : items.hashCode());
		result = prime * result + ((totalCost == null) ? 0 : totalCost.hashCode());
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
		ShoppingCart other = (ShoppingCart) obj;
		if (items == null) {
			if (other.items != null)
				return false;
		} else if (!items.equals(other.items))
			return false;
		if (totalCost == null) {
			if (other.totalCost != null)
				return false;
		} else if (!totalCost.equals(other.totalCost))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ShoppingCart [items=" + items + ", totalCost=" + totalCost + "]";
	}
}
