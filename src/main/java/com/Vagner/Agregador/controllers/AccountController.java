package com.Vagner.Agregador.controllers;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Vagner.Agregador.dto.CreateAccountDto;
import com.Vagner.Agregador.entity.Account;
import com.Vagner.Agregador.services.AccountService;

@RestController
@RequestMapping("/v1/accounts")
public class AccountController {

	@Autowired
	private AccountService accountService;
	
	public AccountController (AccountService accountService) {
		this.accountService = accountService;
	}
	
	@PostMapping
	public ResponseEntity<Account> createAccount(@RequestBody CreateAccountDto createAccountDto){
		var accountId = accountService.createAccount(createAccountDto);
		return ResponseEntity.created(URI.create("/v1/accounts" + accountId.toString())).build();
	}
	
	
}
