package bankingsystem;

import java.util.ArrayList;
import java.util.Date;

public class AtmCards extends AbstractAccount implements CreditInterface, DebitInterface {

    //---------------------------------
    //  Variable decleration
    //---------------------------------
    
    //ATM variables
    protected int PIN;
    protected int cardNum;
    //debit variables
    protected ArrayList<Double> DebitAmounts=new ArrayList<Double>();
    protected ArrayList<Long> DebitDates=new ArrayList<Long>();
    protected int NumberOfDebits;
    //credit variables
    private ArrayList<Double> CreditAmounts=new ArrayList<Double>();
    private ArrayList<Long> CreditDates=new ArrayList<Long>();
    private int NumberOfCredits;
    
    //-------------------------------------
    //  Constructor
    //-------------------------------------
    public AtmCards(int customerID, int accountNum, int PIN, int cardNum) {
        //constructor to set the basic account details
        this.customerID = customerID;
        this.accountNum = accountNum;
        this.PIN = PIN;
        this.cardNum = cardNum;
    }
    
    public AtmCards(int customerID, int accountNum, 
            double balance, int accountFlag) {
        //constructor to set the basic account details
        this.customerID = customerID;
        this.accountNum = accountNum;
        this.balance = balance;
        this.accountFlag = accountFlag;
    }
    
    //-----------------------------
    //      Methods
    //-----------------------------

    public void balanceCheck() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public int getNumOfWithdraw() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    
    int getPIN() {
        return PIN;
    }
    
    int getCardNum() {
        return cardNum;
    }
    
    void ATMwithdrawal() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    public int Withdrawl(double amount){
        int status =-1;
        if(amount<=balance){//if the payment is less then or equal to the balence
            status=1;
            this.DebitAccount(amount);
            return status;
        }else{
            System.out.println("now withdrawls greater than account balence!");
            return status;
        }
    }

    
    //---------------------------------
    //  implemented from Interface
    //---------------------------------
    
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