package com.unla.grupo7.controllers;

import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.unla.grupo7.entities.Product;
import com.unla.grupo7.entities.Stock;
import com.unla.grupo7.helpers.ViewRouteHelper;
import com.unla.grupo7.services.IProductService;
import com.unla.grupo7.services.IStockService;

@Controller
@RequestMapping("/products")
public class ProductController {
	
	//DEFINIMOS INTERFACE DEL SERVICIO DE PRODUCTO
	private IProductService productService;
	private IStockService stockService;
	
	//CONSTRUCTOR DEL SERVICIO
	public ProductController(IProductService productService) {
		super();
		this.productService = productService;
	}

	//1- AGREGAR PRODUCTOS
	@GetMapping("/productAdd")
	public ModelAndView productAdd() {
		ModelAndView modelAndView = new ModelAndView(ViewRouteHelper.PRODUCT_ADD);
		modelAndView.addObject("product", new Product ());
		return modelAndView;
	}
	
	//2- GUARDAMOS EL PRODUCTO EN LA BD
	@PostMapping("/productSave") 
	public ModelAndView create(@ModelAttribute("product") Product product, @ModelAttribute("stock") Stock stock) {
		ModelAndView modelAndView = new ModelAndView(ViewRouteHelper.PRODUCT_SAVE);
		try 
		{
			//INSERTAMOS NUESTRO PRODUCTO EN LA BD
			productService.insert(product);
			
			//INICIALIZAMOS EL STOCK
			
		} 
		catch(Exception e)
		{
			e.getMessage();
		}
		modelAndView.addObject("product", product);
		return modelAndView;
	}
	
	//4- EDITAMOS EL PRODUCTO
	@GetMapping("/edit/{productId}")
	public ModelAndView edit(@PathVariable int productId) throws Exception {
		
		ModelAndView modelAndView = new ModelAndView(ViewRouteHelper.PRODUCT_EDIT);
		
		Product product = productService.findByProductId(productId);
		modelAndView.addObject("product", product);
		
		return modelAndView;
	}
	
	
	//5- REMOVER EL PRODUCTO
	@GetMapping("/remove/{productId}")
	public ModelAndView remove(@PathVariable int productId) throws Exception {
		
		ModelAndView modelAndView = new ModelAndView(ViewRouteHelper.PRODUCT_REMOVE);
		Product product = productService.findByProductId(productId);
		productService.remove(productId);
		modelAndView.addObject("product", product);
		return modelAndView;
		
	}
	

	//1- Cuando se peticiona /ourShoes enviamos la vista --> products/ourShoes.
	@GetMapping("/ourShoes")
	public ModelAndView ourShoes() {
		ModelAndView modelAndView = new ModelAndView(ViewRouteHelper.OURSHOES);
		List <Product> listaProducts = productService.getAll();
		modelAndView.addObject("listaProducts", listaProducts);
		return modelAndView;
	}
	

	
}