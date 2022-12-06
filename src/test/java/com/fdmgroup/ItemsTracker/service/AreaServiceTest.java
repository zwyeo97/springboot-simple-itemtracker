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

import com.fdmgroup.ItemsTracker.model.Area;
import com.fdmgroup.ItemsTracker.repositories.AreaRepo;

@SpringBootTest
public class AreaServiceTest {

	@Autowired
	AreaService areaService;
	
	@MockBean
	AreaRepo areaRepo;
	
	
	@Test
	public void test_CRUD_operations() {
		Area area = new Area("Kitchen");
		
		
		areaService.addNewArea(area);
		verify(areaRepo).save(area);
		
		area.setAreaName("Bathroom");
		areaService.updateAreaInfo(area);
		verify(areaRepo, times(2)).save(area);
		
		Optional<Double> returnVal = Optional.of(0.0);
		when(areaRepo.sumItemsFromThisArea(0)).thenReturn(returnVal);
		
		double val2 = areaService.getValueOfArea(area);
		assertEquals(val2, returnVal.get());
		
	
	}
	
	
}
