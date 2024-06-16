package com.unla.grupo7.services.implementation;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.unla.grupo7.entities.Purchase;
import com.unla.grupo7.repositories.IPurchaseRepository;
import com.unla.grupo7.services.IPurchaseService;

@Service("purchaseService")
public class PurchaseService implements IPurchaseService {
	
	private IPurchaseRepository purchaseRepository;
	
	public PurchaseService(IPurchaseRepository purchaseRepository) {
		this.purchaseRepository = purchaseRepository;
	}

	@Override
	public Purchase findByPurchaseId(int purchaseId) throws Exception {
		
		
		if( (purchaseRepository.findByPurchaseId(purchaseId)) == null) {
			throw new Exception("ERROR: No existe ese id en la BD");
		}else {
			return purchaseRepository.findByPurchaseId(purchaseId);
		}
	
	}

	@Override
	public List<Purchase> findByProduct(int productId) {
		return purchaseRepository.findByProduct(productId);
	}

	@Override
	public List<Purchase> findByPurchasePrice(double purchasePrice) {
		return purchaseRepository.findByPurchasePrice(purchasePrice);
	}

	@Override
	public List<Purchase> findByPurchasePriceGreaterThanOrEqualTo(double purchasePrice) {
		return purchaseRepository.findByPurchasePrice(purchasePrice);
	}

	@Override
	public List<Purchase> findByPurchasePriceLessThanOrEqualTo(double purchasePrice) {
		return purchaseRepository.findByPurchasePriceLessThanOrEqualTo(purchasePrice);
	}

	@Override
	public List<Purchase> findByPurchasePriceRange(double minimumPurchasePrice, double maximumPurchasePrice) {
		return purchaseRepository.findByPurchasePriceRange(minimumPurchasePrice, maximumPurchasePrice);
	}

	@Override
	public List<Purchase> findByAmount(int amount) {
		return purchaseRepository.findByAmount(amount);
	}

	@Override
	public List<Purchase> findByAmountGreaterThanOrEqualTo(int amount) {
		return purchaseRepository.findByAmountGreaterThanOrEqualTo(amount);
	}

	@Override
	public List<Purchase> findByAmountLessThanOrEqualTo(int amount) {
		return purchaseRepository.findByAmountLessThanOrEqualTo(amount);
	}

	@Override
	public List<Purchase> findByAmountRange(int minimumAmount, int maximimumAmount) {
		return purchaseRepository.findByAmountRange(minimumAmount, maximimumAmount);
	}

	@Override
	public List<Purchase> findByMethodOfPay(String methodOfPay) {
		return purchaseRepository.findByMethodOfPay(methodOfPay);
	}

	@Override
	public List<Purchase> findByDateTime(LocalDateTime dateTime) {
		return purchaseRepository.findByDateTime(dateTime);
	}

	@Override
	public List<Purchase> findByDateTimeGreaterThanOrEqualTo(LocalDateTime dateTime) {
		return purchaseRepository.findByDateTimeGreaterThanOrEqualTo(dateTime);
	}

	@Override
	public List<Purchase> findByDateTimeLessThanOrEqualTo(LocalDateTime dateTime) {
		return purchaseRepository.findByDateTimeLessThanOrEqualTo(dateTime);
	}

	@Override
	public List<Purchase> findByDateTimeRange(LocalDateTime fromDateTime, LocalDateTime untilDateTime) {
		return purchaseRepository.findByDateTimeRange(fromDateTime, untilDateTime);
	}

	@Override
	public List<Purchase> getAll() {
		return purchaseRepository.findAll();
	}

	@Override
	public Purchase insert(Purchase purchase) {
		return  purchaseRepository.save(purchase);
	}
	
	

}
