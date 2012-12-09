package bankingsystem;

import java.util.ArrayList;
import java.util.Calendar;//this is eaisier to calculate future dates with
import java.util.Date;

/**
 * <p>This is the abstract class for the Loans and Credit Card classes. 
 * this class lays the framework for billing out to customers with loans or 
 * credit cards</p>
 * 
 * @author sjhajeer
 * @version 1
 */
public abstract class BillingAccounts extends AbstractAccount implements DebitInterface{

    
    //---------------------------------
    //  Variable decleration
    //---------------------------------
    
    protected ArrayList<Double> DebitAmounts=new ArrayList<Double>();
    protected ArrayList<Long> DebitDates=new ArrayList<Long>();
    protected int NumberOfDebits;
    private double Billamount;//amount the bill costs
    private long BillDue; //day the bill is due
    private long BillSentOut;//day the bill was last sent out 
    private int graceperiod=7;//days after the bill is sent out that the customer has to pay off their bill

    //---------------------------------
    //  Constructor
    //---------------------------------
    
    public BillingAccounts(int customerID, int accountNum, double balance, int accountFlag) {
        super(customerID, accountNum, balance, accountFlag);//send to the constructor
        NumberOfDebits=0;
    }    
    
    //---------------------------------
    //  methods
    //---------------------------------
    
    //----getter & setter methods----
    public long getBillDue() {return BillDue;}
    public Date getDueDate(){
        Calendar cr = Calendar.getInstance();
        cr.setTime(new Date(this.BillDue));
        return cr.getTime();
    }//end getDueDate
    public void setBillDue(long BillDue) {this.BillDue = BillDue;}
    public long getBillSentOut() {return BillSentOut;}
    public Date getSendOutDate(){
        Calendar cr = Calendar.getInstance();
        cr.setTime(new Date(this.BillSentOut));
        return cr.getTime();
    }//end getSendOutDate
    public void setBillSentOut(long BillSentOut) {this.BillSentOut = BillSentOut;}
    public double getBillamount() {return Billamount;}
    public void setBillamount(double Billamount) {this.Billamount = Billamount;}
    public int getGraceperiod() {return graceperiod;}
    public void setGraceperiod(int graceperiod) {this.graceperiod = graceperiod;}
    
    /**abstract method to calculate how much this month’s payment should be*/
    public abstract double CalculateBill();//let the lower classes figure this one out
    /**
     * first Checks the current date against the due date. 
     * Then checks the customer’s recent payments to see if this month’s bill has
     * been paid off. If the customer has not paid off the bill amount the 
     * system flags the account 
     * @return <p>returns an integer number signifying the bills status</p>
     * <ul>
     * <li>if due and  paid off: return 1</li>
     * <li>if not due yet: return 0</li>
     * <li>if due and not paid off: return -1</li>
     * </ul> 
     */
    public int CheckPaymentStatus(){
        int status=-1;
        Date currentDate=new Date();
        if(currentDate.getTime()<BillDue){
            //the bill is not due yet, no need to do anything
            return 0;
        }else{
        //the bill is due. check the payment status
            double recentPaymentAmts=0;
            /**
             * goes through payment history and finds payments sent in after the
             * bill was sent out.
            */
            for(int i=0;i<NumberOfDebits;i++){
                if(DebitDates.get(i)>BillSentOut){
                 //adds the amount of the payment to recentPaymentAmts variable
                    recentPaymentAmts+=DebitAmounts.get(i);                
                }//end if
            }//end for loop
            if(Billamount<=recentPaymentAmts){
                //This months bill has been paid in full
                return 1;
            }//end if
            //if bill has not been paid off flag as problem account
            if(Billamount>recentPaymentAmts){super.setAccountFlag(1);}
        }//end else
        return status;
    }//end CheckPaymentStatus
    /**
     * Allows the manager to send out bill notices to customers. 
     * Marks the date of sendoff and the payment due date
     */
    public String SendoutBills(){
        if(new Date().getTime()>=this.BillDue){
            //calculate the amount of the bill. will be implamented in lower classes
            Billamount=CalculateBill();
            //set the date of sendoff
            BillSentOut= new Date().getTime();
            //set the due date
            //this is supposed to set the calender date when the bill is due.
            Calendar cal = Calendar.getInstance();
            //this should set the calender ahead to the date after the grace period
            cal.add(cal.DAY_OF_MONTH, graceperiod);
            //convert the calender to a date, converts the date to a long value
            BillDue = cal.getTime().getTime();
            return "Bill of $"+Billamount+" sent out on "+
                    this.getSendOutDate()+".\nBill is due in "+this.graceperiod+
                    " day(s) on "+this.getDueDate()+".";
        }else{
            return"Cannot send out a new bill until current due date is past";
        }
    }
    //---------------------------------
    //  implemented from DebitInterface
    //---------------------------------
    @Override
    public void DebitAccount(double amount) {
        if(amount<=balance){//if the payment is less then or equal to the amount owed
            DebitAmounts.add(amount);
            DebitDates.add(new Date().getTime());
            NumberOfDebits++;
            //send a negative number to detract from the balence
            this.updateBalance(0-amount);
        }else{
            System.out.println("No payments greater then the amount owed!");
        }
    }
    @Override
    public void addDebititRecord(double amount, long creditdate) {
        DebitAmounts.add(amount);
        DebitDates.add(creditdate);
        NumberOfDebits++;
    }//end addDebititRecord
    @Override
    public double getDebitAmounts(int index) {
        if(index<NumberOfDebits){
            return DebitAmounts.get(index);
        }else {
            System.out.println("index not within range of "+NumberOfDebits);
            return 0.00;
        }
    }//end getDebitAmounts
    @Override//people need to be able to search by dates
    public long getDebitDates(int index) {
        if(index<NumberOfDebits){
            return DebitDates.get(index);
        }else {
            System.out.println("index not within range of "+NumberOfDebits);
            return 0;
        }
    }//end getDebitDates
    public Date getDebitDate(int index) {
        Calendar cr = Calendar.getInstance();
        cr.setTime(new Date(this.DebitDates.get(index)));
        return cr.getTime();
    }//end getDebitDate
    @Override
    public int getNumOfDebits() {
        return NumberOfDebits;
    }//end getNumOfDebits
    
    //-------------------------------------
    //  printmethods
    //-------------------------------------
    public String getBillDetails(){
        String bill="\tAmount: $"+this.getBillamount()+
                "\n\tDate sent out: "+this.getSendOutDate()+
                "\n\tDate Due: "+this.getDueDate();
        return bill;
    }
}//end class