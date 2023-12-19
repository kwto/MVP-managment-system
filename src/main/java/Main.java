import java.io.*;
import java.util.*;

public class Main {
    private static final String CSV_FILE = "src/main/java/Data/Items.csv";
    private static final Map<String, String> inventory = new HashMap<>();

    public static void main(String[] args) {
        loadInventory();

        int option;
        do {
            displayMenu();
            Scanner sc = new Scanner(System.in);
            option = sc.nextInt();

            switch (option) {
                case 1:
                    checkoutItem(sc);
                    break;
                case 2:
                    returnItem(sc);
                    break;
                case 3:
                    listInventory();
                    break;
                case 4:
                    saveAndExit();
                    break;
                default:
                    System.out.println("Invalid choice, try again.");
                    break;
            }
        } while (option != 4);
    }

    private static void loadInventory() {
        try (BufferedReader br = new BufferedReader(new FileReader(CSV_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] columns = line.split(",");
                if (columns.length == 2) {
                    String key = columns[0].trim();
                    String value = columns[1].trim();
                    inventory.put(key, value);
                } else {
                    System.out.println("Skipping invalid CSV line: " + line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void checkoutItem(Scanner sc) {
        System.out.print("What item do you want to checkout: ");
        try {
            String checkout = sc.nextLine();
            checkout = sc.nextLine();

            if (inventory.containsKey(checkout)) {
                String value = inventory.get(checkout);
                if ("in".equalsIgnoreCase(value)) {
                    inventory.replace(checkout, "out");
                    System.out.println("Item checked out");
                    updateInventory();
                } else {
                    System.out.println("Item has been checked out");
                }
            } else {
                System.out.println("Item not found in the inventory");
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a valid string.");
        } catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
        }
    }

    private static void returnItem(Scanner sc) {
        System.out.print("What item do you want to return: ");
        try {
            String returnItem = sc.nextLine();
            returnItem = sc.nextLine();

            if (inventory.containsKey(returnItem)) {
                String value = inventory.get(returnItem);
                if ("out".equalsIgnoreCase(value)) {
                    inventory.replace(returnItem, "in");
                    System.out.println("Item returned");
                    updateInventory();
                } else {
                    System.out.println("Item is not checked out");
                }
            } else {
                System.out.println("Item not found in the inventory");
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a valid string.");
        } catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
        }
    }

    private static void listInventory() {
        for (Map.Entry<String, String> entry : inventory.entrySet()) {
            System.out.println(entry.getKey() + ", " + entry.getValue());
        }
    }

    private static void updateInventory() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(CSV_FILE))) {
            for (String key : inventory.keySet()) {
                writer.write(key + "," + inventory.get(key) + "\n");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void saveAndExit() {
        updateInventory();
        System.exit(0);
    }

    private static void displayMenu() {
        System.out.println("----MENU----");
        System.out.println("1. Checkout");
        System.out.println("2. Return");
        System.out.println("3. List");
        System.out.println("4. Exit");
        System.out.print("Enter your choice: ");
    }
}
