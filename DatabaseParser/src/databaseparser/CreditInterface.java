package databaseparser;
/**
 * <p>
 * interface for Crediting accounts. implement these methods in classes that 
 * require the ability to remove from the balance.
 * </p>
 * 
 * @author sjhajeer
 * @version 1
 */
public interface CreditInterface {
        /**
     * <p>
     * is used to deduct from the balance of account. record the transactions 
     * in this method and use getDebitAmounts() and getDebitDates() to return 
     * the record data.
     * </p>
     * 
     */
    public abstract void CreditAccount();
    /**
     * returns the number of records for account
     */
    public abstract int getNumOfCredits();
    /**
     * will be used to return the Credit amount for a specific record
     */
    public abstract void getCreditAmounts(int index);
     /**
     * will be used to return the Credit date for a specific record
     */
    public abstract void getCreditDates(int index);
}
