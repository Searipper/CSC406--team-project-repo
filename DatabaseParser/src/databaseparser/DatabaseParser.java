package databaseparser;

import java.util.ArrayList;
import java.util.Date;

/**System main file for CSC406 banking system.*/
public class DatabaseParser {
    
    public static void main(String[] args) {
        AccountParser a1 = new AccountParser("");
        a1.getRecordCount();
        
        ArrayList<savingsAccount> savings=a1.getSavingsAccounts();
        
        for(int i=0;i<savings.size();i++){
            System.out.println("Account #"+savings.get(i).getAccountNum()+
                    " Account holder:"+savings.get(i).getCustomerID()+
                    " Balence: $"+savings.get(i).checkBalance()+
                    " is overdraft:"+savings.get(i).isOverdraftAcc()+
                    " Intrest Rate: "+savings.get(i).getInterestRate()+
                    "Recent Debits: "+savings.get(i).getNumOfDebits()+"");
            
                    for(int j=0;j<savings.get(i).getNumOfDebits();j++){
                        Date day1=new Date(savings.get(i).getDebitDates(j));
                        System.out.println(day1.toString()+" $"+savings.get(i).getDebitAmounts(i));
                    }
        }
    }//end main
}//end class
