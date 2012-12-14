package bankingsystem;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * this account is for Long-term mortgages and Short-term loans
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
    /**Array to hold Interest the bank earns on payments*/
    private ArrayList<Double> IntrestGained= new ArrayList<Double>();
    /**initial amount of the loan*/
    private double IntitialLoanAmount;
    /**number of years the loan is to be paid back over*/
    private int loanyears;
    /**the fixed payment amount of the loan*/
    private double FixedPaymentAmount;
    
    
    //---------------------------------
    //  Constructor
    //---------------------------------
    
    /**constructs a loan account.
     * @param customerID account holder ssn
     * @param accountNum Unique account identifier
     * @param balance amount the loan is taken out for.
     * @param accountFlag flags on the account.
     */
    public LoanAccounts(int customerID, int accountNum, double balance, int accountFlag) {
        super(customerID, accountNum, balance, accountFlag);
        super.setAccountType(5);
        this.setLoanyears(5);
        this.setFixedPaymentAmount(0.00);
    }//end constructor
    
    /**constructs a loan account and sets the type of loan based off of the length of the loan.
     * @param customerID account holder ssn
     * @param accountNum Unique account identifier
     * @param balance amount the loan is taken out for.
     * @param accountFlag flags on the account.
     * @param loanlength number of years account is to be paid back over.
     */
    public LoanAccounts(int customerID, int accountNum, double balance, int accountFlag,int loanlength) {
        super(customerID, accountNum, balance, accountFlag);
        Calendar ed = Calendar.getInstance(); //create a calendar object to set today's date for account creation
        ed.add(ed.YEAR, loanlength);    //sets loan length
        loanyears=loanlength;
        this.IntitialLoanAmount=balance;
        EndLoanDate = ed.getTime().getTime();//sets loan length
        if(loanlength<15){
            super.setAccountType(6);//if less then 15 years in length it is a short-term Loan
            InterestRate=0.03;
        }else{
            super.setAccountType(5);//else it is a Long-term Mortgage
            InterestRate=0.015;
        }//end if
        this.CalculateFixedPaymentAmount();
    }//end constructor
    
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
    /**Gets the end date for the loan @ return EndLoanDate: date*/
    
    public Date getEndDate() {
        Calendar cr = Calendar.getInstance();
        cr.setTime(new Date(this.EndLoanDate));
        return cr.getTime();
    }    
    /**Sets the EndDate for the loan @param EndLoanDate date by which the loan is to be paid off*/
    public void setEndLoanDate(long EndLoanDate) {this.EndLoanDate = EndLoanDate;}
    /**Returns the amount for the next payment*/
    public double getFixedPaymentAmount() {
        if(this.FixedPaymentAmount==0){this.CalculateFixedPaymentAmount();}
        return FixedPaymentAmount;
    }
    /**sets the amount for the next payment*/
    public void setFixedPaymentAmount(double FixedPaymentAmount) {
        this.FixedPaymentAmount = FixedPaymentAmount;
    }
    /**sets the Fixed payment amount = (Principle * Interest Rate) / 365 days*/
    public void CalculateFixedPaymentAmount() {
        /**           InitialLoanAmount + Interest
         *  Monthly = ----------------------------
         *  Payment    years of loan * 12 months
         */
        double payment = (this.IntitialLoanAmount+this.CalcIntrest(this.IntitialLoanAmount))/(this.loanyears*12);
        try{
        BigDecimal roundedup = new BigDecimal(payment).setScale(2, RoundingMode.HALF_UP);
        payment = roundedup.doubleValue();
        }catch(Exception e){
            System.out.println("error here");
        }
//        System.out.println("Loan amount: $"+this.IntitialLoanAmount+" Intrest of account $"+
//                CalcIntrest(this.IntitialLoanAmount)+" Loan months: "+this.loanyears+" years * 12\n"
//                +"Total loan value: $"+(this.IntitialLoanAmount+this.CalcIntrest(this.IntitialLoanAmount))+
//                "\ttotal months:"+(this.loanyears*12)+"\nmonthly payment: "+payment);
        this.FixedPaymentAmount=payment;
    }//end setFixedPaymentAmount

    private double getMonthlyIntrestAmount(){
        double intrest = (this.CalcIntrest(this.IntitialLoanAmount)/(this.loanyears*12));
        BigDecimal roundedup = new BigDecimal(intrest).setScale(2, RoundingMode.HALF_UP);
        intrest=roundedup.doubleValue();
        return intrest;
    }//end getMonthlyIntrestAmount
    
    /**get the interest Incurred on a specific payment*/
    public double getIntrestGained(int index) {
        return IntrestGained.get(index);
    }
    /**returns the Initial balance or principle of the loan@return Initial Loan Amount*/
    public double getIntitialLoanAmount() {return IntitialLoanAmount;}
    /**sets the Initial balance or principle of the loan@param InitialLoanAmount Initial Loan Amount*/
    public void setIntitialLoanAmount(double IntitialLoanAmount) {this.IntitialLoanAmount = IntitialLoanAmount;}
    /**returns the number of years the loan is for@return number of years*/
    public int getLoanyears() {return loanyears;}
    /**sets the number of years the loan is for@param loanyears number of years*/
    public void setLoanyears(int loanyears) {this.loanyears = loanyears;}
    /**this method calculates the interest on a payment given. 
     * @param amount amount to calculate interest on
     * @return interest the bank will take from payment*/
    
    public double CalcIntrest (double amount){
        double intrest = amount * this.InterestRate;
        return intrest;
    }
    /**this method returns the amount needed to completely payoff the account
     * @return amount needed to pay off loan*/
    public double getPayoffAmount(){
        double totalneeded= balance+CalcIntrest(balance);
        return totalneeded;
    }
    /**this method makes a fixed payment on the loan amount. 
     * to find the amount of this payment call getFixedPaymentAmount()
     * @return Amount of the payment
     */
    public String MakePayment(){
        
        if(this.FixedPaymentAmount==0){
            this.CalculateFixedPaymentAmount();
        }
        if(balance!=0){//if the payment is less then or equal to the amount owed
            
            System.out.println("Making payment of: "+this.FixedPaymentAmount);
            
            System.out.println(balance+" "+getMonthlyIntrestAmount());
            this.DebitAccount(this.FixedPaymentAmount,getMonthlyIntrestAmount());
            
                    this.setBillamount(this.getBillamount()-FixedPaymentAmount);
            System.out.println(balance);
            return "Payment of $"+this.FixedPaymentAmount+" made. bal: $"+this.balance;
            
        }else{
            return "Nothing owed";
        }
        
    }//end MakePayment
    
    //----------------------------------
    //  implemented from BillingAccounts
    //----------------------------------
    
    /**Calculates the Minimum monthly bill @return bill amount*/
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
    }//end CalculateBill
    
    public void DebitAccount(double amount,double intrest) {
        System.out.println("amt: "+amount+" intrest: "+intrest);
        if(amount>balance){
            System.out.println("No payments greater then the amount owed!");
        }else if(amount<0){//if the payment is less then the amount owed
            System.out.println("payment must be greater than 0!");
        }else{
            //calculating intrest on payment
            DebitAmounts.add(amount);
            DebitDates.add(new Date().getTime());
            this.IntrestGained.add(intrest);
            NumberOfDebits++;
            System.out.println("payment made. balance is now $"+this.balance);
            //the amount applied to the principal is the amount - intrest
            amount= amount - intrest;
            //send a negative number to detract from the principal
            this.updateBalance(0-amount);
        }
    }//end DebitAccount
    public void addDebititRecord(double amount, long creditdate,double interest) {
        DebitAmounts.add(amount);
        DebitDates.add(creditdate);
        this.IntrestGained.add(interest);
        NumberOfDebits++;
    }//end addDebitRecord
    
    //-------------------------------------
    //  printmethods
    //-------------------------------------
    
    /**returns all of the transactions of this account. @return transaction history*/
    @Override
    public String getTransactionHistory(){
        String history="";
        for(int i=0;i<this.NumberOfDebits;i++){
            double amount=this.getDebitAmounts(i),
                    intrest=this.getIntrestGained(i);
            history = history+"\nDate: "+this.getDebitDate(i)+"\n\tAmount: \t\t$"+
                    amount+" \n\tApplied to intrest: \t$"+intrest+" \n\tApplied to Principle: \t$"+(amount-intrest);
        }
        return history;
    }//end getEntirePaymentHistory
}//end Loans
