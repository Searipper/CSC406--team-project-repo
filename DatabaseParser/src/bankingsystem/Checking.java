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
    public int CheckNumber = 0;// check number
    public int BackupAccountNumber;//Account number for over draft backup account
    public boolean HasOverDraftProtection;//True = yes False = No
//    protected ArrayList<Double> DebitAmounts = new ArrayList<Double>();
//    protected ArrayList<Long> DebitDates = new ArrayList<Long>();
//    protected int NumberOfDebits;
//    protected ArrayList<Double> CreditAmounts = new ArrayList<Double>();
//    protected ArrayList<Long> CreditDates = new ArrayList<Long>();
    
    
    protected int NumberOfCredits;
    protected ArrayList<Integer>CheckNumbers = new ArrayList<Integer>();
    protected ArrayList<Double>TransactionChargeList = new ArrayList<Double>();
    protected ArrayList<Double>TransferChargeList = new ArrayList<Double>();
    

    //---------------------------------
    //  Constructor
    //---------------------------------
    public Checking(int customerID, int accountNum, double balance, int accountFlag) {
        super(customerID, accountNum, balance, accountFlag);//send to the constructor
        NumberOfDebits = 0;
    }
    public Checking(int customerID, int accountNum, int AcctType, double balance, int accountFlag) {
        super(customerID, accountNum, balance, accountFlag);//send to the constructor
        NumberOfDebits = 0;
    }

    //-----------------------------
    //      Getter and Setter Methods
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

    public void setHasOverDraftProtection(boolean HasOverDraftProtection) {
        this.HasOverDraftProtection = HasOverDraftProtection;
    }
    
    public boolean getHasOverDraftProtection() {
        return HasOverDraftProtection;
    }
    
    public void setCheckNumber(int CheckNumber) {
        this.CheckNumber = CheckNumber;
    }

    public int getStopPaymentCheckNumber() {
        return CheckNumber;
    }

    public void setProtectingAcc(int protectingAcc) {
        this.protectingAcc = protectingAcc;
    }
    
     public double getProtectingAcc() { //sets account to protect from ODing
        return protectingAcc;
    }

    
    
    //-----------------------------
    //      Other Methods
    //-----------------------------
     
     public void applyTMBtransCharge(){//charges Account with a transaction charge
        DebitAccount(TMBtransCharge);
    }
     
     public void applyTMBmonthlyTransCharge(){//charges Account with a montly transaction charge
        DebitAccount(TMBmonthlyTransCharge);
    }
     
    public void calcGDavgBalance(){//calcs GD average balance
        GDavgBalance = balance /30;
    
    }
    
    public void calcGDinterestRate(){//calcs GD interst Rate
        GDinterestRate = .15 *.5; //.15 is Savings interest rate
    }

    public void calcGDinterest(){ //calcs GD interst
        GDinterest = GDavgBalance * GDinterestRate;
    }

    public void applyGDinterest(){//adds interest rate to acctBalance
        if (AcctType == 1)
        {
            calcGDavgBalance();
            calcGDinterestRate();
            calcGDinterest();
            CreditAccount(GDinterest);
        }
    }

    //-----------------------------
    //      Other Methods (NOT FINISHED)
    //-----------------------------



    public void stopPayment(int StopPaymentCheckNumber){//stops the payment on a check and charges account
       
        //void check numbers debit amount
        DebitAccount(StopPaymentCharge);
    }

    public void overDraft(double amt){//credit back up, debit checking, and then credit checking
        //check if prot. acc. exists
        //if one doesn't, stop transaction charge 20
        //if prot. acc. exists, determine that balance
        //if prot acc can cover cost, withdraw from savings, deposit to checking
        //if one doesn't, stop charge 20
        if(amt>balance)
         {   
           if(HasOverDraftProtection = false)//no account protection
            {
                
               DebitAccount(OverDraftCharge);
               setAccountFlag(1);
            }
              else {//has account protection
                 if /*backup account */(balance > amt)//check backup account balance // HOW?
                  {
                 //  BACKUP DebitAccount(amt); //withdraw from backup account // HOW?
                     CreditAccount(amt);//deposit into checking account
                  }
               
                  else {//backup account doesnt have enought to withdraw
                    DebitAccount(OverDraftCharge);
                    setAccountFlag(1);
                   }
              }
         }
           else {
              System.out.println("No over draft occured");
           }
    }

    //------------------------------------
    //      Impliments from DebitInterface
    //------------------------------------

    
    @Override
    public int Withdrawl(double amount) {
        int status =-1;
        if(amount<=balance){//if the payment is less then or equal to the balence
            status=1;
            int checknum = this.CheckNumber + 1;
            this.DebitAccount(amount,checknum);
            return status;
        }else{
            System.out.println("Overdraft!");
            return status;
        }
    }
    
    public double Transfer(double amount){
        if(amount<=balance){//if the payment is less then or equal to the balence
            int checknum = this.CheckNumber + 1;
            this.DebitAccount(amount,checknum);
            if(this.AcctType!=4){
                this.TransferChargeList.set((this.TransferChargeList.size()-1), this.TMBmonthlyTransCharge);
                this.updateBalance(0-TMBmonthlyTransCharge);
            }
            return amount;
        }else{
            System.out.println("now withdrawls greater than account balence!");
            return 0.00;
        }
    }
     public void DebitAccount(double amount, int checknum) {
        
            DebitAmounts.add(amount);
            DebitDates.add(new Date().getTime());
            NumberOfDebits++;
            CheckNumbers.add(checknum);
            if(this.accountType==4){
                this.TransactionChargeList.add(0.00);
                this.TransferChargeList.add(0.00);
                this.updateBalance(0-(amount));
            }else{
                this.TransactionChargeList.add(TMBtransCharge);
                this.TransferChargeList.add(0.00);
                this.updateBalance(0-(amount+TMBtransCharge));
            }
            //send a negative number to detract from the balence
            this.updateBalance(0-amount);
    }

    public void DebitAccount(double amount,long DateOfActivation,int checknumber,double transaction,double transfer) {
        super.addDebititRecord(amount, DateOfActivation);
        this.CheckNumbers.add(checknumber);
        this.TransactionChargeList.add(transaction);
        this.TransferChargeList.add(transfer);
    }
     

}
            
       
     
   
    

   

    



 

 

 

 




 
