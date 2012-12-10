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
    public CdAccount(int customerID, int accountNum, double balance, int accountFlag, double interest, int maturity) { //creation of CD requires: initial deposit, interest applied, and end date of CD
        //maturity should be in 'YEAR' form. So, five years for full maturity for example: (param.., 5);
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
    public long getLongRolloverDate() {
        return longRolloverDate;
    }

    public void setLongRolloverDate(long longRolloverDate) {
        this.longRolloverDate = longRolloverDate;
    }

    public Date getRolloverDate() {
        Calendar cr = Calendar.getInstance();
        cr.setTime(this.rolloverDate);
        return cr.getTime();
    }

    public void setRolloverDate(long longRolloverDate) {
        this.rolloverDate.setTime(longRolloverDate);
    }

    public long getLongStartDate() {
        return longStartDate;
    }

    public void setLongStartDate(long longStartDate) {
        this.longStartDate = longStartDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(long startDate) {
        this.startDate.setTime(startDate);
    }

    public long getLongEndDate() {
        return longEndDate;
    }

    public void setLongEndDate(long endDate) {
        this.longEndDate = endDate;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double i) {
        interestRate = i;
    }

    public double getInterestEarned() {
        return interestEarned;
    }

    public Date getEndDate() {
        Calendar cr = Calendar.getInstance();
        cr.setTime(this.endDate);
        return cr.getTime();
    }

    public void setEndDate(long longEndDate) {
        this.endDate.setTime(longEndDate);
    }

    public double calculateInterest() { //calculates the interest to apply to account, applies it and returns the total applied
        double total = (balance * interestRate);
        balance += total;
        interestEarned += total;
        return total;
        //do we want to consider if interest is
        //a cyclic rate aswell? (3mos, 6mos, etc,)
    }

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

    public double closeCD() { //closes account, requests current mm/dd/yyyy, return funds depending on maturity

        double temp = balance; //a temp variable to return the balance, since the balance will be set to zero at the end of method
        long currDate = new Date().getTime();
        if (currDate < endDate.getTime()) {
            double temppen = (PENALTY + interestEarned); //if closing before maturity, apply penalty
            balance -= temppen;
            temp = balance;
            System.out.println(temppen + " penalty for early withdrawal, you will only recieve " + balance + " instead of " + temp);
            balance = 0;
            return temp;
        } else {
            System.out.println("Closing fulling mature account, you will recieve " + balance);
            balance = 0;
            return temp;
        }
    }
}