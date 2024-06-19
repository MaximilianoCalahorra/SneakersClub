package com.unla.grupo7.services.implementation;

import java.util.List;

import org.springframework.stereotype.Service;

import com.unla.grupo7.entities.SupplyOrder;
import com.unla.grupo7.repositories.ISupplyOrderRepository;
import com.unla.grupo7.services.ISupplyOrderService;

@Service("supplyOrderService")
public class SupplyOrderService implements ISupplyOrderService {
	
	private ISupplyOrderRepository supplyOrderRepository;
	
	

	public SupplyOrderService(ISupplyOrderRepository supplyOrderRepository) {
		this.supplyOrderRepository = supplyOrderRepository;
	}

	@Override
	public SupplyOrder findBySupplyOrderId(int supplyOrderId) throws Exception {
		
		if(supplyOrderRepository.findBySupplyOrderId(supplyOrderId) == null) {
			throw new Exception("ERROR: no existe esa supplyOrder en la BD");
		}else {
			return supplyOrderRepository.findBySupplyOrderId(supplyOrderId);
		}
		
	}

	@Override
	public List<SupplyOrder> findByProduct(int productId) {
		return supplyOrderRepository.findByProduct(productId);
	}

	@Override
	public List<SupplyOrder> findBySupplier(String supplier) {
		return supplyOrderRepository.findBySupplier(supplier);
	}

	@Override
	public List<SupplyOrder> findByAmount(int amount) {
		return supplyOrderRepository.findByAmount(amount);
	}

	@Override
	public List<SupplyOrder> findByAmountGreaterThanOrEqualTo(int amount) {
		return supplyOrderRepository.findByAmountGreaterThanOrEqualTo(amount);
	}

	@Override
	public List<SupplyOrder> findByAmountLessThanOrEqualTo(int amount) {
		return supplyOrderRepository.findByAmountLessThanOrEqualTo(amount);
	}

	@Override
	public List<SupplyOrder> findByAmountRange(int minimumAmount, int maximumAmount) {
		return supplyOrderRepository.findByAmountRange(minimumAmount, maximumAmount);
	}

	@Override
	public List<SupplyOrder> findByState(String state) {
		return supplyOrderRepository.findByState(state);
	}

	@Override
	public List<SupplyOrder> getAll() {
		return supplyOrderRepository.findAll();
	}

	@Override
	public SupplyOrder insert(SupplyOrder supplyOrder) {
		return supplyOrderRepository.save(supplyOrder);
	}

	

}
