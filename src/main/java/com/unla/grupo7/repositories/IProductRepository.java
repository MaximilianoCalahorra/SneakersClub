package com.unla.grupo7.repositories;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.unla.grupo7.entities.Product;

@Repository("productRepository")
public interface IProductRepository extends JpaRepository<Product, Serializable>
{
	///Encontrar:
	
	//Encontramos el producto con determinado id:
	public abstract Product findByProductId(int productId);
	
	//Encontramos el producto con determinado código:
	public abstract Product findByCode(String code); 
	
	//Encontramos el producto con determinado nombre:
	public abstract Product findByName(String name); 
	
	//Encontramos los productos con determinada marca:
	public abstract List<Product> findByBrand(String brand); 
	
	//Encontramos los productos que tengan un determinado precio de venta:
	public abstract List<Product> findBySalePrice(double salePrice); 
	
	//Encontramos los productos que estén en determinado estado:
	public abstract List<Product> findByEnabled(boolean enabled);
	
	//Encontramos los productos que tengan un precio de venta mayor o igual a uno determinado:
	@Query("SELECT p FROM Product p WHERE p.salePrice >= (:salePrice)")
	public abstract List<Product> findBySalePriceGreaterThanOrEqualTo(@Param("salePrice")double salePrice); 
	
	//Encontramos los productos que tengan un precio de venta menor o igual a uno determinado:
	@Query("SELECT p FROM Product p WHERE p.salePrice <= (:salePrice)")
	public abstract List<Product> findBySalePriceLessThanOrEqualTo(@Param("salePrice")double salePrice); 
	
	//Encontramos los productos que tengan un precio de venta mayor o igual a uno mínimo determinado y menor o igual a uno máximo determinado:
	@Query("SELECT p FROM Product p WHERE p.salePrice >= (:minimumSalePrice) AND p.salePrice <= (:maximumSalePrice)")
	public abstract List<Product> findBySalePriceRange(@Param("minimumSalePrice")double minimumSalePrice, 
													   @Param("maximumSalePrice")double maximumSalePrice); 
}
