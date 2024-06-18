package com.unla.grupo7.services.implementation;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.unla.grupo7.entities.Lot;
import com.unla.grupo7.repositories.ILotRepository;
import com.unla.grupo7.services.ILotService;

@Service("lotService")
public class LotService implements ILotService{

	private ILotRepository lotRepository;
	
	public LotService (ILotRepository lotRepository) {
		
		this.lotRepository = lotRepository;
		
	}
	
	
	@Override
	public Lot findByLotId(int lotId) throws Exception {
		
		Lot lot = lotRepository.findByLotId(lotId);
		
		if (lot == null) {
		
			throw new Exception("ERROR the lot is non-existent " + lotId);
			
		}
		
		return lot;
	}

	@Override
	public List<Lot> findByReceptionDate(LocalDateTime receptionDate) {
	
		return lotRepository.findByReceptionDate(receptionDate);
	}

	@Override
	public List<Lot> findByReceptionDateAfterOrEqualThan(LocalDateTime receptionDate) {
	
		return lotRepository.findByReceptionDateAfterOrEqualThan(receptionDate);
	}

	@Override
	public List<Lot> findByReceptionDateBeforeOrEqualThan(LocalDateTime receptionDate) {
		
		return lotRepository.findByReceptionDateBeforeOrEqualThan(receptionDate);
	}

	@Override
	public List<Lot> findByReceptionDateRange(LocalDateTime fromReceptionDate, LocalDateTime untilReceptionDate) {
		
		return lotRepository.findByReceptionDateRange(fromReceptionDate, untilReceptionDate);
	}

	@Override
	public List<Lot> findByExistingAmount(int amount) {
	
		return lotRepository.findByExistingAmount(amount);
	}

	@Override
	public List<Lot> findByExistingAmountLessThanOrEqualTo(int amount) {
		
		return lotRepository.findByExistingAmountLessThanOrEqualTo(amount);
	}

	@Override
	public List<Lot> findByExistingAmountGreaterThanOrEqualTo(int amount) {
	
		return lotRepository.findByExistingAmountGreaterThanOrEqualTo(amount);
	}

	@Override
	public List<Lot> findByExistingAmountRange(int minimumAmount, int maximumAmount) {
	
		return lotRepository.findByExistingAmountRange(minimumAmount, maximumAmount);
	}

	@Override
	public List<Lot> findByPurchasePrice(double purchasePrice) {
		
		return lotRepository.findByPurchasePrice(purchasePrice);
	}

	@Override
	public List<Lot> findByPurchasePriceGreaterThanOrEqualTo(double purchasePrice) {
		
		return lotRepository.findByPurchasePriceGreaterThanOrEqualTo(purchasePrice);
	}

	@Override
	public List<Lot> findByPurchasePriceLessThanOrEqualTo(double purchasePrice) {
	
		return lotRepository.findByPurchasePriceLessThanOrEqualTo(purchasePrice);
	}

	@Override
	public List<Lot> findByPurchasePriceRange(double minimumPurchasePrice, double maximumPurchasePrice) {
		
		return lotRepository.findByPurchasePriceRange(minimumPurchasePrice, maximumPurchasePrice);
	}
	
	@Override
	public List<Lot> findByStock(int stockId) 
	{	
		return lotRepository.findByStock(stockId);
	}
	
	@Override
	public Lot findBySupplyOrder(int supplyOrderId) {
		
		return lotRepository.findBySupplyOrder(supplyOrderId);
	}

	@Override
	public List<Lot> getAll() {
	
		return lotRepository.findAll();
	}

	@Override
	public Lot insertOrUpdate(Lot lot) {
		
		return lotRepository.save(lot);
		
	}


	@Override
	public boolean remove(int lotId) {
		
		try {
			
			lotRepository.deleteById(lotId);
			return true;
			
		}catch(Exception e){
			
			return false;
		}
	}





	
	
	
}
