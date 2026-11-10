/**
 * Represents a customer of the campus cafe.
 * Customers earn loyalty points and students receive a discount.
 *
 * @author Cuadra
 * @version 1.0
 */
public class Customer {

    /** Pesos that must be spent to earn one loyalty point. */
    private static final double PESOS_PER_POINT = 50.0;

    /** The customer's name. */
    private String name;

    /** Whether the customer is a student. */
    private boolean student;

    /** The accumulated loyalty points (never negative). */
    private int loyaltyPoints;

    /**
     * Creates a guest customer who is not a student.
     */
    public Customer() {
        this("Guest", false);
    }

    /**
     * Creates a non-student customer with the given name.
     *
     * @param name the customer's name
     */
    public Customer(String name) {
        this(name, false);
    }

    /**
     * Creates a customer with the given name and student status.
     *
     * @param name    the customer's name
     * @param student true if the customer is a student
     */
    public Customer(String name, boolean student) {
        this.name = name;
        this.student = student;
        this.loyaltyPoints = 0;
    }

    /**
     * Adds loyalty points directly. Non-positive amounts are ignored.
     *
     * @param points the number of points to add
     */
    public void addPoints(int points) {
        // Only a positive number of points can be added
        if (points > 0) {
            loyaltyPoints += points;
        }
    }

    /**
     * Adds loyalty points based on money spent: 1 point for every 50 pesos.
     *
     * @param amountSpent the amount spent in pesos
     */
    public void addPoints(double amountSpent) {
        // Ignore zero or negative spending
        if (amountSpent > 0) {
            loyaltyPoints += (int) (amountSpent / PESOS_PER_POINT);
        }
    }

    /**
     * Gets the customer's name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the customer's name. Blank names are rejected.
     *
     * @param name the new name
     */
    public void setName(String name) {
        // Keep the old name if the new one is blank
        if (name != null && !name.trim().isEmpty()) {
            this.name = name;
        } else {
            System.out.println("Error: customer name cannot be empty.");
        }
    }

    /**
     * Checks whether the customer is a student.
     *
     * @return true if a student
     */
    public boolean isStudent() {
        return student;
    }

    /**
     * Sets the student status.
     *
     * @param student true if the customer is a student
     */
    public void setStudent(boolean student) {
        this.student = student;
    }

    /**
     * Gets the loyalty points.
     *
     * @return the loyalty points
     */
    public int getLoyaltyPoints() {
        return loyaltyPoints;
    }

    /**
     * Sets the loyalty points. Negative values are rejected.
     *
     * @param loyaltyPoints the new number of points
     */
    public void setLoyaltyPoints(int loyaltyPoints) {
        // Points can never go below zero
        if (loyaltyPoints < 0) {
            System.out.println("Error: loyalty points cannot be negative.");
        } else {
            this.loyaltyPoints = loyaltyPoints;
        }
    }
}
