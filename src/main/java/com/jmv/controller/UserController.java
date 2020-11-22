package com.jmv.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.jmv.model.User;
import com.jmv.repository.UserMongoRepository;

@RestController
@RequestMapping("users")
public class UserController {

	@Autowired
	private UserMongoRepository userMongoRepository;

	// Create
	@PostMapping
	public ResponseEntity<User> createUser(@RequestBody User user) {
		return new ResponseEntity<>(userMongoRepository.save(user), HttpStatus.CREATED);
	}

	// Retrieve
	@GetMapping("{id}")
	public ResponseEntity<User> retrieveUserById(@PathVariable Integer id) {

		User user = userMongoRepository.findById(id).orElse(null);

		return user != null ? new ResponseEntity<User>(user, HttpStatus.OK)
				: new ResponseEntity<User>(HttpStatus.NOT_FOUND);
	}

	@GetMapping
	public ResponseEntity<List<User>> retrieveAllUsers() {
		return new ResponseEntity<List<User>>(userMongoRepository.findAll(), HttpStatus.OK);
	}

	// Delete
	@DeleteMapping
	public ResponseEntity<String> deleteUser(@RequestBody User user) {
		userMongoRepository.delete(user);
		return new ResponseEntity<String>("User " + user + " deleted", HttpStatus.OK);
	}

	@DeleteMapping("{id}")
	public ResponseEntity<String> deleteUserById(@PathVariable Integer id) {
		userMongoRepository.deleteById(id);
		return new ResponseEntity<String>("User with id: " + id + " deleted", HttpStatus.OK);
	}

	// Create
	@PutMapping
	public ResponseEntity<User> updateUser(@RequestBody User user) {
		
		User existingUser = userMongoRepository.findById(user.getId())
		.orElse(null);
		
		existingUser.setEmail(user.getEmail()); 
		existingUser.setName(user.getName()); 
		
		return new ResponseEntity<>(userMongoRepository.save(existingUser), HttpStatus.OK);
	}

}
