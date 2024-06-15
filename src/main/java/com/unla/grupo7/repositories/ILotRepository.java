package com.unla.grupo7.repositories;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.unla.grupo7.entities.Lot;

@Repository("lotRepository")
public interface ILotRepository extends JpaRepository<Lot, Serializable>  
{
	///Encontrar:
	   
	//Encontramos el lote con determinado id:
	@Query("SELECT l FROM Lot l INNER JOIN FETCH l.stock WHERE l.lotId = (:lotId)")
	public abstract Optional<Lot> findByLotId(@Param("lotId") int lotId);
	
	//Encontramos los lotes con determinada fecha de recepción:
	@Query("SELECT l FROM Lot l INNER JOIN FETCH l.stock WHERE l.receptionDate = '(:receptionDate)'")
	public abstract List<Lot> findByReceptionDate(@Param("receptionDate")LocalDateTime receptionDate);
	
	//Encontramos los lotes con fecha de recepción posterior o igual a una fecha de recepción determinada:
	@Query("SELECT l FROM Lot l INNER JOIN FETCH l.stock WHERE l.receptionDate >= '(:receptionDate)'")
	public abstract List<Lot> findByReceptionDateAfterOrEqualThan(@Param("receptionDate")LocalDateTime receptionDate);
	
	//Encontramos los lotes con fecha de recepción anterior o igual a a una fecha de recepción determinada:
	@Query("SELECT l FROM Lot l INNER JOIN FETCH l.stock WHERE l.receptionDate =< '(:receptionDate)'")
	public abstract List<Lot> findByReceptionDateBeforeOrEqualThan(@Param("receptionDate")LocalDateTime receptionDate);
	
	//Encontramos los lotes con una fecha de recepción entre un intervalo de fechas (extremos incluidos):
	@Query("SELECT l FROM Lot l INNER JOIN FETCH l.stock WHERE l.receptionDate BETWEEN '(:fromReceptionDate)' AND"
		   + " '(:untilReceptionDate)'")
	public abstract List<Lot> findByReceptionDateRange(@Param("fromReceptionDate")LocalDateTime fromReceptionDate,
													   @Param("untilReceptionDate")LocalDateTime untilReceptionDate);
	
	//Encontramos los lotes con determinado cantidad existente:
	@Query("SELECT l FROM Lot l INNER JOIN FETCH l.stock WHERE l.existingAmount = (:amount)")
	public abstract List<Lot> findByExistingAmount(@Param("amount")int amount);
	
	//Encontramos los lotes con cantidad existente menor o igual a una cantidad determinada:
	@Query("SELECT l FROM Lot l INNER JOIN FETCH l.stock WHERE l.existingAmount =< (:amount)")
	public abstract List<Lot> findByExistingAmountLessThanOrEqualTo(@Param("amount")int amount);
	
	//Encontramos los lotes con cantidad existente mayor o igual a una cantidad determinada:
	@Query("SELECT l FROM Lot l INNER JOIN FETCH l.stock WHERE l.existingAmount >= (:amount)")
	public abstract List<Lot> findByExistingAmountGreaterThanOrEqualTo(@Param("amount")int amount);
	
	//Encontramos los lotes con cantidad existente mayor o igual a una cantidad mínima determinada y menor o igual a una cantidad máxima
	//determinada (extremos incluidos):
	@Query("SELECT l FROM Lot l INNER JOIN FETCH l.stock WHERE l.existingAmount >= (:minimumAmount) AND l.existingAmount =< (:maximumAmount)")
	public abstract List<Lot> findByExistingAmountRange(@Param("minimumAmount")int minimumAmount, @Param("maximumAmount")int maximumAmount);
	
	//Encontramos los lotes con determinado precio de venta:
	@Query("SELECT l FROM Lot l INNER JOIN FETCH l.stock WHERE l.purchasePrice = (:purchasePrice)")
	public abstract List<Lot> findByPurchasePrice(@Param("purchasePrice")double purchasePrice);
	
	//Encontramos los lotes con un precio de venta mayor o igual a uno determinado:
	@Query("SELECT l FROM Lot l INNER JOIN FETCH l.stock WHERE l.purchasePrice >= (:purchasePrice)")
	public abstract List<Lot> findByPurchasePriceGreaterThanOrEqualTo(@Param("purchasePrice")double purchasePrice);
	
	//Encontramos los lotes con un precio de venta menor o igual a uno determinado:
	@Query("SELECT l FROM Lot l INNER JOIN FETCH l.stock WHERE l.purchasePrice =< (:purchasePrice)")
	public abstract List<Lot> findByPurchasePriceLessThanOrEqualTo(@Param("purchasePrice")double purchasePrice);
	
	//Encontramos los lotes con un precio de venta entre un precio de venta mínimo y y un precio de venta máximo:
	@Query("SELECT l FROM Lot l INNER JOIN FETCH l.stock WHERE l.purchasePrice >= (:minimumPurchasePrice) AND"
		   + " l.purchasePrice =< (:maximumPurchasePrice)")
	public abstract List<Lot> findByPurchasePriceRange(@Param("minimumPurchasePrice")double minimumPurchasePrice,
													   @Param("maximumPurchasePrice")double maximumPurchasePrice);
}
