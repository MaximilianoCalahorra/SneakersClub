package com.unla.grupo7.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.unla.grupo7.helpers.ViewRouteHelper;

@Controller
@RequestMapping("/stores")
public class StoresController {

	//1- Cuando se peticiona /stores enviamos la vista --> stores/stores.
	@GetMapping("/stores")
	public ModelAndView stores() {
		ModelAndView modelAndView = new ModelAndView(ViewRouteHelper.STORES);
		return modelAndView;
	}
	
}


