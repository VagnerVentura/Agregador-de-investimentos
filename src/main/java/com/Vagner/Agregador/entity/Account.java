package com.Vagner.Agregador.entity;

import java.util.UUID;
import jakarta.persistence.ManyToOne;

public class Account {

	private UUID accountId;
	private String description;
	
//	@ManyToOne
	private User user;
	
	
	public Account(UUID accountId, String description) {
		super();
		this.accountId = accountId;
		this.description = description;
	}


	public UUID getAccountId() {
		return accountId;
	}


	public void setAccountId(UUID accountId) {
		this.accountId = accountId;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}
	
	
	
}
