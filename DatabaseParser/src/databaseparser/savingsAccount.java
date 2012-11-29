package databaseparser;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author mlaunet
 */
public class savingsAccount extends AbstractAccount implements CreditInterface, DebitInterface {
    
    //--------------------------------------
    //  Variables
    //--------------------------------------
    double interestRate = .015; //changable to whatever
    boolean isOverdraftAcc = false; //is an overdraft protection account flag
    int protectingAcc = 0; // the account to protect from overdraft
    //debit variables
    protected ArrayList<Double> DebitAmounts=new ArrayList<Double>();
    protected ArrayList<Long> DebitDates=new ArrayList<Long>();
    protected int NumberOfDebits;
    //credit variables
    private ArrayList<Double> CreditAmounts=new ArrayList<Double>();
    private ArrayList<Long> CreditDates=new ArrayList<Long>();
    private int NumberOfCredits;
    
    //--------------------------------------
    //  Constructors
    //--------------------------------------
    public savingsAccount(int customerID, int accountNum, double balance, int accountFlag, double interest, boolean OP) {
        super(customerID, accountNum, balance, accountFlag);
        interestRate = interest;
        isOverdraftAcc = OP;
    }
    
    public savingsAccount(int customerID, int accountNum, double balance, int accountFlag, boolean OP) {
            super(customerID, accountNum, balance, accountFlag);
            isOverdraftAcc = OP;
    }
    public savingsAccount(int customerID, int accountNum, double balance, int accountFlag) {
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

    public double getProtectingAcc() {
        return protectingAcc;
    }

    public void setProtectingAcc(int protectingAcc) {
        this.protectingAcc = protectingAcc;
    }

    @Override
    public void updateBalance(double balance) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
//    public double transferFunds(account accNum, double amount) { //you'll have to specify account to pass funds to
//        balance -= amount;
//        accNum.balance += amount;        
//        return balance;
//    }

    @Override
    public void CreditAccount(double amount) {
            this.CreditAmounts.add(amount);
            this.CreditDates.add(new Date().getTime());
            this.NumberOfCredits++;
            this.updateBalance(amount);//add amount to the balence
    }//end CreditAccount

    @Override
    public void addCreditRecord(double amount, long creditdate) {       
        this.CreditAmounts.add(amount);
        this.CreditDates.add(creditdate);
        this.NumberOfCredits++;
    }
    @Override
    public double getCreditAmounts(int index) {return CreditAmounts.get(index);}
    @Override
    public long getCreditDates(int index) {return CreditDates.get(index);}
    @Override
    public int getNumOfCredits() {return NumberOfCredits;}


    @Override
    public void DebitAccount(double amount) {
        if(amount<=balance){//if the payment is less then or equal to the balence
            DebitAmounts.add(amount);
            DebitDates.add(new Date().getTime());
            NumberOfDebits++;
            //send a negative number to detract from the balence
            this.updateBalance(0-amount);
        }else{
            System.out.println("now withdrawls greater than account balence!");
        }
    }
    @Override
    public void addDebititRecord(double amount, long creditdate) {
        DebitAmounts.add(amount);
        DebitDates.add(creditdate);
        NumberOfDebits++;
    }
    @Override
    public double getDebitAmounts(int index) {
        if(index<NumberOfDebits){
            return DebitAmounts.get(index);
        }else {
            System.out.println("index not within range of "+NumberOfDebits);
            return 0.00;
        }
    }
    @Override//people need to be able to search by dates
    public long getDebitDates(int index) {
        if(index<NumberOfDebits){
            return DebitDates.get(index);
        }else {
            System.out.println("index not within range of "+NumberOfDebits);
            return 0;
        }
    }
    @Override
    public int getNumOfDebits() {
        return NumberOfDebits;
    }
    
}
