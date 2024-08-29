package com.Vagner.Agregador.services;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.Vagner.Agregador.dto.CreateAccountDto;
import com.Vagner.Agregador.dto.CreateUserDTO;
import com.Vagner.Agregador.dto.UpdateUserDTO;
import com.Vagner.Agregador.entity.Account;
import com.Vagner.Agregador.entity.BillingAddress;
import com.Vagner.Agregador.entity.User;
import com.Vagner.Agregador.repositories.AccountRepository;
import com.Vagner.Agregador.repositories.BillingAddressRepository;
import com.Vagner.Agregador.repositories.UserRepository;

@Service
public class UserService {

	private UserRepository userRepository;

	private AccountRepository accountRepository;

	private BillingAddressRepository billingAddressRepository;

	public UserService(UserRepository userRepository, AccountRepository accountRepositoy,
			BillingAddressRepository billingAddressRepository) {
		this.userRepository = userRepository;
		this.accountRepository = accountRepositoy;
		this.billingAddressRepository = billingAddressRepository;
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

	public void createAccount(String userId, CreateAccountDto createAccountDto) {

		var user = userRepository.findById(UUID.fromString(userId))
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario não existe"));

		if (user.getAccounts() == null) {
			user.setAccounts(new ArrayList<>());
		}

		var account = new Account(UUID.randomUUID(), user, null, createAccountDto.description());

		var accountSaved = accountRepository.save(account);

		var billingAddress = new BillingAddress(
				// DTO -> Entity
				accountSaved.getAccountId(), accountSaved, createAccountDto.street(), createAccountDto.number());

		billingAddressRepository.save(billingAddress);

	}

}
