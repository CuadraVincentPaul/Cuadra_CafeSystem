/**
 * Represents a rice meal that can be upsized and given extra sides.
 *
 * @author Cuadra
 * @version 1.0
 */
public class Meal extends MenuItem {

    /** Extra charge for upsizing a meal, in pesos. */
    private static final double UPSIZE_CHARGE = 20;

    /** Charge for each extra side, in pesos. */
    private static final double SIDE_CHARGE = 12;

    /** Whether the meal is upsized. */
    private boolean upsized;

    /** The number of extra sides ordered. */
    private int extraSides;

    /**
     * Creates a regular meal with no extra sides and default item values.
     */
    public Meal() {
        super();
        this.upsized = false;
        this.extraSides = 0;
    }

    /**
     * Creates a meal with the given values.
     *
     * @param name       the meal name
     * @param basePrice  the base price in pesos
     * @param itemCode   the menu code
     * @param upsized    true if the meal is upsized
     * @param extraSides the number of extra sides (negative values become 0)
     */
    public Meal(String name, double basePrice, String itemCode, boolean upsized, int extraSides) {
        super(name, basePrice, itemCode);
        this.upsized = upsized;
        this.extraSides = 0;
        setExtraSides(extraSides); // validated
    }

    /**
     * Computes the price: base price, plus 20 if upsized, plus 12 per extra side.
     *
     * @return the price of this meal in pesos
     */
    @Override
    public double computePrice() {
        double price = basePrice;
        // Upsizing adds a flat fee
        if (upsized) {
            price += UPSIZE_CHARGE;
        }
        return price + (SIDE_CHARGE * extraSides);
    }

    /**
     * Returns the parent's description with the upsize status and number of extra sides.
     *
     * @return the description, e.g. "Chicken Adobo [M-01] Upsized, 1 side"
     */
    @Override
    public String getDescription() {
        String size;
        // Show whether the meal is upsized or regular
        if (upsized) {
            size = "Upsized";
        } else {
            size = "Regular";
        }
        String sides;
        // Use the singular form for exactly one side
        if (extraSides == 1) {
            sides = "1 side";
        } else {
            sides = extraSides + " sides";
        }
        return super.getDescription() + " " + size + ", " + sides;
    }

    /**
     * Checks whether the meal is upsized.
     *
     * @return true if upsized
     */
    public boolean isUpsized() {
        return upsized;
    }

    /**
     * Sets whether the meal is upsized.
     *
     * @param upsized true to upsize the meal
     */
    public void setUpsized(boolean upsized) {
        this.upsized = upsized;
    }

    /**
     * Gets the number of extra sides.
     *
     * @return the number of extra sides
     */
    public int getExtraSides() {
        return extraSides;
    }

    /**
     * Sets the number of extra sides. Negative values are rejected.
     *
     * @param extraSides the new number of extra sides
     */
    public void setExtraSides(int extraSides) {
        // A negative number of sides is impossible, so keep the old value
        if (extraSides < 0) {
            System.out.println("Error: extra sides cannot be negative.");
        } else {
            this.extraSides = extraSides;
        }
    }
}
