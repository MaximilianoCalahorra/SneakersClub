package com.unla.grupo7.services.implementation;

import java.util.List;
import java.util.Optional;

import com.unla.grupo7.entities.Stock;
import com.unla.grupo7.repositories.IStockRepository;
import com.unla.grupo7.services.IStockService;



public class StockService implements IStockService{
	
	private IStockRepository stockRepository;
	
	public StockService (IStockRepository stockRepository) {
		
		this.stockRepository = stockRepository;
		
	}

	@Override
	public Stock findByStockId(int stockId) throws Exception {
		
		Stock stock = stockRepository.findByStockId(stockId);
		
		if (stock == null) {
			
			throw new Exception("ERROR the stock is non-existent " + stockId);
			
		}
		
		return stock;
	}

	@Override
	public Stock findByProduct(int productId) throws Exception {
		
		Stock stock = stockRepository.findByProduct(productId);
		
		if (stock == null) {
			
			throw new Exception("ERROR the stock is non-existent " + productId);
			
		}
		
		return stock;
	}

	@Override
	public List<Stock> findByMinimumAmount(int minimumAmount) {
		
		return stockRepository.findByMinimumAmount(minimumAmount);
	}

	@Override
	public List<Stock> findByActualAmount(int actualAmount) {
		
		return stockRepository.findByActualAmount(actualAmount);
	}

	@Override
	public List<Stock> findByActualAmountGreaterThanOrEqualTo(int amount) {
	
		return stockRepository.findByActualAmountGreaterThanOrEqualTo(amount);
	}

	@Override
	public List<Stock> findByActualAmountLessThanOrEqualTo(int amount) {
		
		return stockRepository.findByActualAmountLessThanOrEqualTo(amount);
	}

	@Override
	public List<Stock> findByActualAmountRange(int minimumAmount, int maximumAmount) {
		
		return stockRepository.findByActualAmountRange(minimumAmount, maximumAmount);
	}

	@Override
	public List<Stock> getAll() {
		
		return stockRepository.findAll();
	}

	@Override
	public Stock insertOrUpdate(Stock stock) {
		
		return stockRepository.save(stock);
		
	}
	
	
	

}
