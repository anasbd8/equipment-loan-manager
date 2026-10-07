# Equipment Loan Manager

## Author

Anas

## Project Idea

A Java console application for managing equipment loans at a company.
Users will be able to register, search for, check out, and return
laptops, mobile phones, and projectors, as well as view overdue loans.

## Current Status

The project is under active development.

Implemented:
- Equipment, Laptop, MobilePhone, and Projector with field validation.
- Loanable interface implemented by all three equipment types.
- A polymorphic ArrayList<Equipment> managed by EquipmentManager.
- Registration through addEquipment(), including null and duplicate ID checks.
- Exact, case-sensitive inventory ID search through findEquipmentById().
  Leading and trailing whitespace is removed; invalid IDs are rejected and
  missing IDs return null.
- ConsoleMenu with a repeated menu, equipment listing, invalid-option messages,
  and an exit option.

Not yet connected or implemented:
- Main currently lists five sample items and exits; it does not start ConsoleMenu.
- The menu's registration and search methods are empty placeholders.
- Name search, loans, returns, overdue loans, and file storage are planned.
- Equipment display still belongs to EquipmentManager; moving output to
  ConsoleMenu is a planned step toward separating presentation and business logic.

## How to Run

1. Clone or download this repository.
2. Open the project folder in IntelliJ IDEA.
3. Configure an installed JDK under File > Project Structure > Project SDK.
   The source uses String.isBlank(), which requires Java 11 or later.
4. Open src/Main.java and run its main method.

The current entry point prints three laptops, one mobile phone, and one
projector, then exits. No interactive input is requested yet.
Once Main creates ConsoleMenu with the existing manager and calls start(),
the menu below will be available.

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

The planned loan service will use a Loanable reference to calculate a due date.
Interface-reference examples have been removed from Main; practical interface
polymorphism will be integrated into the loan workflow.
This allows the same operation to handle different equipment types
through the interface.

## Supporting Classes and Responsibilities

### Loan — Planned

This class is not implemented yet. It will store information about an equipment loan:

- Equipment inventory ID.
- Borrower name.
- Loan date.
- Due date.
- Return date, which is empty until the equipment is returned.

### EquipmentManager

- Maintains the equipment collection.
- Registers equipment and checks for duplicate inventory IDs.
- Searches for equipment by inventory ID. Name search is planned.

### LoanService — Planned

This class is not implemented yet. Its planned responsibilities are:

- Maintains the loan collection.
- Checks whether equipment is available.
- Creates loans and calculates due dates.
- Registers returns.
- Finds overdue loans.

An item is considered borrowed when it has an active loan.
A separate isBorrowed field will not be stored, to avoid keeping
the same information in two places.

### ConsoleMenu

- Displays the menu in a loop.
- Reads user input.
- Calls the manager to list equipment.
- Displays invalid-option messages and an exit message.
- Contains empty registration and search methods awaiting implementation.

Business rules will remain outside the menu class.

### Main

Creates EquipmentManager, registers five fictional sample items, and lists them.
Connecting ConsoleMenu to this entry point is the next integration step.

## Current ConsoleMenu

1. List equipment — implemented through EquipmentManager.
2. Register equipment — placeholder; currently performs no action.
3. Find equipment by ID — placeholder; currently performs no action.
4. Exit — ends the menu loop.

The menu reads choices as trimmed strings and rejects unsupported values.
It is not yet launched by Main.

## Planned Full Menu

1. Register equipment.
2. List all equipment and its availability.
3. Search for equipment by name or inventory ID.
4. Check out equipment to a borrower.
5. Return equipment.
6. View overdue loans.
7. Exit.

Search terms will come from user input.
Overdue loans will be found by comparing due dates with the current date
and excluding returned loans.

## Planned Error Scenarios

The scenarios below describe the target interactive workflow. Constructor
validation, duplicate ID checks, and inventory ID search validation already
exist. Interactive numeric parsing and loan error handling are not yet implemented.

- A user enters letters, an empty answer, or an unsupported menu option.
  The application will show a helpful message and allow another attempt.

- A user registers equipment with an empty name or inventory ID.
  Validation will reject the input.

- A user enters invalid numeric specifications, such as negative RAM.
  Validation will require positive values.

- A user registers an inventory ID that already exists.
  The manager will reject the duplicate.

- A user tries to borrow equipment that is already on loan.
  The loan service will reject the request.

- A user tries to return equipment that has no active loan.
  The loan service will explain why the return cannot be registered.

- A user enters an empty borrower name.
  The loan will not be created.

The application will include specific try/catch blocks for numeric
input parsing and rejected domain operations. Error messages will
explain the problem without terminating the menu loop.

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

- Connect ConsoleMenu to Main.
- Move equipment output from EquipmentManager to ConsoleMenu.
- Implement menu registration and inventory ID search.
- Add numeric input parsing and exception handling.
- Build the planned loan workflow and use Loanable references there.