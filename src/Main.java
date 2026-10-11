import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {

        EquipmentManager manager = new EquipmentManager();

        manager.addEquipment(new Laptop("L001", "Lenovo ThinkPad", 16));
        manager.addEquipment(new Laptop("L002", "Apple MacBook Nero", 16));
        manager.addEquipment(new Laptop("L003", "MacBook Pro M5", 32));
        manager.addEquipment(new MobilePhone("M001", "iPhone 15 pro", "IOS"));
        manager.addEquipment(new Projector("P001", "Epson EB-FH52", 4000));

        ConsoleMenu menu = new ConsoleMenu(manager);

        LocalDate loanDate = LocalDate.now();
        Loan loan = new Loan("L001", "Anna Andersson", loanDate, loanDate.plusDays(14));
        System.out.println("Borrower: " + loan.getBorrowerName());
        System.out.println("Due date: " + loan.getDueDate());
        System.out.println("Active loan: " + loan.isActive());

        Loan loan1 = new Loan("L002, ", "Molly Gustavsson",  loanDate, loanDate.plusDays(14));
        System.out.println("Borrower: " + loan1.getBorrowerName());
        System.out.println("Due date: " + loan1.getDueDate());
        System.out.println("Active loan: " + loan1.isActive());
        System.out.println("Overdue on due date: " + loan.isOverdue(loan.getDueDate()));
        System.out.println("Overdue one day after due date: " + loan.isOverdue(loan.getDueDate().plusDays(1)));

        loan.registerReturn(loanDate);
        System.out.println("Return date: " + loan.getReturnDate());
        System.out.println("Active after return: " + loan.isActive());
        System.out.println("Overdue one day after due date after return: " + loan.isOverdue(loan.getDueDate().plusDays(1)));

        try {
            loan.registerReturn(loanDate);
            throw new AssertionError("A second return must be rejected.");
        } catch (IllegalStateException e) {
            System.out.println(e.getMessage());
        }

        menu.start();
    }
}
