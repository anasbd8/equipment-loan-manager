import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class ConsoleMenu {

    private final EquipmentManager manager;
    private final LoanService loanService;
    private final Scanner scanner;

    public ConsoleMenu(EquipmentManager manager, LoanService loanService) {
        this.manager = manager;
        this.loanService = loanService;
        this.scanner = new Scanner(System.in);
    }

    public void start() {

        boolean running = true;
        while (running) {
            System.out.println("\nEquipment Loan Manager");
            System.out.println("1. List equipment");
            System.out.println("2. Register equipment");
            System.out.println("3. Find equipment by ID");
            System.out.println("4. Exit");
            System.out.println("5. Check out equipment");
            System.out.println("6. Return equipment");
            System.out.println("7. View overdue loans");
            System.out.print("Choose an option: ");

            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1":
                    listEquipment();
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
                case "5":
                    checkoutEquipment();
                    break;
                case "6":
                    returnEquipment();
                    break;
                case "7":
                    viewOverdueLoans();
                    break;
                default:
                    System.out.println("Invalid option. Please choose 1–7.");
                    break;
            }
        }

    }

    private void listEquipment() {
        ArrayList<Equipment> equipmentList = manager.getEquipment();
        if (equipmentList.isEmpty()) {
            System.out.println("No equipment registered.");
            return;
        }

        for (Equipment equipment : equipmentList) {
            Loan loan = loanService.findActiveLoanByEquipmentId(equipment.getInventoryId());
            String status;
            if (loan == null) {
                status = "Available";
            } else {
                status = "On loan";
            }
            System.out.println(equipment.getDescription() + " | Status: " + status);
        }
    }

    private void registerEquipment() {
        System.out.println("\nSelect equipment type: ");
        System.out.println("1. Laptop");
        System.out.println("2. Mobile phone");
        System.out.println("3. Projector");
        System.out.print("Choice: ");
        String equipmentType = scanner.nextLine().trim();

        if (!equipmentType.equals("1") && !equipmentType.equals("2")
                && !equipmentType.equals("3")) {
            System.out.println("Invalid equipment type. Please choose 1–3.");
            return;
        }

        try {
            System.out.print("Enter inventory ID: ");
            String inventoryID = scanner.nextLine().trim();
            System.out.print("Enter equipment name: ");
            String name = scanner.nextLine().trim();

            Equipment equipment;
            switch (equipmentType) {
                case "1": {
                    System.out.print("Enter RAM in GB: ");
                    int ram = Integer.parseInt(scanner.nextLine().trim());
                    equipment = new Laptop(inventoryID, name, ram);
                    break;
                }
                case "2": {
                    System.out.print("Enter operating system: ");
                    String operatingSystem = scanner.nextLine().trim();
                    equipment = new MobilePhone(inventoryID, name, operatingSystem);
                    break;
                }
                case "3": {
                    System.out.print("Enter brightness in lumens: ");
                    int brightnessLumens = Integer.parseInt(scanner.nextLine().trim());
                    equipment = new Projector(inventoryID, name, brightnessLumens);
                    break;
                }
                default:
                    return;
            }

            manager.addEquipment(equipment);
            System.out.println("Equipment registered successfully.");
        } catch (NumberFormatException e) {
            System.out.println("RAM or brightness must be a whole number.");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private void checkoutEquipment() {
        try {
            System.out.print("Enter inventory ID: ");
            String inventoryId = scanner.nextLine().trim();
            Equipment equipment = manager.findEquipmentById(inventoryId);
            if (equipment == null) {
                System.out.println("No equipment found with that ID.");
                return;
            }
            System.out.print("Enter borrower name: ");
            String borrowerName = scanner.nextLine().trim();
            Loan loan = loanService.checkout(equipment, borrowerName, LocalDate.now());
            System.out.println("Equipment checked out. Due date: " + loan.getDueDate());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } catch (IllegalStateException e) {
            System.out.println(e.getMessage());
        }
    }

    private void returnEquipment() {
        try {
            System.out.print("Enter inventory ID: ");
            String inventoryId = scanner.nextLine().trim();
            loanService.returnEquipment(inventoryId, LocalDate.now());
            System.out.println("Equipment returned successfully.");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } catch (IllegalStateException e) {
            System.out.println(e.getMessage());
        }
    }

    private void viewOverdueLoans() {
        ArrayList<Loan> overdueLoans = loanService.findOverdueLoans(LocalDate.now());
        if (overdueLoans.isEmpty()) {
            System.out.println("No overdue loans.");
            return;
        }

        for (Loan loan : overdueLoans) {
            System.out.println("Inventory ID: " + loan.getInventoryId()
                    + " | Borrower name: " + loan.getBorrowerName()
                    + " | Due date: " + loan.getDueDate());
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
