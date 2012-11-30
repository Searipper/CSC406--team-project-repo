package databaseparser;

import java.util.Date;

/**
 * <p>
 * interface for Debiting accounts. implement these methods in classes that 
 * require the ability remove from to the balance.
 * </p>
 * 
 * @author sjhajeer
 * @version 1
 */
public interface DebitInterface {
    /**
     * <p>
     * is used to remove from balance of account. record the transactions in this 
     * method and use getDebitAmounts() and getDebitDates() to return the 
     * record data.
     * </p>
     * 
     */
    public abstract void DebitAccount(double amount);
    /**
     * returns the number of records for account
     */
    public abstract int getNumOfDebits();
    /**
     * will be used to return the debit amount for a specific record
     * @param index this is the index in the list of debit amounts
     */
    public abstract double getDebitAmounts(int index);
     /**
     * will be used to return the debit date for a specific record
     * @param index this is the index in the list of debit dates
     */
    public abstract long getDebitDates(int index);
        /**
     * adds a record into the history list. this is needed when parsing the file
     * in to get all of the credits stored
     * @param amount this is the amount of the Debit. type double
     * @param creditdate this is the date this Debit was made. type Date
     */
    public abstract void addDebititRecord(double amount,long creditdate);
}
