package bankingsystem;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 *  this account is for credit cards only.
 * @author sjhajeer
 */
public class CreditCards extends BillingAccounts implements CreditInterface{

    //-------------------------------------
    //  Variables
    //-------------------------------------
    /**holds amounts of credit history*/
    private ArrayList<Double> CreditAmounts=new ArrayList<Double>();
    /**holds dates of credit history*/
    private ArrayList<Long> CreditDates=new ArrayList<Long>();
    /**holds number of credits*/
    private int NumberOfCredits;
    //----creditcard specific----
    /**holds purchase descriptions of credit history*/
    private ArrayList<String> CreditDescriptions=new ArrayList<String>();
    /**holds the credit limit*/
    private double CreditLimit;
    /**average of purchases during the month*/
    private double FinanceCharge;
    /**date that user paid off finance charge*/
    private long DateOfFinanceChargeReset;
    
    //-------------------------------------
    //  Constructor
    //-------------------------------------
    
    /**CreditCard Constructor 
     * @param customerID customer ssn 
     * @param accountNum Account number 
     * @param balence account starting balance
     * @param accountFlag accountFlags
     */
    public CreditCards(int customerID, int accountNum, double balance, int accountFlag) {
        super(customerID, accountNum, balance, accountFlag);
        super.setAccountType(7);
        CreditLimit=500.00;
        DateOfFinanceChargeReset=0;
    }
    /**CreditCard Constructor 
     * @param customerID customer ssn 
     * @param accountNum Account number 
     * @param balence account starting balance
     * @param accountFlag accountFlags
     * @param creditlimit credit card limit
     */
    public CreditCards(int customerID, int accountNum, double balance, int accountFlag,double creditlimit) {
        super(customerID, accountNum, balance, accountFlag);
        super.setAccountType(7);
        CreditLimit=creditlimit;
        DateOfFinanceChargeReset=0;
    }

    //-------------------------------------
    //          Methods
    //-------------------------------------
    
    //----getter & setter methods----
    
    /**get method for Credit card Credit Limit @return credit limit value*/
    public double getCreditLimit() {return CreditLimit;}
    /**set method for Credit card Credit Limit 
     * @param CreditLimit new credit limit value*/
    public void setCreditLimit(double CreditLimit){this.CreditLimit = CreditLimit;}
    /**get method for Credit card Finance charge @return finance charge value*/
    public double getFinanceCharge() {
        this.CalculateFinanceCharge();
        return FinanceCharge;
    }
    /**sets the last time the finance charge was calculated 
     * @param DateOfFinanceCharge date in long format
     */
    public void setDateOfFinanceCharge(long DateOfFinanceCharge) 
        {this.DateOfFinanceChargeReset = DateOfFinanceCharge;}
    /**get the last time the finance charge was calculated*/
    public long getDateOfFinanceCharge() {return DateOfFinanceChargeReset;}
    
    //----finace charge related----
    
    /**
     * calculate the finance charge by taking the average value of purchases 
     * made this month. get the average balance by dividing the purchase 
     * values by number of purchases
     */
    private void CalculateFinanceCharge()
    {//<editor-fold  defaultstate="collapsed">
        //check to see if there is even a need to calculate finance charge
        if(balance>0){
            double monthlyamount=0;//get the total of purchases this month
            int purchases=0;//get number of purchases this month
            for(int i=0;i<NumberOfCredits;i++){
//                    System.out.println(getCreditDates(i)+"\t"+DateOfFinanceChargeReset);
                if(getCreditDates(i)>=DateOfFinanceChargeReset){
                    monthlyamount+=getCreditAmounts(i);
//                    System.out.println(i+") $"+getCreditAmounts(i));
                    purchases++;
                }
            }
//            System.out.println("monthlyamount: $"+monthlyamount+". NOpurchases: "+purchases);
            BigDecimal roundedup = new BigDecimal(monthlyamount/purchases).setScale(2, RoundingMode.HALF_UP);
//            System.out.println(roundedup.doubleValue());
            FinanceCharge = roundedup.doubleValue();
            
            System.out.println("Finance charge value: "+FinanceCharge);
        }else{
            System.out.println("Nothing owed. no need to calculate finance charge");
        }
        //</editor-fold>
    }
    
    /**
     * Customer has paid off the credit card balance. reset finance charge to 0.
     * calculate new finance charges after present date.
     */
    private void ResetFinanceCharge(){
        FinanceCharge = 0;
        DateOfFinanceChargeReset= new Date().getTime();
    }
    
    //----Usage-&-Payments----
    
