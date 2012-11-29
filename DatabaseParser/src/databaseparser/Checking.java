
package databaseparser;


import java.io.*;
import javax.swing.*;
import java.util.*;
import java.lang.*;


public class Checking extends AbstractAccount implements  DebitInterface, CreditInterface {

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

    public int AcctType;// 0 = TMB 1=GD
    public int StopPaymentCheckNumber;// stop payment check number
    public int BackupAccountNumber;//Account number for over draft backup account

    public boolean HasOverDraftProtection;//True = yes False = No

    protected ArrayList<Double> DebitAmounts=new ArrayList<Double>();
    protected ArrayList<Long> DebitDates=new ArrayList<Long>();
    protected int NumberOfDebits;

    protected ArrayList<Double> CreditAmounts=new ArrayList<Double>();
    protected ArrayList<Long> CreditDates=new ArrayList<Long>();
    protected int NumberOfCredits;


    //---------------------------------
    //  Constructor
    //---------------------------------

    public Checking(int customerID, int accountNum, double balance, int accountFlag) {
        super(customerID, accountNum, balance, accountFlag);//send to the constructor
        NumberOfDebits=0;
    }

    //-----------------------------
    //      Methods
    //-----------------------------

    public double getGDinterestRate() {return GDinterestRate;}
    public void setGDinterestRate(double GDinterestRate) {this.GDinterestRate = GDinterestRate;}

    public int getAcctType() {return AcctType;}
    public void setAcctType(int AcctType) {this.AcctType = AcctType;}

    public int getBackupAccountNumber() {return BackupAccountNumber;}
    public void setBackupAccountNumber(int BackupAccountNumber) {this.BackupAccountNumber = BackupAccountNumber;}

    public double getGDavgBalance() {return GDavgBalance;}
    public void setGDavgBalance(double GDavgBalance) {this.GDavgBalance = GDavgBalance;}

    public double getGDinterest() {return GDinterest;}
    public void setGDinterest(double GDinterest) {this.GDinterest = GDinterest;}

    public boolean isHasOverDraftProtection() {return HasOverDraftProtection;}
    public void setHasOverDraftProtection(boolean HasOverDraftProtection) {this.HasOverDraftProtection = HasOverDraftProtection;}

    public int getStopPaymentCheckNumber() {return StopPaymentCheckNumber;}
    public void setStopPaymentCheckNumber(int StopPaymentCheckNumber) {this.StopPaymentCheckNumber = StopPaymentCheckNumber; }

    //---------------------------------
    //  implemented from AbstractAccount
    //---------------------------------

    public void updateBalance(double balance) {
        this.balance = this.balance+balance;
    }

    //---------------------------------
    //  implemented from DebitInterface
    //---------------------------------
    
    public void addDebititRecord(double amount, long creditdate) {
        DebitAmounts.add(amount);
        DebitDates.add(creditdate);
        NumberOfDebits++;
    }//end addDebitRecord
    
    public void addCredititRecord(double amount, long creditdate) {
        DebitAmounts.add(amount);
        DebitDates.add(creditdate);
        NumberOfDebits++;
    }//end addDebitRecord
    @Override
    public void DebitAccount(double amount) {//WITHDRAW
        if(amount<=balance){//if the amount to WITHDRAW is less then or equal to balance
            DebitAmounts.add(amount);
            DebitDates.add(new Date().getTime());
            NumberOfDebits++;
            //send a negative number to detract from the balence
            this.updateBalance(0-amount);
        }else{
            System.out.println("Withdrawing more money than in account");
        }
    }
    @Override
      public long getDebitDates(int index) {
        if(index<NumberOfDebits){
            return DebitDates.get(index);
        }else {
            System.out.println("index not within range of "+NumberOfDebits);
            return 0;
        }
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
      @Override
      public int getNumOfDebits() {
        return NumberOfDebits;
    }

    //-----------------------------------
    //  implemented from CreditInterface
    //-----------------------------------
    @Override
      public void addCreditRecord(double amount, long creditdate) {
        this.CreditAmounts.add(amount);
        this.CreditDates.add(creditdate);
        this.NumberOfCredits++;
    }

    @Override
    public void CreditAccount(double amount) {//DEPOSIT
            CreditAmounts.add(amount);
            CreditDates.add(new Date().getTime());
            NumberOfCredits++;
            updateBalance(amount);//add amount to the balence
    }//end CreditAccount
    @Override
      public long getCreditDates(int index) {return CreditDates.get(index);}
    @Override
      public double getCreditAmounts(int index) {return CreditAmounts.get(index);}
    @Override
      public int getNumOfCredits() {return NumberOfCredits;}
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

    

   






