package com.fdmgroup.ItemsTracker.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdmgroup.ItemsTracker.model.Area;
import com.fdmgroup.ItemsTracker.model.Item;
import com.fdmgroup.ItemsTracker.repositories.ItemRepo;


/**
 * Service class that implement the functionality of item.
 * @author Steven
 * 
 */
@Service
public class ItemService {

	@Autowired
	private ItemRepo itemRepo;
	
	public Optional<Item> findItemById(int id) {
		Optional<Item> item = itemRepo.findById(id);
		return item;
		
	}
	
	public void addNewItem(Item item) {
		itemRepo.save(item);
	}
	
	public void updateItemInfo(Item item) {
		itemRepo.save(item);
	}

	public void deleteItem(Item item) {
		itemRepo.delete(item);
	}
	
	/**
	 * 
	 * @param area - delete all items in this area
	 */
	public void deleteAllItemsByArea(Area area) {
		List<Item> result = itemRepo.findAllByAreaOwnerId(area.getId());
		itemRepo.deleteAll(result);
		
	}
	
	/**
	 * 
	 * @param area - area to be checked
	 * @return all items in this area
	 */
	public List<Item> getAllItemsByArea(Area area){
		List<Item> result = itemRepo.findAllByAreaOwnerId(area.getId());
		
		return result;
	}
	
	/**
	 * 
	 * @param area
	 * @param items
	 * - move item from one area to another
	 * 
	 */
	public void moveItemToAnotherArea(Area area, Item ...items) {
		for(Item item : items) {
			item.setAreaOwner(area);
		}
		itemRepo.saveAll(Arrays.asList(items));
	}
	
	
	
}
