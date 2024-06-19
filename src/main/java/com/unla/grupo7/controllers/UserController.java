package com.unla.grupo7.controllers;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.unla.grupo7.helpers.ViewRouteHelper;


@Controller
public class UserController {
	
	//1- CUANTO SE PETICIONA /login ENVIAMOS LA VISTA DE LOGUEO ---> user/login.
	//AÑADIMOS AUTENTICACION CON springSecurity
	@GetMapping("/login")
	public String login(Model model,
						@RequestParam(name="error",required=false) String error,
						@RequestParam(name="logout", required=false) String logout) {
		model.addAttribute("error", error);
		model.addAttribute("logout", logout);
		return ViewRouteHelper.USER_LOGIN;
	}
	
	//2- CUANDO SE PETICIONA /logout ENVIAMOS LA VISTA DE LOGOUT ---> user/logout. 
	@GetMapping("/logout")
	public String logout(Model model) {
		return ViewRouteHelper.USER_LOGOUT;
	}
	
	
	@GetMapping("/loginsuccess")
	public String loginCheck() {
		return "redirect:/index";
	}
}
