import java.util.ArrayList;

public class EquipmentManager {
    private final ArrayList<Equipment> equipmentList = new ArrayList<>();

    public void addEquipment(Equipment equipment) {
        if (equipment == null) {
            throw new IllegalArgumentException("Equipment must not be null");
        }
        for (Equipment existingEquipment : equipmentList) {
            if (existingEquipment.getInventoryId().equals(equipment.getInventoryId())) {
                throw new IllegalArgumentException(
                        "Equipment with inventory ID " + equipment.getInventoryId() + " already exists"
                );
            }
        }
        equipmentList.add(equipment);
    }

    public void displayEquipments() {
        for (Equipment equipment : equipmentList) {
            System.out.println(equipment.getDescription());
        }
    }

    public Equipment findEquipmentById(String inventoryId) {
        if (inventoryId == null || inventoryId.isBlank()) {
            throw new IllegalArgumentException("Inventory ID must not be blank.");
        }
        String searchId = inventoryId.trim();
        for (Equipment equipment : equipmentList) {
            if (equipment.getInventoryId().equals(searchId)) {
                return equipment;
            }
        }
        return null;
    }
}
