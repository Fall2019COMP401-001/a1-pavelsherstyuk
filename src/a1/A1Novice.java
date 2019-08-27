package a1;

import java.util.Scanner;

public class A1Novice {

	public static void main(String[] args) {
		new A1Novice();
	}
	
	public A1Novice() {
		Scanner scan = new Scanner(System.in);

		int customersCount = scan.nextInt();
		
		Customer[] customers = new Customer[customersCount];
		
		for (int i = 0; i < customers.length; i++) {
			String firstName = scan.next();
			String lastName = scan.next();
			int itemsAmount = scan.nextInt();
			
			customers[i] = new Customer(firstName, lastName, itemsAmount);
			Item[] items = new Item[itemsAmount];
			
			for (int j = 0; j < itemsAmount; j++) {
				int quantity = scan.nextInt();
				String name = scan.next();
				double price = scan.nextDouble();
				
				items[j] = new Item(quantity, name, price);
			}
			
			customers[i].addItems(items);
		}
		
		for (int i = 0; i < customers.length; i++) {
			System.out.println(customers[i].printOut());
		}
	}
	
	class Customer {
		private String firstName;
		private String lastName;
		private int itemsAmount;
		private Item[] items;
		
		public Customer(String firstName, String lastName, int itemsAmount) {
			this.firstName = firstName;
			this.lastName = lastName;
			this.itemsAmount = itemsAmount;
		}
		
		public void addItems(Item[] items) {
			this.items = items;
		}
		
		private double totalPrice() {
			double total = 0;
			
			for (int i = 0; i < items.length; i++) {
				total += items[i].getTotalPrice();
			}
			
			return total;
		}
		
		public String printOut() {
			return firstName.charAt(0) + ". " + 
					lastName + ": " + 
					String.format("%.2f", totalPrice());
		}
	}
	
	class Item {
		private int quantity;
		private String name;
		private double price;
		
		public Item(int quantity, String name, double price) {
			this.quantity = quantity;
			this.name = name;
			this.price = price;
		}
		
		public double getTotalPrice() {
			return price * quantity;
		}
	}
}
