package com.Vagner.Agregador.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.Vagner.Agregador.dto.CreateStockDto;
import com.Vagner.Agregador.dto.StockResponseDto;
import com.Vagner.Agregador.entity.Stock;
import com.Vagner.Agregador.repositories.StockRepository;

@Service
public class StockService {

	private StockRepository stockRepository;
	
	public StockService(StockRepository stockRepository) {
		this.stockRepository = stockRepository;
	}
	
	public void createStock(CreateStockDto createStockDto) {
		
		 var stock = new Stock(
				 createStockDto.stockId(),
				 createStockDto.description()				 
				 );
		 
		stockRepository.save(stock);
	}
	
	public List<StockResponseDto> listStocks(){
		var stocks = stockRepository.findAll();
		return stocks.stream()
				.map(st -> new StockResponseDto(
				st.getStockId(),st.getDescription()))
				.toList()
				;
	}
	
}
