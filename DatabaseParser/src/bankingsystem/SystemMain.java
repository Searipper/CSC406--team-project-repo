package bankingsystem;

//import java.util.ArrayList;
//import java.util.Date;

/**System main file for CSC406 banking system.*/
public class SystemMain {
    
    public static void main(String[] args) {
//        AccountParser a1 = new AccountParser("");
//        a1.getRecordCount();
//        Application_Start_Form start = new Application_Start_Form();
//        start.run();
        SavingsAccount s1 = new SavingsAccount(1,1,1234.00,0);
        System.out.println(s1.getAccountDescription());
    }//end main
}