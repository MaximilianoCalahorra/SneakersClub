package com.unla.grupo7.entities;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @NoArgsConstructor
@Table(name="purchase") //nombre de la tabla
public class Purchase {
	
	@Id //id se genera automaticamente
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int purchaseId;
	
	//Many to one con user
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="user_id", nullable=false)
	private User customer;
	
	// many to one con product
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="product_id", nullable=false)
	private Product product;
	
	private double purchasePrice;
	
	private int amount;
	
	private String methodOfPay;
	
	@CreationTimestamp
	private LocalDateTime dateTime;

	public Purchase(double purchasePrice, int amount, String methodOfPay) {
		
		this.purchasePrice = purchasePrice;
		this.amount = amount;
		this.methodOfPay = methodOfPay;
		
	}

	///CONSTRUCTOR COMPLETO
	public Purchase(User customer, Product product, double purchasePrice, int amount, String methodOfPay) {
		
		this.customer = customer;
		this.product = product;
		this.purchasePrice = purchasePrice;
		this.amount = amount;
		this.methodOfPay = methodOfPay;
	}
	
	
	
	
	
	
	
}
