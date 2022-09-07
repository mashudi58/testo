package com.demo.bvk.controller;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demo.bvk.model.Cart;
import com.demo.bvk.model.Item;
import com.demo.bvk.model.ResponseData;
import com.demo.bvk.repository.CartRepository;
import com.demo.bvk.repository.ItemRepository;

@RestController
public class OrderController {

	@Autowired
	CartRepository repository;
	
	@Autowired
	ItemRepository itemRepository;
	
	@GetMapping("/cart")
	public ResponseData testo(@RequestParam Long cartId) {
		ResponseData resp = new ResponseData();
		try {
			Cart cart = repository.findById(cartId).orElseThrow(() -> new EntityNotFoundException("Unknown Entity"));
			Map map = new HashMap<>();
			map.put("cart", cart);
			resp.setError(false);
			resp.setMessage("Sukses !");
		}catch(Exception e) {
			resp.setError(true);
			resp.setMessage("Failed !");
		}

		return resp;
	}
	
	@PostMapping("/cart/add")
	public ResponseData addToChart(@RequestBody Cart cart ) {
		ResponseData resp = new ResponseData();
		
		try {
			Map map = new HashMap<>();
			map.put("cart", cart);
			for (Item i : cart.getItems()) {
				itemRepository.findById(i.getItemId()).orElseThrow(() -> new EntityNotFoundException("Unknown Entity"));
			}
			repository.save(cart);
			resp.setData(map);
			resp.setError(false);
			resp.setMessage("Sukses !");
		}catch(Exception e) {
			resp.setError(true);
			resp.setMessage("Failed !");
		}

		return resp;
	}
	
	@PutMapping("/cart/update")
	public ResponseData editChart(@RequestBody Cart cart) {
		ResponseData resp = new ResponseData();
		Cart mychart = repository.findById(cart.getCartId()).orElse(null);
		try {
			Map map = new HashMap<>();
			map.put("cart", cart);
			for (Item i : cart.getItems()) {
				itemRepository.findById(i.getItemId()).orElseThrow(() -> new EntityNotFoundException("Unknown Entity"));
			}
			mychart.setItems(cart.getItems());
			repository.save(cart);
			resp.setData(map);
			resp.setError(false);
			resp.setMessage("Sukses !");
		}catch(Exception e) {
			resp.setError(true);
			resp.setMessage("Failed !");
		}

		return resp;
		
	}
	
	@GetMapping("/cart/totalPrice")
	public ResponseData totalPrice(@RequestBody Cart cart) {
		ResponseData resp = new ResponseData();
		Map totalPrice = new HashMap<>();
		Double sum = 0.0;
		for (Item item : cart.getItems()) {
			sum = sum + item.getItemPrice()*item.getItemQty(); 
		}
		totalPrice.put("totalPrice", sum);
		resp.setData(totalPrice);
		resp.setError(false);
		resp.setMessage("Sukses !");
		
		return resp;
	}
	
}
