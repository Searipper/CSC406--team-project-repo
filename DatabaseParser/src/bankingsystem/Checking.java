package bankingsystem;

import java.io.*;
import javax.swing.*;
import java.util.*;
import java.lang.*;

public class Checking extends AtmCards {

    //---------------------------------
    //  Variable decleration
    //---------------------------------
    private static final double TMBtransCharge = .5;//TMB transaction charge
    private static final double TMBmonthlyTransCharge = .75;//TMB Monthly transacion charge
    private static final double GDminBalance = 1000.00;//GD minimum balance
    private static final double StopPaymentCharge = 15.00;//Stop payment charge
    private static final double OverDraftCharge = 20.00;//Overdraft charge
    private double GDinterestRate;//GD interest rate (savingAccountRate*.5)
    private double GDavgBalance;//Used to calc interst
    private double GDinterest;//Interst Amount to be added to balance
    public int protectingAcct; // the account# of backup overdraft protection account  
    private int CheckNumber = 0;// sets first check number to 0
    private boolean HasOverDraftProtection;//True = yes False = No
    private int BackupAccountNumber;
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
        super(customerID,accountNum,balance, accountFlag);//send to the constructor
        NumberOfDebits = 0;
       
        if(balance<=999.99){
            accountType = 3;
//             System.out.println("A TMB account has been created.");
        }
        else{
            accountType = 4;
//             System.out.println("A Gold/Diamond account has been created.");
        }
    }
       
