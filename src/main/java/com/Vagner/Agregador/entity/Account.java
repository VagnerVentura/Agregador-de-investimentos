package com.Vagner.Agregador.entity;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;

public class Account {

	@Id
	@Column(name = "account_id")
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID accountId;
	
	private String description;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	
	@OneToOne(mappedBy = "account")
	@PrimaryKeyJoinColumn
	private BillingAddress billingAddress;
	
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

	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}

	
	
}
