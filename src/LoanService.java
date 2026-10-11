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

        LocalDate dueDate = loanDate.plusDays(equipment.getLoanPeriodDays());
        Loan loan = new Loan(equipment.getInventoryId(), borrowerName, loanDate, dueDate);
        loans.add(loan);
        return loan;
    }

}
