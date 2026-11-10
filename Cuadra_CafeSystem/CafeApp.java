/**
 * Main class that drives the Campus Cafe Ordering System.
 * It creates customers, menu items, and orders, then prints the receipts.
 *
 * @author Cuadra
 * @version 1.0
 */
public class CafeApp {

    /**
     * Program entry point.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        // Customers made with different (overloaded) constructors
        Customer ana = new Customer("Ana Reyes", true);
        Customer ben = new Customer("Ben Cruz");

        // Order 1001: items made with parameterized constructors
        Beverage latte = new Beverage("Iced Latte", 95.00, "B-01", "L");
        Pastry ensaymada = new Pastry("Ensaymada", 45.00, "P-01", true);
        Meal adobo = new Meal("Chicken Adobo", 110.00, "M-01", true, 1);

        // A pastry made with the default constructor, then filled in with setters
        Pastry cookie = new Pastry();
        cookie.setName("Cookie");
        cookie.setBasePrice(35.00);
        cookie.setItemCode("P-02");

        Order order1 = new Order("1001", ana);
        order1.addItem(latte);
        order1.addItem(ensaymada);
        order1.addItem(adobo);
        order1.addItem(cookie); // fourth item: rejected by the limit check
        order1.printReceipt();

        // Order 1002: items made with the default constructors, then setters
        Beverage hotChocolate = new Beverage();
        hotChocolate.setName("Hot Chocolate");
        hotChocolate.setBasePrice(-5);  // invalid: rejected with an error message
        hotChocolate.setBasePrice(80);  // valid
        hotChocolate.setItemCode("B-02");
        hotChocolate.setSize("M");

        Meal tapa = new Meal();
        tapa.setName("Beef Tapa");
        tapa.setBasePrice(130.00);
        tapa.setItemCode("M-02");
        tapa.setUpsized(false);
        tapa.setExtraSides(0);

        Order order2 = new Order("1002", ben);
        order2.addItem(hotChocolate);
        order2.addItem(tapa);
        order2.printReceipt(20.00); // with a 20-peso voucher
    }
}
