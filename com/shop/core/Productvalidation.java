package com.shop.core;

import static utils.ShopUtils.populateProductList;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Scanner;



import custom_exceptions.ProductHandlingException;


public class Productvalidation {




	// Add validate all input method return customer
	public static  Product validateProductInputs(int productId, String name, String desc, int quantity, double price, Category productCategory,
			LocalDate manufactureDate, Map<String, Product>  product) throws ProductHandlingException {
		
		return new Product( productId,  name, desc, quantity,  price,  productCategory,
				manufactureDate);

	}

	
	}





