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
 * Area object class.
 * @author Steven
 *
 */

@Entity
@Table(name = "area_table")
public class Area {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "AREA_ID")
	private int id;
	@Column(name = "AREA_NAME")
	private String areaName;
	@JoinColumn(name = "FK_LOCATION_ID")
	@ManyToOne
	private Location locationOwner;
	@OneToMany(mappedBy = "areaOwner", cascade = CascadeType.ALL)
	private List<Item> items = null;
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

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public Location getLocationOwner() {
		return locationOwner;
	}

	public void setLocationOwner(Location locationOwner) {
		this.locationOwner = locationOwner;
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	public Area(String areaName) {
		super();
		this.areaName = areaName;
	}

	public Area() {
		super();
	}

	@Override
	public String toString() {
		return "Area [id=" + id + ", areaName=" + areaName + ", location=" + locationOwner + "]";
	}

}
