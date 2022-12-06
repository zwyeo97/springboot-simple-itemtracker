package com.fdmgroup.ItemsTracker.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Item object class
 * 
 * @author Steven
 *
 */
@Entity
@Table(name = "item_table")
public class Item {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ITEM_ID")
	private int id;
	@Column(name = "ITEM_NAME")
	private String itemName;
	@Column(name = "ITEM_VALUE")
	private double value;
	@Column(name = "ITEM_DESCRIPTION")
	private String description;
	@JoinColumn(name = "FK_AREA_ID")
	@ManyToOne
	private Area areaOwner;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String name) {
		this.itemName = name;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Area getAreaOwner() {
		return areaOwner;
	}

	public void setAreaOwner(Area areaOwner) {
		this.areaOwner = areaOwner;
	}

	public Item(String itemName, String description, double value) {
		super();
		this.itemName = itemName;
		this.value = value;
		this.description = description;
	}

	public Item() {
		super();
	}

	@Override
	public String toString() {
		return "Item [id=" + id + ", name=" + itemName + ", value=" + value + ", description=" + description + ", area="
				+ areaOwner + "]";
	}

}
