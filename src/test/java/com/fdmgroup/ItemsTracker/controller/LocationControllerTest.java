package com.fdmgroup.ItemsTracker.controller;

import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithSecurityContext;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fdmgroup.ItemsTracker.model.Location;
import com.fdmgroup.ItemsTracker.model.User;
import com.fdmgroup.ItemsTracker.security.UserPrincipal;
import com.fdmgroup.ItemsTracker.service.AreaService;
import com.fdmgroup.ItemsTracker.service.ItemService;
import com.fdmgroup.ItemsTracker.service.LocationService;
import com.fdmgroup.ItemsTracker.service.UserService;

@WebMvcTest
@AutoConfigureMockMvc(addFilters = false)
public class LocationControllerTest {
	@Autowired
	MockMvc mvc;
	
	@MockBean
	UserService userService;

	@MockBean
	AreaService areaService;

	@MockBean
	ItemService itemService;

	@MockBean
	LocationService locationService;

	@MockBean
	UserPrincipal userPrincipal;
	
	
	
	
	@Test
	@WithMockUser(username="tester1", password="123456")
	public void test_location_pages() throws Exception {
		Location location = new Location("B", "c");
		
		mvc.perform(MockMvcRequestBuilders.get("/location")).andExpect(MockMvcResultMatchers.view().name("redirect:/home"));
		mvc.perform(MockMvcRequestBuilders.get("/location/editLocation").sessionAttr("currentLocation", location)).andExpect(MockMvcResultMatchers.view().name("editLocation"));
	}
	
}
