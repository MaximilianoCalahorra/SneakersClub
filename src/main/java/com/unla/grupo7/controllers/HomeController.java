package com.unla.grupo7.controllers;
import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.unla.grupo7.entities.Product;
import com.unla.grupo7.entities.Stock;
import com.unla.grupo7.helpers.ViewRouteHelper;
import com.unla.grupo7.services.IProductService;
import com.unla.grupo7.services.IStockService;

@Controller
@RequestMapping("/")
public class HomeController {
	
	//DEFINIMOS INTERFACE DEL SERVICIO DE PRODUCTO.
	//ESTO ES PARA PODER MOSTRAR LOS PRODUCTOS GUARDADOS EN LA BD EN EL INDEX DEL ADMIN.
	private IProductService productService;
	private IStockService stockService;
		
	//CONSTRUCTOR DEL SERVICIO
	public HomeController(IProductService productService, IStockService stockService) {
		super();
		this.productService = productService;
		this.stockService = stockService;
	}
	
	//CONSTRUIMOS UNA CLASE WRAPPER PARA PODER PASAR EL STOCK Y LOS PRODUCTOS A LA VISTA DEL ADMIN Y
	//PODER RECORRERLOS DENTRO DE LA MISMA.
	public class ProductStockWrapper {
		
		//ATRIBUTOS
		private Product product;
		private Stock stock;
		
		//CONSTRUCTOR
		public ProductStockWrapper (Product product, Stock stock) {
			this.product = product;
			this.stock = stock;
		}
				
		//GETTERS
		public Product getProduct() {
			return product;
		}
		public Stock getStock () {
			
			return stock;
		}		
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
			
			//CREAMOS UNA LISTA DEL TIPO ProductStockWrapper
			List <ProductStockWrapper> listaProductosStock = new ArrayList <ProductStockWrapper>();
			
			//OBTENEMOS LA LISTA DE PRODUCTOS
			List <Product> listaProductos = productService.getAll();
			
			for (int i = 0; i < listaProductos.size(); i ++) {
				
				Product product = listaProductos.get(i);
				
				Stock stock = null;
				
				try {
					stock = stockService.findByProduct(product.getProductId());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				//CREAMOS UN productStockWrapper Y LO CARGAMOS CON EL STOCK Y SU PRODUCTO CORRESPONDIENTE.
				ProductStockWrapper productStock = new ProductStockWrapper (product, stock);
				
				//LO AÑADIMOS A LA LISTA
				listaProductosStock.add(productStock);
			}
			
			//PASAMOS EL ProductStockWrapper A LA VISTA PARA PODER RECORRER SU LISTA DENTRO DE LA VISTA.
			modelAndView.addObject("listaProductosStock", listaProductosStock);
			
		//SI ES UN USER...	
		} else {
			
			modelAndView = new ModelAndView(ViewRouteHelper.USER_INDEX);
		}
		
		//AÑADIMOS EL NOMBRE DEL USER/ADMIN PARA MOSTRARLO EN LA VISTA...
		modelAndView.addObject("username", user.getUsername());
		
		return modelAndView;
	}
}