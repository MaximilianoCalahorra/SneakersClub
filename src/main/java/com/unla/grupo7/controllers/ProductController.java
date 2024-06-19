 package com.unla.grupo7.controllers;

import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

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
	public ProductController(IProductService productService, IStockService stockService) {
		super();
		this.productService = productService;
		this.stockService = stockService;
	}
	
	//CREAMOS UN WRAPPER PARA PODER PASAR VARIAS VARIABLES DENTRO DE LA VISTA. 
	//EN ESTE CASO NECESITAMOS PASAR AL Product, AL desirableAmount Y AL minimumAmount.
	public class ProductFormWrapper {
		
		private Product product = new Product ();
		private int desirableAmount = 0;
		private int minimumAmount = 0;
		
		//GETTERS
		public Product getProduct() {
			return product;
		}
		
		public int getDesirableAmount() {
			return desirableAmount;
		}
		
		public void setDesirableAmount(int desirableAmount) {
	            this.desirableAmount = desirableAmount;
	    }
		
		public int getMinimumAmount() {
			return minimumAmount;
		}
		
		public void setMinimumAmount(int minimumAmount) {
            this.minimumAmount = minimumAmount;
		}
	
		//SETERS
		
		public void setProduct(Product product) {
			
			this.product = product;
			
		}
	}
	
	//1- CUANDO SE PETICIONA /ourShoes ENVIAMOS LA VISTA PRINCIPAL --> products/ourShoes/
	@GetMapping("/ourShoes")
	public ModelAndView ourShoes() {
		ModelAndView modelAndView = new ModelAndView(ViewRouteHelper.OURSHOES);
		List <Product> listaProducts = productService.findByEnabled(true); //Devolvemos solo los productos habilitados.
		modelAndView.addObject("listaProducts", listaProducts);
		return modelAndView;
	}
	
	
	//2- AGREGAR PRODUCTOS
	@GetMapping("/productAdd")
	public ModelAndView productAdd() {
		
		ModelAndView modelAndView = new ModelAndView(ViewRouteHelper.PRODUCT_ADD);
		
		//PASAMOS EL WRAPPER A LA VISTA
		modelAndView.addObject("productFormWrapper", new ProductFormWrapper ());
		
		return modelAndView;
	}
	
	//3- GUARDAMOS EL PRODUCTO EN LA BD
	@PostMapping("/productSave") 
	public RedirectView create(@ModelAttribute ("productFormWrapper") ProductFormWrapper wrapper) {
		
		try 
		{
			//INSERTAMOS NUESTRO PRODUCTO EN LA BD
			productService.insert(wrapper.getProduct());
			
			//INICIALIZAMOS EL STOCK
			Stock stock = new Stock (wrapper.getProduct(), wrapper.getDesirableAmount(), wrapper.getMinimumAmount());
			stock = stockService.insertOrUpdate(stock);
		} 
		catch(Exception e)
		{
			e.getMessage();
		}
		
		return new RedirectView(ViewRouteHelper.ROUTE);
	}
	
	//4- EDITAMOS EL PRODUCTO
	@GetMapping("/edit/{productId}")
	public ModelAndView edit(@PathVariable int productId) throws Exception {
		
		ModelAndView modelAndView = new ModelAndView(ViewRouteHelper.PRODUCT_EDIT);
		
		Product product = productService.findByProductId(productId);
		Stock stock = stockService.findByProduct(productId);
		
		ProductFormWrapper productFormWrapper = new ProductFormWrapper();
		
		productFormWrapper.setProduct(product);
		productFormWrapper.setDesirableAmount(stock.getDesirableAmount());
		productFormWrapper.setMinimumAmount(stock.getMinimumAmount());

		modelAndView.addObject("productFormWrapper",productFormWrapper);
		
		return modelAndView;
	}
	
	
	//5- GUARDAMOS LOS DATOS EDITADOS PROVENIENDO DEL PRODUCTFORMWRAPPER
	@PostMapping("/productUpdate") 
	public RedirectView update(@ModelAttribute ("productFormWrapper") ProductFormWrapper wrapper) {

		try 
		{
			//INSERTAMOS NUESTROS NUEVOS DATOS EN LA BD
			productService.update(wrapper.getProduct());
				
			//INICIALIZAMOS EL STOCK			
			Stock stock = stockService.findByProduct(wrapper.getProduct().getProductId());
				
			stock.setDesirableAmount(wrapper.getDesirableAmount());
			stock.setMinimumAmount(wrapper.getMinimumAmount());
			
			stockService.insertOrUpdate(stock);
		} 
			
		catch(Exception e)
		{
				e.getMessage();
		}
			
		return new RedirectView(ViewRouteHelper.ROUTE);
	}
	
	//6- REMOVER EL PRODUCTO (BAJA LOGICA)
	@GetMapping("/remove/{productId}")
	public RedirectView remove(@PathVariable int productId) throws Exception {
		
		productService.removeLogical(productId);
		
		return new RedirectView(ViewRouteHelper.ROUTE);
		
	}
	
}