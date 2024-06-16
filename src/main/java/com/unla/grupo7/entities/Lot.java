package com.unla.grupo7.entities;

import java.time.LocalDateTime;
import java.util.Set;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @NoArgsConstructor
@Table(name="lot") //nombre de la tabla
public class Lot {
	
	@Id //id se genera automaticamente
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int lotId;
	
	//cada lote tiene su stock, clave foranea 
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="stock_id", nullable=false)
	private Stock stock;
	
	//cada lote tiene su orden, clave foranea
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "supply_order_id", referencedColumnName = "supplyOrderId", nullable=false)
	private SupplyOrder supplyOrder;
	
	@CreationTimestamp //rellena automaticamente
	private LocalDateTime receptionDate;
	
	private int initialAmount;
	
	private int existingAmount;
	
	private double purchasePrice;

	public Lot( int initialAmount, int existingAmount, double purchasePrice) {

		this.initialAmount = initialAmount;
		this.existingAmount = existingAmount;
		this.purchasePrice = purchasePrice;
	}
	
	
	

}
