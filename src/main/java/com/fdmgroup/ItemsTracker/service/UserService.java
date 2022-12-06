package com.fdmgroup.ItemsTracker.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.fdmgroup.ItemsTracker.model.User;
import com.fdmgroup.ItemsTracker.repositories.UserRepo;

/**
 * Service class that implement the functionality of user.
 * @author Steven
 * 
 */
@Service
public class UserService {

	@Autowired
	private UserRepo userRepo;
	@Autowired
	private BCryptPasswordEncoder pwEncoder;
		
	/**
	 * 
	 * @param user - user object
	 * @return true if user created, false if otherwise
	 */
	public boolean registerNewUser(User user) {
		if(userRepo.existsByUsername(user.getUsername())) {
			return false;
		}else {
			String hashedPw = pwEncoder.encode(user.getPassword());
			user.setPassword(hashedPw);
			userRepo.save(user);
			return true;
		}
	}

	/**
	 * 
	 * @param id user id
	 * @return User object that contain the id.
	 */
	public User findUserById(int id) {
		return userRepo.findById(id).orElse(new User());
	}
}

