package com.unla.grupo7.controllers;
import java.util.List;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.unla.grupo7.entities.Product;
import com.unla.grupo7.helpers.ViewRouteHelper;
import com.unla.grupo7.services.IProductService;

@Controller
@RequestMapping("/")
public class HomeController {
	
	//DEFINIMOS INTERFACE DEL SERVICIO DE PRODUCTO.
	//ESTO ES PARA PODER MOSTRAR LOS PRODUCTOS GUARDADOS EN LA BD EN EL INDEX DEL ADMIN.
	private IProductService productService;
		
	//CONSTRUCTOR DEL SERVICIO
	public HomeController(IProductService productService) {
		super();
		this.productService = productService;
	}

	//1- CUANDO SE PETICIONA /index ENVIAMOS LA VISTA PRINCIPAL --> home/index.
	@GetMapping("/index")
	public ModelAndView index() {
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		ModelAndView modelAndView;
		
		//SI EL USUARIO ES UN ADMIN...
		//DESPLEGAMOS TABLA CON LOS PRODUCTOS DE LA BD.
		if (user.getAuthorities().stream() .anyMatch(r -> r.getAuthority().equals("ROLE_ADMIN"))) {
		
			modelAndView = new ModelAndView(ViewRouteHelper.ADMIN_INDEX);
			
			List <Product> listaProductos = productService.findByEnabled(true);
			modelAndView.addObject("listaProducts", listaProductos);
		
		//SI ES UN USER...	
		} else {
			
			modelAndView = new ModelAndView(ViewRouteHelper.USER_INDEX);
		}
		
		//AÑADIMOS EL NOMBRE DEL USER/ADMIN PARA MOSTRARLO EN LA VISTA...
		modelAndView.addObject("username", user.getUsername());
		
		return modelAndView;
	}
	
	
	/*COSITAS EXTRAS
	@GetMapping("/hello/{name}")
	public ModelAndView helloParams2(@PathVariable("name") String name) {
		ModelAndView mV = new ModelAndView(ViewRouteHelper.HELLO);
		mV.addObject("name", name);
		return mV;
	}

	@GetMapping("/")
	public RedirectView redirectToHomeIndex() {
		return new RedirectView(ViewRouteHelper.ROUTE);
	}*/
}