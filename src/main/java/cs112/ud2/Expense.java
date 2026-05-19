package cs112.ud2;

/**
 * Concrete child class representing a specific expense.
 * Inherits from Transaction class.
 *
 * @author Rohith Mekala
 * @version 1.0
 */
public class Expense extends Transaction {
    // CONSTANT VARIABLES
    public static final boolean DEFAULT_IS_TAX_DEDUCTIBLE = false;

    // INSTANCE VARIABLES
    private boolean isTaxDeductible;

    // CONSTRUCTORS
    /**
     * Default Constructor. Invokes parent defaults and sets deductible to false.
     */
    public Expense() {
        super();
        this.isTaxDeductible = DEFAULT_IS_TAX_DEDUCTIBLE;
    }

    /**
     * Full Constructor, validates ranges before applying arguments (validates
     * parent fields via super constructor).
     * 
     * @param description     Summary of transaction
     * @param amount          Monetary value of transaction (must be positive)
     * @param category        Category of transaction (examples: Food, Rent, etc.)
     * @param isTaxDeductible isTaxDeductible True if the expense qualifies for tax
     *                        write-offs
     */
    public Expense(String description, double amount, String category, boolean isTaxDeductible) {
        super(description, amount, category);
        this.isTaxDeductible = isTaxDeductible;
    }

    /**
     * Copy Constructor. Prevents shallow copying of object data references.
     * * @param original Expense object to duplicate
     */
    public Expense(Expense original) {
        super(original); // Passes original to parent copy constructor
        if (original == null) {
            throw new IllegalArgumentException("Cannot copy a null Expense object.");
        }
        this.isTaxDeductible = original.isTaxDeductible;
    }

    // SETTERS
    /**
     * Sets whether or not expense is tax deductible.
     * 
     * @param isTaxDeductible True if the expense qualifies for tax write-offs
     * @return Always returns true because booleans have no invalid state limits
     */
    public boolean setIsTaxDeductible(boolean isTaxDeductible) {
        this.isTaxDeductible = isTaxDeductible;
        return true;
    }

    // GETTERS
    /**
     * Get whether or not the transaction is tax deductible.
     * 
     * @return isTaxDeductible as either true or false.
     */
    public boolean getIsTaxDeductible() {
        return isTaxDeductible;
    }

    // REQUIRED METHODS
    /**
     * String of all instance variables (category, description, amount and
     * isTaxDeductible)
     * 
     * @return String containing category, description, amount and isTaxDeductible,
     *         in this format:
     *         Category: [General] "Generic description" + | Amount: 0.00$ + " | Tax
     *         Deductible: NO"
     */
    @Override
    public String toString() {
        return super.toString() + " | Tax Deductible: " + (this.isTaxDeductible ? "YES" : "NO");
    }

    /**
     * Checking for equality of Expense objects, all instance variables exactly
     * equal
     * to each other (ignoring case). Uses super to compare core transaction data.
     * 
     * @param other Expense object to compare for equality
     * 
     * @return boolean representing equality between both objects, all data is
     *         exactly equal to each other
     */
    @Override
    public boolean equals(Object other) {
        // Check if the parent class thinks the core transaction data matches
        if (!super.equals(other)) {
            return false;
        }
        // Safely cast to checking the child variable state
        Expense otherExpense = (Expense) other;
        return this.isTaxDeductible == otherExpense.isTaxDeductible;
    }
}
