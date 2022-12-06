package com.fdmgroup.ItemsTracker.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fdmgroup.ItemsTracker.model.User;


/**
 * Repository for user
 * @author Steven
 *
 */
public interface UserRepo extends JpaRepository<User, Integer>{
	/**
	 * 
	 * @param username
	 * @return User that have this username, if exist.
	 */
	Optional<User> findByUsername(String username);
	
	/**
	 * 
	 * @param usernane
	 * @return True if user exist, false if otherwise.
	 */
	boolean existsByUsername(String usernane);
	
	
	
}
