package com.unla.grupo7.controllers;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.unla.grupo7.helpers.ViewRouteHelper;

@Controller
@RequestMapping("/")
public class HomeController {

	//1- CUANDO SE PETICIONA /index ENVIAMOS LA VISTA PRINCIPAL --> home/index.
	@GetMapping("/index")
	public ModelAndView index() {
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		ModelAndView modelAndView;
		
		//SI EL USUARIO ES UN ADMIN...
		if (user.getAuthorities().stream() .anyMatch(r -> r.getAuthority().equals("ROLE_ADMIN"))) {
		
			modelAndView = new ModelAndView(ViewRouteHelper.ADMIN_INDEX);
		
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