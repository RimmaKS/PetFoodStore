package com.epam.tcfp.zooproject.entity;

import java.io.Serializable;

public class UserAddress implements Serializable {
	private static final long serialVersionUID = 1L;
	private long id;
	private long userId; 
	private String zipCode;
	private String country;
	private String region;
	private String city;
	private String street;
	private String building;
	private String apartment;

	public UserAddress() {
	}

	public UserAddress(long userId) {
		this.userId = userId;
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


	public String getCountry() {
		return country;
	}


	public void setCountry(String country) {
		this.country = country;
	}


	public String getRegion() {
		return region;
	}


	public void setRegion(String region) {
		this.region = region;
	}


	public String getCity() {
		return city;
	}


	public void setCity(String city) {
		this.city = city;
	}


	public String getStreet() {
		return street;
	}


	public void setStreet(String street) {
		this.street = street;
	}


	public String getBuilding() {
		return building;
	}


	public void setBuilding(String building) {
		this.building = building;
	}


	public String getApartment() {
		return apartment;
	}


	public void setApartment(String apartment) {
		this.apartment = apartment;
	}


	public String getZipCode() {
		return zipCode;
	}


	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((apartment == null) ? 0 : apartment.hashCode());
		result = prime * result + ((building == null) ? 0 : building.hashCode());
		result = prime * result + ((city == null) ? 0 : city.hashCode());
		result = prime * result + ((country == null) ? 0 : country.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((region == null) ? 0 : region.hashCode());
		result = prime * result + ((street == null) ? 0 : street.hashCode());
		result = prime * result + (int) (userId ^ (userId >>> 32));
		result = prime * result + ((zipCode == null) ? 0 : zipCode.hashCode());
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
		UserAddress other = (UserAddress) obj;
		if (apartment == null) {
			if (other.apartment != null)
				return false;
		} else if (!apartment.equals(other.apartment))
			return false;
		if (building == null) {
			if (other.building != null)
				return false;
		} else if (!building.equals(other.building))
			return false;
		if (city == null) {
			if (other.city != null)
				return false;
		} else if (!city.equals(other.city))
			return false;
		if (country == null) {
			if (other.country != null)
				return false;
		} else if (!country.equals(other.country))
			return false;
		if (id != other.id)
			return false;
		if (region == null) {
			if (other.region != null)
				return false;
		} else if (!region.equals(other.region))
			return false;
		if (street == null) {
			if (other.street != null)
				return false;
		} else if (!street.equals(other.street))
			return false;
		if (userId != other.userId)
			return false;
		if (zipCode == null) {
			if (other.zipCode != null)
				return false;
		} else if (!zipCode.equals(other.zipCode))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "UserAddress [id=" + id + ", userId=" + userId + ", country=" + country + ", region=" + region
				+ ", city=" + city + ", street=" + street + ", building=" + building + ", apartment=" + apartment
				+ ", zipCode=" + zipCode + "]";
	}	
}
