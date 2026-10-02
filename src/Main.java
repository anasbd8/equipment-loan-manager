import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        ArrayList<Equipment> equipmentList = new ArrayList<>();
        equipmentList.add(new Laptop("L001", "Lenovo ThinkPad", 16));
        equipmentList.add(new Laptop("L002", "Apple MacBook Nero", 16));
        equipmentList.add(new Laptop("L003", "MacBook Pro M5", 32));
        equipmentList.add(new MobilePhone("M001", "IPhone 15 pro", "IOS"));

        equipmentList.add(new Projector("P001", "Epson EB-FH52", 4000));

        for (Equipment equipment : equipmentList) {
            System.out.println(equipment.getDescription() + " || Loan period: " + equipment.getLoanPeriodDays() + " Days");
        }

        Loanable item = new Laptop("L004", "Dell Latitude", 16);
        System.out.println(item.getLoanPeriodDays() + " Days");

        Loanable item1 = new MobilePhone("M004", "Iphone 16", "IOS");
        System.out.println(item1.getLoanPeriodDays() + " Days");
    }
}
