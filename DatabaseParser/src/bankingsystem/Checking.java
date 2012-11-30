package bankingsystem;

import java.io.*;
import javax.swing.*;
import java.util.*;
import java.lang.*;

public class Checking extends AtmCards {

    //---------------------------------
    //  Variable decleration
    //---------------------------------
    public static final double TMBtransCharge = .5;//TMB transaction charge
    public static final double TMBmonthlyTransCharge = .75;//TMB Monthly transacion charge
    public static final double GDminBalance = 1000.00;//GD minimum balance
    public static final double StopPaymentCharge = 15.00;//Stop payment charge
    public static final double OverDraftCharge = 20.00;//Overdraft charge
    public double GDinterestRate;//GD interest rate (savingAccountRate*.5)
    public double GDavgBalance;//Used to calc interst
    public double GDinterest;//Interst Amount to be added to balance
    public int protectingAcc; // the account to protect from overdraft
    public int AcctType;// 0 = TMB 1=GD
    public int StopPaymentCheckNumber;// stop payment check number
    public int BackupAccountNumber;//Account number for over draft backup account
    public boolean HasOverDraftProtection;//True = yes False = No
    protected ArrayList<Double> DebitAmounts = new ArrayList<Double>();
    protected ArrayList<Long> DebitDates = new ArrayList<Long>();
    protected int NumberOfDebits;
    protected ArrayList<Double> CreditAmounts = new ArrayList<Double>();
    protected ArrayList<Long> CreditDates = new ArrayList<Long>();
    protected int NumberOfCredits;

    //---------------------------------
    //  Constructor
    //---------------------------------
    public Checking(int customerID, int accountNum, double balance, int accountFlag) {
        super(customerID, accountNum, balance, accountFlag);//send to the constructor
        NumberOfDebits = 0;
    }

    //-----------------------------
    //      Methods
    //-----------------------------
    public double getGDinterestRate() {
        return GDinterestRate;
    }

    public void setGDinterestRate(double GDinterestRate) {
        this.GDinterestRate = GDinterestRate;
    }

    public int getAcctType() {
        return AcctType;
    }

    public void setAcctType(int AcctType) {
        this.AcctType = AcctType;
    }

    public int getBackupAccountNumber() {
        return BackupAccountNumber;
    }

    public void setBackupAccountNumber(int BackupAccountNumber) {
        this.BackupAccountNumber = BackupAccountNumber;
    }

    public double getGDavgBalance() {
        return GDavgBalance;
    }

    public void setGDavgBalance(double GDavgBalance) {
        this.GDavgBalance = GDavgBalance;
    }

    public double getGDinterest() {
        return GDinterest;
    }

    public void setGDinterest(double GDinterest) {
        this.GDinterest = GDinterest;
    }

    public boolean isHasOverDraftProtection() {
        return HasOverDraftProtection;
    }

    public void setHasOverDraftProtection(boolean HasOverDraftProtection) {
        this.HasOverDraftProtection = HasOverDraftProtection;
    }

    public int getStopPaymentCheckNumber() {
        return StopPaymentCheckNumber;
    }

    public void setStopPaymentCheckNumber(int StopPaymentCheckNumber) {
        this.StopPaymentCheckNumber = StopPaymentCheckNumber;
    }

    //---------------------------------
    //  implemented from AbstractAccount
    //---------------------------------
    
    public double getProtectingAcc() { //sets account to protect from ODing
        return protectingAcc;
    }

    public void setProtectingAcc(int protectingAcc) {
        this.protectingAcc = protectingAcc;
    }

    public void overdraft(double amt) {
        if (amt > balance) {
            //check if prot. acc. exists
            //if one doesn't, stop transaction charge 20
            //if prot. acc. exists, determine that balance
            //if prot acc can cover cost, withdraw from savings, deposit to checking
            //if one doesn't, stop charge 20
        }

    }
}
/*

 public void applyTMBtransCharge(){//charges Account with a transaction charge

 //AcctBalance = TMBtransCharge + AcctBalance;


 }

 public void applyTMBmonthlyTransCharge(){//charges Account with a montly transaction charge

 //AcctBalance  = AcctBalance + TMBmonthlyTransCharge;


 }

 public void calcGDavgBalance(){//calcs GD average balance

 //Figure out how to calc avgBalance
 }

 public void calcGDinterestRate(){//calcs GD interst Rate


 //GDinterestRate = savingsInterestRate *.5;


 }

 public void calcGDinterest(){ //calcs GD interst

 GDinterest = GDavgBalance * GDinterestRate;

 }

 public void applyGDinteres(){//adds interest rate to acctBalance

 calcGDavgBalance();
 calcGDinterestRate();
 calcGDinterest();

 //acctBalance = GDinterst + acctBalance;

 }

 public void stopPayment(int StopPaymentCheckNumber){//stops the payment on a check and charges account
 this.StopPaymentCheckNumber = StopPaymentCheckNumber;

 //void check numbers debit amount
 //acctBalance = StopPaymentCharge + acctBalance;

 }

 public void overDraft(){//credit back up, debit checking, and then credit checking


 if(HasOverDraftProtection = false)
 {
 //acctBalance = acctBalance + OverDraftCharge;
 //flag account as a troubled account
 }

 else
 {
 //credit amount from backup account
 //debit amount to checking account
 //credit amount from checking account
 }

 }




 */
