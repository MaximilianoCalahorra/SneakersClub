package com.unla.grupo7.controllers;
import java.util.List;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.unla.grupo7.entities.Product;
import com.unla.grupo7.helpers.ViewRouteHelper;
import com.unla.grupo7.services.IProductService;

@Controller
@RequestMapping("/products")
public class ProductController {
	
	//DEFINIMOS INTERFACE DEL SERVICIO DE PRODUCTO
	private IProductService productService;
	
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
	public ModelAndView create(@ModelAttribute("product") Product product) {
		ModelAndView modelAndView = new ModelAndView(ViewRouteHelper.PRODUCT_SAVE);
		productService.insertOrUpdate(product);
		modelAndView.addObject("product", product);
		return modelAndView;
	}
	
	//3- CUANDO SE PETICIONA /products ENVIAMOS LA VISTA CON TODOS LOS PRODUCTOS ---> products/products.
	//ENVIAMOS A LA VISTA LA LISTA DE PRODUCTOS DE LA BD.
	@GetMapping("/")
	public ModelAndView products() {
		ModelAndView modelAndView = new ModelAndView(ViewRouteHelper.PRODUCTS);
		List <Product> listaProductos = productService.getAll();
		modelAndView.addObject("listaProducts", listaProductos);
		return modelAndView;
	}
}