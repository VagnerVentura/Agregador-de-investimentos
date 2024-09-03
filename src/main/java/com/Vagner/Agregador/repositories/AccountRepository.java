package com.Vagner.Agregador.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Vagner.Agregador.entity.Account;

public interface AccountRepository extends JpaRepository<Account, UUID>{

	List<Account> findByUser_UserId(UUID userId);
	
}
