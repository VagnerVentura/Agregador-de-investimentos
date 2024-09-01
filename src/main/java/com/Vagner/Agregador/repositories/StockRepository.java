package com.Vagner.Agregador.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Vagner.Agregador.entity.Stock;

public interface StockRepository extends JpaRepository<Stock, String>{

}
