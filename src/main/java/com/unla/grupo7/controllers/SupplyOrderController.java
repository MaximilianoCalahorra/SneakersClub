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
import com.unla.grupo7.entities.SupplyOrder;
import com.unla.grupo7.helpers.ViewRouteHelper;
import com.unla.grupo7.services.IProductService;
import com.unla.grupo7.services.ISupplyOrderService;

@Controller
@RequestMapping("/supplyOrder")
public class SupplyOrderController {
	
	private ISupplyOrderService supplyOrderService;
	private IProductService productService;

	public SupplyOrderController(ISupplyOrderService supplyOrderService, IProductService productService) {
		super();
		this.supplyOrderService = supplyOrderService;
		this.productService = productService;
	}
	
	//1- AGREGAR PRODUCTOS
	@GetMapping("/supplyOrderAdd/{productId}")
	public ModelAndView supplyOrderAdd(@PathVariable int productId) {
		
		ModelAndView modelAndView = new ModelAndView(ViewRouteHelper.SUPPLY_ORDER_ADD);
		SupplyOrder supplyOrder = new SupplyOrder();
		
		
		try {
			
			Product p = productService.findByProductId(productId);
			
			supplyOrder.setProduct(p);
			
			modelAndView.addObject("supplyOrder", supplyOrder);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
			
		return modelAndView;
	}
	
	///2- GUARDAR EN LA BD
	@PostMapping("/supplyOrders") 
	public String create(@ModelAttribute("supplyOrder") SupplyOrder supplyOrder )  ///recibe el codigo y lo busca el producto
	{
		//ModelAndView modelAndView = new ModelAndView(ViewRouteHelper.SUPPLY_ORDERS);
		
		//System.out.println("Product ID" + supplyOrder.getProduct().getName());
		
		try {	
			
			supplyOrderService.insert(supplyOrder);
			
		} catch(Exception e){
			
			System.out.println(e.getMessage());
			
		}
		
		//modelAndView.addObject("supplyOrder", supplyOrder);
		return "redirect:/supplyOrder/supplyOrders";
	}
	
	///3 LISTA DE 
	@GetMapping("/supplyOrders")
	public ModelAndView supplyOrders() {
		ModelAndView modelAndView = new ModelAndView(ViewRouteHelper.SUPPLY_ORDERS);
		List <SupplyOrder> listaSuppyOrdersInProcess = supplyOrderService.findByState("In process");
		List <SupplyOrder> listaSuppyOrdersDelivered = supplyOrderService.findByState("Delivered");
		modelAndView.addObject("listaSupplyOrdersInProcess", listaSuppyOrdersInProcess);
		modelAndView.addObject("listaSupplyOrdersDelivered", listaSuppyOrdersDelivered);
		return modelAndView;
	}
	
	@GetMapping("/registerDelivered/{supplyOrderId}")
	public RedirectView registerDelivered(@PathVariable int supplyOrderId) 
	{
		try
		{
			SupplyOrder supplyOrder = supplyOrderService.findBySupplyOrderId(supplyOrderId); //Obtenemos el SupplyOrder a modificar.
			supplyOrder.setState("Delivered"); //Seteamos el estado de la SupplyOrder a entregado como solicitó el administrador.
			supplyOrderService.insert(supplyOrder); //Modificamos la SupplyOrder en la base de datos.
		} 
		catch(Exception e)
		{
			e.printStackTrace();
		} 
		return new RedirectView (ViewRouteHelper.REDIRECT_SUPPLY_ORDERS); //Redireccionamos a la lista de SupplyOrder.
	}
}
