package com.unla.grupo7.entities;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
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
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="stock")
	private Set<Lot> lots = new HashSet<>();

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
	
	
	
	
}
