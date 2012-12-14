package bankingsystem;

import java.util.ArrayList;
import java.util.Date;

public class AtmCards extends AbstractAccount implements CreditInterface, 
        DebitInterface {

    //---------------------------------
    //  Variable decleration
    //---------------------------------
    
    //ATM variables
    /**This is the pin number*/
    protected int PIN;
    /**This is the Card number */
    protected int cardNum;
    //debit variables
    /**This is an ArrayList of Debit Amounts  */
    protected ArrayList<Double> DebitAmounts=new ArrayList<Double>();
    /**This is an ArrayList of Debit Dates  */
    protected ArrayList<Long> DebitDates=new ArrayList<Long>();
    /**This is the number of Debits  */
    protected int NumberOfDebits;
    //credit variables
    /**This is an ArrayList of Credit Amounts  */
    protected ArrayList<Double> CreditAmounts=new ArrayList<Double>();
    /**This is an ArrayList of Credit Dates  */
    protected ArrayList<Long> CreditDates=new ArrayList<Long>();
    /**This is the number of Credits  */
    protected int NumberOfCredits;
    
    //-------------------------------------
    //  Constructor
    //-------------------------------------
    
     /**  Constructs a ATM Card.
     *    @param customerID account holder ssn
     *    @param accountNum Unique account identifier
     *    @param PIN this is the pin for the card
     *    @param cardNum this is the cards number
     */
    public AtmCards(int customerID, int accountNum, int PIN, int cardNum) {
        //constructor to set the basic account details
        this.customerID = customerID;
        this.accountNum = accountNum;
        this.PIN = PIN;
        this.cardNum = cardNum;
    }
     /** Constructs a ATM Card.
     *   @param customerID account holder SSN
     *   @param accountNum Unique account identifier
     *   @param balance customer's account balance
     *   @param accountFlag 0 = No flags on account
     */   
    public AtmCards(int customerID, int accountNum, double balance, 
            int accountFlag) {
        //constructor to set the basic account details
        this.customerID = customerID;
        this.accountNum = accountNum;
        this.balance = balance;
        this.accountFlag = accountFlag;
    }
    
    //-----------------------------
    //      Methods
    //-----------------------------

    /**  Gets the balance of the account
     *   @return balance
     */
    public double balanceCheck() {
        return this.balance;
    }

    public int getNumOfWithdraw() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**  Gets the pin of the card
     *   @return PIN
     */
    int getPIN() {
        return PIN;
    }
    
    /**  Gets the Card number
     *   @return cardNum
     */ 
    int getCardNum() {
        return cardNum;
    }
   /**  Sets the card number of the card
     *   @param cardNum this is the card number to set it to
     */
    public void setCardNum(int cardNum) {
        this.cardNum = cardNum;
    }
   /**  Sets the PIN of the Card
     *   @param PIN this is the PIN to set it to
     */
    public void setPIN(int PIN) {
        this.PIN = PIN;
    }
    
    void ATMwithdrawal() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    /**  Withdraw from ATM card
     *   @param amount this is the amount to be withdrawn
     *   @return status
     */    
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
    /** Credits an account by the amount
     *  @param amount This is the amount to be credited
     */ 
    public void CreditAccount(double amount) {
            this.CreditAmounts.add(amount);
            this.CreditDates.add(new Date().getTime());
            this.NumberOfCredits++;
            this.updateBalance(amount);//add amount to the balence
    }//end CreditAccount
    @Override
    /** Adds a credit Record to the array list
     *  @param amount This is the amount to be credited
     *  @param creditdate This is the date of the credit
     */     
    public void addCreditRecord(double amount, long creditdate) {       
        this.CreditAmounts.add(amount);
        this.CreditDates.add(creditdate);
        this.NumberOfCredits++;
    }
    @Override
     /**  Gets an array list of credit amounts 
      *   @return CreditAmounts
      */ 
    public double getCreditAmounts(int index) {return CreditAmounts.get(index);}
    @Override
     /**  Gets an array list of credit dates 
      *   @return CreditDates
      */    
    public long getCreditDates(int index) {return CreditDates.get(index);}
    @Override
    /**  Gets the numbers of credits
     *   @return NumberOfCredits
     */ 
    public int getNumOfCredits() {return NumberOfCredits;}
    @Override
    /** Debits account by the amount
     *  @param amount This the amount to be debited
     */    
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
    /** Adds a debit record to the array lists
     *  @param amount This is the amount to be debited
     *  @param creditdate This is the date of debit
     */    
    public void addDebititRecord(double amount, long creditdate) {
        DebitAmounts.add(amount);
        DebitDates.add(creditdate);
        NumberOfDebits++;
    }
    @Override
     /**  Gets an array list of Debit Amounts 
      *   @return DebitAmounts
      */     
    public double getDebitAmounts(int index) {
        if(index<NumberOfDebits){
            return DebitAmounts.get(index);
        }else {
            System.out.println("index not within range of "+NumberOfDebits);
            return 0.00;
        }
    }
    @Override//people need to be able to search by dates
     /**  Gets an array list of Debit Dates 
      *   @return DebitDates
      */     
    public long getDebitDates(int index) {
        if(index<NumberOfDebits){
            return DebitDates.get(index);
        }else {
            System.out.println("index not within range of "+NumberOfDebits);
            return 0;
        }
    }
    @Override
    /**  Gets the numbers of debits
     *   @return NumberOfDebits
     */   
    public int getNumOfDebits() {
        return NumberOfDebits;
    }

}