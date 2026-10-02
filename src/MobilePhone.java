public class MobilePhone extends Equipment {

    private final String operatingSystem;

    public MobilePhone(String inventoryId, String name, String operatingSystem) {
        super(inventoryId, name);
        if (operatingSystem == null || operatingSystem.isBlank()) {
            throw new IllegalArgumentException("Operating must not be blank");
        } else {
            this.operatingSystem = operatingSystem.trim();
        }
    }

    @Override
    public String getDescription() {
        return "Mobile phone: " + getName() + " || " + "ID: " + getInventoryId() + " || " + "OS: " + operatingSystem;
    }

    @Override
    public int getLoanPeriodDays() {
        return 7;
    }
}
