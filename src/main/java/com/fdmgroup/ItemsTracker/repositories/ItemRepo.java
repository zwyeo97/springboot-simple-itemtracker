package com.fdmgroup.ItemsTracker.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fdmgroup.ItemsTracker.model.Item;

/**
 * Repository for item
 * @author Steven
 *
 */
public interface ItemRepo extends JpaRepository<Item, Integer>{

	/**
	 * 
	 * @param areaId
	 * @return All items stored in this area
	 */
	List<Item> findAllByAreaOwnerId(int areaId);
	
}
