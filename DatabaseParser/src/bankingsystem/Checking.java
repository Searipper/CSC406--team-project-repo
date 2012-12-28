package bankingsystem;

import java.util.*;
import java.lang.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Calendar;

public class Checking extends AtmCards {

    //---------------------------------
    //  Variable decleration
    //---------------------------------
    /**  */
    /** TMB transaction charge*/
    private static final double TMBtransCharge = .5;
    /** TMB Monthly transaction charge */
    private static final double TMBmonthlyTransCharge = .75;
    /**  GD minimum balance*/
    private static final double GDminBalance = 1000.00;
    /**  Stop payment charge*/
    private static final double StopPaymentCharge = 15.00;
    /**  Overdraft charge*/
    private static final double OverDraftCharge = 20.00;
    /**  GD interest rate (savingAccountRate*.5)*/
    protected double GDinterestRate = .015;
    /** GD average of the balance */
    protected double GDavgBalance;
    /** Interest Amount to be added to balance */
    protected double GDinterest; 
    /** The account# of backup overdraft protection account */
    public int protectingAcct; 
    /**  Check number, sets first check number to 0*/
    protected int CheckNumber = 0;
    /**  True = yes False = No*/
    protected boolean HasOverDraftProtection;
    /**  Array list of check numbers*/
    protected ArrayList<Integer> CheckNumbers = new ArrayList<Integer>();
    /**  Array list of Transaction charges*/
    protected ArrayList<Double> TransactionChargeList = new ArrayList<Double>();
    /**  Array list of Transfer charges*/
    protected ArrayList<Double> TransferChargeList = new ArrayList<Double>();
    /**  Array list of Deposits*/
    protected ArrayList<Double> DepositList = new ArrayList<Double>();

    //---------------------------------
    //  Constructor
    //---------------------------------
    
