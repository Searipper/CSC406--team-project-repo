package bankingsystem;

import java.util.ArrayList;
/**
 * Abstract account containing variables that all accounts will need.
 */
abstract class AbstractAccount {
 
    //----------------------------
    //        Variables
    //----------------------------
    
    protected int customerID;//the SSN number of the customer associated with this account
    protected int accountNum;//unique account identifier number 9-digit number
    protected int accountType;//account type identifier
    protected double balance;//the current balence of the account
    protected int accountFlag;//any flaggs associated with this account
    protected long DateOfActivation;//long date of activation

    //---------------------------
    //Abstract constructor
    //---------------------------
    
    public AbstractAccount() {}//default abstract constructor.
    public AbstractAccount(int customerID, int accountNum, 
            double balance, int accountFlag) {
        //constructor to set the basic account details
        this.customerID = customerID;
        this.accountNum = accountNum;
        this.balance = balance;
        this.accountFlag = accountFlag;
    }
    public AbstractAccount(int customerID, int accountNum, 
            double balance) {
        //constructor to set the basic account details
        this.customerID = customerID;
        this.accountNum = accountNum;
        this.balance = balance;
    }
    
    

    //-----------------------------
    //      Methods
    //-----------------------------
    
    
    public double checkBalance(){return balance;}
    /**
     * updates the balance of the account
     * @param balance value to be added to the account balance if balance passed is negative it deducts from 
     */
    public void updateBalance(double balance) {
        this.balance = this.balance+balance;
    }

    //getter/setter methods
    
    /**returns account flags for this account @return accountFlag flag*/
    public int getAccountFlag() {return accountFlag;}
    /**sets account flags for this account @param accountFlag flag*/
    public void setAccountFlag(int accountFlag) {this.accountFlag = accountFlag;}
    /**returns the unique account identifier for the account @return accountNum number*/
    public int getAccountNum() {return accountNum;}
    /**sets the unique account identifier for the account @param accountNum number*/
    public void setAccountNum(int accountNum) {this.accountNum = accountNum;}
    /**returns the account type 
     * @return accounttype 
     * <ul>
        * <li>1=SimpleSavings</li>
        * <li>2=CDs</li>
        * <li>3=TMB</li>
        * <li>4=G/D</li>
        * <li>5=LLoans</li>
        * <li>6=SLoans</li>
        * <li>7=CreditCards</li>
     * </ul>
     */
    public int getAccountType() {return accountType;}
    /**sets the account type @param accounttype <ul>
        * <li>1=SimpleSavings</li>
        * <li>2=CDs</li>
        * <li>3=TMB</li>
        * <li>4=G/D</li>
        * <li>5=LLoans</li>
        * <li>6=SLoans</li>
        * <li>7=CreditCards</li>
     * </ul>
     */
    public void setAccountType(int accountType) {this.accountType = accountType;}
    /**Returns the account type based off of integer accounttype 
     * @return if accounttype = <ul>
        * <li>1: return SimpleSavings</li>
        * <li>2: return CD</li>
        * <li>3: return ThatsMyBank</li>
        * <li>4: return Gold / Diamond</li>
        * <li>5: return Long-Term Mortgage</li>
        * <li>6: return Short-Term Loan</li>
        * <li>7: return Credit Card</li>
     * </ul>
     */
    public String getAccountDescription(){
        switch(this.accountType){
            case 1: return "SimpleSavings";
            case 2: return "CD";
            case 3: return "ThatsMyBank";
            case 4: return "Gold / Diamond";
            case 5: return "Long-Term Mortgage";
            case 6: return "Short-Term Loan";
            case 7: return "Credit Card";
            default: return "Undefined Account Type";
        }
    }
    /**Returns the customer identifier @return customerID customer ssn*/
    public int getCustomerID() {return customerID;}
    /**sets the customer identifier @param customerID customer ssn*/
    public void setCustomerID(int customerID) {this.customerID = customerID;}
    /**returns the date of activation @return DateOfActivation long date*/
    public long getDateOfActivation() {return DateOfActivation;}
    /**sets the date of activation @param DateOfActivation long date*/
    public void setDateOfActivation(long DateOfActivation) {this.DateOfActivation = DateOfActivation;}
        
    public String getTransactionHistory() {
        return "";
    }
}//end Abstract class