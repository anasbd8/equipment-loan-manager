import java.time.LocalDate;
import java.util.ArrayList;

public class LoanService {

    private final ArrayList<Loan> loans = new ArrayList<>();

    public Loan findActiveLoanByEquipmentId(String inventoryId) {
        if (inventoryId == null || inventoryId.isBlank()) {
            throw new IllegalArgumentException("Inventory ID must not be blank.");
        }

        String searchId = inventoryId.trim();
        for (Loan loan : loans) {
            if (loan.getInventoryId().equals(searchId) && loan.isActive()) {
                return loan;
            }
        }
        return null;
    }

    public Loan checkout(Equipment equipment, String borrowerName, LocalDate loanDate) {
        if (equipment == null) {
            throw new IllegalArgumentException("Equipment must not be null.");
        }
        if (findActiveLoanByEquipmentId(equipment.getInventoryId()) != null) {
            throw new IllegalStateException("Equipment is already on loan.");
        }
        if (loanDate == null) {
            throw new IllegalArgumentException("Loan date must not be null.");
        }

        Loanable loanable = equipment;
        LocalDate dueDate = loanDate.plusDays(loanable.getLoanPeriodDays());
        Loan loan = new Loan(equipment.getInventoryId(), borrowerName, loanDate, dueDate);
        loans.add(loan);
        return loan;
    }

    public void returnEquipment(String inventoryId, LocalDate returnDate) {
        Loan loan = findActiveLoanByEquipmentId(inventoryId);
        if (loan == null) {
            throw new IllegalStateException("Equipment has no active loan.");
        }
        loan.registerReturn(returnDate);
    }

    public ArrayList<Loan> findOverdueLoans(LocalDate date) {
        if (date == null) {
            throw new IllegalArgumentException("Check date must not be null.");
        }

        ArrayList<Loan> overdueLoans = new ArrayList<>();
        for (Loan loan : loans) {
            if (loan.isOverdue(date)) {
                overdueLoans.add(loan);
            }
        }
        return overdueLoans;
    }
}
