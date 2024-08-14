package com.Vagner.Agregador.entity;

import java.util.UUID;

public class Stock {

	private UUID stockId;
	private String description;
	private String ticker;
	
	
	
	public Stock(UUID stockId, String description, String ticker) {
		super();
		this.stockId = stockId;
		this.description = description;
		this.ticker = ticker;
	}
	
	protected UUID getStockId() {
		return stockId;
	}
	protected void setStockId(UUID stockId) {
		this.stockId = stockId;
	}
	protected String getDescription() {
		return description;
	}
	protected void setDescription(String description) {
		this.description = description;
	}
	protected String getTicker() {
		return ticker;
	}
	protected void setTicker(String ticker) {
		this.ticker = ticker;
	}
	
	
	
	
}
