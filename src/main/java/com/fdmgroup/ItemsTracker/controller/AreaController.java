package com.fdmgroup.ItemsTracker.controller;

import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fdmgroup.ItemsTracker.model.Area;
import com.fdmgroup.ItemsTracker.model.Location;
import com.fdmgroup.ItemsTracker.service.AreaService;
import com.fdmgroup.ItemsTracker.service.ItemService;
/**
 * The controller class that handle CRUD operation for area.
 * @author Steven
 * 
 */
@Controller
@RequestMapping("/location")
public class AreaController {
	private static final Logger LOGGER = LogManager.getLogger(AreaController.class);

	@Autowired
	private AreaService areaService;
	
	@Autowired
	private ItemService itemService;
	
	@GetMapping("/addArea")
	public String getAddAreaPage(Model model) {
		model.addAttribute("area", new Area());
		return "addArea";
	}
	
	@PostMapping("/addArea")
	public String addArea(Area area, HttpSession session) {
		Location loc = (Location) session.getAttribute("currentLocation");
		area.setLocationOwner(loc);
		areaService.addNewArea(area);
		LOGGER.trace("User ( ID: " + session.getAttribute("userID") + ", username : " + session.getAttribute("username") + " has added  new area: " + area.getAreaName()+ ")" );

		return "redirect:/location/" + loc.getId();
	}
	
	@GetMapping("/area/{id}")
	public String getAreaOfLocation(@PathVariable int id, HttpSession session) {
		Optional<Area >area = areaService.findAreaById(id);
		
		if(area.isPresent()) {
			Location location = (Location) session.getAttribute("currentLocation");
			Area finalArea = area.get();
			if(finalArea.getLocationOwner().getId() == location.getId()) {
				finalArea.setCurrentValue(areaService.getValueOfArea(finalArea));
				finalArea.setItems(itemService.getAllItemsByArea(finalArea));
				session.setAttribute("currentArea", finalArea);
				return "area";
			}
		}
		return "redirect:/home";
		
	}
	
	@GetMapping("/area/editArea")
	public String editAreaPage() {
		return "editArea";
	}
	
	// save the editted area
	@PostMapping("/area/editArea")
	public String saveAreaPage(HttpSession session, @RequestParam String areaName) {
		Area area = (Area) session.getAttribute("currentArea");
		String pastName = area.getAreaName();
		area.setAreaName(areaName);
		areaService.updateAreaInfo(area);
		LOGGER.trace("User ( ID: " + session.getAttribute("userID") + ", username : " + session.getAttribute("username") + " has edited area, name : " 
		+ pastName + " -> " + areaName + ")" );

		return "redirect:" + area.getId();
	}
	
	@GetMapping("/area/deleteArea")
	public String deleteArea(HttpSession session) {
		Area area = (Area) session.getAttribute("currentArea");
		LOGGER.trace("User ( ID: " + session.getAttribute("userID") + ", username : " + session.getAttribute("username") + " has deleted area: " + area.getAreaName()+ ")" );
		areaService.deleteArea(area);
		session.setAttribute("currentArea", null);	

		return "redirect:/location/" + ((Location) session.getAttribute("currentLocation")).getId();
	}
	
}
