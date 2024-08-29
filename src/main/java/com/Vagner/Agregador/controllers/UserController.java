package com.Vagner.Agregador.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Vagner.Agregador.dto.CreateAccountDto;
import com.Vagner.Agregador.dto.CreateBillingAddressDto;
import com.Vagner.Agregador.dto.CreateUserDTO;
import com.Vagner.Agregador.dto.UpdateUserDTO;
import com.Vagner.Agregador.entity.User;
import com.Vagner.Agregador.services.UserService;

@RestController
@RequestMapping("/v1/users")
public class UserController {

	UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	@PostMapping
	public ResponseEntity<User> create(@RequestBody CreateUserDTO createUserDto) {
		var userId = userService.createUser(createUserDto);
		return ResponseEntity.created(URI.create("/v1/users" + userId.toString())).build();
	}

	@GetMapping("/{id}")
	public ResponseEntity<User> getUserById(@PathVariable("id") String userId) {
		var user = userService.getUserById(userId);

		if (user.isPresent()) {
			return ResponseEntity.ok(user.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping
	public ResponseEntity<List<User>> listUsers() {
		var users = userService.listUsers();
		return ResponseEntity.ok(users);
	}

	@PutMapping("/{userId}")
	public ResponseEntity<Void> updateUserById(@PathVariable("userId") String userId,
			@RequestBody UpdateUserDTO updateUserDto) {
		
		userService.updateUserById(userId, updateUserDto);
		return ResponseEntity.noContent().build();		
	}

	@DeleteMapping("/{userId}")
	public ResponseEntity<Void> deleteById(@PathVariable("userId") String userId){
	
		userService.deleteById(userId);
		return ResponseEntity.noContent().build();
		
	}
	
	@PostMapping("/{userId}/accounts")
	public ResponseEntity<Void> createAccount(@PathVariable("userId") String userId,
			@RequestBody CreateAccountDto createAccountDto){
		
		userService.createAccount(userId, createAccountDto);
		return ResponseEntity.ok().build();
		
	}
	
	
}
