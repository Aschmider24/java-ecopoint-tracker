import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class EcoPointsRecyclingTracker {
    private static Scanner scanner = new Scanner(System.in);
    private static Map<String, Household> households = new HashMap<>();


    private static void registerHousehold() {

        System.out.print("Enter household ID: ");
        String id = scanner.nextLine().trim();

        if (households.containsKey(id)) {
            System.out.println("Error: Household ID already exists.");
            return;
        }


        System.out.print("Enter household name: ");
        String name = scanner.nextLine().trim();

        System.out.print("Enter household address: ");
        String address = scanner.nextLine().trim();

        Household household = new Household(id, name, address);

        households.put(id, household);

        System.out.println("Household registered successfully on " + household.getJoinDate());
    }

    private static void logRecyclingEvent() {

        System.out.print("Enter household ID: ");
        String id = scanner.nextLine().trim();


        Household household = households.get(id);


        if (household == null) {

            System.out.println("Error: Household ID not found.");
            return;
        }


        RecyclingEvent.MaterialType material;

        while (true) {
            try {
                System.out.print("Enter material type (plastic/glass/metal/paper): ");
                material = RecyclingEvent.MaterialType.fromString(scanner.nextLine());
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid material. Must be plastic, glass, metal or paper.");
            }
        }

        double weight = 0.0;


        while (true) {
            try {
                System.out.print("Enter weight in kilograms: ");
                weight = Double.parseDouble(scanner.nextLine());  // Convert input to double
                if (weight <= 0){
                    throw new IllegalArgumentException();
                }
                break;
            }  catch (NumberFormatException e) {
                System.out.println("Invalid weight. Must be a positive number.");
            }  catch (IllegalArgumentException e) {
                System.out.println("Invalid weight. Must be a real number.");
            }
        }

        RecyclingEvent event = new RecyclingEvent(material, weight);

        household.addEvent(event);

        System.out.println("Recycling event logged! Points earned: " + event.getEcoPointsEarned());
    }

    private static void displayHouseholds() {

        if (households.isEmpty()) {
            System.out.println("No households registered.");
            return;
        }

        System.out.println("\nRegistered Households:");

        for (Household h : households.values()) {
            System.out.println("ID: " + h.getId() +
                    ", Name: " + h.getName() +
                    ", Address: " + h.getAddress() +
                    ", Joined: " + h.getJoinDate());
        }
    }
    // Task 6
    private static void displayHouseholdEvents() {

        System.out.print("Enter household ID: ");
        String id = scanner.nextLine().trim();

        Household household = households.get(id);

        if (household == null) {
            System.out.println("Household not found.");
            return;
        }

        System.out.println("\nRecycling Events for " + household.getName() + ":");

        if (household.getEvents().isEmpty()) {
            System.out.println("No events logged.");
        } else {
            for (RecyclingEvent e : household.getEvents()) {
                System.out.println(e);
            }

            System.out.println("Total Weight: " + household.getTotalWeight() + " kg");

            System.out.println("Total Points: " + household.getTotalPoints() + " pts");
        }
    }

    private static void generateReports() {
        if (households.isEmpty()) {
            System.out.println("No households registered.");
            return;
        }

        // ------------------------------
        // Find the household with the highest points
        // ------------------------------
        Household top = null;
        for (Household h : households.values()) {
            if (top == null || h.getTotalPoints() > top.getTotalPoints()) {
                top = h;
            }
        }

        System.out.println("\nHousehold with Highest Points:");
        System.out.println("ID: " + top.getId() +
                ", Name: " + top.getName() +
                ", Points: " + top.getTotalPoints());

        // ------------------------------
        // Calculate total community recycling weight
        // ------------------------------
        double totalWeight = 0.0;

        for (Household h : households.values()) {
            totalWeight += h.getTotalWeight();
        }

        System.out.println("Total Community Recycling Weight: " + totalWeight + " kg");
    }

    private static void saveHouseholdsToFile() {
        try {
            ObjectOutputStream out = new ObjectOutputStream(
                    new FileOutputStream("households.ser")
            );
            out.writeObject(households);

         } catch (IOException e) {
            System.out.println("Error saving data: " + e.getMessage());
        }
    }

    private static void loadHouseholdsFromFile() {
        try (
                 ObjectInputStream in = new ObjectInputStream(new FileInputStream("households.ser"))
        ) {
            households = (Map<String, Household>) in.readObject();

            System.out.println("Household data loaded.");
        } catch (FileNotFoundException e) {
            System.out.println("No saved data found. Starting fresh.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading data: " + e.getMessage());
        }
    }


    public static void main(String[] args) {
        boolean running = true;
        loadHouseholdsFromFile();
        while (running) {
            System.out.println("\n=== Eco-Points Recycling Tracker ===");
            System.out.println("1. Register Household");
            System.out.println("2. Log Recycling Event");
            System.out.println("3. Display Households");
            System.out.println("4. Display Household Recycling Events");
            System.out.println("5. Generate Reports");
            System.out.println("6. Save and Exit");
            System.out.print("Choose an option: ");

            String choice = scanner.nextLine().trim();


            switch (choice){
                case "1":
                    registerHousehold();
                    break;
                case "2":
                    logRecyclingEvent();
                    break;
                case "3":
                    displayHouseholds();
                    break;
                case "4":
                    displayHouseholdEvents();
                    break;
                case "5":
                    generateReports();
                    break;
                case "6":
                    running = false;
                    saveHouseholdsToFile();
                    System.out.println("Data saved. Goodbye!");
                    break;
                default:
                    System.out.println("Command not supported");

            }

        }
        scanner.close();
    }


}
