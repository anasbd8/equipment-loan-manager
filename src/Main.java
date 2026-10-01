import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        ArrayList<Equipment> equipmentList = new ArrayList<>();
        equipmentList.add(new Laptop("L001", "Lenovo ThinkPad", 16));
        equipmentList.add(new Laptop("L002", "Apple MacBook Nero", 16));
        equipmentList.add(new Laptop("L003", "MacBook Pro M5", 32));

        for (Equipment equipment : equipmentList) {
            System.out.println(equipment.getDescription() + " || Loan period: " + equipment.getLoanPeriodDays() + " Days");
        }
    }
}
