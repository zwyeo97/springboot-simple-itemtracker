package com.fdmgroup.ItemsTracker;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication()
public class ItemsTrackerApplication {
	private static final Logger LOGGER = LogManager.getLogger();

	public static void main(String[] args) {
		SpringApplication.run(ItemsTrackerApplication.class, args);

		LOGGER.info("Server starting up...");

	}

}
