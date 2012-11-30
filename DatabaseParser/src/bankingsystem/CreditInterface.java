package databaseparser;

import java.util.Date;

/**
 * <p>
 * interface for Crediting accounts. implement these methods in classes that 
 * require the ability to add to the balance.
 * </p>
 * 
 * @author sjhajeer
 * @version 1
 */
public interface CreditInterface {
        /**
     * <p>
     * is used to add to the balance of account. record the transactions 
     * in this method and use getCreditAmounts() and getCreditDates() to return 
     * the record data.
     * </p>
     * 
     */
    public abstract void CreditAccount(double amount);
    /**
     * returns the number of records for account
     */
    public abstract int getNumOfCredits();
    /**
     * will be used to return the Credit amount for a specific record
     * @param index this is the index in the list of credit amounts
     */
    public abstract double getCreditAmounts(int index);
     /**
     * will be used to return the Credit date for a specific record
     * @param index this is the index in the list of credit dates
     */
    public abstract long getCreditDates(int index);
    /**
     * adds a record into the history list. this is needed when parsing the file
     * in to get all of the credits stored
     * @param amount this is the amount of the Debit. type double
     * @param creditdate this is the date this credit was made. type Date
     */
    public abstract void addCreditRecord(double amount,long creditdate);
}
