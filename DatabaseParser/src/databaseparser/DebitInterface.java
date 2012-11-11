package databaseparser;
/**
 * <p>
 * interface for Debiting accounts. implement these methods in classes that 
 * require the ability to add to the balance.
 * </p>
 * 
 * @author sjhajeer
 * @version 1
 */
public interface DebitInterface {
    /**
     * <p>
     * is used to add to balance of account. record the transactions in this 
     * method and use getDebitAmounts() and getDebitDates() to return the 
     * record data.
     * </p>
     * 
     */
    public abstract void DebitAccount();
    /**
     * returns the number of records for account
     */
    public abstract int getNumOfDebits();
    /**
     * will be used to return the debit amount for a specific record
     */
    public abstract void getDebitAmounts(int index);
     /**
     * will be used to return the debit date for a specific record
     */
    public abstract void getDebitDates(int index);
}
