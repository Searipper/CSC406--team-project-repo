package bankingsystem;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */

/**
 *
 * @author mlaunet
 */
public class CdAccount extends AbstractAccount {

    //---------------------------------
    //  Variable decleration
    //---------------------------------
    final private int accountType = 2;
    final double PENALTY = 90; //temp constant for penalty to apply to early CD withdrawal
    final int FIXEDMAT = 5; //standard maturity date if none set
    private long longEndDate; // date that CD reaches full maturity
    private long longStartDate;// date that CD is created
    Date endDate = new Date();
    Date startDate = new Date();
    Date rolloverDate = new Date();
    private double interestEarned; //variable to store interest earned
    private double interestRate = .015; //changable to whatever
    long longRolloverDate; // date the the CD will rollover once it has reached maturity, should be ten days standard

    //---------------------------------
    //  Constructor
    //---------------------------------
    /**
     * most specific constructor for a CD account, requiring the most parameters
     *
     * @param customerID is the SSN or ID of a customer
     * @param accountNum is the account number of the savings account to create
     * @param balance is the initial balance of the creating account
     * @param accountFlag is the initial flag of an account; this should ideally
     * be set to 0 as any account being flagged indicates a problem
     * @param interest is the interest you personally specify for this account
     * @param maturity is a int which will indicate that amount of years it will
     * take for the CD to fully mature (IE: 5 = 5 years from current date; -5 =
     * 5 years BACK TO THE FUTURE!)
     */
    public CdAccount(int customerID, int accountNum, double balance, int accountFlag, double interest, int maturity) { //creation of CD requires: initial deposit, interest applied, and end date of CD

        super(customerID, accountNum, balance, accountFlag);
        interestRate = interest;
        interestEarned = 0;
        Calendar ed = Calendar.getInstance(); //create a calendar object to set today's date for account creation
        startDate.setTime(ed.getTime().getTime());
        longStartDate = ed.getTime().getTime();
        ed.add(ed.YEAR, maturity);    //sets maturity date
        longEndDate = ed.getTime().getTime();
        endDate.setTime(longEndDate);
        super.setAccountType(accountType);
    }

    /**
     * less specific constructor for a CD account, not requiring the interest
     * which will automatically be set to .15
     *
     * @param customerID is the SSN or ID of a customer
     * @param accountNum is the account number of the savings account to create
     * @param balance is the initial balance of the creating account
     * @param accountFlag is the initial flag of an account; this should ideally
     * be set to 0 as any account being flagged indicates a problem
     * @param maturity is a int which will indicate that amount of years it will
     * take for the CD to fully mature (IE: 5 = 5 years from current date; -5 =
     * 5 years BACK TO THE FUTURE!)
     */
    public CdAccount(int customerID, int accountNum, double balance, int accountFlag, int maturity) { //creation of CD requires: initial deposit, interest applied, and end date of CD
        super(customerID, accountNum, balance, accountFlag);
        interestEarned = 0;
        Calendar ed = Calendar.getInstance();
        startDate.setTime(ed.getTime().getTime());
        longStartDate = ed.getTime().getTime();
        ed.add(ed.YEAR, maturity);
        longEndDate = ed.getTime().getTime();
        endDate.setTime(longEndDate);
        super.setAccountType(accountType);
    }

    /**
     * less specific constructor for a CD account, not requiring the maturity
     * which will automatically be set to 5 years
     *
     * @param customerID is the SSN or ID of a customer
     * @param accountNum is the account number of the savings account to create
     * @param balance is the initial balance of the creating account
     * @param accountFlag is the initial flag of an account; this should ideally
     * be set to 0 as any account being flagged indicates a problem
     * @param interest is the interest you personally specify for this account
     */
    public CdAccount(int customerID, int accountNum, double balance, int accountFlag, double interest) { //creation of CD requires: initial deposit, interest applied, and end date of CD
        super(customerID, accountNum, balance, accountFlag);
        interestEarned = 0;
        interestRate = interest;
        Calendar ed = Calendar.getInstance();
        startDate.setTime(ed.getTime().getTime());
        longStartDate = ed.getTime().getTime();
        ed.add(ed.YEAR, FIXEDMAT);
        longEndDate = ed.getTime().getTime();
        endDate.setTime(longEndDate);
        super.setAccountType(accountType);
    }

