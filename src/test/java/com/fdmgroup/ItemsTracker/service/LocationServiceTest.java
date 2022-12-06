package com.fdmgroup.ItemsTracker.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.fdmgroup.ItemsTracker.model.Location;
import com.fdmgroup.ItemsTracker.repositories.LocationRepo;

@SpringBootTest
public class LocationServiceTest {

	@Autowired
	LocationService locationService;
	
	@MockBean
	LocationRepo locationRepo;
	
	
	@Test
	public void test_CRUD_operations() {
		Location location = new Location("Nice building", "Nice street");
		
		locationService.addNewLocation(location);
		verify(locationRepo).save(location);
		
		locationService.updateLocationInfo(location);
		verify(locationRepo, times(2)).save(location);
		
		locationService.deleteLocation(location);
		verify(locationRepo).delete(location);
		
	}
	
	@Test
	public void test_getValueOfLocation_return_totalValue() {
		Location location = new Location("Nice building", "Nice street");
		Optional<Double> val = Optional.of(100.0);
		when(locationRepo.sumItemsFromThisLocation(0)).thenReturn(val);
		
		Double val2 = locationService.getValueOfLocation(location);
		verify(locationRepo).sumItemsFromThisLocation(0);
		
		assertEquals(val2, val.get());
		
	}
}
