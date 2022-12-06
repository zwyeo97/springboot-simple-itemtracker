package com.fdmgroup.ItemsTracker.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * Location object class
 * 
 * @author Steven
 *
 */
@Entity
@Table(name = "location_table")
public class Location {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "LOCATION_ID")
	private int id;
	@Column(name = "LOCATION_NAME")
	private String locationName;
	@Column(name = "LOCATION_ADDRESS")
	private String locationAddress;
	@JoinColumn(name = "FK_USER_ID", nullable = false)
	@ManyToOne
	private User userOwner;
	@OneToMany(mappedBy = "locationOwner", cascade = CascadeType.ALL)
	private List<Area> areas = null;
	@Transient
	private double currentValue = 0;

	public double getCurrentValue() {
		return currentValue;
	}

	public void setCurrentValue(double currentValue) {
		this.currentValue = currentValue;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLocationName() {
		return locationName;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}

	public String getLocationAddress() {
		return locationAddress;
	}

	public void setLocationAddress(String locationAddress) {
		this.locationAddress = locationAddress;
	}

	public User getUserOwner() {
		return userOwner;
	}

	public void setUserOwner(User userOwner) {
		this.userOwner = userOwner;
	}

	public List<Area> getAreas() {
		return areas;
	}

	public void setAreas(List<Area> areas) {
		this.areas = areas;
	}

	public Location(String locationName, String locationAddress) {
		super();
		this.locationName = locationName;
		this.locationAddress = locationAddress;
	}

	public Location() {
		super();
	}

	@Override
	public String toString() {
		return "Location [id=" + id + ", locationName=" + locationName + ", locationAddress=" + locationAddress + "]";
	}

}