    /**Simulates the CreditCard Usage 
     * @param accountnum checks to see if this is the right account number
     * @param amount amount of the charge
     * @param desc description of the charge item
     * @return message describing the success or failure of the transaction*/
    public String UseCreditCard(int accountnum,double amount,String desc)
    {//<editor-fold  defaultstate="collapsed">
        if(accountnum==this.accountNum){
        if(amount+balance<=this.CreditLimit){
            this.CreditAccount(amount, desc);
            return"Purchase Authorized";
        }else{
            return"Purchase over Credit Limit";
        }//end if
        }else{
            return "Either wrong account or account does not exist";
        }//</editor-fold>
    }//end UseCreditCard
    
    /**Used to pay off account. 
     * @param amount amount you wish to pay off. cannot be greater than balance
     * @return String message describing the success or failure of payment
     */
    public String MakePayment(double amount)
    {//<editor-fold  defaultstate="collapsed">
        if(balance>0){
            if(!(amount>balance)){
                if(amount==balance){//payment in full. debit account and reset finance charge
                    System.out.println("about to reset finance charge.");
                    this.DebitAccount(amount);
                    this.ResetFinanceCharge();
                    this.setBillamount(this.getBillamount()-amount);
                    return"Payment of "+amount+" recieved. balance is now $"+this.balance+". Finance Charge reset";
                }else{//recived payment debit account
                    this.DebitAccount(amount);
                    this.setBillamount(this.getBillamount()-amount);
                    return"Payment of "+amount+" recieved. balance is now $"+this.balance;
                }//end else
            }else{//payment greater then amount owed. return error msg.
                return "Cannot make greater payment then amount on balance";
            }//end else
        }else{//nothing owed. return error msg.
            return"Cannot make payment. balance is 0!";
        }//end else//</editor-fold>
    }//end MakePayment
    
    //-------------------------------------
    //  Abstract Method Implementation 
    //-------------------------------------
    
    /**
     * Bill consists of the current balance and the finance charge.
     * @return Value of bill.
     */
    @Override
    public double CalculateBill() 
    {//<editor-fold  defaultstate="collapsed">
        if(balance>0){
        this.CalculateFinanceCharge();//calculate the finance charge.
        System.out.println("balance: $"+balance+" FinanceCharge: $"+this.FinanceCharge);
        double Billamount = balance+FinanceCharge;//set the bill amount
        if(FinanceCharge>0){this.CreditAccount(FinanceCharge, "Bill Finance Charge");}//if there is a finance charge apply it to the balance.
        BigDecimal roundedup = new BigDecimal(Billamount).setScale(2, RoundingMode.HALF_EVEN);
//            System.out.println(roundedup.doubleValue());
            Billamount = roundedup.doubleValue();
//        this.ResetFinanceCharge();//done with this months finance charge. reset it.
        return Billamount;//return the bill amount
        }else{
            return 0;
        }//end if//</editor-fold>
    }//end CalculateBill
    
    //-----------------------------------
    //  implemented from CreditInterface
    //-----------------------------------
    
    /**Adds amount to balance @param amount amount of purchase*/
    @Override
    public void CreditAccount(double amount) 
    {//<editor-fold  defaultstate="collapsed">
        //check against the credit limit first
        if(amount+balance<=this.CreditLimit){
            this.CreditAmounts.add(amount);
            this.CreditDates.add(new Date().getTime());
            this.NumberOfCredits++;
            this.updateBalance(amount);//add amount to the balence
        }else{System.out.println("Amount over the Credit limit");}//end if//</editor-fold>
    }//end CreditAccount
    
    /**adds purchase data @param amount purchase amount @param description text*/
        public void CreditAccount(double amount,String description) 
        {//<editor-fold  defaultstate="collapsed">
                this.CreditAmounts.add(amount);
                this.CreditDates.add(new Date().getTime());
                this.CreditDescriptions.add(description);
                this.NumberOfCredits++;
                this.updateBalance(amount);//add amount to the balence//</editor-fold>
        }//end CreditAccount

    /**adds purchase data @param amount purchase amount @param date purchase date*/
    @Override
    public void addCreditRecord(double amount, long creditdate) 
    {//<editor-fold  defaultstate="collapsed">       
        this.CreditAmounts.add(amount);
        this.CreditDates.add(creditdate);
        this.NumberOfCredits++;//</editor-fold>
    }
    /**adds purchase data @param amount purchase amount @param date purchase date @param description text*/
        public void addCreditRecord(double amount, long creditdate,String description) 
        {//<editor-fold  defaultstate="collapsed">       
            this.CreditAmounts.add(amount);
            this.CreditDates.add(creditdate);
            this.CreditDescriptions.add(description);
            this.NumberOfCredits++;//</editor-fold>
        }

