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
        }catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}
