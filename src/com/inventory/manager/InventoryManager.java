package com.inventory.manager;

import com.inventory.dto.Item;

import java.io.*;
import java.util.ArrayList;

public class InventoryManager {

    private ArrayList<Item> items;
    private final String FILE_NAME = "inventory.csv";

    public InventoryManager() {
        items = new ArrayList<>();
        loadFromFile();
    }

    public void addItem(String name, int quantity, double price) {
        if (quantity <= 0 || price <= 0) {
            System.out.println("Error: Quantity and price must be positive!");
            return;
        }
        Item item = new Item(name, quantity, price);
        items.add(item);
        saveToFile();
        System.out.println("Item added successfully!");
    }

    public void viewInventory() {
        if (items.isEmpty()) {
            System.out.println("Inventory is empty!");
            return;
        }
        System.out.println("\n=== INVENTORY ===");
        double totalValue = 0;
        for (Item item : items) {
            System.out.println(item);
            totalValue += item.getTotalValue();
        }
        System.out.printf("\nTotal Inventory Value: $%.2f\n", totalValue);
    }

    public void updateQuantity(String name, int newQuantity) {
        if (newQuantity <= 0) {
            System.out.println("Error: Quantity must be positive!");
            return;
        }
        for (Item item : items) {
            if (item.getName().equalsIgnoreCase(name)) {
                item.setQuantity(newQuantity);
                saveToFile();
                System.out.println("Quantity updated successfully!");
                return;
            }
        }
        System.out.println("Item not found!");
    }

    private void loadFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    String name = parts[0];
                    int quantity = Integer.parseInt(parts[1]);
                    double price = Double.parseDouble(parts[2]);
                    items.add(new Item(name, quantity, price));
                }
            }
            System.out.println("Loaded " + items.size() + " items from file.");
        } catch (FileNotFoundException e) {
            System.out.println("No existing inventory file found. Starting fresh.");
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }

    private void saveToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Item item : items) {
                writer.write(item.toCSV());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving to file: " + e.getMessage());
        }
    }
    
}