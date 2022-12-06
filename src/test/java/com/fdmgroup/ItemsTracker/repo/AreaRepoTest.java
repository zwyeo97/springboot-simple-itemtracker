package com.fdmgroup.ItemsTracker.repo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.fdmgroup.ItemsTracker.model.Area;
import com.fdmgroup.ItemsTracker.model.Item;
import com.fdmgroup.ItemsTracker.model.Location;
import com.fdmgroup.ItemsTracker.model.User;
import com.fdmgroup.ItemsTracker.repositories.AreaRepo;
import com.fdmgroup.ItemsTracker.repositories.ItemRepo;
import com.fdmgroup.ItemsTracker.repositories.LocationRepo;
import com.fdmgroup.ItemsTracker.repositories.UserRepo;

@DataJpaTest
public class AreaRepoTest {

	@Autowired
	LocationRepo locationRepo;

	@Autowired
	UserRepo userRepo;

	@Autowired
	AreaRepo areaRepo;

	@Autowired
	ItemRepo itemRepo;

	private User user;
	private List<Location> existLocation;

	private List<Area> existArea;

	@BeforeEach public void setUp() {
		user = new User("tester1", "Jalice", "123456");
		Location loc1 = new Location("Something building", "123456 street");
		Location loc2 = new Location("Something building2", "654421 street");
		loc1.setUserOwner(user);
		loc2.setUserOwner(user);
		existLocation = new ArrayList<Location>();
		existLocation.add(loc1);
		existLocation.add(loc2);
		userRepo.save(user);
		locationRepo.save(loc1);
		locationRepo.save(loc2);

		Area area1 = new Area("Kitchen");

		Area area2 = new Area("Store room");

		Item item1 = new Item("Watch", "beautiful watch", 100);
		Item item2 = new Item("Watch", "old watch", 200);
		Item item3 = new Item("Knife", "old knife", 300);
		Item item4 = new Item("Gun", "old gun", 400);

		area1.setLocationOwner(loc1);
		area2.setLocationOwner(loc1);
		item1.setAreaOwner(area1);
		item2.setAreaOwner(area1);
		item3.setAreaOwner(area1);
		item4.setAreaOwner(area2);
		existArea = new ArrayList<>();
		existArea.add(area1);
		existArea.add(area2);

		areaRepo.save(area1);
		areaRepo.save(area2);
		itemRepo.save(item1);
		itemRepo.save(item2);
		itemRepo.save(item3);
		itemRepo.save(item4);

	}
	
	@Test
	public void test_all_area_by_this_location_id() {
		List<Area> allArea1 = areaRepo.findAllByLocationOwnerId(existLocation.get(0).getId());
		List<Area> allArea2 = areaRepo.findAllByLocationOwnerId(existLocation.get(1).getId());
		
		assertEquals(allArea1, existArea);
		assertEquals(allArea2.isEmpty(), true);
		
	}
	
	@Test
	public void test_total_value_of_an_area() {
		Optional<Double> val1 = areaRepo.sumItemsFromThisArea(existArea.get(0).getId());
		Optional<Double> val2 = areaRepo.sumItemsFromThisArea(existArea.get(1).getId());
		
		assertEquals(val1.orElse(0.0), 600);
		assertEquals(val2.orElse(0.0), 400);

	}
	
}
