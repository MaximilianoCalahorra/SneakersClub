package com.unla.grupo7.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.unla.grupo7.entities.Lot;

public interface ILotService 
{
	///Encontrar:
	   
	//Encontramos el lote con determinado id o lanzamos una excepción por lote inexistente:
	public Lot findByLotId(int lotId) throws Exception;
		
	//Encontramos los lotes con determinada fecha de recepción:
	public List<Lot> findByReceptionDate(LocalDateTime receptionDate);
		
	//Encontramos los lotes con fecha de recepción posterior o igual a una fecha de recepción determinada:
	public List<Lot> findByReceptionDateAfterOrEqualThan(LocalDateTime receptionDate);
		
	//Encontramos los lotes con fecha de recepción anterior o igual a a una fecha de recepción determinada:
	public List<Lot> findByReceptionDateBeforeOrEqualThan(LocalDateTime receptionDate);
		
	//Encontramos los lotes con una fecha de recepción entre un intervalo de fechas (extremos incluidos):
	public List<Lot> findByReceptionDateRange(LocalDateTime fromReceptionDate, LocalDateTime untilReceptionDate);
		
	//Encontramos los lotes con determinado cantidad existente:
	public List<Lot> findByExistingAmount(int amount);
		
	//Encontramos los lotes con cantidad existente menor o igual a una cantidad determinada:
	public List<Lot> findByExistingAmountLessThanOrEqualTo(int amount);
		
	//Encontramos los lotes con cantidad existente mayor o igual a una cantidad determinada:
	public List<Lot> findByExistingAmountGreaterThanOrEqualTo(int amount);
		
	//Encontramos los lotes con cantidad existente mayor o igual a una cantidad mínima determinada y menor o igual a una cantidad máxima
	//determinada (extremos incluidos):
	public List<Lot> findByExistingAmountRange(int minimumAmount, int maximumAmount);
		
	//Encontramos los lotes con determinado precio de venta:
	public List<Lot> findByPurchasePrice(double purchasePrice);
		
	//Encontramos los lotes con un precio de venta mayor o igual a uno determinado:
	public List<Lot> findByPurchasePriceGreaterThanOrEqualTo(double purchasePrice);
		
	//Encontramos los lotes con un precio de venta menor o igual a uno determinado:
	public List<Lot> findByPurchasePriceLessThanOrEqualTo(double purchasePrice);
		
	//Encontramos los lotes con un precio de venta entre un precio de venta mínimo y y un precio de venta máximo:
	public List<Lot> findByPurchasePriceRange(double minimumPurchasePrice, double maximumPurchasePrice);
	
	///Obtener:
	
	//Obtenemos todos los lotes:
	public List<Lot> getAll();
	
	///Agregar:
	
	//Agregamos un lote:
	public Lot insert(Lot lot);
}
