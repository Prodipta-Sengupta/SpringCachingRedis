package com.example.etisalat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

import com.example.etisalat.dao.UserRepository;
import com.example.etisalat.model.User;

@SpringBootApplication
@EnableCaching
public class SpringBootCachingRedisApplication implements CommandLineRunner {

	private final Logger LOG = LoggerFactory.getLogger(getClass());

	private final UserRepository userRepository;

	@Autowired
	public SpringBootCachingRedisApplication(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringBootCachingRedisApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		LOG.info("Saving Users. Current user Count is {}", userRepository.count());
		User user1 = new User("Shubham", 2000);
		User user2 = new User("Pankaj", 29000);
		User user3 = new User("Lewis", 550);

		userRepository.save(user1);
		userRepository.save(user2);
		userRepository.save(user3);

		LOG.info("Done Saving users. Data:{}.", userRepository.findAll());

	}

}
