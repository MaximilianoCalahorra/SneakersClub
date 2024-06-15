package com.unla.grupo7.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.unla.grupo7.helpers.ViewRouteHelper;

@Controller
@RequestMapping("/")
public class HomeController {

	//1- CUANDO SE PETICIONA /index ENVIAMOS LA VISTA PRINCIPAL --> home/index.
	@GetMapping("/index")
	public ModelAndView index() {
		ModelAndView modelAndView = new ModelAndView(ViewRouteHelper.INDEX);
		return modelAndView;
	}
	
	//2- CUANDO SE PETICIONA /stores ENVIAMOS LA VISTA CON LA UBICACION DE LAS SUCURSALES --> home/stores.
	@GetMapping("/stores")
	public ModelAndView stores() {
		ModelAndView modelAndView = new ModelAndView(ViewRouteHelper.STORES);
		return modelAndView;
	}
	
	//3- CUANDO SE PETICIONA /products ENVIAMOS LA VISTA CON TODOS LOS PRODUCTOS ---> home/products.
	@GetMapping("/products")
	public ModelAndView products() {
		ModelAndView modelAndView = new ModelAndView(ViewRouteHelper.PRODUCTS);
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
	}
	
	User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	modelAndView.addObject("username", user.getUsername());*/
	
	
}
