package com.sportyshoes.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sportyshoes.model.Purchase;

public interface PurchaseRepository extends JpaRepository<Purchase, Integer>{

}
