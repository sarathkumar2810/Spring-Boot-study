package com.sample.sample.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.sample.sample.Grocery;
import com.sample.sample.dao.GroceryDao;


@Service
public class GroceryService {
	
	@Autowired
	GroceryDao groceryDao;

	public ResponseEntity<List<Grocery>> getAllGrocery() {
		try {
			return new ResponseEntity<>(groceryDao.findAll(), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(new ArrayList<>(), HttpStatus.OK);
	}

}