    /** This is the Checking Account Constructor
     *  @param customerID customer's SSN 
     *  @param accountNum customer's account number
     *  @param balance customer's account balance
     *  @param accountFlag 0 = No flags on account
     */
    public Checking(int customerID, int accountNum, double balance, 
            int accountFlag) {
        super(customerID, accountNum, balance, accountFlag);//send constructor
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
    
    /**  Gets the balance of the account
     *   @return balance
     */
    public double getBalance() {
        return balance;
    }
    /**  Sets the balance of the account
     *   @param balance this is the balance to set it to
     */
    public void setBalance(double balance) {
        this.balance = balance;
    }
    /** Gets the interest rate of a GD account
     *  @return GDinterestRate
     */
    public double getGDinterestRate() {
        return GDinterestRate;
    }
   /**  Sets the interest rate of a GD account
    *   @param GDinterestRate this is the rate to set it to
    */
    public void setGDinterestRate(double GDinterestRate) {
        this.GDinterestRate = GDinterestRate;
    }
    /**  Gets the account type of the account 3 = TMB 4 = GD
     *   @return  accountType
     */
    public int getAcctType() {
        return accountType;
    }
    /**  Sets the account type of the account 3 = TMB 4 = GD
     *   @param AcctType this is the account type to set the account to
     */
    public void setAcctType(int AcctType) {
        this.accountType = AcctType;
    }
    /**  Gets the average balance of a GD account
     *   @return GDavgBalance
     */
    public double getGDavgBalance() {
        return GDavgBalance;
    }
     /**  Sets the average balance of a GD account
      *   @param GDavgBalance this is the GD avg balance to set
      */
    public void setGDavgBalance(double GDavgBalance) {
        this.GDavgBalance = GDavgBalance;
    }
     /**  Gets the interest of a GD account
      *   @return GDinterest
      */
    public double getGDinterest() {     
        return GDinterest*.5;
    }
    /**  Sets the interest of a GD account
     *   @param GDinterest this is the interest rate to set to
     */
    public void setGDinterest(double GDinterest) {
        this.GDinterest = GDinterest;
    }
    /**  Sets hasOverDraftProtection, true = yes or false = no
     *   @param HasOverDraftProtection Sets to true or false
     */
    public void setHasOverDraftProtection(boolean HasOverDraftProtection) {
        this.HasOverDraftProtection = HasOverDraftProtection;
    }
    /**  Gets hasOverDraftProtection, true = yes or false = no
     *   @return HasOverDraftProtection   
     */
    public boolean getHasOverDraftProtection() {
        return HasOverDraftProtection;
    }
    /**  Sets the check number
     *   @param checkNumber this is the check number to set it to
     */
    public void setCheckNumber(int CheckNumber) {
        this.CheckNumber = CheckNumber;
    }
    /** Gets a check number for the account
     *  @return CheckNumber
     */
    public int getCheckNumber() {
        return CheckNumber;
    }
    /**  Gets stop payment check number*/
    public int getStopPaymentCheckNumber() {
        return CheckNumber;
    }
    /**  Sets the backup protecting account
     *   @param protectingAcc account number of protecting account
     */
    public void setProtectingAcc(int protectingAcc) {
        this.protectingAcct = protectingAcc;
        if (this.protectingAcct != 0) {
            this.setHasOverDraftProtection(true);
        }
    }
    /**  Gets the protecting backup account number
     *   @return protectionAcct
     */
    public int getProtectingAcc() { //sets account to protect from ODing
        return protectingAcct;
    }
    /**  Gets a list of transaction charges
     *   @return TransactionChargeList
     */
    public double getTransactionChargeList(int index) {
        return TransactionChargeList.get(index);
    }
    /**  Gets a list of transfer charges
     *   @return TransferChargeList   
     */
    public double getTransferChargeList(int index) {
        return TransferChargeList.get(index);
    }
    /**  Gets a list check numbers
     *   @return CheckNumbers
     */
    public int getCheckNumbers(int index) {
        return CheckNumbers.get(index);
    } 
     /**  Gets an array list of check numbers 
      *   @return CheckNumbers
      */   
    public ArrayList<Integer> getCheckNumList(){        
        return this.CheckNumbers;
    }

    //-----------------------------
    //      Other Methods
    //-----------------------------
      /** Applies TMB transaction charge */  
    public void applyTMBtransCharge() {//charges Account with a transaction charge
        if (accountType == 3) {
            balance = balance - TMBtransCharge;
            System.out.println("A $.50 transaction charge has been charged to account balance.");
        } else {
            System.out.println("This is a Gold/Diamond Account, No transaction charge needed");
        }
    }
    /** Applies TMB Transfer charge */
    public void applyTMBmonthlyTransCharge() {//charges Account with a montly transaction charge
        if (accountType == 3) {
            balance = balance - TMBmonthlyTransCharge;
            System.out.println("A $.75 monthly transaction charge has been charged to account balance.");
        } else {
            System.out.println("This is a Gold/Diamond Account, No monthly transaction charge needed");
        }
    }
    /** Calculates the average balance of a GD account*/
    public void calcGDavgBalance() {//calcs GD average balance
        GDavgBalance = balance / 30;
        BigDecimal roundedup = new BigDecimal(GDavgBalance).setScale(2, RoundingMode.HALF_UP);
        GDavgBalance=roundedup.doubleValue();
        
        System.out.println("Calculating Gold/Diamond average balance. $" + GDavgBalance);
    }
    /**  Calculates the interest of a GD account*/
    public void calcGDinterest() { //calcs GD interst
        GDinterest = GDavgBalance * GDinterestRate;
        BigDecimal roundedup = new BigDecimal(GDinterest).setScale(2, RoundingMode.HALF_UP);
        GDinterest=roundedup.doubleValue();
        System.out.println("Calculating Gold/Diamond interest. $" + GDinterest);
    }
    /**  Applies the Interest earned on a GD account*/
    public void applyGDinterest() {//adds interest rate to acctBalance        
        if (accountType == 4) {
            calcGDavgBalance();
            calcGDinterest();
            
            this.Deposit(this.GDinterest);
            System.out.println("Adding Gold/Diamond interest to account balance. $" + GDinterest);
        } else {
            System.out.println("Error - Cannot apply Gold/Diamond interest to a TMB account.");
        }
    }
    /** Transfers amount from account
     *  @param amount this is the amount to be transfered
     *  @return amount
     */
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
    /** Deposits amount into account
     *  @param amount this is the amount to be deposited
     */
    public void Deposit(double amount) {
     
        this.CreditAmounts.add(amount);
        this.CreditDates.add(new Date().getTime());
        this.NumberOfCredits++;
        this.updateBalance(amount);//add amount to the balence

        if (balance <= 999.99) {
            accountType = 3;
            System.out.println("Account Updated to 3, after deposit");
        } else {
            accountType = 4;
            System.out.println("Account Updated to 4, after deposit");
        }
    }
    /** Debits account by the amount
     *  @param amount This the amount to be debited
     *  @param checknum This is the check number for the debit
     */
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
    /** Adds a debit record to the array lists
     *  @param amount This is the amount to be debited
     *  @param  DateOfActivation This is the date of debit
     *  @param checknumber This is the checknumber of the debit
     *  @param transaction This is the transaction charge
     *  @param transfer This is the transfer charge
     */
    public void addDebititRecord(double amount, long DateOfActivation, 
            int checknumber, double transaction, double transfer) {
        super.addDebititRecord(amount, DateOfActivation);
        CheckNumber++;
        this.CheckNumbers.add(checknumber);
        this.TransactionChargeList.add(transaction);
        this.TransferChargeList.add(transfer);
    }
    /** Credits an account by the amount
     *  @param amount This is the amount to be credited
     *  @param checknum This is the check number of the credit
     */   
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
    /** Adds a credit Record to the array list
     *  @param amount This is the amount to be credited
     *  @param date This is the date of the credit
     *  @param transaction This is the transaction charge
     */    
      public void addCreditRecord(double amount,long date, double transaction) {
        super.addCreditRecord(amount, date);        
        this.DepositList.add(transaction);
    }
      /** Stop the payment of a check 
       *  @param StopPaymentCheckNumber this is the check number that you want to stop
       *  @return msg
       */    
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
         System.out.println("Payment Stopped");
         
          if (balance <= 999.99) {
            accountType = 3;
            System.out.println("Account Updated to 3, after deposit");
            
            } else {
                accountType = 4;
                System.out.println("Account Updated to 4, after deposit");
                }
        }

