package databaseparser;

import java.util.ArrayList;
import java.util.Date;

/**
 * account for loans
 * @author sjhajeer
 */
public class LoanAccounts extends BillingAccounts{
    
    //---------------------------------
    //  Variable decleration
    //---------------------------------
    
    /**used to calculate interest on payments*/
    private double InterestRate;
    /**date the loan is to be paid off by*/
    private long EndLoanDate;
    private ArrayList<Double> IntrestGained;
    private double FixedPaymentAmount;
    
    
    //---------------------------------
    //  Constructor
    //---------------------------------
    
    public LoanAccounts(int customerID, int accountNum, double balance, int accountFlag) {
        super(customerID, accountNum, balance, accountFlag);
    }
    
    //-----------------------------
    //      Methods
    //-----------------------------
    
    /**returns the Interest Rate value @return IntrestRate: double*/
    public double getInterestRate() {return InterestRate;}
    /**sets the interest rate for the loan account. 
     * @param IntrestRate percentage that the bank takes from each payment*/
    public void setInterestRate(double InterestRate) {this.InterestRate = InterestRate;}
    /**Gets the end date for the loan @return EndLoanDate: long*/
    public long getEndLoanDate() {return EndLoanDate;}
    /**Sets the EndDate for the loan @param EndLoanDate date by which the loan is to be paid off*/
    public void setEndLoanDate(long EndLoanDate) {this.EndLoanDate = EndLoanDate;}
    /**Returns the amount for the next payment*/
    public double getFixedPaymentAmount() {
        this.setFixedPaymentAmount();
        return FixedPaymentAmount;
    }
    /**sets the Fixed payment amount = (Principle * Interest Rate) / 365 days*/
    private void setFixedPaymentAmount() {
        this.FixedPaymentAmount=((this.balance*this.InterestRate)/365)*30;
    }
    /**get the interest Incurred on a specific payment*/
    public double getIntrestGained(int index) {
        return IntrestGained.get(index);
    }
    
    
    //----------------------------------
    //  implemented from BillingAccounts
    //----------------------------------
    @Override
    public double CalculateBill() {
        double billamount=0;
        if(balance>0){
            billamount=this.getFixedPaymentAmount();
            return billamount;
        }else{
            System.out.println("Nothing owed. Need to close loan account");
            return billamount;
        }//end else
    }
    
    @Override
    public void DebitAccount(double amount) {
        if(amount<=balance&&amount>0){//if the payment is less then or equal to the amount owed
            
            //calculating intrest on payment
            double intrest = amount * this.InterestRate;
            
            DebitAmounts.add(amount);
            DebitDates.add(new Date().getTime());
            this.IntrestGained.add(intrest);
            NumberOfDebits++;
            //the amount applied to the principal is the amount - intrest
            amount= amount - intrest;
            //send a negative number to detract from the principal
            this.updateBalance(0-amount);
        }else{
            System.out.println("No payments greater then the amount owed!");
        }
    }//end DebitAccount
    public void addDebititRecord(double amount, long creditdate,double interest) {
        DebitAmounts.add(amount);
        DebitDates.add(creditdate);
        this.IntrestGained.add(interest);
        NumberOfDebits++;
    }//end addDebitRecord
}
