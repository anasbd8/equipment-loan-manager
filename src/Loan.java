import java.time.LocalDate;

public class Loan {
    private final String borrowerName;
    private final String inventoryId;
    private final LocalDate loanDate;
    private final LocalDate dueDate;
    private LocalDate returnDate;

    public Loan(String inventoryId, String borrowerName, LocalDate loanDate, LocalDate dueDate) {
        if (inventoryId == null || inventoryId.isBlank()) {
            throw new IllegalArgumentException("InventoryId must not be blank");
        }

        if (borrowerName == null || borrowerName.isBlank()) {
            throw new IllegalArgumentException("Borrower name must not be blank");
        }

        if (loanDate == null) {
            throw new IllegalArgumentException("Loan date must not be null.");
        }

        if (dueDate == null) {
            throw new IllegalArgumentException("Due date must not be null.");
        }

        if (dueDate.isBefore(loanDate)) {
            throw new IllegalArgumentException("Due date must not be before loan date.");
        }

        this.inventoryId = inventoryId.trim();
        this.borrowerName = borrowerName.trim();
        this.loanDate = loanDate;
        this.dueDate = dueDate;
    }

    public String getInventoryId() {
        return inventoryId;
    }

    public String getBorrowerName() {
        return borrowerName;
    }

    public LocalDate getLoanDate() {
        return loanDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public boolean isActive() {
        return returnDate == null;
    }

    public void registerReturn(LocalDate date) {
        if (date == null) {
            throw new IllegalArgumentException("Return date must not be null.");
        }

        if (date.isBefore(loanDate)) {
            throw new IllegalArgumentException("Return date must not be before loan date.");
        }

        if (!isActive()) {
            throw new IllegalStateException("Loan has already been returned.");
        }

        this.returnDate = date;
    }

    public boolean isOverdue(LocalDate date) {
        if (date == null) {
            throw new IllegalArgumentException("Check date must not be null.");
        }

        return isActive() && date.isAfter(dueDate);
    }
}
