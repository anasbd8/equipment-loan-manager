public class Laptop extends Equipment {

    private final int ramGb;

    public Laptop(String inventoryId, String name, int ramGb) {
        super(inventoryId, name);
        if (ramGb > 0 ) {
            this.ramGb = ramGb;
        }else  {
            throw new IllegalArgumentException("RAM Gb must be more  than 0.");
        }
    }

    @Override
    public String getDescription() {
        return "Laptop: "+ getName() + " ||" + " ID: " + getInventoryId() +" || "+ "RAM: " + ramGb + " GB";
    }

    @Override
    public int getLoanPeriodDays() {
        return 14;
    }
}
