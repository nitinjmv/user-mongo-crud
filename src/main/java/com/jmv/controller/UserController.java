package com.jmv.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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

	@GetMapping("{id}")
	public ResponseEntity<User> user(@PathVariable Integer id){
		
		User user = userMongoRepository.findById(id)
				.orElse(null);
		
		return user != null 
				? new ResponseEntity<User>(user, HttpStatus.OK) 
				: new ResponseEntity<User>(HttpStatus.NOT_FOUND);
	}
	
	@GetMapping
	public ResponseEntity<List<User>> users() {
		return new ResponseEntity<List<User>>(userMongoRepository.findAll(), HttpStatus.OK);
	}
	
	@PostMapping
	public User user(@RequestBody User user){
		return userMongoRepository.save(user);
	}
	
}
