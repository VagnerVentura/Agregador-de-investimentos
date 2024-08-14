package com.Vagner.Agregador.entity;

import java.util.UUID;

import jakarta.persistence.OneToOne;

public class BillingAddress {

	private UUID accountId;
	private String street;
	private Integer number;
	
//	@OneToOne
	private Account account;
	
	public BillingAddress(UUID accountId, String street, Integer number) {
		super();
		this.accountId = accountId;
		this.street = street;
		this.number = number;
	}

	protected UUID getAccountId() {
		return accountId;
	}

	protected void setAccountId(UUID accountId) {
		this.accountId = accountId;
	}

	protected String getStreet() {
		return street;
	}

	protected void setStreet(String street) {
		this.street = street;
	}

	protected Integer getNumber() {
		return number;
	}

	protected void setNumber(Integer number) {
		this.number = number;
	}
	
}
