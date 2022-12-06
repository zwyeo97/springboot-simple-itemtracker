package com.fdmgroup.ItemsTracker.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fdmgroup.ItemsTracker.model.Location;
import com.fdmgroup.ItemsTracker.model.User;
import com.fdmgroup.ItemsTracker.security.UserPrincipal;
import com.fdmgroup.ItemsTracker.service.LocationService;
import com.fdmgroup.ItemsTracker.service.UserService;

/**
 *  
 *  The controller class that handle operation related to user such as login and registration.
 * @author Steven
 *
 */
@Controller
public class UserController {
	@Autowired
	private UserService userService;

	private static final Logger LOGGER = LogManager.getLogger(UserController.class);

	@Autowired
	private LocationService locationService;

	@GetMapping("/")
	public String goToIndexPage() {
		return "index";
	}

	@GetMapping("/register")
	public String goToRegisterPage(Model model) {
		model.addAttribute("user", new User());

		return "register";

	}

	@PostMapping("/register")
	public String registerNewUser(User user, Model model) {
		if (userService.registerNewUser(user)) {
			LOGGER.trace("New user created : username - " + user.getUsername() + ", id = " + user.getId());

			return "redirect:/login";
		} else {
			model.addAttribute("invalidFlag", 1);
			return "register";
		}
	}

	@GetMapping("/login")
	public String goToLoginPage() {
		return "login";
	}

	//redirect when failed to login
	@GetMapping("/login-error")
	public String loginFailed(RedirectAttributes redirectAttrs) {
		redirectAttrs.addFlashAttribute("invalidFlag", 2);
		return "redirect:/login";
	}

	@GetMapping("/home")
	public String goToHomePage(@AuthenticationPrincipal UserPrincipal principal, HttpSession session) {
		List<Location> locations = locationService.getAllLocationByUser(principal.getUser());

		for (Location location : locations) {
			location.setCurrentValue(locationService.getValueOfLocation(location));
		}
		
		session.setAttribute("userID", principal.getId());
		session.setAttribute("username", principal.getUsername());
		session.setAttribute("location", locations);
		return "home";

	}

}
