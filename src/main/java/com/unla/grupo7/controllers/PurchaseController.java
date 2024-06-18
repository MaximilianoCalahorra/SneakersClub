package com.unla.grupo7.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.RequestMapping;

import com.unla.grupo7.entities.Product;
import com.unla.grupo7.entities.Purchase;
import com.unla.grupo7.helpers.ViewRouteHelper;
import com.unla.grupo7.services.IProductService;
import com.unla.grupo7.services.IPurchaseService;
import com.unla.grupo7.services.IStockService;

@Controller
@RequestMapping("/purchases")
public class PurchaseController 
{
	//Atributo:
	private IPurchaseService purchaseService;
	private IStockService stockService;
	private IProductService productService;

	
	//Constructor:
	public PurchaseController(IPurchaseService purchaseService, IStockService stockService, IProductService productService) 
	{
		this.purchaseService = purchaseService;
		this.stockService = stockService;
		this.productService = productService;
	
	}
	
	//Presentamos el producto con toda su información y un formulario donde se permite la compra del mismo:
	@GetMapping("/purchaseForm/{productId}")
	public ModelAndView purchaseForm(@PathVariable int productId) throws Exception 
	{
		ModelAndView modelAndView = new ModelAndView(ViewRouteHelper.PURCHASE_FORM); //Indicamos la vista a cargar.
		Product product = productService.findByProductId(productId); //Obtenemos el producto por su id.
		modelAndView.addObject("product", product); //Agregamos el producto a la vista.
		return modelAndView; //Retornamos la vista.
	}
	
	//Agregamos los datos de la compra a la base de datos en caso de que alcance el stock:
	@PostMapping("/purchaseSave")
	public ModelAndView create(@RequestParam("productId")int productId, @RequestParam("amount")int amount,
						 @RequestParam("methodOfPay")String methodOfPay) 
	
	{
		ModelAndView modelAndView = new ModelAndView(ViewRouteHelper.POSTPURCHASE);
		
		try 
		{
			//ACA SE ROMPE!!!
			stockService.availableStock(productId, amount); //Verificamos que el stock sea suficiente. En caso de que no levanta la excepción.
			Product product = productService.findByProductId(productId); //Obtenemos el producto a comprar.
			//AVANZAMOS
			double purchasePrice = product.getSalePrice() * amount; //Calculamos el total de la compra.
			//SEGUMOS AVANZANDO SOMOS LA MAQUINA
			Purchase purchase = new Purchase(product, purchasePrice, amount, methodOfPay); //Generamos un objeto 'Purchase' con los datos de la compra.
			
			purchaseService.insert(purchase); //Guardamos la compra en la base de datos.
			
			modelAndView.addObject("product", product);
			
			modelAndView.addObject("purchase", purchase);
		} 
		catch(Exception e) 
		{
			e.getMessage(); 
		}
		return modelAndView;
	}
}