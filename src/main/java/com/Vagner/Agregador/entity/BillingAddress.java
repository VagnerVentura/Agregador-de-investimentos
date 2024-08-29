package com.Vagner.Agregador.entity;

import java.util.UUID;

import jakarta.persistence.Id;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name= "tb_billingaddress")
public class BillingAddress {

	@Id
	@Column(name = "account_id")
	private UUID id;
	
	@Column(name = "street")
	private String street;
	
	@Column(name = "number")
	private Integer number;
	
	@OneToOne(cascade = CascadeType.ALL)
	@MapsId
	@JoinColumn(name = "account_id")
	private Account account;
	
	public BillingAddress() {}
	
	public BillingAddress(UUID id, Account account, String street, Integer number) {
		this.id = id;
		this.account = account;
		this.street = street;
		this.number = number;
	}

	protected UUID getid() {
		return id;
	}

	protected void setid(UUID id) {
		this.id = id;
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
