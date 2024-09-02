package com.Vagner.Agregador.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Vagner.Agregador.entity.AccountStock;
import com.Vagner.Agregador.entity.AccountStockId;

public interface AccountStockRepository extends JpaRepository<AccountStock, AccountStockId>{

}
