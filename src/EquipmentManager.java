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

}
