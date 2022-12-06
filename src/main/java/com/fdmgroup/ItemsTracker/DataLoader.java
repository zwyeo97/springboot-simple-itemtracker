package com.fdmgroup.ItemsTracker;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.fdmgroup.ItemsTracker.model.Area;
import com.fdmgroup.ItemsTracker.model.Item;
import com.fdmgroup.ItemsTracker.model.Location;
import com.fdmgroup.ItemsTracker.model.User;
import com.fdmgroup.ItemsTracker.repositories.AreaRepo;
import com.fdmgroup.ItemsTracker.repositories.ItemRepo;
import com.fdmgroup.ItemsTracker.repositories.LocationRepo;
import com.fdmgroup.ItemsTracker.repositories.UserRepo;
import com.fdmgroup.ItemsTracker.service.AreaService;
import com.fdmgroup.ItemsTracker.service.ItemService;
import com.fdmgroup.ItemsTracker.service.LocationService;
import com.fdmgroup.ItemsTracker.service.UserService;

@Component
public class DataLoader implements ApplicationRunner {
	@Autowired
	private UserRepo userRepo;
	@Autowired
	private LocationRepo locationRepo;
	@Autowired
	private ItemRepo itemRepo;
	@Autowired
	private AreaRepo areaRepo;

	@Autowired
	private LocationService locationService;

	@Autowired
	private AreaService areaService;

	@Autowired
	private ItemService itemService;

	@Autowired
	private UserService userService;
	
	@Override
	public void run(ApplicationArguments args) throws Exception {

		//sample data 
//		
//		User user = new User("tester1", "Alice", "123456");
//		
//		userService.registerNewUser(user);
//		
//		
//		Location loc1 = new Location("Nice Building", "123 Street");
//
//		Area area1 = new Area("Master room");
//
//		Area area2 = new Area("Store room");
//
//		Item item1 = new Item("Watch", "rolex watch", 5000);
//		Item item2 = new Item("Watch", "apple watch", 3443);
//		Item item3 = new Item("Baseball bat", "brand new, white colour", 221);
//		Item item4 = new Item("Chainsaw", "blue colour", 7676);
//
//		loc1.setUserOwner(user);
//		area1.setLocationOwner(loc1);
//		area2.setLocationOwner(loc1);
//		item1.setAreaOwner(area1);
//		item2.setAreaOwner(area1);
//		item3.setAreaOwner(area1);
//		item4.setAreaOwner(area2);
//
//		userRepo.save(user);
//		locationRepo.save(loc1);
//		areaRepo.save(area1);
//		areaRepo.save(area2);
//		itemRepo.save(item1);
//		itemRepo.save(item2);
//		itemRepo.save(item3);
//		itemRepo.save(item4);
	
	}

}
