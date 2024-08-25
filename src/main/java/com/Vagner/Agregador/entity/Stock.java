package com.Vagner.Agregador.entity;

import java.util.UUID;


import jakarta.persistence.Column;
import jakarta.persistence.Id;

public class Stock {

	@Id
	@Column(name = "stock_id")
	private String stockId;

	@Column(name = "description")
	private String description;
	
	private String ticker;
	
	public Stock() {}
	
	public Stock(String stockId, String description, String ticker) {
		this.stockId = stockId;
		this.description = description;
		this.ticker = ticker;
	}
	
	protected String getStockId() {
		return stockId;
	}
	protected void setStockId(String stockId) {
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
