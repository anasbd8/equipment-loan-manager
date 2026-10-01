public abstract class Equipment {
    private final String inventoryId;
    private final String name;

    protected Equipment(String inventoryId, String name) {
        if (inventoryId == null || inventoryId.isBlank()) {
            throw new IllegalArgumentException("InventoryId must not be blank");
        }

        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name must not be blank");
        }

        this.inventoryId = inventoryId.trim();
        this.name = name.trim();
    }

    public String getInventoryId() {

        return inventoryId;
    }

    public String getName() {

        return name;
    }

    public abstract String getDescription();

    public abstract int getLoanPeriodDays();
}
