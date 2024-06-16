package com.unla.grupo7.repositories;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.unla.grupo7.entities.SupplyOrder;

@Repository("supplyOrderRepository")
public interface ISupplyOrderRepository extends JpaRepository<SupplyOrder, Serializable>
{
	///Encontrar:
	
	//Encontramos el pedido de aprovisionamiento con determinado id:
	@Query("SELECT so FROM SupplyOrder so INNER JOIN FETCH so.product WHERE so.supplyOrderId = (:supplyOrderId)")
	public abstract Optional<SupplyOrder> findBySupplyOrderId(@Param("supplyOrderId")int supplyOrderId);
	
	//Encontramos los pedidos de aprovisionamiento de un producto:
	@Query("SELECT so FROM SupplyOrder so INNER JOIN FETCH so.product p WHERE p.productId = (:productId)")
	public abstract List<SupplyOrder> findByProduct(@Param("productId")int productId);
	
	//Encontramos los pedidos de aprovisionamiento de un proveedor:
	@Query("SELECT so FROM SupplyOrder so INNER JOIN FETCH so.product WHERE so.supplier = '(:supplier)'")
	public abstract List<SupplyOrder> findBySupplier(@Param("supplier")String supplier);
	
	//Encontramos los pedidos de aprovisionamiento de determinada cantidad:
	@Query("SELECT so FROM SupplyOrder so INNER JOIN FETCH so.product WHERE so.amount = (:amount)")
	public abstract List<SupplyOrder> findByAmount(@Param("amount")int amount);
	
	//Encontramos los pedidos de aprovisionamiento con una cantidad mayor o igual a una determinada:
	@Query("SELECT so FROM SupplyOrder so INNER JOIN FETCH so.product WHERE so.amount >= (:amount)")
	public abstract List<SupplyOrder> findByAmountGreaterThanOrEqualTo(@Param("amount")int amount);
	
	//Encontramos los pedidos de aprovisionamiento con una cantidad menor o igual a una determinada:
	@Query("SELECT so FROM SupplyOrder so INNER JOIN FETCH so.product WHERE so.amount <= (:amount)")
	public abstract List<SupplyOrder> findByAmountLessThanOrEqualTo(@Param("amount")int amount);
	
	//Encontramos los pedidos de aprovisionamiento con una cantidad entre un rango (extremos incluidos):
	@Query("SELECT so FROM SupplyOrder so INNER JOIN FETCH so.product WHERE so.amount >= (:minimumAmount) AND so.amount <= (:maximumAmount)")
	public abstract List<SupplyOrder> findByAmountRange(@Param("minimumAmount")int minimumAmount, @Param("maximumAmount")int maximumAmount);

	//Encontramos los pedidos de aprovisionamiento con determinado estado:
	@Query("SELECT so FROM SupplyOrder so INNER JOIN FETCH so.product WHERE so.state = '(:state)'")
	public abstract List<SupplyOrder> findByState(@Param("state")String state);
}
