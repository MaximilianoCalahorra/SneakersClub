package com.unla.grupo7.services;

import java.util.List;

import com.unla.grupo7.entities.SupplyOrder;

public interface ISupplyOrderService 
{
	///Encontrar:
	
	//Encontramos el pedido de aprovisionamiento con determinado id o lanzamos una excepción por pedido de aprovisionamiento inexistente:
	public SupplyOrder findBySupplyOrderId(int supplyOrderId) throws Exception;
		
	//Encontramos los pedidos de aprovisionamiento de un producto:
	public List<SupplyOrder> findByProduct(int productId);
		
	//Encontramos los pedidos de aprovisionamiento de un proveedor:
	public List<SupplyOrder> findBySupplier(String supplier);
		
	//Encontramos los pedidos de aprovisionamiento de determinada cantidad:
	public List<SupplyOrder> findByAmount(int amount);
		
	//Encontramos los pedidos de aprovisionamiento con una cantidad mayor o igual a una determinada:
	public List<SupplyOrder> findByAmountGreaterThanOrEqualTo(int amount);
		
	//Encontramos los pedidos de aprovisionamiento con una cantidad menor o igual a una determinada:
	public List<SupplyOrder> findByAmountLessThanOrEqualTo(int amount);
		
	//Encontramos los pedidos de aprovisionamiento con una cantidad entre un rango (extremos incluidos):
	public List<SupplyOrder> findByAmountRange(int minimumAmount, int maximumAmount);

	//Encontramos los pedidos de aprovisionamiento con determinado estado:
	public List<SupplyOrder> findByState(String state);
	
	///Obtener:
	
	//Obtenemos los pedidos de aprovisionamiento:
	public List<SupplyOrder> getAll();
	
	
	
	
	
	///Agregar:
	
	//Agregamos un pedido de aprovisionamiento:
	public SupplyOrder insert(SupplyOrder supplyOrder);
}
