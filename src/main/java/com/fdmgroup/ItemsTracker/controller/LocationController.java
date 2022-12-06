package com.fdmgroup.ItemsTracker.controller;

import java.util.List;
import java.util.Optional;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fdmgroup.ItemsTracker.model.Area;
import com.fdmgroup.ItemsTracker.model.Location;
import com.fdmgroup.ItemsTracker.model.User;
import com.fdmgroup.ItemsTracker.security.UserPrincipal;
import com.fdmgroup.ItemsTracker.service.AreaService;
import com.fdmgroup.ItemsTracker.service.LocationService;
/**
 * The controller class that handle CRUD operations related to location
 * and also SQL query to get the sum of the value of a location.
 * @author Steven
 * 
 */
@Controller
public class LocationController {

	private static final Logger LOGGER = LogManager.getLogger(LocationController.class);

	@Autowired
	private LocationService locationService;

	@Autowired
	private AreaService areaService;

	@GetMapping("/addLocation")
	public String getAddLocationPage(Model model) {
		model.addAttribute("location", new Location());

		return "addLocation";
	}

	@PostMapping("/addLocation")
	public String addNewLocation(Location location) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User user = ((UserPrincipal) principal).getUser();
		location.setUserOwner(user);
		locationService.addNewLocation(location);
		LOGGER.trace("User ( ID: " + user.getId() + ", username : " + user.getUsername() + " has added new location : "
				+ location.getLocationName() + ")");
		return "redirect:/home";
	}

	@GetMapping("/location")
	public String getLocationWithoutId() {
		return "redirect:/home";
	}

	@GetMapping("/location/{id}")
	public String getLocationPage(@PathVariable int id, HttpSession session) {
		Optional<Location> loc = locationService.findLocationById(id);
		if (loc.isPresent()) {
			Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			User user = ((UserPrincipal) principal).getUser();
			Location location = loc.get();

			if (location.getUserOwner().getId() == (user.getId())) {
				double locationValue = locationService.getValueOfLocation(location);
				List<Area> areas = areaService.getAllAreaByLocation(location);
				for (Area area : areas) {
					area.setCurrentValue(areaService.getValueOfArea(area));
				}
				location.setAreas(areas);
				location.setCurrentValue(locationValue);
				session.setAttribute("currentLocation", location);
				return "location";
			}
		}
		return "redirect:/home";

	}

	@GetMapping("/location/editLocation")
	public String getEditLocationPage() {
		return "editLocation";
	}

	@PostMapping("/location/editLocation")
	public String editLocationPage(HttpSession session, @RequestParam String locationName,
			@RequestParam String locationAddress) {
		Location location = (Location) session.getAttribute("currentLocation");
		String[] pastRecord = { location.getLocationName(), location.getLocationAddress() };
		location.setLocationName(locationName);
		location.setLocationAddress(locationAddress);
		locationService.updateLocationInfo(location);
		LOGGER.trace("User ( ID: " + session.getAttribute("userID") + ", username : " + session.getAttribute("username")
				+ " has edited location, name: " + pastRecord[0] + " -> " + locationName + ", address: " + pastRecord[1]
				+ "->" + locationAddress + ")");

		return "redirect:/location/" + location.getId();
	}

	@GetMapping("/location/deleteLocation")
	public String deletePage(HttpSession session) {
		Location location = (Location) session.getAttribute("currentLocation");
		locationService.deleteLocation(location);
		LOGGER.trace("User ( ID: " + session.getAttribute("userID") + ", username : " + session.getAttribute("username")
				+ " has deleted location : " + location.getLocationName() + ")");

		session.setAttribute("currentLocation", null);
		return "redirect:/location";
	}

}
