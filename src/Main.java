public class Main {
    public static void main(String[] args) {

        EquipmentManager manager = new EquipmentManager();

        manager.addEquipment(new Laptop("L001", "Lenovo ThinkPad", 16));
        manager.addEquipment(new Laptop("L002", "Apple MacBook Nero", 16));
        manager.addEquipment(new Laptop("L003", "MacBook Pro M5", 32));
        manager.addEquipment(new MobilePhone("M001", "iPhone 15 pro", "IOS"));
        manager.addEquipment(new Projector("P001", "Epson EB-FH52", 4000));

        ConsoleMenu menu = new ConsoleMenu(manager);
        menu.start();

    }
}
