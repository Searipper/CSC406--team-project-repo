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
    protected double GDinterestRate;//GD interest rate (savingAccountRate*.5)
    protected double GDavgBalance;//Used to calc interst
    protected double GDinterest;//Interst Amount to be added to balance
    public int protectingAcct; // the account# of backup overdraft protection account  
    protected int CheckNumber = 0;// sets first check number to 0
    protected boolean HasOverDraftProtection;//True = yes False = No
    protected int BackupAccountNumber;
//    protected ArrayList<Double> DebitAmounts = new ArrayList<Double>();
//    protected ArrayList<Long> DebitDates = new ArrayList<Long>();
//    protected int NumberOfDebits;
//    protected ArrayList<Double> CreditAmounts = new ArrayList<Double>();
//    protected ArrayList<Long> CreditDates = new ArrayList<Long>();
    protected int NumberOfCredits;
    protected ArrayList<Integer> CheckNumbers = new ArrayList<Integer>();
    protected ArrayList<Double> TransactionChargeList = new ArrayList<Double>();
    protected ArrayList<Double> TransferChargeList = new ArrayList<Double>();
    protected ArrayList<Double> DepositList = new ArrayList<Double>();

    //---------------------------------
    //  Constructor
    //---------------------------------
    public Checking(int customerID, int accountNum, double balance, int accountFlag) {
        super(customerID, accountNum, balance, accountFlag);//send to the constructor
        NumberOfDebits = 0;

        if (balance <= 999.99) {
            accountType = 3;
            System.out.println("A TMB account has been created.");
        } else {
            accountType = 4;
            System.out.println("A Gold/Diamond account has been created.");
        }
    }

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
        if (this.protectingAcct != 0) {
            this.setHasOverDraftProtection(true);
        }

    }

    public double getProtectingAcc() { //sets account to protect from ODing
        return protectingAcct;
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

    //-----------------------------
    //      Other Methods
    //-----------------------------
    public void applyTMBtransCharge() {//charges Account with a transaction charge
        if (accountType == 3) {
            balance = balance - TMBtransCharge;
            System.out.println("A $.50 transaction charge has been charged to account balance.");
        } else {
            System.out.println("This is a Gold/Diamond Account, No transaction charge needed");
        }
    }

    public void applyTMBmonthlyTransCharge() {//charges Account with a montly transaction charge
        if (accountType == 3) {
            balance = balance - TMBmonthlyTransCharge;
            System.out.println("A $.75 monthly transaction charge has been charged to account balance.");
        } else {
            System.out.println("This is a Gold/Diamond Account, No monthly transaction charge needed");
        }
    }

    public void calcGDavgBalance() {//calcs GD average balance
        GDavgBalance = balance / 30;
        System.out.println("Calculating Gold/Diamond average balance. " + GDavgBalance);
    }

    public void calcGDinterestRate() {//calcs GD interst Rate      
        GDinterestRate = .15 * .5; //.15 is Savings interest rate
        System.out.println("Calculating Gold/Diamond interest rate. " + GDinterestRate);
    }

    public void calcGDinterest() { //calcs GD interst
        GDinterest = GDavgBalance * GDinterestRate;
        System.out.println("Calculating Gold/Diamond interest. " + GDinterest);
    }

    public void applyGDinterest() {//adds interest rate to acctBalance
        if (accountType == 4) {
            calcGDavgBalance();
            calcGDinterestRate();
            calcGDinterest();
            balance = balance + GDinterest;
            System.out.println("Adding Gold/Diamond interest to account balance. +" + GDinterest);
        } else {
            System.out.println("Error - Cannot apply Gold/Diamond interest to a TMB account.");
        }
    }

    public double Transfer(double amount) {
        if (amount <= balance) {//if the payment is less then or equal to the balence
            int checknum = this.CheckNumber + 1;
            this.DebitAccount(amount, checknum);
            if (this.accountType != 4) {
                this.TransferChargeList.set(this.NumberOfDebits - 1, this.TMBmonthlyTransCharge);//Transfer charge applied here
                this.updateBalance(0 - TMBmonthlyTransCharge);
            }
            return amount;
        } else {
            System.out.println("no withdrawls greater than account balance!");
            return 0.00;
        }
    }

    public void Deposit(double amount) {
     
        this.CreditAmounts.add(amount);
        this.CreditDates.add(new Date().getTime());
        this.NumberOfCredits++;
        //int checknum = this.CheckNumber + 1;
        this.CreditAccount(amount);
        this.updateBalance(amount);//add amount to the balence

        if (balance <= 999.99) {
            accountType = 3;
            System.out.println("Account Updated to 3, after deposit");
        } else {
            accountType = 4;
            System.out.println("Account Updated to 4, after deposit");
        }

    }

    public void DebitAccount(double amount, int checknum) {

        DebitAmounts.add(amount);
        DebitDates.add(new Date().getTime());
        NumberOfDebits++;
        CheckNumbers.add(checknum);
        
        if (this.accountType == 4) {
            this.TransactionChargeList.add(0.00);
            this.TransferChargeList.add(0.00);
            this.updateBalance(0 - (amount));
        } else {
            this.TransactionChargeList.add(TMBtransCharge);
            this.TransferChargeList.add(0.00);
            this.updateBalance(0 - (amount + TMBtransCharge));
        }
        //send a negative number to detract from the balence
    }

    public void addDebititRecord(double amount, long DateOfActivation, int checknumber, double transaction, double transfer) {
        super.addDebititRecord(amount, DateOfActivation);
        CheckNumber++;
        this.CheckNumbers.add(checknumber);
        this.TransactionChargeList.add(transaction);
        this.TransferChargeList.add(transfer);
    }
    
     public void CreditAccount(double amount, int checknum) {

        CreditAmounts.add(amount);
        CreditDates.add(new Date().getTime());
        NumberOfCredits++;
        CheckNumbers.add(checknum);
        if (this.accountType == 4) {
           // this.TransactionChargeList.add(0.00);
            this.DepositList.add(0.00);
            this.updateBalance(amount);
        } else {
            //this.TransactionChargeList.add(TMBtransCharge);
            this.DepositList.add(this.TMBtransCharge);
            this.updateBalance (amount - TMBtransCharge);
        }
        //send a negative number to detract from the balence
    }
     
      public void addCreditRecord(double amount,long date, double transaction) {
        super.addCreditRecord(amount, date);        
        this.DepositList.add(transaction);
    }

    //------------------------------------
    //      Impliments from DebitInterface
    //------------------------------------
    //@Override
    public int Withdrawl(double amount, AccountParser ap) {

        int status = -1;
        if (amount + this.TMBtransCharge <= (balance )) {//if the payment is less then or equal to the balence
            status = 1;
            int checknum = this.CheckNumber + 1;
            this.DebitAccount(amount, checknum);
            if (balance <= 999.99) {
                accountType = 3;
                System.out.println("Account is now TMB.");

            } else {
                accountType = 4;
                System.out.println("Account is now a Gold/Diamond.");
            }
            status = 0;
            return status;
        } else if (this.HasOverDraftProtection) {

            SavingsAccount s1 = ap.getSavingsAccount(this.protectingAcct);
            if (amount <= this.balance + s1.checkBalance()+.5) {
                double temp = amount - (balance - .5);
                int checknum = this.CheckNumber + 1;
                this.DebitAccount(balance - .5, checknum);
                 
                System.out.println(s1.withdraw(temp));
                status = 1;
                System.out.println("Overdraft protected. Amount withdrawn from backup accout $" + temp
                        + " Amount withdrawn from checking account $" + (balance));
                return status;
            } else {
                this.DebitAccount(OverDraftCharge, -1);

                System.out.println("Not enough funds in backup account to cover charges. Account Overdrafted charging fee");
                return status;
            }

        } else {
            this.DebitAccount(OverDraftCharge, -1);
            System.out.println("Account Overdrafted charging fee");
            return status;
        }

    }

    //-----------------------------
    //      Other Methods (NOT FINISHED)
    //-----------------------------
    public String stopPayment(int StopPaymentCheckNumber) {//stops the payment on a check and charges account
        String msg = "";
        int temp = -1;
        for (int i = 0; i < this.getNumOfDebits(); i++) {
            if (this.CheckNumbers.get(i) == StopPaymentCheckNumber) {
                temp = i;
            }
        }
        if (temp == -1) {
            msg = "Check not found";
        }
        else {
         this.updateBalance(this.getDebitAmounts(temp));
         this.CheckNumbers.remove(temp);
         this.DebitAmounts.remove(temp);
         this.DebitDates.remove(temp);
         this.TransactionChargeList.remove(temp);
         this.TransferChargeList.remove(temp);
         
         this.NumberOfDebits--;
         this.DebitAccount(this.StopPaymentCharge, -1);
         msg = "Payment Stopped";
        }

        return msg;
    }

    public void overDraft(double amt) {//credit back up, debit checking, and then credit checking
        //check if prot. acc. exists
        //if one doesn't, stop transaction charge 20
        //if prot. acc. exists, determine that balance
        //if prot acc can cover cost, withdraw from savings, deposit to checking
        //if one doesn't, stop charge 20
        if (amt > balance) {
            if (HasOverDraftProtection = false)//no account protection
            {

                DebitAccount(OverDraftCharge);
                setAccountFlag(1);
            } else {//has account protection
                if /*
                         * backup account
                         */ (balance > amt)//check backup account balance // HOW?
                {
                    //  BACKUP DebitAccount(amt); //withdraw from backup account // HOW?
                    CreditAccount(amt);//deposit into checking account
                } else {//backup account doesnt have enought to withdraw
                    DebitAccount(OverDraftCharge);
                    setAccountFlag(1);
                }
            }
        } else {
            System.out.println("No over draft occured");
        }
    }
}
