package tester;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.Map;
import java.util.Scanner;
import custom_exceptions.ProductHandlingException;
import com.shop.core.Category;
import com.shop.core.Product;
import static utils.ShopUtils.populateProductMap;
import static com.shop.core.Productvalidation.*;

public class tester5 {

	public static void main(String[] args) {

		Map<String, Product> map = populateProductMap();

		try (Scanner sc = new Scanner(System.in)) {
			boolean exit = false;

			while (!exit) {
				System.out.println("Options:\n1. Enter product\n2. Display products\n3. Exit\n4.Enter category name and price to filter products\n5.category name and discount\n");
				System.out.println("Choose an option:");

				try {
					switch (sc.nextInt()) {
					case 1:
						System.out.println("Enter product details: productId, name, desc, quantity, price, productCategory, manufactureDate (YYYY-MM-DD)");

						int productId = sc.nextInt();
						sc.nextLine(); // Consume newline
						String name = sc.nextLine();
						String desc = sc.nextLine();
						int quantity = sc.nextInt();
						double price = sc.nextDouble();
						Category productCategory = Category.valueOf(sc.next());
						LocalDate manufactureDate = LocalDate.parse(sc.next());

						Product product = validateProductInputs(productId, name, desc, quantity, price, productCategory, manufactureDate, map);
						map.put(Integer.toString(productId), product);

						System.out.println("Product entered successfully.");
						break;

					case 2:
						System.out.println("All Products:");
						for (Map.Entry<String, Product> entry : map.entrySet()) {
							System.out.println(entry.getValue());
						}
						break;

					case 3:
						exit = true;
						break;
					case 4:
						System.out.println("Enter category name and price to filter products:");
						String categoryName = sc.next();
						double minPrice = sc.nextDouble();

						System.out.println("Products in category " + categoryName + " exceeding price " + minPrice + ":");

						map.values().stream()
						.filter(p -> p.getProductCategory().name().equalsIgnoreCase(categoryName) && p.getPrice() > minPrice)
						.map(p -> p.getName())
						.forEach(p -> System.out.println(p));
						break;
					case 5:
						System.out.println("Enter category name and discount to apply:");
						String categoryDiscountName = sc.next();
						double discount = sc.nextDouble();

						System.out.println("Products in category " + categoryDiscountName + " with applied discount of " + discount + "%:");

						map.values().stream()
						.filter(p -> p.getProductCategory().name().equalsIgnoreCase(categoryDiscountName))
						.forEach(p -> {
							double discountedPrice = p.getPrice() * (1 - discount / 100);
							System.out.println(p.getName() + " - Discounted Price: " + discountedPrice);
						});
						break;
					case 6:
						System.out.println("Enter category name to display the sum of product prices:");
						String categoryNameForSum = sc.next();

						double sum = map.values().stream()
								.filter(p -> p.getProductCategory().name().equalsIgnoreCase(categoryNameForSum))
								.mapToDouble(p -> p.getPrice())
								.sum();

						System.out.println("Sum of product prices in category " + categoryNameForSum + ": " + sum);
						break;
					case 7:
						System.out.println("Enter category name to display the average of product prices:");
						String categoryNameForAverage = sc.next();

						double average = map.values().stream()
								.filter(p -> p.getProductCategory().name().equalsIgnoreCase(categoryNameForAverage))
								.mapToDouble(p -> p.getPrice())
								.average()
								.orElse(0); // If there are no products in the category, return 0

						System.out.println("Average of product prices in category " + categoryNameForAverage + ": " + average);
						break;



					case 8:
						System.out.println("Enter category name to display sorted product names by price:");
						String categoryNameForSorting = sc.next();

						map.values().stream()
						.filter(p -> p.getProductCategory().name().equalsIgnoreCase(categoryNameForSorting))
						.sorted(Comparator.comparingDouble(Product::getPrice))
						.map(Product::getName)
						.forEach(System.out::println);
						break;
					case 9:
					    System.out.println("Enter category name and minimum price to display product names:");
					    String categoryNameForFiltering = sc.next();
					    double minPriceForFiltering = sc.nextDouble();

					    System.out.println("Product names in category " + categoryNameForFiltering + " exceeding price " + minPriceForFiltering + ":");

					    map.values().stream()
					            .filter(p -> p.getProductCategory().name().equalsIgnoreCase(categoryNameForFiltering) && p.getPrice() > minPriceForFiltering)
					            .map(p -> p.getName())
					            .forEach(p -> System.out.println(p));
					    break;
					  
					case 10:
					    System.out.println("Enter category name to display sorted product names by manufacture date and price:");
					    String categoryNameForSorting1 = sc.next();

					    map.values().stream()
					            .filter(p -> p.getProductCategory().name().equalsIgnoreCase(categoryNameForSorting1))
					            .sorted(Comparator.comparing((Product p) -> p.getManufactureDate()).thenComparing((Product p) -> p.getPrice()))
					            .map(p -> p.getName())
					            .forEach(p -> System.out.println(p));
					    break;




					default:
						System.out.println("Invalid option.");
						break;
					}
				} catch (Exception e) {
					System.out.println(e.getMessage());
					// Consume invalid input
					sc.nextLine();
				}
			}
		}
	}
}



