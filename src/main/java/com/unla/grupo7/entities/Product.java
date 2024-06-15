package com.unla.grupo7.entities;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @NoArgsConstructor
@Table(name="product") //nombre de la tabla
public class Product {
	
	@Id //id se genera automaticamente
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int productId;
	
	
	private String code;
	
	private String brand;
	
	private String name;
	
	private String description;
	
	private double cost;
	
	private double salePrice;
	
	private boolean enabled;

	public Product(String code, String brand, String name, String description, double cost, double salePrice,
			boolean enabled) {
		
		this.code = code;
		this.brand = brand;
		this.name = name;
		this.description = description;
		this.cost = cost;
		this.salePrice = salePrice;
		this.enabled = enabled;
	}
	
	
	

}
