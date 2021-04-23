package com.ird.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ird.entities.ProductsEntity;

public interface ProductsDAO extends JpaRepository<ProductsEntity, Long> {

}
