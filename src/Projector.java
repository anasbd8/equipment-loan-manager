public class Projector extends Equipment {

    private final int brightnessLumens;

    public Projector(String inventoryId, String name, int brightnessLumens) {
        super(inventoryId, name);
        if (brightnessLumens <= 0) {
            throw new IllegalArgumentException("Brightness lumens must be greater than 0");
        } else {
            this.brightnessLumens = brightnessLumens;
        }
    }

    @Override
    public String getDescription() {
        return "Projector: " + getName() + " || " + "ID: " + getInventoryId() + " || " + "Brightness: " + brightnessLumens + " Lumens";
    }

    @Override
    public int getLoanPeriodDays() {
        return 3;
    }
}
