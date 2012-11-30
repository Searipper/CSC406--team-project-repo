package databaseparser;

import java.util.ArrayList;
import java.util.Date;

/**
 *  this account is for credit cards only.
 * @author sjhajeer
 */
public class CreditCards extends BillingAccounts implements CreditInterface{

    //-------------------------------------
    //  Variables
    //-------------------------------------
    private ArrayList<Double> CreditAmounts=new ArrayList<Double>();
    private ArrayList<Long> CreditDates=new ArrayList<Long>();
    private int NumberOfCredits;
    //----creditcard specific----
    private ArrayList<String> CreditDescriptions=new ArrayList<String>();
    private double CreditLimit;
    private double FinanceCharge;//average of purchases during the month
    private long DateOfFinanceCharge;//date that user paid off finance charge
    
    //-------------------------------------
    //  Constructor
    //-------------------------------------
    
    public CreditCards(int customerID, int accountNum, double balance, int accountFlag) {
        super(customerID, accountNum, balance, accountFlag);
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
            System.out.println("Number of purchases this month: "+purchases);
            System.out.println("Total value of purchases this month: "+monthlyamount);
            //get the average balence by dividing the purchase values by number of purchases
            FinanceCharge=monthlyamount/purchases;
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
    @Override
    public int getNumOfCredits() {return NumberOfCredits;}

    public String getCreditDescriptions(int index) {
        return CreditDescriptions.get(index);
    }
    
}
