package bankingsystem;

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
    private ArrayList<Double> CreditAmounts=new ArrayList<Double>();//holds amounts of credit history
    private ArrayList<Long> CreditDates=new ArrayList<Long>();//holds dates of credit history
    private int NumberOfCredits;//holds number of credits
    //----creditcard specific----
    private ArrayList<String> CreditDescriptions=new ArrayList<String>();//holds purchase discriptions of credit history
    private double CreditLimit;//holds the credit limit
    private double FinanceCharge;//average of purchases during the month
    private long DateOfFinanceCharge;//date that user paid off finance charge
    
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
        {this.DateOfFinanceCharge = DateOfFinanceCharge;}
    /**get the last time the finance charge was calculated*/
    public long getDateOfFinanceCharge() {return DateOfFinanceCharge;}
    
    //----finace charge related----
    
    /**
     * calculate the finance charge by taking the average value of purchases 
     * made this month. get the average balance by dividing the purchase 
     * values by number of purchases
     */
    private void CalculateFinanceCharge(){
        //check to see if there is even a need to calculate finance charge
        if(balance>0){
            double monthlyamount=0;//get the total of purchases this month
            int purchases=0;//get number of purchases this month
            for(int i=0;i<NumberOfCredits;i++){
                if(getCreditDates(i)>DateOfFinanceCharge){
                    monthlyamount+=getCreditAmounts(i);
                    purchases++;
                }
            }
//            System.out.println("Number of purchases this month: "+purchases);
//            System.out.println("Total value of purchases this month: "+monthlyamount);
            //get the average balence by dividing the purchase values by number of purchases
            FinanceCharge=Math.rint(monthlyamount/purchases);
            
            System.out.println("Finance charge value: "+FinanceCharge);
        }else{
            System.out.println("Nothing owed. no need to calculate finance charge");
        }
        
    }
    /**
     * Customer has paid off the credit card balance. reset finance charge to 0.
     * calculate new finance charges after present date.
     */
    private void ResetFinanceCharge(){
        FinanceCharge = 0;
        DateOfFinanceCharge= new Date().getTime();
    }
    public String UseCreditCard(int accountnum,double amount,String desc){
        if(accountnum==this.accountNum){
        if(amount+balance<=this.CreditLimit){
            this.CreditAccount(amount, desc);
            return"Purchase Authorized";
        }else{
            return"Purchase over Credit Limit";
        }//end if
        }else{return "Either wrong account or account does not exist";}
    }//end UseCreditCard
    
    //-------------------------------------
    //  Abstract Method Implementation 
    //-------------------------------------
    
    /**
     * Bill consists of the current balance and the finance charge.
     * @return Value of bill.
     */
    @Override
    public double CalculateBill() {
        this.CalculateFinanceCharge();//calculate the finance charge.
        double Billamount = balance+FinanceCharge;//set the bill amount
        this.ResetFinanceCharge();//done with this months finance charge. reset it.
        return Billamount;//return the bill amount
    }
    
    //-----------------------------------
    //  implemented from CreditInterface
    //-----------------------------------
    
    /**Adds amount to balance @param amount amount of purchase*/
    @Override
    public void CreditAccount(double amount) {
        //check against the credit limit first
        if(amount+balance<=this.CreditLimit){
            this.CreditAmounts.add(amount);
            this.CreditDates.add(new Date().getTime());
            this.NumberOfCredits++;
            this.updateBalance(amount);//add amount to the balence
        }else{System.out.println("Amount over the Credit limit");}//end if
    }//end CreditAccount
        public void CreditAccount(double amount,String description) {
            //check against the credit limit first
            if(amount+balance<=this.CreditLimit){
                this.CreditAmounts.add(amount);
                this.CreditDates.add(new Date().getTime());
                this.CreditDescriptions.add(description);
                this.NumberOfCredits++;
                this.updateBalance(amount);//add amount to the balence
            }else{System.out.println("Amount over the Credit limit");}//end if
        }//end CreditAccount

    @Override
    public void addCreditRecord(double amount, long creditdate) {       
        this.CreditAmounts.add(amount);
        this.CreditDates.add(creditdate);
        this.NumberOfCredits++;
    }
        public void addCreditRecord(double amount, long creditdate,String description) {       
            this.CreditAmounts.add(amount);
            this.CreditDates.add(creditdate);
            this.CreditDescriptions.add(description);
            this.NumberOfCredits++;
        }

    @Override
    public double getCreditAmounts(int index) {return CreditAmounts.get(index);}
    @Override
    public long getCreditDates(int index) {return CreditDates.get(index);}
    
    public Date getCreditDate(int index) {
        Calendar cr = Calendar.getInstance();
        cr.setTime(new Date(this.CreditDates.get(index)));
        return cr.getTime();
    }   
    
    @Override
    public int getNumOfCredits() {return NumberOfCredits;}

    public String getCreditDescriptions(int index) {
        return CreditDescriptions.get(index);
    }
    //-------------------------------------
    //  printmethods
    //-------------------------------------
    public String getEntirePurchaseHistory(){
        String history="";
        for(int i=0;i<this.NumberOfCredits;i++){
             history = history+"\nDate: "+this.getCreditDate(i)+"\t"+
                     this.getCreditAmounts(i)+"\t"+this.getCreditDescriptions(i);
        }
        return history;
    }
    public String getPurchaseHistorySinceLastPayment(){
        String history="";
        for(int i=0;i<this.NumberOfCredits;i++){
            if(this.getCreditDates(i)>this.FinanceCharge){
             history = history+"\nDate: "+this.getCreditDate(i)+"\t"+
                     this.getCreditAmounts(i)+"\t"+this.getCreditDescriptions(i);
            }
        }
        return history;
    }
    
    public String getEntirePaymentHistory(){
        String history="";
        for(int i=0;i<this.NumberOfDebits;i++){
            double amount=this.getDebitAmounts(i);
            history = history+"\nDate: "+this.getDebitDate(i)+"\n\tAmount: \t\t$"+
                    amount;
        }
        return history;
    }//end getEntirePaymentHistory
}
