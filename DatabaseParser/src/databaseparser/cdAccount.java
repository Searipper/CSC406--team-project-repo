package databaseparser;

import java.util.Calendar;
import java.util.Date;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author mlaunet
 */
public class cdAccount extends AbstractAccount {

    final double PENALTY = 90; //temp constant for penalty to apply to early CD withdrawal
    final int FIXEDMAT = (365 * 5); //standard maturity date if none set
    private long endDate; // date that CD reaches full maturity
    private double interestEarned; //variable to store interest earned
    private double interestRate = .015; //changable to whatever

    public cdAccount(int customerID, int accountNum, double balance, int accountFlag, double interest, long maturity) { //creation of CD requires: initial deposit, interest applied, and end date of CD
        //maturity should be in 'YEAR' form. So, five years for full maturity for example: (param.., 5);
        super(customerID, accountNum, balance, accountFlag);
        interestRate = interest;
        interestEarned = 0;
        endDate = (maturity * 365);
    }

    public cdAccount(int customerID, int accountNum, double balance, int accountFlag, long maturity) { //creation of CD requires: initial deposit, interest applied, and end date of CD
        super(customerID, accountNum, balance, accountFlag);
        interestEarned = 0;
        endDate = (maturity * 365);;
    }

    public cdAccount(int customerID, int accountNum, double balance, int accountFlag) { //creation of CD requires: initial deposit, interest applied, and end date of CD
        super(customerID, accountNum, balance, accountFlag);
        interestEarned = 0;
        endDate = new Date().getTime() + FIXEDMAT;
    }

    public double calculateInterest() { //calculates the interest to apply to account, applies it and returns the total applied
        double total = (balance * interestRate);
        balance += total;
        interestEarned += total;
        return total;
        //do we want to consider if interest is
        //a cyclic rate aswell? (3mos, 6mos, etc,)
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void sendRolloverNotices() {
        //Have to look up notifiable accounts to send notices to
        //and then appriopriately flag said accounts.
        //-Rollover I assume is taking balance of previous account
        //and creating a new CD, putting interestEarned into
        //users other avaliable accounts
        System.out.println("Account owner's notified");
    }

    public void setInterestRate(double i) {
        interestRate = i;
    }

    public double getInterestEarned() {
        return interestEarned;
    }

    public double closeCD(double amount, int month, int year, int day) { //closes account, requests current mm/dd/yyyy, return funds depending on maturity
        long currDate = new Date().getTime(); //
        if (currDate < endDate) {
            balance -= (PENALTY + interestEarned);
            return balance;
        } else {
            return balance;
        }
    }

    @Override
    public void updateBalance(double balance) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
