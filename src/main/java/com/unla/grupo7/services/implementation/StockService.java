package com.unla.grupo7.services.implementation;

import java.util.List;

import org.springframework.stereotype.Service;

import com.unla.grupo7.entities.Lot;
import com.unla.grupo7.entities.Stock;
import com.unla.grupo7.repositories.IStockRepository;
import com.unla.grupo7.services.IStockService;
import com.unla.grupo7.services.ILotService;


@Service("stockService")
public class StockService implements IStockService{
	
	private IStockRepository stockRepository;
	private ILotService lotService;
	
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
	
	@Override
	public int actualAmount(int stockId) 
	{
		int actualAmount = 0;
		for(Lot lot: lotService.findByStock(stockId)) 
		{
			actualAmount += lot.getExistingAmount();
		}
		return actualAmount;
	}
	

	///Verificar:
	
	//Verificamos que el stock del producto sea suficiente y actuamos en consecuencia:
	@Override
	public void availableStock(int productId, int amount) throws Exception
	{
		int stockId = findByProduct(productId).getStockId(); //Obtenemos el id del stock del producto.
		int totalStock = actualAmount(stockId); //Obtenemos la cantidad de stock que hay del producto.
		
		//Si el stock del producto no alcanza para satisfacer la demanda:
		if(amount > totalStock) 
		{
			throw new Exception("ERROR product stock is insufficient."); //Generamos una excepción indicando que el stock es insuficiente.
		}
		
		//Por el contrario, si el stock es suficiente, realizamos el proceso de baja de stock en los lotes que correspondan y en el stock:
		int purchaseAmount = amount; //Definimos en una variable auxiliar la cantidad de unidades a comprar.
		
		int i = 0;
		List<Lot> lots = lotService.findByStock(stockId);
		//Recorremos cada lote hasta satisfacer la cantidad que se quiere comprar:
		while(i < lots.size() && purchaseAmount > 0) 
		{
			Lot lot = lots.get(i); //Obtenemos el lote.
			
			//Si el lote tiene unidades del producto:
			if(lot.getExistingAmount() > 0) 
			{
				int newExistingAmount = lot.getExistingAmount(); //La nueva cantidad en principio es la actual. 
						
				//Si la cantidad existente en el lote alcanza para satisfacer la demanda:
				if(lot.getExistingAmount() >= purchaseAmount) 
				{
					newExistingAmount -= purchaseAmount; //La nueva cantidad existente será la que estaba menos la que se compra.
					purchaseAmount = 0; //No quedan unidades por comprar.
				}
				else 
				{
					newExistingAmount = 0; //La nueva cantidad existente será 0 porque compramos todos las unidades que se pudo.
					purchaseAmount -= lot.getExistingAmount(); //La cantidad restante por comprar será la original menos la comprada.
				}
				lot.setExistingAmount(newExistingAmount); //Seteamos la cantidad existente del lote teniendo en cuenta las bajas.
				
				//Si el lote quedó vacío, lo eliminamos:
				if(lot.getExistingAmount() == 0) 
				{
					lotService.remove(lot.getLotId());
				}
				else //Por el contrario, actualizamos la cantidad:
				{
					lotService.insertOrUpdate(lot); //Actualizamos el lote con la nueva cantidad existente.
				}
			}
			i++;
		}
		totalStock -= amount; //Decrementamos el stock total con la cantidad comprada.
		Stock stock = findByStockId(stockId); //Obtenemos el stock.
		stock.setActualAmount(totalStock); //Actualizamos la cantidad actual del stock.
		insertOrUpdate(stock); //Actualizamos el stock en la base de datos con la baja producida.
	}
}