        return msg;
    }
    /** OverDraft the account 
     *  @param amt this is the amount to withdraw from overdraft protected account
     */
    public void overDraft(double amt) {//credit back up, debit checking, and then credit checking
   
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

    //------------------------------------
    //      Impliments from DebitInterface
    //------------------------------------
    
    /**  Withdraw from checking account
     *   @param amount this is the amount to be withdrawn
     *   @param AccountParser This is the AccountParser object
     *   @return status
     */   
    public int Withdrawl(double amount, AccountParser ap) 
    {

        int status = -1;
        if(accountType==3){
            
        
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
            }
        } else if (accountType==4){
            if (amount <= (balance )) {//if the payment is less then or equal to the balence
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
            }
        }
        
        else if (this.HasOverDraftProtection) {

            SavingsAccount s1 = ap.getSavingsAccount(this.protectingAcct);
            if (amount <= this.balance + s1.checkBalance()+this.TMBtransCharge) {
                double temp=0.00;
                if(accountType==4){
                    temp=amount - balance;
                }else{
                    temp=amount - (balance - this.TMBtransCharge);
                }
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
                this.accountFlag++;
                return status;
            }

        } else {
            this.DebitAccount(OverDraftCharge, -1);
            System.out.println("Account Overdrafted charging fee");
            this.accountFlag++;
            return status;
        }
return status;
    }
    
    /**  Withdraws amount from the account
     *   @param amount this is the amount to be withdrawn
     *   @param CheckNum this is the Check number
     *   @param AccountParser This is the AccountParser object
     *   @return status
     */   
    public int Withdrawl(double amount,int CheckNum, AccountParser ap) {

        int status = -1;
        if (amount + this.TMBtransCharge <= (balance )) {//if the payment is less then or equal to the balence
            status = 1;
            //int checknum = this.CheckNumber + 1;
            this.DebitAccount(amount, CheckNum);
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
    
    public int ATMwithdrawal(double amount) {

        int status = -1;
        if(accountType==3){
            
        
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
            }
        } else if (accountType==4){
            if (amount <= (balance )) {//if the payment is less then or equal to the balence
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
            }
        }
        else{
            System.out.println("amount grater then account balance");
            return status;
        }
        return status;
    }
    
    //--------------------------------------
    //      Implements From Abstract Account
    //--------------------------------------
    
    @Override
    /**
     * Gets the Transaction History for the account @returns the transaction
     * history
     */
    public String getTransactionHistory() {
        Calendar c1 = Calendar.getInstance();
        Date temp = new Date();
        String history = "";
        //prints out all debits
        for (int i = 0; i < this.NumberOfDebits; i++) {
            temp.setTime(this.getDebitDates(i));
            double amount = this.getDebitAmounts(i);
            switch(this.CheckNumbers.get(i)){
                case -1:
                    history = history + "Stop payment\t";
                    break;
                case 0:
                    history = history + "ATM withdrawl\t";
                    break;
                default:
                    history = history + "check NO: "+this.CheckNumbers.get(i)+"\t";

        }
            history = history + "Date: " + temp + "\n\tDebit Amount: \t\t$" + amount+"\n";
        

        }

       //prints out all credits
        for (int i = 0; i < this.NumberOfCredits; i++) {
            temp.setTime(this.getCreditDates(i));
            double amount = this.getCreditAmounts(i);
            history = history + "\nDate: " + temp + "\n\tCredit Amount: \t\t$" + amount;

        }
        System.out.println(history);
        return history;

    }
}
