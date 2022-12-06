package com.fdmgroup.ItemsTracker.controller;

import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fdmgroup.ItemsTracker.model.User;
import com.fdmgroup.ItemsTracker.security.UserPrincipal;
import com.fdmgroup.ItemsTracker.service.AreaService;
import com.fdmgroup.ItemsTracker.service.ItemService;
import com.fdmgroup.ItemsTracker.service.LocationService;
import com.fdmgroup.ItemsTracker.service.UserService;

@WebMvcTest
@AutoConfigureMockMvc(addFilters = false)
@WithMockUser("tester1")
public class UserControllerTest {
	@Autowired
    MockMvc mvc;
    @Autowired
    UserDetailsService userDetailsService;
    private User user;

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
	public void test_login_Get_Request_handler() throws Exception {
//		MockMvc mockMvc = MockMvcBuilders
//                .standaloneSetup(new UserController())
//                .setCustomArgumentResolvers(new PrincipalDetailsArgumentResolver())
//                .build();

		mvc.perform(MockMvcRequestBuilders.get("/login")).andExpect(MockMvcResultMatchers.view().name("login"));
//		User user = new User("tester1", "Alice", "123456");;
//		when(userPrincipal.getUser()).thenReturn(user);
		mvc.perform(MockMvcRequestBuilders.get("/register")).andExpect(MockMvcResultMatchers.view().name("register"));

		// mvc.perform(MockMvcRequestBuilders.get("/login").contentType(MediaType.APPLICATION_FORM_URLENCODED).param("username",
		// "tester1").param("password",
		// "123456")).andExpect(MockMvcResultMatchers.view().name("home"));
		mvc.perform(MockMvcRequestBuilders.get("/home")) .andExpect(MockMvcResultMatchers.view().name("home"));

	}
}
