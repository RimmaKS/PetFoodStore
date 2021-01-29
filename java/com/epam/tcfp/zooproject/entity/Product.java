package com.epam.tcfp.zooproject.entity;

import java.io.Serializable;
import java.math.BigDecimal;

public class Product implements Serializable {
	private static final long serialVersionUID = 1L;
	private final long id;
	private final String title;
	private final String brand;
	private final String form;
	private final String breed;
	private final String ageRate;
	private final String weight;
	private final String animalType;
	private final String summary;
	private final BigDecimal price;
	private final float discount;
	private long quantity;
	private final String image;
		
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public long getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public String getBrand() {
		return brand;
	}

	public String getForm() {
		return form;
	}

	public String getBreed() {
		return breed;
	}

	public String getAgeRate() {
		return ageRate;
	}

	public String getWeight() {
		return weight;
	}

	public String getAnimalType() {
		return animalType;
	}

	public String getSummary() {
		return summary;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public float getDiscount() {
		return discount;
	}

	public long getQuantity() {
		return quantity;
	}

	public String getImage() {
		return image;
	}

	public void setQuantity(long quantity) {
		this.quantity = quantity;
	}

	public static class Builder {
		private final String title;		
		
		private long id = 0;
		private BigDecimal price = new BigDecimal(0);		
		private long quantity = 0;
		private String brand = "1";
		private String form = "1" ;
		private String breed = "1";
		private String ageRate = "4";
		private String weight = "1";
		private String animalType = "1";
		private String summary = "Pet food";
		private float discount = 0;
		private String image  = "default.jpg";
		
		public Builder(String title) {
			this.title = title;
		}
		
		public Builder id(long id) {
			this.id = id;
			return this;
		}
		
		public Builder brand(String brand) {
			this.brand = brand;
			return this;
		}
		

		public Builder form(String form) {
			this.form = form;
			return this;
		}

		public Builder breed(String breed) {
			this.breed = breed;
			return this;
		}

		public Builder ageRate(String ageRate) {
			this.ageRate = ageRate;
			return this;
		}


		public Builder weight(String weight) {
			this.weight = weight;
			return this;
		}
		
		public Builder animalType(String animalType) {
			this.animalType = animalType;
			return this;
		}
		
		public Builder summary(String summary) {
			this.summary = summary;
			return this;
		}
		
		public Builder price(BigDecimal price) {
			this.price = price;
			return this;
		}
		
		public Builder discount(float discount) {
			this.discount = discount;
			return this;
		}
		
		public Builder quantity(long quantity) {
			this.quantity = quantity;
			return this;
		}
		
		public Builder image(String image) {
			this.image = image;
			return this;
		}
		
		public Product build() {
			return new Product(this);
		}
		
	}
	
	private Product(Builder builder) {
		id = builder.id;
		title = builder.title;
		brand = builder.brand;
		form = builder.form;
		breed = builder.breed;
		ageRate = builder.ageRate;
		weight = builder.weight;
		animalType = builder.animalType;
		summary = builder.summary;
		price = builder.price;
		discount = builder.discount;
		quantity = builder.quantity;
		image = builder.image;
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
				+ ", price=" + price + ", discount=" + discount + ", quantity=" + quantity + ", image=" + image + "]";
	}
}
