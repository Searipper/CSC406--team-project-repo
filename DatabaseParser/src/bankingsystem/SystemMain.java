package bankingsystem;

import java.util.ArrayList;
import java.util.Date;

/**System main file for CSC406 banking system.*/
public class SystemMain {
    
    public static void main(String[] args) {
        AccountParser a1 = new AccountParser("");
        a1.getRecordCount();
        a1.CreateAccount(new CreditCards(0,0,0.00,0));
        a1.CloseAccount(0);
//        Application_Start_Form start = new Application_Start_Form();
//        start.run();
    }//end main
}