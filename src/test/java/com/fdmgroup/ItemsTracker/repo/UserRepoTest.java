package com.fdmgroup.ItemsTracker.repo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.fdmgroup.ItemsTracker.model.User;
import com.fdmgroup.ItemsTracker.repositories.UserRepo;

@DataJpaTest
public class UserRepoTest {
	
	@Autowired
	UserRepo userRepo;
	@Test
	public void test_findByUsername_returns_correctUser() {
		User user = new User("tester1", "jalice", "123456");
		userRepo.save(user);
		
		Optional<User> userOptional = userRepo.findByUsername("tester1");
		User user2 = userOptional.orElse(new User());
		
		assertEquals(user2, user);
		//assertEquals(1,1);
		
	}
	
	
	@Test
	public void test_username_exist() {
		User user = new User("tester1", "jalice", "123456");
		userRepo.save(user);
		
		boolean exist = userRepo.existsByUsername("tester1");
		
		assertEquals(exist, true);
		
	}
	
	@Test
	public void test_username_notExist() {

		boolean exist = userRepo.existsByUsername("tester1");
		
		assertEquals(exist, false);
		
	}

}
