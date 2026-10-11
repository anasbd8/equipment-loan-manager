# Equipment Loan Manager

## Author

Anas

## Project Idea

A Java console application for managing equipment loans at a company.
Users can register, search for, check out, and return
laptops, mobile phones, and projectors, as well as view overdue loans.

## Current Status

The core features are implemented. Final verification and documentation
are in progress.

Implemented features:

- Register laptops, mobile phones, and projectors with validated fields
  and unique inventory IDs.
- List equipment with an Available or On loan status.
- Search by exact, case-sensitive inventory ID. Leading and trailing
  whitespace is removed.
- Check out equipment to a borrower and calculate its due date using
  the Loanable interface.
- Reject checkout when the equipment already has an active loan.
- Return equipment and make it available for checkout again.
- List active overdue loans with inventory ID, borrower name, and due date.
- Handle invalid menu choices, invalid numeric input, blank required fields,
  duplicate IDs, and rejected loan operations with specific error messages.

## Data Storage and Limitations

Equipment and loans are stored in memory only. Registered equipment,
loans, and returns are lost when the application closes. Each startup
loads five sample equipment items and starts with no loans.

File storage is not implemented. Name search is not implemented;
search currently supports inventory IDs only.

## How to Run

1. Clone or download this repository.
2. Open the project folder in IntelliJ IDEA.
3. Configure an installed JDK under File > Project Structure > Project SDK.
   The source uses String.isBlank(), which requires Java 11 or later.
4. Open src/Main.java and run its main method.

The application starts an interactive console menu with three sample laptops,
one mobile phone, and one projector. Enter a menu number and follow the prompts.
Choose 4 to exit.

## Superclass

- Name: Equipment
- Common fields:
  - inventoryId: a unique identifier for each item.
  - name: the name of the equipment.
- Common methods:
  - getDescription(): returns a description of the equipment.
  - getLoanPeriodDays(): returns the standard loan period in days.

Equipment is an abstract superclass.
EquipmentManager stores the equipment in an ArrayList<Equipment>.
When the application loops through this collection, it calls
the overridden methods for each item's actual type.

## Subclasses

### Laptop

- Additional field: ramGb.
- Overrides getDescription() to include RAM capacity.
- Overrides getLoanPeriodDays() to return 14 days.

### MobilePhone

- Additional field: operatingSystem.
- Overrides getDescription() to include the operating system.
- Overrides getLoanPeriodDays() to return 7 days.

### Projector

- Additional field: brightnessLumens.
- Overrides getDescription() to include brightness.
- Overrides getLoanPeriodDays() to return 3 days.

These loan periods are initial business rules for this project
and may be adjusted during development.

## Interface

- Name: Loanable
- Method: getLoanPeriodDays()
- Implemented by: Laptop, MobilePhone, and Projector.

Equipment implements Loanable, and all three equipment types provide their
own getLoanPeriodDays() implementation. LoanService uses a Loanable reference
to calculate the due date during checkout.
This allows the same operation to handle different equipment types
through the interface.

## Supporting Classes and Responsibilities

### Loan

Stores information about an equipment loan:

- Equipment inventory ID.
- Borrower name.
- Loan date.
- Due date.
- Return date, which is empty until the equipment is returned.

### EquipmentManager

- Maintains the equipment collection.
- Registers equipment and checks for duplicate inventory IDs.
- Searches for equipment by inventory ID. Name search is planned.

### LoanService

Responsibilities:

- Maintains the loan collection.
- Checks whether equipment is available.
- Creates loans and calculates due dates.
- Registers returns.
- Finds overdue loans.

An item is considered borrowed when it has an active loan.
A separate isBorrowed field is not stored, to avoid keeping
the same information in two places.

### ConsoleMenu

- Displays the menu in a loop and reads user input.
- Calls EquipmentManager and LoanService for the requested operations.
- Displays equipment descriptions, availability, loan results, and errors.
- Handles registration, ID search, checkout, returns, and overdue loan listing.

Business rules and domain validation are handled by the domain classes,
EquipmentManager, and LoanService.

### Main

Creates EquipmentManager, registers five sample items, creates LoanService,
and starts ConsoleMenu with the same manager and service instances.

## Console Menu

1. List equipment.
2. Register equipment.
3. Find equipment by ID.
4. Exit.
5. Check out equipment.
6. Return equipment.
7. View overdue loans.

The menu reads choices as trimmed strings and rejects unsupported values.
Search IDs come from user input. Overdue loans are active loans whose due
dates are before the current date. A loan is not overdue on its due date.

## Error Handling

The interactive workflow handles the following scenarios:

- A user enters letters, an empty answer, or an unsupported menu option.
  The application shows a helpful message and allows another attempt.

- A user registers equipment with an empty name or inventory ID.
  Validation rejects the input.

- A user enters invalid numeric specifications, such as negative RAM.
  Validation requires positive values.

- A user registers an inventory ID that already exists.
  The manager rejects the duplicate.

- A user tries to borrow equipment that is already on loan.
  The loan service rejects the request.

- A user tries to return equipment that has no active loan.
  The loan service explains why the return cannot be registered.

- A user enters an empty borrower name.
  The loan is not created.

The application uses specific try/catch blocks for numeric input parsing
and rejected domain operations. Error messages explain the problem
without terminating the menu loop.

## Initial Scope

- Console interface.
- One local user operating the application.
- Data stored in memory during the first version.
- No login system, database, or graphical interface.
- File storage may be added after the core features work.

## Three-Week Work Plan

### Week 1 — Foundation

- Complete the initial project plan.
- Set up the Java project, Git, and GitHub.
- Create the equipment superclass and three subclasses.
- Define and implement the Loanable interface.
- Verify overridden methods using a polymorphic collection.
- Implement equipment registration, listing, and searching.

### Week 2 — Loans and User Interaction

- Create the Loan class and LoanService.
- Implement checkout and return operations.
- Calculate due dates and identify overdue loans.
- Connect the features to the console menu.
- Add input validation and specific exception handling.
- Check that invalid menu input does not crash the application.

### Week 3 — Verification and Documentation

- Test complete checkout and return workflows.
- Test duplicate IDs, invalid input, and rejected loans.
- Review class responsibilities and naming.
- Add file storage if time allows.
- Update this README with setup and usage instructions.
- Document design decisions and limitations.
- Prepare fictional sample data and a short project demonstration.

## Git Workflow

- Make meaningful commits throughout development.
- Use English commit messages describing actual changes.
- Reach at least 20 meaningful commits across at least 5 different days
  when working individually.
- Push progress to GitHub regularly.

## Design Rationale — To Be Completed Later

After implementation begins, explain:

- Why the responsibilities were divided between these classes.
- How inheritance and interface polymorphism are used.
- Which alternative designs were considered.
- What changed from the initial plan and why.
- How the design could support additional equipment types.

## Next Steps

- Complete final workflow and invalid-input checks.
- Complete the design rationale with reflections on the implementation.
- Review the course requirements and Git history.
- Prepare a short project demonstration.
