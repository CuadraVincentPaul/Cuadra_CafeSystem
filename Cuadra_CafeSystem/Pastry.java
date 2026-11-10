/**
 * Represents a baked item that can be warmed for an extra fee.
 *
 * @author Cuadra
 * @version 1.0
 */
public class Pastry extends MenuItem {

    /** Extra fee for warming a pastry, in pesos. */
    private static final double WARMING_FEE = 10;

    /** Whether the pastry is warmed. */
    private boolean warmed;

    /**
     * Creates a pastry that is not warmed, with default item values.
     */
    public Pastry() {
        super();
        this.warmed = false;
    }

    /**
     * Creates a pastry with the given values.
     *
     * @param name      the pastry name
     * @param basePrice the base price in pesos
     * @param itemCode  the menu code
     * @param warmed    true if the pastry is to be warmed
     */
    public Pastry(String name, double basePrice, String itemCode, boolean warmed) {
        super(name, basePrice, itemCode);
        this.warmed = warmed;
    }

    /**
     * Computes the price: the base price plus a warming fee when warmed.
     *
     * @return the price of this pastry in pesos
     */
    @Override
    public double computePrice() {
        double price = basePrice;
        // Warming costs an extra 10 pesos
        if (warmed) {
            price += WARMING_FEE;
        }
        return price;
    }

    /**
     * Returns the parent's description, adding "(Warmed)" when applicable.
     *
     * @return the description, e.g. "Ensaymada [P-01] (Warmed)"
     */
    @Override
    public String getDescription() {
        String description = super.getDescription();
        // Only mention warming when it was requested
        if (warmed) {
            description += " (Warmed)";
        }
        return description;
    }

    /**
     * Checks whether the pastry is warmed.
     *
     * @return true if warmed
     */
    public boolean isWarmed() {
        return warmed;
    }

    /**
     * Sets whether the pastry is warmed.
     *
     * @param warmed true to warm the pastry
     */
    public void setWarmed(boolean warmed) {
        this.warmed = warmed;
    }
}
