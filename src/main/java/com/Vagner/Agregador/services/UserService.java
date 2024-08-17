package com.Vagner.Agregador.services;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.Vagner.Agregador.dto.CreateUserDTO;
import com.Vagner.Agregador.dto.UpdateUserDTO;
import com.Vagner.Agregador.entity.User;
import com.Vagner.Agregador.repositories.UserRepository;

@Service
public class UserService {

	private UserRepository userRepository;

	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public UUID createUser(CreateUserDTO createUserDto) {
		var entity = new User(
				// DTO -> Entity
				UUID.randomUUID(), createUserDto.username(), createUserDto.email(), createUserDto.password(),
				Instant.now(), null);
		var userSaved = userRepository.save(entity);
		return userSaved.getUserId();
	}

	public Optional<User> getUserById(String userId) {

		return userRepository.findById(UUID.fromString(userId));

	}

	public List<User> listUsers() {

		return userRepository.findAll();

	}

	public void updateUserById(String userId, UpdateUserDTO updateUserDto) {

		var id = UUID.fromString(userId);

		var userEntity = userRepository.findById(id);

		if (userEntity.isPresent()) {
			var user = userEntity.get();
			if (updateUserDto.username() != null) {
				user.setUsername(updateUserDto.username());
			}
			if (updateUserDto.password() != null) {
				user.setPassword(updateUserDto.password());
			}
			userRepository.save(user);
		}
	}

	public void deleteById(String userId) {

		var id = UUID.fromString(userId);

		var userExists = userRepository.existsById(id);

		if (userExists) {
			userRepository.deleteById(id);
		}

	}

}
