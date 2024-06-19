package com.unla.grupo7.controllers;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.unla.grupo7.entities.Lot;
import com.unla.grupo7.entities.Product;
import com.unla.grupo7.entities.Stock;
import com.unla.grupo7.entities.SupplyOrder;
import com.unla.grupo7.helpers.ViewRouteHelper;
import com.unla.grupo7.services.ILotService;
import com.unla.grupo7.services.IStockService;
import com.unla.grupo7.services.ISupplyOrderService;

@Controller
@RequestMapping("/lot")
public class LotController {
	
	//1- 
	private ILotService lotService;
	private ISupplyOrderService supplyOrderService; 
	private IStockService stockService;
	
	public LotController(ILotService lotService, ISupplyOrderService supplyOrderService, IStockService stockService) {
		
		this.lotService = lotService;
		this.supplyOrderService = supplyOrderService;
		this.stockService = stockService;
		
	}
	

	
	//1- Cuando se peticiona /registerLot enviamos la vista -->products/products
	@GetMapping("/registerLot/{supplyOrderId}")
	public RedirectView registerLot(@PathVariable int supplyOrderId) {
		
		if (lotService.findBySupplyOrder(supplyOrderId) == null) {
			
			try {
				//CAPTURAR SUPPLY ORDER
				SupplyOrder supplyOrder = supplyOrderService.findBySupplyOrderId(supplyOrderId);
				//INSTANCIAMOS PRODUCT
				Product product = supplyOrder.getProduct();
				//INSTANCIAMOS STOCK
				Stock stock = stockService.findByProduct(product.getProductId());
				//DEFINIMOS INITIALAMOUNT
				int initialAmount = supplyOrder.getAmount();
				//DEFINIMOS EXISTINGAMOUNT
				int existingAmount = initialAmount;
				//DEFINIMOS PURCHASEPRICE
				double purchasePrice = product.getSalePrice() * supplyOrder.getAmount();
				//INSTANCIAMOS LOT
				Lot lot = new Lot(stock, supplyOrder, initialAmount, existingAmount, purchasePrice);
				//INSERTAMOS LOT EN LA BD
				lotService.insertOrUpdate(lot);
				//DEFINIMOS TOTALSTOCK
				int totalStock = stockService.actualAmount(product.getProductId());
			
				stock.setActualAmount(totalStock);
			
				stockService.insertOrUpdate(stock);
			
			
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		RedirectView redirectView = new RedirectView (ViewRouteHelper.ROUTE);
		return redirectView;
	}
	
	@GetMapping("/lots")
	public ModelAndView lots() {
		ModelAndView modelAndView = new ModelAndView(ViewRouteHelper.LOTS);
		
		List<Lot> listadoLotes = lotService.getAllLotsInOrderByReceptionDate();
		
		modelAndView.addObject("listaLots", listadoLotes);
		
		
		return modelAndView;
	}
}