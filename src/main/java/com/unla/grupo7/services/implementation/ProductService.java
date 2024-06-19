package com.unla.grupo7.services.implementation;

import java.util.List;

import org.springframework.stereotype.Service;

import com.unla.grupo7.entities.Product;
import com.unla.grupo7.repositories.IProductRepository;
import com.unla.grupo7.services.IProductService;

@Service("productService")
public class ProductService implements IProductService
{
	///Atributos:
	private IProductRepository productRepository;
	
	///Constructor:
	public ProductService(IProductRepository productRepository) 
	{
		this.productRepository = productRepository;
	}
	
	///Encontrar:
	
	//Encontramos el producto con determinado id:
	@Override
	public Product findByProductId(int productId) throws Exception
	{
		return productRepository.findByProductId(productId);
	}
	
	//Encontramos el producto con determinado código:
	@Override
	public Product findByCode(String code) throws Exception
	{
		Product product = productRepository.findByCode(code);
		
		if(product == null) 
		{
			throw new Exception("Error! There isn't exists a Product with code: #" + code);
		}
		return product;
	}
			
	//Encontramos el producto con determinado nombre:
	@Override
	public Product findByName(String name) throws Exception
	{
		Product product = productRepository.findByName(name);
		if(product == null) 
		{
			throw new Exception("ERROR the product is-not existent");
		}
		return product;
	}
			
	//Encontramos los productos con determinada marca:
	@Override
	public List<Product> findByBrand(String brand)
	{
		return productRepository.findByBrand(brand);
	}
			
	//Encontramos los productos que tengan un determinado precio de venta:
	@Override
	public List<Product> findBySalePrice(double salePrice)
	{
		return productRepository.findBySalePrice(salePrice);
	}
			
	//Encontramos los productos que estén en determinado estado:
	@Override
	public List<Product> findByEnabled(boolean enabled)
	{
		return productRepository.findByEnabled(enabled);
	}
		
	//Encontramos los productos que tengan un precio de venta mayor o igual a uno determinado:
	@Override
	public List<Product> findBySalePriceGreaterThanOrEqualTo(double salePrice)
	{
		return productRepository.findBySalePriceGreaterThanOrEqualTo(salePrice);
	}
			
	//Encontramos los productos que tengan un precio de venta menor o igual a uno determinado:
	@Override
	public List<Product> findBySalePriceLessThanOrEqualTo(double salePrice)
	{
		return productRepository.findBySalePriceLessThanOrEqualTo(salePrice);
	}
			
	//Encontramos los productos que tengan un precio de venta mayor o igual a uno mínimo determinado y menor o igual a uno máximo determinado:
	@Override
	public List<Product> findBySalePriceRange(double minimumSalePrice, double maximumSalePrice)
	{
		return productRepository.findBySalePriceRange(minimumSalePrice, maximumSalePrice);
	}
	
	///Obtener:
	
	//Obtenemos todos los productos:
	@Override
	public List<Product> getAll()
	{
		return productRepository.findAll();
	}
	
	//Obtenemos los productos ORDENADOS:
	@Override
	public List<Product> getAllInOrder(){
		return productRepository.getAllInOrder();
	}
	
	///Agregar o modificar:
	
	//Agregamos un producto:
	@Override
	public Product insert(Product product) throws Exception
	{																
		if( productRepository.findByCode(product.getCode()) != null) 
		{
			throw new Exception("ERROR the product already exists");
		}
		
	
		return productRepository.save(product);
	}
	
	///Modificar:
	
	//Modificamos un producto:
	@Override
	public Product update(Product product) 
	{
		return productRepository.save(product);
	}
	
	///Eliminar:
	
	//Eliminamos lógicamente el producto:
	@Override
	public boolean removeLogical(int productId) 
	{
		try 
		{
			Product product = findByProductId(productId);
			product.setEnabled(false);
			update(product);
			return true;
		} 
		catch(Exception e)
		{
			return false;
		}
	}
	
	//Eliminamos físicamente el producto:
	@Override
	public boolean remove(int productId) 
	{
		try 
		{
			productRepository.deleteById(productId);
			return true;
		}
		catch(Exception e)
		{
			return false;
		}
	}

	@Override
	public List<Product> findByEnabledInOrder(boolean enabled) {
		return productRepository.findByEnabledInOrder(enabled);
	}
}
