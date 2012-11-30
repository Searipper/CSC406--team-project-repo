package bankingsystem;

import java.util.Date;


abstract class Account {
    //common prototypes of all account
    private int accountNum;
    private int ssn;
    private int accountType;//1-saving,2-checking,3-Loans
    private int accountFlag;//0-suspended,1-alarm,2-normal,3-outstanding.
    public double balance;


    public abstract void setBalance();
    public abstract double getBalance();
    public abstract void setUserID();
    public abstract double getUserID();
    public abstract void setAccountFlag();
    public abstract int getAccountFlag();
    public abstract void setAccountType();
    public abstract double getAccountType();
    public abstract int setAccountNum();
    public abstract void getAccountNum();
    public abstract void setCreatedDate();
    public abstract Date getCreatedDate();
    
}
