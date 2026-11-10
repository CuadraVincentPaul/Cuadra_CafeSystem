/**
 * Represents one order that connects a Customer to up to three MenuItem objects.
 * Items are stored in separate fields (no arrays or collections).
 *
 * @author Cuadra
 * @version 1.0
 */
public class Order {

    /** The maximum number of items per order. */
    private static final int MAX_ITEMS = 3;

    /** The discount rate given to students (10%). */
    private static final double STUDENT_DISCOUNT_RATE = 0.10;

    /** The width of a dotted receipt line. */
    private static final int LINE_WIDTH = 48;

    /** The customer who placed the order. */
    private Customer customer;

    /** The first item slot. */
    private MenuItem item1;

    /** The second item slot. */
    private MenuItem item2;

    /** The third item slot. */
    private MenuItem item3;

    /** How many item slots are filled. */
    private int itemCount;

    /** The order number. */
    private String orderId;

    /** True once the loyalty points for this order have been awarded. */
    private boolean pointsAwarded;

    /**
     * Creates an empty order for a customer.
     *
     * @param orderId  the order number
     * @param customer the customer placing the order
     */
    public Order(String orderId, Customer customer) {
        this.orderId = orderId;
        this.customer = customer;
        this.itemCount = 0;
        this.pointsAwarded = false;
    }

    /**
     * Places an item in the first empty slot.
     *
     * @param item the menu item to add
     * @return true if added; false if the order is full or the item is null
     */
    public boolean addItem(MenuItem item) {
        // Reject missing items
        if (item == null) {
            System.out.println("Error: cannot add an empty item.");
            return false;
        }
        // Reject a fourth item
        if (itemCount >= MAX_ITEMS) {
            System.out.println("Order is full. Cannot add " + item.getName()
                    + " [" + item.getItemCode() + "].");
            return false;
        }
        // Use the first empty slot
        if (item1 == null) {
            item1 = item;
        } else if (item2 == null) {
            item2 = item;
        } else {
            item3 = item;
        }
        itemCount++;
        return true;
    }

    /**
     * Sums the price of every item (polymorphic computePrice() calls).
     *
     * @return the subtotal in pesos
     */
    public double computeSubtotal() {
        double subtotal = 0;
        // Add only the slots that hold an item
        if (item1 != null) {
            subtotal += item1.computePrice();
        }
        if (item2 != null) {
            subtotal += item2.computePrice();
        }
        if (item3 != null) {
            subtotal += item3.computePrice();
        }
        return subtotal;
    }

    /**
     * Computes the discount amount: 10% of the subtotal for students, otherwise 0.
     *
     * @return the discount in pesos
     */
    public double computeDiscount() {
        // Only students receive the discount
        if (customer.isStudent()) {
            return computeSubtotal() * STUDENT_DISCOUNT_RATE;
        } else {
            return 0;
        }
    }

    /**
     * Computes the total after the student discount (if any).
     *
     * @return the total in pesos
     */
    public double computeTotal() {
        return computeSubtotal() - computeDiscount();
    }

    /**
     * Computes the total after the student discount and a voucher.
     * The total never goes below zero.
     *
     * @param voucher the voucher amount in pesos
     * @return the total in pesos (at least 0)
     */
    public double computeTotal(double voucher) {
        double total = computeTotal();
        // A negative voucher is ignored
        if (voucher > 0) {
            total -= voucher;
        }
        // The total may never be negative
        if (total < 0) {
            total = 0;
        }
        return total;
    }

    /**
     * Prints the receipt without a voucher and awards loyalty points.
     */
    public void printReceipt() {
        printReceipt(0);
    }

    /**
     * Prints the receipt with a voucher and awards loyalty points
     * (1 point per 50 pesos of the final total).
     *
     * @param voucher the voucher amount in pesos (0 for none)
     */
    public void printReceipt(double voucher) {
        String tag;
        // Mark student customers in the header
        if (customer.isStudent()) {
            tag = " (Student)";
        } else {
            tag = "";
        }
        System.out.println("Order #" + orderId + " - " + customer.getName() + tag);
        printLine(item1);
        printLine(item2);
        printLine(item3);
        System.out.println(String.format("Subtotal: %.2f", computeSubtotal()));
        // Show the discount line only for students
        if (customer.isStudent()) {
            System.out.println(String.format("Student discount (10%%): -%.2f", computeDiscount()));
        }
        // Show the voucher line only when one was used
        if (voucher > 0) {
            System.out.println(String.format("Voucher: -%.2f", voucher));
        }
        double total = computeTotal(voucher);
        System.out.println(String.format("TOTAL: %.2f", total));
        // Award points only once per order
        if (!pointsAwarded) {
            customer.addPoints(total); // overloaded: double = pesos spent
            pointsAwarded = true;
        }
        System.out.println("Loyalty points: " + customer.getLoyaltyPoints());
        System.out.println();
    }

    /**
     * Prints one dotted receipt line for an item (skips empty slots).
     *
     * @param item the item to print, may be null
     */
    private void printLine(MenuItem item) {
        // Nothing to print for an empty slot
        if (item == null) {
            return;
        }
        String text = item.getDescription() + " ";
        String price = " " + String.format("%.2f", item.computePrice());
        StringBuilder line = new StringBuilder(text);
        // Fill the gap with dots so prices line up
        while (line.length() + price.length() < LINE_WIDTH) {
            line.append('.');
        }
        System.out.println(line + price);
    }

    /**
     * Gets the number of items in the order.
     *
     * @return the item count
     */
    public int getItemCount() {
        return itemCount;
    }

    /**
     * Gets the order number.
     *
     * @return the order id
     */
    public String getOrderId() {
        return orderId;
    }

    /**
     * Sets the order number. Blank ids are rejected.
     *
     * @param orderId the new order id
     */
    public void setOrderId(String orderId) {
        // Keep the old id if the new one is blank
        if (orderId != null && !orderId.trim().isEmpty()) {
            this.orderId = orderId;
        } else {
            System.out.println("Error: order id cannot be empty.");
        }
    }

    /**
     * Gets the customer.
     *
     * @return the customer
     */
    public Customer getCustomer() {
        return customer;
    }

    /**
     * Sets the customer. A null customer is rejected.
     *
     * @param customer the new customer
     */
    public void setCustomer(Customer customer) {
        // An order must always belong to someone
        if (customer != null) {
            this.customer = customer;
        } else {
            System.out.println("Error: customer cannot be null.");
        }
    }
}
