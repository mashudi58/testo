package com.demo.bvk.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Data;

@Entity
@Data
public class Cart implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long cartId;

	private Integer totalHarga;
	
	@OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true) 
	private List<Item> items;	

	private Integer customerName;

}
