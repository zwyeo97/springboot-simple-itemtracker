package com.fdmgroup.ItemsTracker.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.fdmgroup.ItemsTracker.model.User;
import com.fdmgroup.ItemsTracker.repositories.UserRepo;

@SpringBootTest
public class UserServiceTest {

	@Autowired
	UserService userService;
	
	@MockBean
	UserRepo userRepo;
	
	@Test
	public void test_registerNewUser_returns_true_when_user_doesntExists_and_savesUser() {
		User user = new User("tester3", "jalive", "123456");
		when(userRepo.existsByUsername("tester3")).thenReturn(false);
		
		
		boolean isRegistered = userService.registerNewUser(user);
		verify(userRepo).save(user);
		assertTrue(isRegistered);
	}
	
	@Test
	public void test_findById_return_user_or_newUser_if_notfound() {
		Optional<User> user = Optional.of(new User("tester3", "jalive", "123456"));
		when(userRepo.findById(0)).thenReturn(user);
		
		User user2 = userService.findUserById(0);
		verify(userRepo).findById(0);
		assertEquals(user.get(), user2);
		
	}
	
	
	
}
