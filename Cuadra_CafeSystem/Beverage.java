/**
 * Represents a drink sold at the campus cafe.
 * The price depends on the selected size.
 *
 * @author Cuadra
 * @version 1.0
 */
public class Beverage extends MenuItem {

    /** Extra charge for a medium drink, in pesos. */
    private static final double MEDIUM_CHARGE = 15;

    /** Extra charge for a large drink, in pesos. */
    private static final double LARGE_CHARGE = 25;

    /** The drink size: "S", "M", or "L". */
    private String size;

    /**
     * Creates a small beverage with default item values.
     */
    public Beverage() {
        super();
        this.size = "S";
    }

    /**
     * Creates a beverage with the given values.
     *
     * @param name      the drink name
     * @param basePrice the price of a small drink in pesos
     * @param itemCode  the menu code
     * @param size      the size: "S", "M", or "L"
     */
    public Beverage(String name, double basePrice, String itemCode, String size) {
        super(name, basePrice, itemCode);
        this.size = "S";
        setSize(size); // validated; stays "S" if the size is invalid
    }

    /**
     * Computes the price based on size.
     *
     * @return the price of this beverage in pesos
     */
    @Override
    public double computePrice() {
        // Large adds 25, Medium adds 15, Small uses the base price
        if (size.equals("L")) {
            return basePrice + LARGE_CHARGE;
        } else if (size.equals("M")) {
            return basePrice + MEDIUM_CHARGE;
        } else {
            return basePrice;
        }
    }

    /**
     * Returns the parent's description followed by the size.
     *
     * @return the description, e.g. "Iced Latte [B-01] Size: L"
     */
    @Override
    public String getDescription() {
        return super.getDescription() + " Size: " + size;
    }

    /**
     * Gets the size.
     *
     * @return "S", "M", or "L"
     */
    public String getSize() {
        return size;
    }

    /**
     * Sets the size. Only "S", "M", or "L" are accepted; otherwise the old value is kept.
     *
     * @param size the new size
     */
    public void setSize(String size) {
        // Accept only the three valid sizes (case-insensitive)
        if (size != null && (size.equalsIgnoreCase("S")
                || size.equalsIgnoreCase("M")
                || size.equalsIgnoreCase("L"))) {
            this.size = size.toUpperCase();
        } else {
            System.out.println("Error: size must be S, M, or L. Keeping " + this.size + ".");
        }
    }
}