    /**returns amount of a requested purchase record @param index record index @return amount*/
    @Override
    public double getCreditAmounts(int index) {return CreditAmounts.get(index);}
    
    /**returns date of a requested purchase record @param index record index @return long*/
    @Override
    public long getCreditDates(int index) {return CreditDates.get(index);}
    
    /**returns date of a requested purchase record @param index record index @return date*/
    public Date getCreditDate(int index) 
    {//<editor-fold  defaultstate="collapsed">
        Calendar cr = Calendar.getInstance();
        cr.setTime(new Date(this.CreditDates.get(index)));
        return cr.getTime();//</editor-fold>
    }   
    
    /**returns the number of purchases @return number of purchases*/
    @Override
    public int getNumOfCredits() {return NumberOfCredits;}
    
    /**returns description for a purchase in purchase history
     * @param index index of purchase description @return purchase description */
    public String getCreditDescriptions(int index) {
        return CreditDescriptions.get(index);
    }
    
    //-------------------------------------
    //  printmethods
    //-------------------------------------
    
    /**returns all of the purchases of this account. @return purchase history*/
    public String getEntirePurchaseHistory()
    {//<editor-fold  defaultstate="collapsed">
        String history="Purchase History";
        for(int i=0;i<this.NumberOfCredits;i++){
             history = history+"\nDate: "+this.getCreditDate(i)+"\t"+
                     this.getCreditAmounts(i)+"\t"+this.getCreditDescriptions(i);
        }
        return history;//</editor-fold>
    }//end getEntirePurchaseHistory
    
    /**returns all of the credits of this account since last bill. @return purchase history*/
    public String getPurchaseHistorySinceLastPayment()
    {//<editor-fold  defaultstate="collapsed">
        String history="";
        for(int i=0;i<this.NumberOfCredits;i++){
            if(this.getCreditDates(i)>this.FinanceCharge){
             history = history+"\nDate: "+this.getCreditDate(i)+"\t"+
                     this.getCreditAmounts(i)+"\t"+this.getCreditDescriptions(i);
            }//end if
        }//end for
        return history;//</editor-fold>
    }//end getPurchaseHistory
    
    /**returns all of the debits of this account. @return payment history*/
    public String getEntirePaymentHistory()
    {//<editor-fold  defaultstate="collapsed">
        String history="Payment History";
        for(int i=0;i<this.NumberOfDebits;i++){
            double amount=this.getDebitAmounts(i);
            history = history+"\nDate: "+this.getDebitDate(i)+"\n\tAmount: \t\t$"+
                    amount;
        }
        return history;//</editor-fold>
    }//end getEntirePaymentHistory
    
    /**returns all of the transactions of this account. @return transaction history*/
    @Override
    public String getTransactionHistory()
    {//<editor-fold  defaultstate="collapsed">
        String msg="Account: "+this.getAccountNum()+"\tType: "+
                this.getAccountDescription()+"\n"+
                this.getEntirePurchaseHistory()+
                "\n"+this.getEntirePaymentHistory();
        return msg;//</editor-fold>
    }
    /**returns all of the purchases and payments of this account that factor into the new bill. 
     * @return bill details*/
    @Override
    public String getBillDetails()
    {//<editor-fold  defaultstate="collapsed">
        String bill="\tBill Details:";
        bill=bill+"\n\t--------------------------------------\n"
                +"\tAmount: $"+this.getBillamount()+
                "\n\tDate sent out: "+this.getSendOutDate()+
                "\n\tDate Due: "+this.getDueDate()+
                "\n\tFinance Charge: $"+this.FinanceCharge
                +"\n\t--------------------------------------\n"
                +"\tpurchases durring month"
                +"\n\t--------------------------------------";
        for(int i=0;i<this.NumberOfCredits;i++){
            if((this.CreditDates.get(i)<=this.getBillSentOut())&&(this.CreditDates.get(i)>=this.DateOfFinanceChargeReset)){
                bill=bill+"\n\t$"+this.CreditAmounts.get(i)+"\t"+
                this.getCreditDate(i) +"\t"+this.CreditDescriptions.get(i);
            }//end if
        }//end if
        bill=bill+"\n\t--------------------------------------\n"
        +"\tpayments durring month\n\t--------------------------------------";
        for(int i=0;i<this.NumberOfDebits;i++){
            if((this.DebitDates.get(i)<=this.getBillSentOut())&&(this.DebitDates.get(i)>=this.DateOfFinanceChargeReset)){
                bill=bill+"\n\t$"+this.DebitAmounts.get(i)+"\t"+
                this.getDebitDate(i);
            }//end if
        }//end for
        return bill;//</editor-fold>
    }//end getBillDetails
    
}//end Loans
