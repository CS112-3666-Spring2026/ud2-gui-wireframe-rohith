/**
 * Abstract base class representing a generic financial transaction.
 * Serves as the structural foundation for Income and Expense sub-classes.
 *
 * @author Rohith Mekala
 * @version 1.0
 */
public abstract class Transaction {
    // CONSTANT VARIABLES
    public static final String DEFAULT_DESCRIPTION = "Unknown Transaction";
    public static final double DEFAULT_AMOUNT = 0.0;
    public static final String DEFAULT_CATEGORY = "General";

    // INSTANCE VARIABLES
    private String description;
    private double amount;
    private String category;

    // CONSTRUCTORS
    /**
     * Default constructor, builds default transaction object with the default
     * constant values
     */
    public Transaction() {
        this(DEFAULT_DESCRIPTION, DEFAULT_AMOUNT, DEFAULT_CATEGORY);
    }

    /**
     * Full Constructor, validates ranges before applying arguments
     * 
     * @param description Summary of transaction
     * @param amount      Monetary value of transaction (must be positive)
     * @param category    Category of transaction (examples: Food, Rent, etc.)
     */
    public Transaction(String description, double amount, String category) {
        if (!setAll(description, amount, category)) {
            throw new IllegalArgumentException("Invalid data parameters passed to Transaction constructor.");
        }
    }

    /**
     * Copy constructor to prevent shallow copying reference chains.
     * * @param original Transaction to be duplicated
     */
    public Transaction(Transaction original) {
        if (original == null) {
            throw new IllegalArgumentException("Cannot copy a null Transaction object.");
        }
        this.description = original.description.trim();
        this.amount = original.amount;
        this.category = original.category.trim();
    }

    // SETTERS
    /**
     * Sets description for transaction after checking if valid. Returns false for invalid values and returns true for valid values.
     * @param description Summary of transaction
     * @return True if description is not empty or null. False otherwise.
    */
    public boolean setDescription(String description) {
        if (description == null || description.trim().length() == 0) {
            return false;
        }
        this.description = description.trim();
        return true;
    }

    /**
     * Sets amount for transaction after checking if valid. Returns false for invalid values and returns true for valid values.
     * @param amount Monetary value of transaction (must be positive)
     * @return True if amount is not negative. False otherwise.
    */
    public boolean setAmount(double amount) {
        if (amount < 0.0) {
            return false;
        }
        this.amount = amount;
        return true;
    }

    /**
     * Sets category for transaction after checking if valid. Returns false for invalid values and returns true for valid values.
     * @param category Category of transaction (examples: Food, Rent, etc.)
     * @return True if category is not empty or null. False otherwise.
    */
    public boolean setCategory(String category) {
        if (category == null || category.trim().length() == 0) {
            return false;
        }
        this.category = category.trim();
        return true;
    }

    /**
     * Sets description, amount and category for transaction after checking if valid. Returns false for invalid values and returns true for valid values.
     * @param description Summary of transaction
     * @param amount      Monetary value of transaction (must be positive)
     * @param category    Category of transaction (examples: Food, Rent, etc.)
     * @return True if all description, amount and category are valid. False otherwise.
     */
    public boolean setAll(String description, double amount, String category) {
        // Make sure that all parameters are valid, if not then return false.
        if (description == null || description.trim().length() == 0 ||
                amount < 0.0 ||
                category == null || category.trim().length() == 0) {
            return false;
        }

        // If we get past the if statement, then all values are valid and we can apply
        // them directly.
        this.description = description.trim();
        this.amount = amount;
        this.category = category.trim();
        return true;
    }

    // ACCESSORS (GETTERS)
    /**
     * Get description of transaction
     * @return description as String.
    */
    public String getDescription() {
        return this.description;
    }

    /**
     * Get amount of transaction
     * @return amount as double that is positive.
    */
    public double getAmount() {
        return this.amount;
    }

    /**
     * Get category of transaction
     * @return category as String.
    */
    public String getCategory() {
        return this.category;
    }

    // REQUIRED METHODS
    /**
	 * String of all instance variables (category, description and amount)
	 * @return String containing category, description and amount, in this format: Category: [General] "Generic description" + | Amount: 0.00$
	 */
    @Override
    public String toString() {
        return "Category: [" + this.category + "] " + this.description + " | Amount: $"
                + String.format("%.2f", this.amount);
    }

    /**
	 * Checking for equality of Transaction objects, all instance variables exactly equal
	 * to each other (ignoring case).
	 * 
	 * @param other Transaction object to compare for equality
	 * 
	 * @return boolean representing equality between both objects, all data is
	 *         exactly equal to each other
	 */
    @Override
    public boolean equals(Object other) {
        if (other == null || this.getClass() != other.getClass()) {
            return false;
        }
        Transaction otherTx = (Transaction) other;
        return this.description.equalsIgnoreCase(otherTx.description) &&
                Double.compare(this.amount, otherTx.amount) == 0 &&
                this.category.equalsIgnoreCase(otherTx.category);
    }
}