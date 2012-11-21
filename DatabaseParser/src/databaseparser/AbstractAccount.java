package databaseparser;

import java.util.ArrayList;
/**
 * Abstract account containing variables that all accounts will need.
 */
abstract class AbstractAccount {
 
    //----------------------------
    //        Variables
    //----------------------------
    
    protected int customerID;//the SSN number of the customer associated with this account
    protected int accountNum;//unique account identifier number
    protected int accountType;//account type identifier
    protected double balance;//the current balence of the account
    protected int accountFlag;//any flaggs associated with this account

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
    
    

    //-----------------------------
    //      Methods
    //-----------------------------
    
    
    public double checkBalance(){return balance;}
    public abstract void updateBalance(double balance);

    //getter/setter methods
    public int getAccountFlag() {return accountFlag;}
    public void setAccountFlag(int accountFlag) {this.accountFlag = accountFlag;}
    public int getAccountNum() {return accountNum;}
    public void setAccountNum(int accountNum) {this.accountNum = accountNum;}
    public int getAccountType() {return accountType;}
    public void setAccountType(int accountType) {this.accountType = accountType;}
    public int getCustomerID() {return customerID;}
    public void setCustomerID(int customerID) {this.customerID = customerID;}
    
}//end Abstract class