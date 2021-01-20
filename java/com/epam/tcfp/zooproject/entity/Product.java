package com.epam.tcfp.zooproject.entity;

import java.io.Serializable;
import java.math.BigDecimal;

public class Product implements Serializable {
	private static final long serialVersionUID = 1L;
	private long id;
	private String title;
	private String brand;
	private String form;
	private String breed;
	private String ageRate;
	private String weight;
	private String animalType;
	private String summary;
	private BigDecimal price;
	private float discount;
	private long quantity;
	private String image;

	public Product() {
	}	
	
	public Product(long id, String title, String brand, long quantity) {
		super();
		this.id = id;
		this.title = title;
		this.brand = brand;
		this.quantity = quantity;		
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getForm() {
		return form;
	}

	public void setForm(String form) {
		this.form = form;
	}

	public String getBreed() {
		return breed;
	}

	public void setBreed(String breed) {
		this.breed = breed;
	}

	public String getAgeRate() {
		return ageRate;
	}

	public void setAgeRate(String ageRate) {
		this.ageRate = ageRate;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public String getAnimalType() {
		return animalType;
	}

	public void setAnimalType(String animalType) {
		this.animalType = animalType;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public float getDiscount() {
		return discount;
	}

	public void setDiscount(float discount) {
		this.discount = discount;
	}

	public long getQuantity() {
		return quantity;
	}

	public void setQuantity(long quantity) {
		this.quantity = quantity;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}	

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ageRate == null) ? 0 : ageRate.hashCode());
		result = prime * result + ((animalType == null) ? 0 : animalType.hashCode());
		result = prime * result + ((brand == null) ? 0 : brand.hashCode());
		result = prime * result + ((breed == null) ? 0 : breed.hashCode());
		result = prime * result + ((form == null) ? 0 : form.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
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
		Product other = (Product) obj;
		if (ageRate == null) {
			if (other.ageRate != null)
				return false;
		} else if (!ageRate.equals(other.ageRate))
			return false;
		if (animalType == null) {
			if (other.animalType != null)
				return false;
		} else if (!animalType.equals(other.animalType))
			return false;
		if (brand == null) {
			if (other.brand != null)
				return false;
		} else if (!brand.equals(other.brand))
			return false;
		if (breed == null) {
			if (other.breed != null)
				return false;
		} else if (!breed.equals(other.breed))
			return false;
		if (form == null) {
			if (other.form != null)
				return false;
		} else if (!form.equals(other.form))
			return false;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", title=" + title + ", brand=" + brand + ", form=" + form + ", breed=" + breed
				+ ", ageRate=" + ageRate + ", weight=" + weight + ", animalType=" + animalType + ", summary=" + summary
				+ ", price=" + price + ", discount=" + discount + ", quantity=" + quantity + ", image=" + image
				+ "]";
	}
}
