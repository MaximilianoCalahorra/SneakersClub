package com.unla.grupo7.services;

import java.time.LocalDateTime;
import java.util.List;

import com.unla.grupo7.entities.Purchase;

public interface IPurchaseService
{
	///Encontrar:
	
	//Encontramos la compra con determinado id o lanzamos una excepción por compra inexistente:
	public Purchase findByPurchaseId(int purchaseId) throws Exception;
		
	//Encontramos las compras de un producto determinado:
	public List<Purchase> findByProduct(int productId);
		
	//Encontramos las compras de un precio de compra determiando:
	public List<Purchase> findByPurchasePrice(double purchasePrice);
	
	//Encontramos las compras de un precio de compra mayor o igual a uno determinado:
	public List<Purchase> findByPurchasePriceGreaterThanOrEqualTo(double purchasePrice);
		
	//Encontramos las compras de un precio de compra menor o igual a uno determinado:
	public List<Purchase> findByPurchasePriceLessThanOrEqualTo(double purchasePrice);
		
	//Encontramos las compras de un precio de compra mayor o igual a uno mínimo y menor o igual a uno máximo:
	public List<Purchase> findByPurchasePriceRange(double minimumPurchasePrice, double maximumPurchasePrice);
		
	//Encontramos las compras de una cantidad determinada:
	public List<Purchase> findByAmount(int amount);
		
	//Encontramos las compras de una cantidad mayor o igual a una determinada:
	public List<Purchase> findByAmountGreaterThanOrEqualTo(int amount);
		
	//Encontramos las compras de una cantidad menor o igual a una determinada:
	public List<Purchase> findByAmountLessThanOrEqualTo(int amount);
		
	//Encontramos las compras de una cantidad mayor o igual a una mínima y menor o igual a una máxima determinadas:
	public List<Purchase> findByAmountRange(int minimumAmount, int maximimumAmount);
		
	//Encontramos las compras de un medio de pago determinado:
	public List<Purchase> findByMethodOfPay(String methodOfPay);
		
	//Encontramos las compras de una fecha y hora determinada:
	public List<Purchase> findByDateTime(LocalDateTime dateTime);
		
	//Encontramos las compras de una fecha y hora posterior o igual a una determinada:
	public List<Purchase> findByDateTimeGreaterThanOrEqualTo(LocalDateTime dateTime);
	
	//Encontramos las compras de una fecha y hora anterior o igual a una determinada:
	public List<Purchase> findByDateTimeLessThanOrEqualTo(LocalDateTime dateTime);
		
	//Encontramos las compras de una fecha y hora dentro de un intervalo (extremos incluidos):
	public List<Purchase> findByDateTimeRange(LocalDateTime fromDateTime, LocalDateTime untilDateTime);
	
	///Obtener:
	
	//Obtenemos todas las compras:
	public List<Purchase> getAll();
	
	///Agregar:
	
	//Agregamos una compra:
	public Purchase insert(Purchase purchase);
}
