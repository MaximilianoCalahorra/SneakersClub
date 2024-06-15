package com.unla.grupo7.repositories;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.unla.grupo7.entities.Stock;

@Repository("stockRepository")
public interface IStockRepository extends JpaRepository<Stock, Serializable>
{
	///Encontrar:
	
	//Encontramos el stock con determinado id:
	@Query("SELECT s FROM Stock s INNER JOIN FETCH s.product WHERE s.stockId = (:stockId)")
	public abstract Optional<Stock> findByStockId(@Param("stockId")int stockId);
	
	//Encontramos el stock con determinado producto:
	@Query("SELECT s FROM Stock s INNER JOIN FETCH s.product p WHERE p.productId = (:productId)")
	public abstract Optional<Stock> findByProduct(@Param("productId")int productId);
	
	//Encontramos los stock con determinada cantidad mínima:
	@Query("SELECT s FROM Stock s INNER JOIN FETCH s.product WHERE s.minimumAmount = (:minimumAmount)")
	public abstract List<Stock> findByMinimumAmount(@Param("minimumAmount")int minimumAmount);
	
	//Encontramos los stocks con determinada cantidad actual:
	@Query("SELECT s FROM Stock s INNER JOIN FETCH s.product WHERE s.actualAmount = (:actualAmount)")
	public abstract List<Stock> findByActualAmount(@Param("actualAmount")int actualAmount);
	
	//Encontramos los stocks con una cantidad actual mayor o igual a determinada cantidad:
	@Query("SELECT s FROM Stock s INNER JOIN FETCH s.product WHERE s.actualAmount >= (:amount)")
	public abstract List<Stock> findByActualAmountGreaterThanOrEqualTo(@Param("amount")int amount);
	
	//Encontramos los stocks con una cantidad actual menor o igual a determinada cantidad:
	@Query("SELECT s FROM Stock s INNER JOIN FETCH s.product WHERE s.actualAmount =< (:amount)")
	public abstract List<Stock> findByActualAmountLessThanOrEqualTo(@Param("amount")int amount);
	
	//Encontramos los stocks con una cantidad actual entre dos cantidades determinadas (extremos incluidos):
	@Query("SELECT s FROM Stock s INNER JOIN FETCH s.product WHERE s.actualAmount >= (:minimumAmount) AND s.actualAmount <= (:maximumAmount)")
	public abstract List<Stock> findByActualAmountRange(@Param("minimumAmount")int minimumAmount, @Param("maximumAmount")int maximumAmount);
}
