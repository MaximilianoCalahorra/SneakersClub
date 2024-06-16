package com.unla.grupo7.repositories;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.unla.grupo7.entities.Purchase;

@Repository("purchaseRepository")
public interface IPurchaseRepository extends JpaRepository<Purchase, Serializable>
{
	///Encontrar:
	
	//Encontramos la compra con determinado id:
	@Query("SELECT p FROM Purchase p INNER JOIN FETCH p.product WHERE p.purchaseId = (:purchaseId)")
	public abstract Optional<Purchase> findByPurchaseId(@Param("purchaseId")int purchaseId);
	
	//Encontramos las compras de un producto determinado:
	@Query("SELECT p FROM Purchase p INNER JOIN FETCH p.product pr WHERE pr.productId = (:productId)")
	public abstract List<Purchase> findByProduct(@Param("productId")int productId);
	
	//Encontramos las compras de un precio de compra determiando:
	@Query("SELECT p FROM Purchase p INNER JOIN FETCH p.product WHERE p.purchasePrice = (:purchasePrice)")
	public abstract List<Purchase> findByPurchasePrice(@Param("purchasePrice")double purchasePrice);
	
	//Encontramos las compras de un precio de compra mayor o igual a uno determinado:
	@Query("SELECT p FROM Purchase p INNER JOIN FETCH p.product WHERE p.purchasePrice >= (:purchasePrice)")
	public abstract List<Purchase> findByPurchasePriceGreaterThanOrEqualTo(@Param("purchasePrice")double purchasePrice);
	
	//Encontramos las compras de un precio de compra menor o igual a uno determinado:
	@Query("SELECT p FROM Purchase p INNER JOIN FETCH p.product WHERE p.purchasePrice <= (:purchasePrice)")
	public abstract List<Purchase> findByPurchasePriceLessThanOrEqualTo(@Param("purchasePrice")double purchasePrice);
	
	//Encontramos las compras de un precio de compra mayor o igual a uno mínimo y menor o igual a uno máximo:
	@Query("SELECT p FROM Purchase p INNER JOIN FETCH p.product WHERE p.purchasePrice >= (:minimumPurchasePrice)"
		   + " AND p.purchasePrice <= (:maximumPurchasePrice)")
	public abstract List<Purchase> findByPurchasePriceRange(@Param("minimumPurchasePrice")double minimumPurchasePrice,
															@Param("maximumPurchasePrice")double maximumPurchasePrice);
	
	//Encontramos las compras de una cantidad determinada:
	@Query("SELECT p FROM Purchase p INNER JOIN FETCH p.product WHERE p.amount = (:amount)")
	public abstract List<Purchase> findByAmount(@Param("amount")int amount);
	
	//Encontramos las compras de una cantidad mayor o igual a una determinada:
	@Query("SELECT p FROM Purchase p INNER JOIN FETCH p.product WHERE p.amount >= (:amount)")
	public abstract List<Purchase> findByAmountGreaterThanOrEqualTo(@Param("amount")int amount);
	
	//Encontramos las compras de una cantidad menor o igual a una determinada:
	@Query("SELECT p FROM Purchase p INNER JOIN FETCH p.product WHERE p.amount <= (:amount)")
	public abstract List<Purchase> findByAmountLessThanOrEqualTo(@Param("amount")int amount);
	
	//Encontramos las compras de una cantidad mayor o igual a una mínima y menor o igual a una máxima determinadas:
	@Query("SELECT p FROM Purchase p INNER JOIN FETCH p.product WHERE p.amount >= (:minimumAmount) AND "
		   + "p.amount <= (:maximumAmount)")
	public abstract List<Purchase> findByAmountRange(@Param("minimumAmount")int minimumAmount, @Param("maximumAmount")int maximimumAmount);
	
	//Encontramos las compras de un medio de pago determinado:
	@Query("SELECT p FROM Purchase p INNER JOIN FETCH p.product WHERE p.methodOfPay = '(:methodOfPay)'")
	public abstract List<Purchase> findByMethodOfPay(@Param("methodOfPay")String methodOfPay);
	
	//Encontramos las compras de una fecha y hora determinada:
	@Query("SELECT p FROM Purchase p INNER JOIN FETCH p.product WHERE p.dateTime = (:dateTime)")
	public abstract List<Purchase> findByDateTime(@Param("dateTime")LocalDateTime dateTime);
	
	//Encontramos las compras de una fecha y hora posterior o igual a una determinada:
	@Query("SELECT p FROM Purchase p INNER JOIN FETCH p.product WHERE p.dateTime >= (:dateTime)")
	public abstract List<Purchase> findByDateTimeGreaterThanOrEqualTo(@Param("dateTime")LocalDateTime dateTime);
	
	//Encontramos las compras de una fecha y hora anterior o igual a una determinada:
	@Query("SELECT p FROM Purchase p INNER JOIN FETCH p.product WHERE p.dateTime <= (:dateTime)")
	public abstract List<Purchase> findByDateTimeLessThanOrEqualTo(@Param("dateTime")LocalDateTime dateTime);
	
	//Encontramos las compras de una fecha y hora dentro de un intervalo (extremos incluidos):
	@Query("SELECT p FROM Purchase p INNER JOIN FETCH p.product WHERE p.dateTime BETWEEN (:fromDateTime) AND "
		   + "(:untilDateTime)")
	public abstract List<Purchase> findByDateTimeRange(@Param("fromDateTime")LocalDateTime fromDateTime, 
													   @Param("untilDateTime")LocalDateTime untilDateTime);
}
