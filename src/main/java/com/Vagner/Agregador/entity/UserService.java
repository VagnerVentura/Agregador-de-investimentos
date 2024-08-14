package com.Vagner.Agregador.entity;

import java.time.Instant;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.Vagner.Agregador.dto.CreateUserDTO;

@Service
public class UserService {

	UserRepository userRepository;
	
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public UUID createUser (CreateUserDTO createUserDto) {
		var entity = new User(
				//DTO -> Entity
				UUID.randomUUID(),
				createUserDto.username(),
				createUserDto.email(),
				createUserDto.password(),
				Instant.now(),
				null
				);
		var userSaved = userRepository.save(entity);
		return userSaved.getUserId();
	}
	

	
}
