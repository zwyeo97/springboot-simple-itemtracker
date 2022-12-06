package com.fdmgroup.ItemsTracker.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdmgroup.ItemsTracker.model.Location;
import com.fdmgroup.ItemsTracker.model.User;
import com.fdmgroup.ItemsTracker.repositories.LocationRepo;

/**
 * Service class that implement the functionality of location.
 * @author Steven
 *
 * 
 */
@Service
public class LocationService {
	
	
	@Autowired
	private LocationRepo locationRepo;
	
	public void addNewLocation(Location location) {
		locationRepo.save(location);
	}
	
	public void updateLocationInfo(Location location) {
		locationRepo.save(location);
	}
	

	public void deleteLocation(Location location) {
		locationRepo.delete(location);
	}
	
	public Optional<Location> findLocationById(int id) {
		Optional<Location> loc = locationRepo.findById(id);
		return loc;
	}
	/**
	 * 
	 * @param user - user
	 * @return all locations registered under this user
	 */
	
	public List<Location> getAllLocationByUser(User user){
		List<Location> result = locationRepo.findAllByUserOwnerId(user.getId());
		
		return result;
	}
	
	/**
	 * 
	 * @param user
	 * - delete all locations from this user.
	 */
	
	public void deleteAllLocationFromUser(User user) {
		List<Location> result = locationRepo.findAllByUserOwnerId(user.getId());
		locationRepo.deleteAll(result);
		
	}
	
	/**
	 * 
	 * @param location - Location object
	 * @return val - The total value of this area
	 */
	// get the total value of a location by summing up the total value of areas under this location
	public double getValueOfLocation(Location location) {
		Optional<Double> result = locationRepo.sumItemsFromThisLocation(location.getId());
		double final_ = 0;
		if(result.isPresent()) {
			final_ = result.get();
		}
		return final_;
	}
	
	

}
