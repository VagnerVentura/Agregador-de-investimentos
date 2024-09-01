package com.Vagner.Agregador.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Vagner.Agregador.dto.CreateStockDto;
import com.Vagner.Agregador.dto.StockResponseDto;
import com.Vagner.Agregador.services.StockService;

@RestController
@RequestMapping("/v1/stocks")
public class StockController{
	
	private StockService stockService;
	
	public StockController(StockService stockService) {
		this.stockService = stockService;
	}
	
	@PostMapping
	public ResponseEntity<Void> createStock(@RequestBody CreateStockDto createStockDto){
		 stockService.createStock(createStockDto);
		
		return ResponseEntity.ok().build();
		
	}
	
	@GetMapping
	public ResponseEntity<List<StockResponseDto>> listStocks(){
		var stocks = stockService.listStocks();
		return ResponseEntity.ok(stocks);
	}
	
	
	
}