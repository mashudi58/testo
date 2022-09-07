package com.demo.bvk.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Item implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long itemId;
	
	private String itemName;
	
	private Integer itemQty;

	private Integer itemPrice;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "cart_id", nullable = true)
	private Cart cart;

}
