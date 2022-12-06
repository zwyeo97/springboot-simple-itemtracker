package com.fdmgroup.ItemsTracker.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdmgroup.ItemsTracker.model.Area;
import com.fdmgroup.ItemsTracker.model.Location;
import com.fdmgroup.ItemsTracker.repositories.AreaRepo;

/**
 * Service class that implement the functionality of area.
 * 
 * @author Steven
 * 
 */

@Service

public class AreaService {

	@Autowired
	private AreaRepo areaRepo;

	public void addNewArea(Area area) {
		areaRepo.save(area);
	}

	public void updateAreaInfo(Area area) {
		areaRepo.save(area);
	}

	public void deleteArea(Area area) {
		areaRepo.delete(area);
	}

	public void deleteAllArea(Location location) {
		List<Area> result = areaRepo.findAllByLocationOwnerId(location.getId());
		areaRepo.deleteAll(result);
	}

	/**
	 * 
	 * @param location
	 * @return all areas existed in this location
	 */
	public List<Area> getAllAreaByLocation(Location location) {
		List<Area> result = areaRepo.findAllByLocationOwnerId(location.getId());

		return result;
	}

	public Optional<Area> findAreaById(int id) {
		return areaRepo.findById(id);
	}

	/**
	 * 
	 * @param area - Area object
	 * @return val - Total value of this area
	 */
	public double getValueOfArea(Area area) {
		Optional<Double> result = areaRepo.sumItemsFromThisArea(area.getId());
		double final_ = 0;

		if (result.isPresent()) {
			final_ = result.get();
		}

		return final_;
	}

}