    /**
     * is the least specific constructor for a CD account, automatically setting
     * the interest to .15 and not requiring the maturity which will
     * automatically be set to 5 years
     *
     * @param customerID is the SSN or ID of a customer
     * @param accountNum is the account number of the savings account to create
     * @param balance is the initial balance of the creating account
     * @param accountFlag is the initial flag of an account; this should ideally
     * be set to 0 as any account being flagged indicates a problem
     */
    public CdAccount(int customerID, int accountNum, double balance, int accountFlag) { //creation of CD requires: initial deposit, interest applied, and end date of CD
        super(customerID, accountNum, balance, accountFlag);
        interestEarned = 0;
        Calendar ed = Calendar.getInstance();
        startDate.setTime(ed.getTime().getTime());
        longStartDate = ed.getTime().getTime();
        ed.add(ed.YEAR, FIXEDMAT);
        longEndDate = ed.getTime().getTime();
        endDate.setTime(longEndDate);
        super.setAccountType(accountType);
    }

    //-----------------------------
    //      Methods
    //-----------------------------
    /**
     * getter method to return the rollover date of a CD in milliseconds
     *
     * @return longRolloverDate
     */
    public long getLongRolloverDate() {
        return longRolloverDate;
    }

    /**
     * setter method to set the rollover date of a CD in milliseconds
     *
     * @param longRolloverDate
     */
    public void setLongRolloverDate(long longRolloverDate) {
        this.longRolloverDate = longRolloverDate;
    }

    /**
     * getter method to return the rollover date of a CD in Date() format
     *
     * @return Rollover date in Date() format
     */
    public Date getRolloverDate() {
        Calendar cr = Calendar.getInstance();
        cr.setTime(this.rolloverDate);
        return cr.getTime();
    }

    /**
     * setter method to set the rollover date of a CD in Date() format, from a
     * long (milliseconds)
     *
     * @param longRolloverDate
     */
    public void setRolloverDate(long longRolloverDate) {
        this.rolloverDate.setTime(longRolloverDate);
    }

    /**
     * getter method to return the creation date of a CD in milliseconds
     *
     * @return longStartDate
     */
    public long getLongStartDate() {
        return longStartDate;
    }

    /**
     * setter method to set the creation date of a CD in long format, from a
     * long (milliseconds)
     *
     * @param longStartDate
     */
    public void setLongStartDate(long longStartDate) {
        this.longStartDate = longStartDate;
    }

    /**
     * getter method to return the creation date of a CD in Date() format
     *
     * @return Creation date in Date() format
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * setter method to set the creation date of a CD in Date() format, from a
     * long (milliseconds)
     *
     * @param startDate
     */
    public void setStartDate(long startDate) {
        this.startDate.setTime(startDate);
    }

    /**
     * getter method to return the maturity date of a CD in milliseconds
     *
     * @return longEndDate
     */
    public long getLongEndDate() {
        return longEndDate;
    }

    /**
     * setter method to set the maturity date of a CD in milliseconds
     *
     * @param endDate
     */
    public void setLongEndDate(long endDate) {
        this.longEndDate = endDate;
    }

    /**
     * getter method to return CD account interest rate
     *
     * @return interestRate
     */
    public double getInterestRate() {
        return interestRate;
    }

    /**
     * setter method to set CD account interest rate
     *
     * @param i is the new rate you intend to set for the account
     */
    public void setInterestRate(double i) {
        interestRate = i;
    }

    /**
     * getter method to return amount earned through interest from the CD
     *
     * @return interestEarned
     */
    public double getInterestEarned() {
        return interestEarned;
    }

    /**
     * getter method to return the maturity date of a CD in Date() format
     *
     * @return Maturity date in Date() format
     */
    public Date getEndDate() {
        Calendar cr = Calendar.getInstance();
        cr.setTime(this.endDate);
        return cr.getTime();
    }

