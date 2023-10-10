import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        String csvFile = "C:\\Users\\gokat\\IdeaProjects\\MVP-managment-system\\src\\Items.csv";


        Map<String, Boolean> dataMap = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                // Split the CSV line into columns
                String[] columns = line.split(",");

                // Assuming there are two columns in your CSV
                if (columns.length == 2) {
                    // Add data to the HashMap
                    String key = columns[0].trim();
                    // Convert the second column to a boolean
                    boolean value = Boolean.parseBoolean(columns[1].trim());
                    dataMap.put(key, value);
                } else {
                    System.out.println("Skipping invalid CSV line: " + line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        menu();

        int option;
        do {
            option = sc.nextInt();


            switch (option) {
                case 1:
                    System.out.println("What item do you want to checkout: ");

                    try {
                        String checkout = sc.nextLine();  // Use nextLine() for string input

                        if (!(checkout.length() < 10)) {
                            System.out.println(checkout);

                            if (dataMap.containsKey(checkout)) {
                                if (dataMap.get(checkout)) {
                                    // Item is available, mark it as checked out
                                    dataMap.replace(checkout, false);
                                    System.out.println("Item checked out");
                                } else {
                                    // Item is already checked out
                                    System.out.println("Item has been checked out");
                                }
                            } else {
                                // Item not found in the inventory
                                System.out.println("Item not found");
                            }
                        }
                    } catch (java.util.InputMismatchException e) {
                        System.out.println("Invalid input. Please enter a valid string.");
                        // Handle the exception (you might want to clear the scanner buffer or take appropriate action)
                    } catch (Exception e) {
                        System.out.println("An unexpected error occurred: " + e.getMessage());
                        // Handle other unexpected exceptions if needed
                    }
                    break;
                case 2:

                    break;
                case 3:
                    for (Map.Entry<String, Boolean> entry : dataMap.entrySet()) {
                        System.out.println(entry.getKey() + entry.getValue());
                    }
                    break;
                case 4:
                    sc.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invaild choice try again.");
                    menu();
                    break;
            }
        }
        while (option != 4);


    }
    public static void menu(){
        System.out.println("----MENU----");
        System.out.println("1. Checkout");
        System.out.println("2. Return");
        System.out.println("3. List");
        System.out.println("4. Exit");
        System.out.print("Enter your choice: ");
    }
}



//        // Now you have the data in the HashMap, you can use it as needed
//        // For example, printing the contents
//        for (Map.Entry<String, String> entry : dataMap.entrySet()) {
//            System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
//        }