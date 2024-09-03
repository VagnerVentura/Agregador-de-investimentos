package com.Vagner.Agregador.entity;

import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_accounts")
public class Account {

	@Id
	@Column(name = "account_id")
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID accountId;
	
	private String description;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	@JsonBackReference
	private User user;
	
	@OneToOne( cascade = CascadeType.ALL,mappedBy = "account")
	@PrimaryKeyJoinColumn
	private BillingAddress billingAddress;
	
	@OneToMany(mappedBy = "account")
	private List<AccountStock> accountStocks;
	
	public Account() {}
	
	public Account(UUID uuid, User user ,BillingAddress billingAddress , String description) {
		this.accountId = uuid;
		this.user = user;
		this.billingAddress = billingAddress;
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

	public List<AccountStock> getAccountStocks() {
		return accountStocks;
	}

	public void setAccountStocks(List<AccountStock> accountStocks) {
		this.accountStocks = accountStocks;
	}

	
	
}
