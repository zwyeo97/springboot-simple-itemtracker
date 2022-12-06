package com.fdmgroup.ItemsTracker.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fdmgroup.ItemsTracker.model.Area;
/**
 * Repository for area
 * @author Steven
 *
 */
public interface AreaRepo extends JpaRepository<Area, Integer>{
	/**
	 * 
	 * @param locationId
	 * @return All areas in that location
	 */
	List<Area> findAllByLocationOwnerId(int locationId);
	
	/**
	 * 
	 * @param areaId
	 * @return Sum of values of item in this area
	 */
	@Query("select SUM(itm.value) from Area a inner join Item itm on a.id = itm.areaOwner.id where a.id = :areaId group by a.id")
	Optional<Double> sumItemsFromThisArea(@Param("areaId") int areaId);
	
	
	
}
