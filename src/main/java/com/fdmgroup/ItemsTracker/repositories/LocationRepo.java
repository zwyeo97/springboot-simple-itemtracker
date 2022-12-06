package com.fdmgroup.ItemsTracker.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fdmgroup.ItemsTracker.model.Location;

/**
 * Repository for location
 * @author Steven
 *
 */
public interface LocationRepo extends JpaRepository<Location, Integer>{

	/**
	 * 
	 * @param userId
	 * @return All locations belong to this user
	 */
	List<Location> findAllByUserOwnerId(int userId);
	
	/**
	 * 
	 * @param locationId
	 * @return Total value of items stored in this location
	 */
	@Query("select SUM(itm.value) from Location l inner join l.areas ar on l.id = ar.locationOwner.id "
			+ "inner join ar.items itm on ar.id = itm.areaOwner.id where l.id = :locationId ")
	Optional<Double> sumItemsFromThisLocation(@Param("locationId") int locationId);
	
}