//     public Checking(int customerID, int accountNum, int AcctType, double balance, int accountFlag) {
//        super(customerID, accountFlag);//send to the constructor
//        this.AcctType = AcctType;
//        if(AcctType == 4){
//            if(balance < GDminBalance)
//            {
//                AcctType = 3;
//                System.out.println("Error - A Gold/Diamond account must have a balance of atleast $1000");
//                System.out.println("A TMB account was created");
//            }
//            else{
//            System.out.println("A Gold/Diamond account was created.");  
//            }
//        }
//        else{
//            System.out.println("A TMB account has been created.");  
//        }
//        
//        NumberOfDebits = 0;
//    }
//     
//    public Checking(int customerID, int accountNum, double balance, int accountFlag, int AcctType, int protectingAcct) {
//        super(customerID, accountFlag);//send to the constructor
//        this.AcctType = AcctType;
//        this.protectingAcct = protectingAcct;
//        this.balance = balance;
//         if(AcctType == 4){
//            if(balance < GDminBalance)
//            {
//                AcctType = 3;
//                System.out.println("Error - A Gold/Diamond account must have a balance of atleast $1000");
//                System.out.println("A TMB account was created");
//            }
//            else{
//            System.out.println("A Gold/Diamond account was created.");  
//            }
//        }
//        else{
//            System.out.println("A TMB account has been created.");  
//        }
//        NumberOfDebits = 0;
//    }    
    //-----------------------------
    //      Getter and Setter Methods
    //-----------------------------

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
    
    
    public double getGDinterestRate() {
        return GDinterestRate;
    }

    public void setGDinterestRate(double GDinterestRate) {
        this.GDinterestRate = GDinterestRate;
    }

    public int getAcctType() {
        return accountType;
    }

    public void setAcctType(int AcctType) {
        this.accountType = AcctType;
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

    public int getBackupAccountNumber() {
        return BackupAccountNumber;
    }

    public void setBackupAccountNumber(int BackupAccountNumber) {
        this.BackupAccountNumber = BackupAccountNumber;
    }
    
    public void setProtectingAcc(int protectingAcc) {
        this.protectingAcct = protectingAcc;
    }
    
     public double getProtectingAcc() { //sets account to protect from ODing
        return protectingAcct;
    }

    
    
    //-----------------------------
    //      Other Methods
    //-----------------------------
     
     public void applyTMBtransCharge(){//charges Account with a transaction charge
        if(accountType==3){
        balance = balance - TMBtransCharge;
        System.out.println("A $.50 transaction charge has been charged to account balance.");
        }
        else{
        System.out.println("This is a Gold/Diamond Account, No transaction charge needed"); 
        }
    }
     
     public void applyTMBmonthlyTransCharge(){//charges Account with a montly transaction charge
        if(accountType==3){
        balance = balance - TMBmonthlyTransCharge;
        System.out.println("A $.75 monthly transaction charge has been charged to account balance.");
        }
        else{
         System.out.println("This is a Gold/Diamond Account, No monthly transaction charge needed");   
        }
     }
     
    public void calcGDavgBalance(){//calcs GD average balance
        GDavgBalance = balance /30;
        System.out.println("Calculating Gold/Diamond average balance. " + GDavgBalance);
        
       
    }
    public void calcGDinterestRate(){//calcs GD interst Rate      
        GDinterestRate = .15 *.5; //.15 is Savings interest rate
        System.out.println("Calculating Gold/Diamond interest rate. "+GDinterestRate);      
    }

    public void calcGDinterest(){ //calcs GD interst
        GDinterest = GDavgBalance * GDinterestRate;
        System.out.println("Calculating Gold/Diamond interest. " +GDinterest);
    }

    public void applyGDinterest(){//adds interest rate to acctBalance
        if (accountType == 4)
        {
            calcGDavgBalance();
            calcGDinterestRate();
            calcGDinterest();
            balance = balance + GDinterest;
            System.out.println("Adding Gold/Diamond interest to account balance. +"+GDinterest);
        }
        else{
            System.out.println("Error - Cannot apply Gold/Diamond interest to a TMB account.");
        }
    }

    //-----------------------------
    //      Other Methods (NOT FINISHED)
    //-----------------------------



    public void stopPayment(int StopPaymentCheckNumber){//stops the payment on a check and charges account
       
        //void check numbers debit amount
        balance = balance - StopPaymentCharge;
        System.out.println("A $15 stop payment charge has been charged to account balance.");
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
            if(balance<=999.99){
            accountType = 3;
             System.out.println("A TMB account has been created.");
        }
        else{
            accountType = 4;
             System.out.println("A Gold/Diamond account has been created.");
        }
            return status;
        }else{//Subject to change for OP implementation
            System.out.println("Overdraft!");
            return status;
        }
        
    }
       
    
    public double Transfer(double amount){
        if(amount<=balance){//if the payment is less then or equal to the balence
            int checknum = this.CheckNumber + 1;
            this.DebitAccount(amount,checknum);
            if(this.accountType!=4){
                this.TransferChargeList.set(this.NumberOfDebits-1, this.TMBmonthlyTransCharge);//Transfer charge applied here
                this.updateBalance(0-TMBmonthlyTransCharge);
            }
            //checl the new account balance and set the account type based on that
            if(balance<=999.99){
                accountType = 3;
            }else{accountType = 4;}
            return amount;
        }else{
            System.out.println("no withdrawls greater than account balance!");
            return 0.00;
        }
    }
    
    public void Deposit(double amount){
      this.CreditAmounts.add(amount);
            this.CreditDates.add(new Date().getTime());
            this.NumberOfCredits++;
            this.updateBalance(amount);//add amount to the balence
            
       if(balance<=999.99){
            accountType = 3;
             System.out.println("Account Updated to 3, after deposit");
        }
        else{
            accountType = 4;
             System.out.println("Account Updated to 4, after deposit");
        }
            
    }

    public double getTransactionChargeList(int index) {
        return TransactionChargeList.get(index);
    }

    public double getTransferChargeList(int index) {
        return TransferChargeList.get(index);
    }

    public int getCheckNumbers(int index) {
        return CheckNumbers.get(index);
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
    }

    public void addDebititRecord(double amount,long DateOfActivation,int checknumber,double transaction,double transfer) {
        super.addDebititRecord(amount, DateOfActivation);
        this.CheckNumbers.add(checknumber);
        this.TransactionChargeList.add(transaction);
        this.TransferChargeList.add(transfer);
    }
     

}
            
       
     
   
    

   

    



 

 

 

 




 
