package com.unla.grupo7.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.unla.grupo7.helpers.ViewRouteHelper;

@Controller
@RequestMapping("/aboutUs")
public class AboutUsController {

	//1- CUANDO SE PETICIONA /aboutUs ENVIAMOS LA VISTA --> aboutUs/aboutUs.
	@GetMapping("/aboutUs")
	public ModelAndView aboutUs() {
		ModelAndView modelAndView = new ModelAndView(ViewRouteHelper.ABOUT_US);
		return modelAndView;
	}
	
}
