package com.Vagner.Agregador.services;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.Vagner.Agregador.dto.AccountStockResponseDto;
import com.Vagner.Agregador.dto.AssociateAccountStockDto;
import com.Vagner.Agregador.entity.AccountStock;
import com.Vagner.Agregador.entity.AccountStockId;
import com.Vagner.Agregador.repositories.AccountRepository;
import com.Vagner.Agregador.repositories.AccountStockRepository;
import com.Vagner.Agregador.repositories.StockRepository;

@Service
public class AccountService {
	
	private AccountRepository accountRepository;
	private StockRepository stockRepository;
	private AccountStockRepository accountStockRepository;

	public AccountService(AccountRepository accountRepository,
			StockRepository stockRepository,
			AccountStockRepository accountStockRepository) {
		
		this.accountRepository = accountRepository;
		this.stockRepository = stockRepository;
		this.accountStockRepository = accountStockRepository;
	}

	public void associateStock(String accountId, AssociateAccountStockDto associateAccountStockDto) {
		
		var account = accountRepository.findById(UUID.fromString(accountId))
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
		
		var stock = stockRepository.findById(associateAccountStockDto.stockId())
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
		
		var id = new AccountStockId(account.getAccountId(),stock.getStockId());
		
		// DTO -> entity
		
		var entity = new AccountStock(
				id,
				account,
				stock,
				associateAccountStockDto.quantity()
				);		
		
		accountStockRepository.save(entity);
		
	}

	public List<AccountStockResponseDto> listStocks(String accountId) {

		var account = accountRepository.findById(UUID.fromString(accountId))
				.orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "account not found"));
		
		return account.getAccountStocks()
		.stream()
		.map(as-> new AccountStockResponseDto(
					as.getStock().getStockId(),
					as.getQuantity(),
					0.0)).toList();
		
	}

}
