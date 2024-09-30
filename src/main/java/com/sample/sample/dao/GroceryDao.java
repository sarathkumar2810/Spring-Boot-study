package com.sample.sample.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.sample.sample.Grocery;

@Repository
public interface GroceryDao extends JpaRepository<Grocery, Integer> {
	
}
