package com.Vagner.Agregador.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_stocks")
public class Stock {

	@Id
	@Column(name = "stock_id")
	private String stockId;

	@Column(name = "description")
	private String description;
	
	private String ticker;
	
	public Stock() {}
	
	public Stock(String stockId, String description) {
		this.stockId = stockId;
		this.description = description;
//		this.ticker = ticker;
	}
	
	public String getStockId() {
		return stockId;
	}
	protected void setStockId(String stockId) {
		this.stockId = stockId;
	}
	public String getDescription() {
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
