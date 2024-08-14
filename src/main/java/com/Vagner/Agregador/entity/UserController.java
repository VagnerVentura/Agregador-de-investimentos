package com.Vagner.Agregador.entity;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Vagner.Agregador.dto.CreateUserDTO;

@RestController
@RequestMapping("/v1/users")
public class UserController {

	
	UserService userService;
	
	public UserController(UserService userService) {
		this.userService = userService;
	}

	@PostMapping
	public ResponseEntity<User> create(@RequestBody CreateUserDTO createUserDto){
		 var userId =  userService.createUser(createUserDto);
		 return ResponseEntity.created(URI.create("/v1/users" + userId.toString())).build();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<User> getUserById(@PathVariable("id") String id ){
		userService.FindById(id);
	}
	
}
