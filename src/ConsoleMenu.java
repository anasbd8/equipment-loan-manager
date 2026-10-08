import java.util.Scanner;

public class ConsoleMenu {

    private final EquipmentManager manager;
    private final Scanner scanner;

    public ConsoleMenu(EquipmentManager manager) {
        this.manager = manager;
        this.scanner = new Scanner(System.in);
    }

    public void start() {

        boolean running = true;
        while (running) {
            System.out.println("\n Equipment Loan Manager");
            System.out.println("1. List equipment");
            System.out.println("2. Register equipment");
            System.out.println("3. Find equipment by ID");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");

            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1":
                    manager.displayEquipments();
                    break;
                case "2":
                    registerEquipment();
                    break;
                case "3":
                    findEquipment();
                    break;
                case "4":
                    running = false;
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("Invalid option. Please choose 1–4.");
                    break;
            }
        }

    }

    private void registerEquipment() {
        System.out.println("\n Select equipment type: ");
        System.out.println("1. Laptop");
        System.out.println("2. Mobile phone");
        System.out.println("3. Projector");
        System.out.print("Choice: ");
        String equipmentType = scanner.nextLine().trim();
        try {
            switch (equipmentType) {
                case "1":
                    System.out.print("Enter inventory ID:");
                    String inventoryID = scanner.nextLine().trim();
                    System.out.print("Enter equipment name: ");
                    String name = scanner.nextLine().trim();
                    System.out.print("Enter RAM in GB: ");
                    int ram = Integer.parseInt(scanner.nextLine().trim());
                    Laptop laptop = new Laptop(inventoryID, name, ram);
                    manager.addEquipment(laptop);
                    break;
            }
        } catch (NumberFormatException e) {
            System.out.println("RAM must be a whole number.");
        }catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        }


    private void findEquipment() {
        System.out.println("Enter equipment ID: ");

        try {
            String inventoryID = scanner.nextLine().trim();
            Equipment equipment = manager.findEquipmentById(inventoryID);
            if (equipment == null) {
                System.out.println("No equipment found with that ID.");
            } else {
                System.out.println(equipment.getDescription());
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}
