package com.example.etisalat.controller;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.etisalat.dao.UserRepository;
import com.example.etisalat.model.User;

@RestController
@RequestMapping("User")
public class UserController {
	private final Logger LOG = LoggerFactory.getLogger(getClass());
	private final UserRepository userRepository;

	@Autowired
	public UserController(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Cacheable(value = "users", key = "#userId", unless = "#result.followers < 12000")
	@GetMapping("/user/{userId}")
	public User getUser(@PathVariable String userId) {
		LOG.info("Getting user with ID {}.", userId);

		Optional<User> user = userRepository.findById(Long.valueOf(userId));
		if (user.isPresent()) {
			return user.get();
		}
		return null;
	}

	@CachePut(value = "users", key = "#user.id")
	@PutMapping("/user")
	public User updateUserById(@RequestBody User user) {
		return userRepository.save(user);

	}

	@CacheEvict(value = "users", allEntries = true)
	@DeleteMapping("/user/{userId}")
	public void deleteUserById(@PathVariable Long id) {
		LOG.info("deleting person with id {}", id);
		userRepository.deleteById(id);
	}

	@GetMapping("/checkStatus")
	public String getStaus() {
		return "Application is Up";
	}
}
