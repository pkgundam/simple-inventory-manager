package com.inventory;

import com.inventory.manager.InventoryManager;

import java.util.Scanner;

// Main class
public class Main {

    public static void main(String[] args) {
        InventoryManager manager = new InventoryManager();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== INVENTORY MANAGEMENT SYSTEM ===");
            System.out.println("1. Add Item");
            System.out.println("2. View Inventory");
            System.out.println("3. Update Quantity");
            System.out.println("4. Remove Item");
            System.out.println("5. Exit");
            System.out.print("Choose option: ");

            try {
                int choice = scanner.nextInt();
                scanner.nextLine(); // consume newline

                switch (choice) {
                    case 1:
                        System.out.print("Enter item name: ");
                        String name = scanner.nextLine();
                        System.out.print("Enter quantity: ");
                        int quantity = scanner.nextInt();
                        System.out.print("Enter price: ");
                        double price = scanner.nextDouble();
                        manager.addItem(name, quantity, price);
                        break;

                    case 2:
                        manager.viewInventory();
                        break;

                    case 3:
                        System.out.print("Enter item name to update: ");
                        String itemName = scanner.nextLine();
                        System.out.print("Enter new quantity: ");
                        int newQty = scanner.nextInt();
                        manager.updateQuantity(itemName, newQty);
                        break;

                    case 4:
                        System.out.print("Enter item name to remove: ");
                        String removeName = scanner.nextLine();
                        manager.removeItem(removeName);
                        break;

                    case 5:
                        System.out.println("Goodbye!");
                        scanner.close();
                        System.exit(0);
                        break;

                    default:
                        System.out.println("Invalid option!");
                }
            } catch (Exception e) {
                System.out.println("Invalid input! Please enter a valid option.");
                scanner.nextLine(); // clear invalid input
            }
        }
    }

}