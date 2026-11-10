/**
 * Represents a generic item on the campus cafe menu.
 * This class is abstract: only its subclasses (Beverage, Pastry, Meal)
 * can be instantiated, and each one defines its own pricing rule.
 *
 * @author Cuadra
 * @version 1.0
 */
public abstract class MenuItem {

    /** The display name of the item (visible to subclasses). */
    protected String name;

    /** The price before any size, warming, or upsize charges (visible to subclasses). */
    protected double basePrice;

    /** The unique menu code of the item, e.g. "B-01". */
    private String itemCode;

    /**
     * Creates an unnamed menu item with default values.
     */
    public MenuItem() {
        this.name = "Unnamed Item";
        this.basePrice = 0.0;
        this.itemCode = "N/A";
    }

    /**
     * Creates a menu item with the given values.
     *
     * @param name      the item name
     * @param basePrice the base price in pesos (must not be negative)
     * @param itemCode  the menu code of the item
     */
    public MenuItem(String name, double basePrice, String itemCode) {
        this.name = name;
        this.basePrice = 0.0;
        setBasePrice(basePrice); // reuse the validated setter
        this.itemCode = itemCode;
    }

    /**
     * Computes the final price of this item. Each subclass defines its own rule.
     *
     * @return the final price in pesos
     */
    public abstract double computePrice();

    /**
     * Returns a short description made of the item name and code.
     * Subclasses override this to add their own details.
     *
     * @return the description, e.g. "Iced Latte [B-01]"
     */
    public String getDescription() {
        return name + " [" + itemCode + "]";
    }

    /**
     * Gets the item name.
     *
     * @return the item name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the item name. Blank names are rejected.
     *
     * @param name the new item name
     */
    public void setName(String name) {
        // Only accept a name that has at least one visible character
        if (name != null && !name.trim().isEmpty()) {
            this.name = name;
        } else {
            System.out.println("Error: name cannot be empty.");
        }
    }

    /**
     * Gets the base price.
     *
     * @return the base price in pesos
     */
    public double getBasePrice() {
        return basePrice;
    }

    /**
     * Sets the base price. Negative values are rejected with an error message.
     *
     * @param basePrice the new base price in pesos
     */
    public void setBasePrice(double basePrice) {
        // A price below zero makes no sense, so keep the old value and report it
        if (basePrice < 0) {
            System.out.println("Error: base price cannot be negative.");
        } else {
            this.basePrice = basePrice;
        }
    }

    /**
     * Gets the item code.
     *
     * @return the item code
     */
    public String getItemCode() {
        return itemCode;
    }

    /**
     * Sets the item code. Blank codes are rejected.
     *
     * @param itemCode the new item code
     */
    public void setItemCode(String itemCode) {
        // Only accept a code that has at least one visible character
        if (itemCode != null && !itemCode.trim().isEmpty()) {
            this.itemCode = itemCode;
        } else {
            System.out.println("Error: item code cannot be empty.");
        }
    }
}
