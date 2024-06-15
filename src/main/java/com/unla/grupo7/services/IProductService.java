package com.unla.grupo7.services;

import java.util.List;
import java.util.Optional;

import com.unla.grupo7.entities.Product;

public interface IProductService 
{
	///Encontrar:
	
	//Encontramos el producto con determinado id o lanzamos una excepción por producto inexistente:
	public Optional<Product> findByProductId(int productId) throws Exception;
	
	//Encontramos el producto con determinado código o lanzamos una excepción por producto inexistente:
	public Product findByCode(String code) throws Exception; 
		
	//Encontramos el producto con determinado nombre o lanzamos una excepción por producto inexistente:
	public Product findByName(String name) throws Exception; 
		
	//Encontramos los productos con determinada marca:
	public List<Product> findByBrand(String brand); 
		
	//Encontramos los productos que tengan un determinado precio de venta:
	public List<Product> findBySalePrice(double salePrice); 
		
	//Encontramos los productos que estén en determinado estado:
	public List<Product> findByEnabled(boolean enabled);
	
	//Encontramos los productos que tengan un precio de venta mayor o igual a uno determinado:
	public List<Product> findBySalePriceGreaterThanOrEqualTo(double salePrice); 
		
	//Encontramos los productos que tengan un precio de venta menor o igual a uno determinado:
	public List<Product> findBySalePriceLessThanOrEqualTo(double salePrice); 
		
	//Encontramos los productos que tengan un precio de venta mayor o igual a uno mínimo determinado y menor o igual a uno máximo determinado:
	public List<Product> findBySalePriceRange(double minimumSalePrice, double maximumSalePrice); 
	
	///Obtener:
	
	//Obtenemos los productos:
	public List<Product> getAll();
	
	///Agregar o modificar:
	
	//Agregamos o modificamos un producto:
	public Product insertOrUpdate(Product product);
	
	///Eliminar:
	
	//Eliminamos lógicamente un producto:
	public boolean removeLogical(int id);
	
	//Eliminamos físicamente un producto:
	public boolean remove(int id);
}
