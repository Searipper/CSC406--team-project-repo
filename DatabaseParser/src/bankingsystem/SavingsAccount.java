package bankingsystem;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author mlaunet
 */
public class SavingsAccount extends AtmCards {

    //--------------------------------------
    //  Variables
    //--------------------------------------
    final private int accountType = 1;
    double interestRate = .015; //changable to whatever
    boolean isOverdraftAcc = false; //is an overdraft protection account flag
    int accountToProtect; //if there is an account to protect, this holds the number

    //--------------------------------------
    //  Constructors
    //--------------------------------------
    public SavingsAccount(int customerID, int accountNum, double balance, int accountFlag, double interest, boolean OP) {
        super(customerID, accountNum, balance, accountFlag);
        interestRate = interest;
        isOverdraftAcc = OP;
    }

    public SavingsAccount(int customerID, int accountNum, double balance, int accountFlag, boolean OP) {
        super(customerID, accountNum, balance, accountFlag);
        isOverdraftAcc = OP;
    }

    public SavingsAccount(int customerID, int accountNum, double balance, int accountFlag) {
        super(customerID, accountNum, balance, accountFlag);
    }

    //--------------------------------------
    //  methods
    //--------------------------------------
    public double withdraw(double amount) { //deducts funds from account, returns updated balance
        balance -= amount;
        return balance;
    }

    public double deposit(double amount) { //adds funds to account, returns updated balance
        balance += amount;
        return balance;
    }

    public double calculateInterest() { //calculates the interest to apply to account, applies it and returns the total applied
        double total = (balance * interestRate);
        balance += total;
        return total;
    }

    public int getAccountToProtect() {
        return accountToProtect;
    }

    public void setAccountToProtect(int accountProtecting) {
        this.accountToProtect = accountProtecting;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double rate) {
        interestRate = rate;
    }

    public boolean isOverdraftAcc() {
        return isOverdraftAcc;
    }

    public void setOverdraftAcc(boolean set) {
        isOverdraftAcc = set;
    }

    @Override
    public void updateBalance(double balance) {
        this.balance = balance;
    }

//    public void print() {
//        System.out.println(getAccountNum() +" - user num: " + getssn());
//    }
//    public double transferFunds(account accNum, double amount) { //you'll have to specify account to pass funds to
//        balance -= amount;
//        accNum.balance += amount;        
//        return balance;
//    }
}
