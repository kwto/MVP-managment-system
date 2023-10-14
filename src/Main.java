import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        String csvFile = "src/Items.csv";
        File Items = new File(csvFile);

        Map<String, String> hm = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                // Split the CSV line into columns
                String[] columns = line.split(",");

                // Assuming there are two columns in your CSV
                if (columns.length == 2) {
                    // Add data to the HashMap
                    String key = columns[0].trim();
                    String value = columns[1].trim();
                    hm.put(key, value);
                } else {
                    System.out.println("Skipping invalid CSV line: " + line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        int option;
        do {
            menu();
            option = sc.nextInt();


            switch (option) {
                case 1:
                    System.out.println("What item do you want to checkout: ");
                    try {
                        String checkout = sc.nextLine();
                        checkout = sc.nextLine();

                        if (hm.containsKey(checkout)) {
                            String value = hm.get(checkout);
                            if ("in".equalsIgnoreCase(value)) {
                                // Item is available, mark it as checked out
                                hm.replace(checkout, "out");
                                System.out.println("Item checked out");
                            } else {
                                // Item is already checked out
                                System.out.println("Item has been checked out");
                            }
                        } else {
                            // Item not found in the inventory
                            System.out.println("Item not found");
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid input. Please enter a valid string.");
                        // Handle the exception (you might want to clear the scanner buffer or take appropriate action)
                    } catch (Exception e) {
                        System.out.println("An unexpected error occurred: " + e.getMessage());
                    }
                        // Handle other unexpected exceptions if needed
                    break;
                case 2:
                    System.out.println("What item do you want to return: ");
                    try {
                        String returnItem = sc.nextLine();
                        returnItem = sc.nextLine();

                        if (hm.containsKey(returnItem)) {
                            String value = hm.get(returnItem);
                            if ("out".equalsIgnoreCase(value)) {
                                // Item is checked out, mark it as returned
                                hm.replace(returnItem, "in");
                                System.out.println("Item returned");
                            } else {
                                // Item is not checked out
                                System.out.println("Item is not checked out");
                            }
                        } else {
                            // Item not found in the inventory
                            System.out.println("Item not found");
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid input. Please enter a valid string.");
                        // Handle the exception (you might want to clear the scanner buffer or take appropriate action)
                    } catch (Exception e) {
                        System.out.println("An unexpected error occurred: " + e.getMessage());
                        // Handle other unexpected exceptions if needed
                    }

                    break;
                case 3:
                    for (Map.Entry<String, String> entry : hm.entrySet()) {
                        System.out.println(entry.getKey() + ", " + entry.getValue());
                    }
                    break;
                case 4:
                    try {
                    Items.delete();
                    BufferedWriter writer = new BufferedWriter(new FileWriter("src/Items.csv"));

                        System.out.println("Updating file...");
                        for (String key : hm.keySet()) {
                            System.out.print(key + "," + hm.get(key) + "\n");
                            writer.write(key + "," + hm.get(key) + "\n");
                        }
                        System.out.println("Updated file");
                        writer.close();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }

                    break;
                case 5:
                    sc.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invaild choice try again.");
                    menu();
                    break;
            }
        }
        while (option != 5);


    }
    public static void menu(){
        System.out.println("----MENU----");
        System.out.println("1. Checkout");
        System.out.println("2. Return");
        System.out.println("3. List");
        System.out.println("4. Update");
        System.out.println("5. Exit");
        System.out.print("Enter your choice: ");
    }
}



//        // Now you have the data in the HashMap, you can use it as needed
//        // For example, printing the contents
//        for (Map.Entry<String, String> entry : dataMap.entrySet()) {
//            System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
//        }