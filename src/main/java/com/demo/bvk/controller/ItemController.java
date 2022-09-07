package com.demo.bvk.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demo.bvk.model.Item;
import com.demo.bvk.model.ResponseData;
import com.demo.bvk.repository.ItemRepository;

@RestController
public class ItemController {
	
	@Autowired
	ItemRepository itemRepository;
	
	@PostMapping("/item")
	public ResponseData addItem(@RequestBody Item item){
		ResponseData response = new ResponseData();
		try {
			itemRepository.save(item);
			response.setError(false);
		}catch(Exception e) {
			response.setMessage(e.getMessage());
			response.setError(true);
		}
		return response;
	}

	@GetMapping("/item/getItem")
	public ResponseData searchItem(@Nullable @RequestParam String itemName) {
		ResponseData response = new ResponseData();
		Map map = new HashMap<>();
		try {
			List<Item> listItem = new ArrayList<>();
			if (itemName!=null) {
				listItem = itemRepository.findByItemNameContains(itemName); 
			}else {
				listItem = itemRepository.findAll(); 
			}
			map.put("items", listItem);
			response.setData(map);
			response.setError(false);
		}catch(Exception e ) {
			response.setMessage(e.getMessage());
			response.setError(true);
		}
		return response;
	}
	
	/*
	 * @GetMapping("/item/getItem") public ResponseData
	 * getAllItem(@Nullable @RequestParam String itemName) { ResponseData response =
	 * new ResponseData(); try { List<Item> data = itemRepository.findAll();
	 * response.setData((Map<String, Object>) data); response.setError(false);
	 * }catch(Exception e ) { response.setMessage(e.getMessage());
	 * response.setError(true); } return response;
	 * 
	 * }
	 */
}
