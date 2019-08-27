package a1;

import java.util.Scanner;

import a1.A1Novice.Customer;

public class A1Adept {

	public static void main(String[] args) {
		new A1Adept();
	}
	
	public A1Adept() {
		Scanner scan = new Scanner(System.in);

		int storeItemsCount = scan.nextInt();
		
		Item[] storeItems = new Item[storeItemsCount];
		
		for (int i = 0; i < storeItemsCount; i++) {
			String name = scan.next();
			double price = scan.nextDouble();
			
			storeItems[i] = new Item(name, price);
		}
		
		int customersCount = scan.nextInt();
		
		Customer[] customers = new Customer[customersCount];
		
		for (int i = 0; i < customersCount; i++) {
			String firstName = scan.next();
			String lastName = scan.next();
			int itemsCount = scan.nextInt();
			
			customers[i] = new Customer(firstName, lastName);
			
			Item[] shoppingCart = new Item[itemsCount];
			
			for (int j = 0; j < itemsCount; j++) {
				int itemQuantity = scan.nextInt();
				String itemName = scan.next();

				double itemPrice = 0;

				for (int k = 0; k < storeItems.length; k++) {
					if (itemName.equals(storeItems[k].getName())) {
						itemPrice = storeItems[k].getPrice();
						break;
					}
				}
				
				shoppingCart[j] = new Item(itemName, itemPrice, itemQuantity);
			}
			
			customers[i].addShoppingCart(shoppingCart);
		}
		
		int biggestIndex = findMax(customers);
		int smallestIndex = findMin(customers);
		double avgCost = findAverage(customers);
		
		System.out.println("Biggest: " + 
				customers[biggestIndex].firstName + " " + 
				customers[biggestIndex].lastName + " (" + 
				String.format("%.2f", customers[biggestIndex].getTotalCost()) + ")"
				);
		System.out.println("Smallest: " + 
				customers[smallestIndex].firstName + " " + 
				customers[smallestIndex].lastName + " (" + 
				String.format("%.2f", customers[smallestIndex].getTotalCost()) + ")"
				);
		System.out.println("Average: " + String.format("%.2f", avgCost));
				
	}
	
	private int findMax(Customer[] customers) {
		int index = 0;
		double cost = 0;
		
		for (int i = 0; i < customers.length; i++) {
			if (customers[i].getTotalCost() > cost) {
				cost = customers[i].getTotalCost();
				index = i;
			}
		}
		
		return index;
	}
	
	private int findMin(Customer[] customers) {
		int index = 0;
		double cost = 1000;
		
		for (int i = 0; i < customers.length; i++) {
			if (customers[i].getTotalCost() < cost) {
				cost = customers[i].getTotalCost();
				index = i;
			}
		}
		
		return index;
	}
	
	private double findAverage(Customer[] customers) {
		double avgCost = 0;
		
		for (int i = 0; i < customers.length; i++) {
			avgCost += customers[i].getTotalCost();
		}
		
		avgCost /= customers.length;
		
		return avgCost;
	}
	
	class Item {
		private String name;
		private double price;
		private int quantity = -1;
		
		public Item(String name, double price) {
			this.name = name;
			this.price = price;
		}
		
		public Item(String name, double price, int quantity) {
			this.name = name;
			this.price = price;
			this.quantity = quantity;
		}
		
		public double getTotalPrice() {
			return price * quantity;
		}
		
		public String getName() {
			return name;
		}
		public double getPrice() {
			return price;
		}
		public int getQuantity() {
			return quantity;
		}
	}
	
	class Customer {
		private String firstName;
		private String lastName;
		private Item[] shoppingCart;
		
		public Customer(String firstName, String lastName) {
			this.firstName = firstName;
			this.lastName = lastName;
		}
		
		public void addShoppingCart(Item[] shoppingCart) {
			this.shoppingCart = shoppingCart;
		}
		
		public double getTotalCost() {
			double total = 0;
			
			for (int i = 0; i < shoppingCart.length; i++) {
				total += shoppingCart[i].getTotalPrice();
			}
			
			return total;
		}
	}
}
