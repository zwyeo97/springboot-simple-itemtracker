package com.fdmgroup.ItemsTracker.controller;

import java.util.List;
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
import com.fdmgroup.ItemsTracker.model.Item;
import com.fdmgroup.ItemsTracker.model.Location;
import com.fdmgroup.ItemsTracker.service.AreaService;
import com.fdmgroup.ItemsTracker.service.ItemService;
/**
 *  The controller class that handle CRUD operation for item.
 * @author Steven
 *
 */
@Controller
@RequestMapping("/location/area")
public class ItemController {
	private static final Logger LOGGER = LogManager.getLogger(ItemController.class);

	@Autowired
	private ItemService itemService;

	@Autowired
	private AreaService areaService;

	@GetMapping("/addItem")
	public String getAddItemPage(Model model) {
		model.addAttribute("item", new Item());
		return "addItem";
	}

	@PostMapping("/addItem")
	public String addItem(Item item, HttpSession session) {
		Area area = (Area) session.getAttribute("currentArea");
		item.setAreaOwner(area);
		itemService.addNewItem(item);
		LOGGER.trace("User ( ID: " + session.getAttribute("userID") + ", username : " + session.getAttribute("username")
				+ " has added new item: " + item.getItemName() + ", description : " + item.getDescription()
				+ ", value : " + item.getValue() + ", location : " + item.getAreaOwner().getAreaName() + ")");

		return "redirect:/location/area/" + area.getId();
	}

	@GetMapping("/deleteItem/{id}")
	public String deleteItem(@PathVariable int id, HttpSession session) {
		Optional<Item> item = itemService.findItemById(id);
		Area area = (Area) session.getAttribute("currentArea");
		if (item.isPresent() && item.get().getAreaOwner().getId() == area.getId()) {
			LOGGER.trace("User ( ID: " + session.getAttribute("userID") + ", username : "
					+ session.getAttribute("username") + " has deleted item: " + item.get().getItemName() + ")");

			itemService.deleteItem(item.get());
		}

		return "redirect:/location/area/" + area.getId();
	}

	@GetMapping("/editItem/{id}")
	public String editItem(@PathVariable int id, HttpSession session, Model model) {
		Optional<Item> item = itemService.findItemById(id);
		Area area = (Area) session.getAttribute("currentArea");
		if (item.isPresent() && item.get().getAreaOwner().getId() == area.getId()) {
			Location loc = area.getLocationOwner();
			List<Area> areaList = areaService.getAllAreaByLocation(loc);
			session.setAttribute("currentItem", item.get());
			model.addAttribute("item", item.get());
			model.addAttribute("allArea", areaList);
		}
		return "editItem";
	}

	@PostMapping("/editItem/save")
	public String saveEditItem(Item item, @RequestParam int areaId, HttpSession session) {
		Area area = (Area) session.getAttribute("currentArea");
		Area moveToArea = areaService.findAreaById(areaId).get();
		Item item2 = (Item) session.getAttribute("currentItem");
		LOGGER.trace("User ( ID: " + session.getAttribute("userID") + ", username : " + session.getAttribute("username")
				+ " has edited item, id: " + item.getId() + ", name: " + item2.getItemName() + " -> "
				+ item.getItemName() + ", description : " + item2.getDescription() + " -> " + item.getDescription()
				+ ", value : " + item2.getValue() + " -> " + item.getValue() + ", location : "
				+ item2.getAreaOwner().getAreaName() + " -> " + moveToArea.getAreaName() + ")");

		item.setAreaOwner(moveToArea);
		itemService.updateItemInfo(item);
		session.setAttribute("currentItem", null);
		return "redirect:/location/area/" + area.getId();
	}

}
