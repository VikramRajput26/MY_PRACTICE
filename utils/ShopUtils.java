package utils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import com.shop.core.Product;

import static com.shop.core.Category.*;
import static java.time.LocalDate.*;

public interface ShopUtils {
//add a static method to return List of some products'
	public static List<Product> populateProductList() {
		// create empty ArrayList
		List<Product> list = new ArrayList<>();// size=0, init capa=10 : up casting
		// int productId, String name, String desc, int quantity, double price, Category
		// productCategory,
		// LocalDate manufactureDate
		list.add(new Product(10, "Brown_Bread", "Healthy Bread", 100, 40, BREAD, now()));
		list.add(new Product(100, "White_Bread", "Tasty Bread", 70, 35, BREAD, parse("2022-05-09")));
		list.add(new Product(50, "Marie", "Healthy Biscuits", 80, 10, BISCUITS, parse("2022-05-01")));
		list.add(new Product(40, "Monaco", "Salty Biscuits", 75, 15, BISCUITS, parse("2022-04-21")));
		list.add(new Product(60, "Sunflowe_Oil", "Healthy Oil", 50, 240, OIL, parse("2022-01-20")));
		list.add(new Product(70, "Gemini_Oil", "Vegetable Oil", 80, 220, OIL, parse("2022-01-30")));
		list.add(new Product(80, "Mango", "Seasonal Fruit", 45, 800, FRUITS, parse("2022-05-06")));
		list.add(new Product(15, "Watermelon", "Great Fruit", 60, 70, FRUITS, parse("2022-05-04")));
		return list;
	}

	
	public static Map<String,Product> populateProductMap() {
		// create empty ArrayList
		Map<String,Product> map = new HashMap<>();// size=0, init capa=10 : up casting
		// int productId, String name, String desc, int quantity, double price, Category
		// productCategory,
		// LocalDate manufactureDate
		Product p1=new Product(10, "Brown_Bread", "Healthy Bread", 100, 40, BREAD, now());
		Product p2=new Product(100, "White_Bread", "Tasty Bread", 70, 35, BREAD, parse("2022-05-09"));
		Product p3=new Product(50, "Marie", "Healthy Biscuits", 80, 10, BISCUITS, parse("2022-05-01"));
		Product p4=new Product(40, "Monaco", "Salty Biscuits", 75, 15, BISCUITS, parse("2022-04-21"));
		Product p5=new Product(60, "Sunflowe_Oil", "Healthy Oil", 50, 240, OIL, parse("2022-01-20"));
		Product p6=new Product(70, "Gemini_Oil", "Vegetable Oil", 80, 220, OIL, parse("2022-01-30"));
		Product p7=new Product(80, "Mango", "Seasonal Fruit", 45, 800, FRUITS, parse("2022-05-06"));
		Product p8=new Product(15, "Watermelon", "Great Fruit", 60, 70, FRUITS, parse("2022-05-04"));
		Product[] product= {p1,p2,p3,p4,p5,p6,p7,p8};
		Map<String,Product> hm = new HashMap<>();
		for (Product a : product)
			System.out.println(map.put(a.toString(), a));
		return map;
	
	}

	// add a static method to return Map of products from populated list of products
	public static Map<Integer, Product> populateProductMap(
			List<Product> productList) {
		Map<Integer, Product> map = new HashMap<>();// size=0, init capa=16 , load factor=.75
		//populate the map from the list based refs : using declarative(FP) style
		
//		for (Product p : productList)
//			System.out.println("Put rets " + map.put(p.getProductId(), p));
		productList.forEach(product -> 
		map.put(product.getProductId(), product) );
		System.out.println("size of the map " + map.size());
		return map;
	}

}
