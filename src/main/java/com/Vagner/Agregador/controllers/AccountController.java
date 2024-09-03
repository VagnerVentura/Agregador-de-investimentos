package com.Vagner.Agregador.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Vagner.Agregador.dto.AccountStockResponseDto;
import com.Vagner.Agregador.dto.AssociateAccountStockDto;
import com.Vagner.Agregador.services.AccountService;

@RestController
@RequestMapping("/v1/accounts")
public class AccountController {

	private AccountService accountService;
	
	public AccountController (AccountService accountService) {
		this.accountService = accountService;
	}
	
	@PostMapping("/{accountId}/stocks")
	public ResponseEntity<Void> associateStock (@PathVariable("accountId") String accountId, 
			@RequestBody AssociateAccountStockDto associateAccountStockDto){
		
		accountService.associateStock(accountId, associateAccountStockDto);
		
		return ResponseEntity.ok().build();
	}
	
	@GetMapping("/{accountId}/stocks")
	public ResponseEntity<List<AccountStockResponseDto>> listStocks(@PathVariable("accountId") String accountId){
		var accounts = accountService.listStocks(accountId);
		return ResponseEntity.ok(accounts);
	}

	
}
