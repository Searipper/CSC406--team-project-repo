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
    /**
     * most specific constructor for a savings account, requiring the most
     * parameters
     *
     * @param customerID is the SSN or ID of a customer
     * @param accountNum is the account number of the savings account to create
     * @param balance is the initial balance of the creating account
     * @param accountFlag is the initial flag of an account; this should ideally
     * be set to 0 as any account being flagged indicates a problem
     * @param interest is the interest you personally specify for this account
     * @param OP is a boolean as to whether or not this savings account will
     * become an overdraft protection account
     */
    public SavingsAccount(int customerID, int accountNum, double balance, int accountFlag, double interest, boolean OP) {
        super(customerID, accountNum, balance, accountFlag);
        interestRate = interest;
        isOverdraftAcc = OP;
        super.setAccountType(accountType);
    }

    /**
     * less specific constructor for a savings account, automatically setting
     * the interest to .15
     *
     * @param customerID is the SSN or ID of a customer
     * @param accountNum is the account number of the savings account to create
     * @param balance is the initial balance of the creating account
     * @param accountFlag is the initial flag of an account; this should ideally
     * be set to 0 as any account being flagged indicates a problem
     * @param OP is a boolean as to whether or not this savings account will
     * become an overdraft protection account
     */
    public SavingsAccount(int customerID, int accountNum, double balance, int accountFlag, boolean OP) {
        super(customerID, accountNum, balance, accountFlag);
        isOverdraftAcc = OP;
        super.setAccountType(accountType);
    }

    /**
     * is the least specific constructor for a savings account, automatically
     * setting the interest to .15 and indicating that it is not a overdraft
     * protecting account
     *
     * @param customerID is the SSN or ID of a customer
     * @param accountNum is the account number of the savings account to create
     * @param balance is the initial balance of the creating account
     * @param accountFlag is the initial flag of an account; this should ideally
     * be set to 0 as any account being flagged indicates a problem
     */
    public SavingsAccount(int customerID, int accountNum, double balance, int accountFlag) {
        super(customerID, accountNum, balance, accountFlag);
        super.setAccountType(accountType);
    }

    //--------------------------------------
    //  methods
    //--------------------------------------
    /**
     * withdraw method which will deduct the amount from balance if amount is less than balance
     *
     * @param amount is a value to deduct from the balance
     * @return updated balance
     */
    public double withdraw(double amount) { //deducts funds from account, returns updated balance
        if (amount <= balance) {//if the payment is less then or equal to the balence
            this.DebitAccount(amount);
        }
        return balance;
    }

    /**
     * deposit method which will deduct the amount from balance
     *
     * @param amount is a value to add to the balance
     * @return updated balance
     */
    public double deposit(double amount) {
        balance += amount;
        this.CreditAccount(amount);
        return balance;
    }

    /**
     * calculates the interest based on equation: new total = balance + (balance
     * * interest)
     *
     * @return updated balance
     */
    public double calculateInterest() {
        double total = (balance * interestRate);
        balance += total;
        return total;
    }

    /**
     * getter method to return account protecting from overdraft
     *
     * @return accountToProtect
     */
    public int getAccountToProtect() {
        return accountToProtect;
    }

    /**
     * setter method to set account to protect from overdraft
     *
     * @param accountProtecting is the checking account you intend to protect
     * from overdrafts
     */
    public void setAccountToProtect(int accountProtecting) {
        this.accountToProtect = accountProtecting;
    }

    /**
     * getter method to return savings account interest rate
     *
     * @return interestRate
     */
    public double getInterestRate() {
        return interestRate;
    }

    /**
     * setter method to set savings account interest rate
     *
     * @param rate is the new rate you intend to set for a savings account
     */
    public void setInterestRate(double rate) {
        interestRate = rate;
    }

    /**
     * getter method to return if this savings account protects from overdrafts
     *
     * @return isOverdraftAcc
     */
    public boolean isOverdraftAcc() {
        return isOverdraftAcc;
    }

    /**
     * setter method to set a savings account to protect from overdrafts
     *
     * @param set is a boolean, whether or not the account protects another
     */
    public void setOverdraftAcc(boolean set) {
        isOverdraftAcc = set;
    }

    /**
     * setter method to quickly update the balance of an account
     *
     * @param balance will update accounts balance to the one passed in
     * parameters
     */
    @Override
    public void updateBalance(double balance) {
        this.balance = balance;
    }

    /**
     * method which will list all of the debits and credits of a savings account into
     * output and return a end string
     *
     * @return words which will contain "end history"
     */
    @Override
    public String getTransactionHistory() {

        String words = "Empty";

        for (int i = 0; i < this.getNumOfDebits(); i++) {
            words = "Debit: " + this.getDebitDates(i) + " in the amount of " + this.getDebitAmounts(i);
            System.out.println(words);
        }
        for (int i = 0; i < this.getNumOfCredits(); i++) {
            words = "Credit: " + this.getCreditDates(i) + " in the amount of " + this.getCreditAmounts(i);
            System.out.println(words);
        }

        words = "end history";

        return words;
    }
}
