package com.demo.bvk.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.demo.bvk.model.Cart;

public interface CartRepository extends JpaRepository<Cart, Long>, JpaSpecificationExecutor<Cart>{

}
