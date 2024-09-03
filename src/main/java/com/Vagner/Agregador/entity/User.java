package com.Vagner.Agregador.entity;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_users")
public class User {

	@Id
	@GeneratedValue(strategy =GenerationType.UUID)
	private UUID userId;
	
	@Column(name ="username")
	private String username;
	
	@Column(name ="email")
	private String email;
	
	@Column(name = "password")
	private String password;
	
	@CreationTimestamp
    private Instant creationTimestamp;
	
	@UpdateTimestamp
    private Instant updateTimestamp;	
	
	@JsonManagedReference
	@OneToMany(mappedBy="user")
	private List<Account> accounts;
	
	public User() {}	
	
	public User(UUID userId, String username, String email, String password, Instant creationTimestamp,
			Instant updateTimestamp) {
		super();
		this.userId = userId;
		this.username = username;
		this.email = email;
		this.password = password;
		this.creationTimestamp = creationTimestamp;
		this.updateTimestamp = updateTimestamp;
	}

	public UUID getUserId() {
		return userId;
	}

	public void setUserId(UUID user_id) {
		this.userId = user_id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

    protected Instant getCreationTimestamp() {
		return creationTimestamp;
	}

	protected void setCreationTimestamp(Instant creationTimestamp) {
		this.creationTimestamp = creationTimestamp;
	}

	protected Instant getUpdateTimestamp() {
		return updateTimestamp;
	}

	protected void setUpdateTimestamp(Instant updateTimestamp) {
		this.updateTimestamp = updateTimestamp;
	}

	public List<Account> getAccounts() {
		return accounts;
	}

	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
	}
	
	
	
}