    /**
     * setter method to set the maturity date of a CD in Date() format, from a
     * long (milliseconds)
     *
     * @param longEndDate
     */
    public void setEndDate(long longEndDate) {
        this.endDate.setTime(longEndDate);
    }

    /**
     * A method to calculate the interest earned on a CD account and add it to
     * the current balance. Will also store the amount of interest earned into
     * the interestEarned variable. It will return the amount of interest
     * earned, but not the updated balance.
     *
     * @return total
     */
    public double calculateInterest() { //calculates the interest to apply to account, applies it and returns the total applied
        double total = (balance * interestRate);
        balance += total;
        interestEarned += total;
        return total;
        //do we want to consider if interest is
        //a cyclic rate aswell? (3mos, 6mos, etc,)
    }

    /**
     * getter method to return the maturity date of a CD in Date() format
     *
     * @return Maturity date in Date() format
     */
    public String checkMaturity() {
        String temp = "";
        Date currentDate = new Date();
        if (currentDate.getTime() < endDate.getTime()) {
            temp = "Account has not reached full maturity, no need to send notice";
        } else {
            temp = "Account is mature, commencing rollover!";
            this.sendRolloverNotices();
        }
        return temp;
    }

    /**
     * A method to determine if an account requires a notice to be rolled over.
     * If an account does require to be rollover over, it's flag will be set and
     * the rollover date will also be set to ten days from the date the notices
     * were sent out.
     *
     */
    public void sendRolloverNotices() {
        //Have to look up notifiable accounts to send notices to
        //and then appriopriately flag said accounts.
        //-Rollover I assume is taking balance of previous account
        //and creating a new CD, putting interestEarned into
        //users other avaliable accounts
        Date currentDate = new Date();
        if (currentDate.getTime() < endDate.getTime()) {
            System.out.println("Account has not reached full maturity, no need to send notice");
        } else {
            this.accountFlag = 1; // account flagged
            longRolloverDate = endDate.getTime() + 864000000; // milliseconds at the moment
            setRolloverDate(longRolloverDate);
            System.out.println("Account owner has ten days to take action before current CD rolls over into a new CD");
        }
    }

    /**
     * A method to close CDs based on the maturity date. If the maturity date
     * has been passed, no penalties will be incurred. Otherwise, a penalty will
     * be applied and all interest earned will be lost from the CD.
     *
     * @return temp which is end balance of the CD after penalty or after
     * successful CD closure
     */
    public double closeCD() { //closes account, requests current mm/dd/yyyy, return funds depending on maturity

        double temp = balance; //a temp variable to return the balance, since the balance will be set to zero at the end of method
        long currDate = new Date().getTime();
        if (currDate < endDate.getTime()) {
            double temppen = (PENALTY + interestEarned); //if closing before maturity, apply penalty
            temp = balance;
            balance -= temppen;
            System.out.println(temppen + " penalty for early withdrawal, you will only recieve " + balance + " instead of " + temp);
            balance = 0;
            return temp;
        } else {
            temp = balance + interestEarned;
            System.out.println("Closing fulling mature account, you will recieve " + balance + " as well as " + interestEarned + " for a total of " + temp);
            balance = 0;
            return temp;
        }
    }

    /**
     * A method to return all of an accounts credit and debit details
     */
    public void rollOverCD() {
        long currDate = new Date().getTime();

        if (currDate > rolloverDate.getTime() && this.accountFlag != 0) { //account must be past rollover date and be flagged

            balance = (balance + interestEarned); //sets new balance balance + interest earned from previous CD

            Calendar ed = Calendar.getInstance(); //create a calendar object to set today's date for account reset
            startDate.setTime(ed.getTime().getTime());//reset start time to current time
            longStartDate = ed.getTime().getTime();
            ed.add(ed.YEAR, 5);    //standard rollover will be set to five years
            longEndDate = ed.getTime().getTime(); //end date is set to five years
            endDate.setTime(longEndDate);
            rolloverDate.setTime(0); //rollover is reset to 0
            System.out.println("Account successfully rolled over, will mature on: " + getEndDate());
        } else {
            System.out.println("Account cannot roll over until: " + getRolloverDate());
        }


    }
}
