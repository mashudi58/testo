package com.demo.bvk.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.demo.bvk.model.Item;

public interface ItemRepository extends JpaRepository<Item, Long>, JpaSpecificationExecutor<Item>{
	List<Item> findByItemNameContains(String itemName);
}
