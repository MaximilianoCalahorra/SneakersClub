package com.unla.grupo7.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @NoArgsConstructor
@Table(name="stock")
public class Stock {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int stockId;

	//one to one  con producto desde stock
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id", referencedColumnName = "productId", nullable=false)
	private Product product;
	
	private int desirableAmount;
	private int minimumAmount;
	private int actualAmount;
	
	public Stock(int desirableAmount, int minimumAmount, int actualAmount) {
		
		this.desirableAmount = desirableAmount;
		this.minimumAmount = minimumAmount;
		this.actualAmount = actualAmount;
	}

	///CONSTRUCTOR STOCK
	public Stock(Product product, int desirableAmount, int minimumAmount, int actualAmount) {
		super();
		this.product = product;
		this.desirableAmount = desirableAmount;
		this.minimumAmount = minimumAmount;
		this.actualAmount = actualAmount;
	}
	
	
	
	
	
	
}
