package bankingsystem;

//import java.util.ArrayList;
//import java.util.Date;

/**System main file for CSC406 banking system.*/
public class SystemMain {
    
    public static void main(String[] args) {
        AccountParser a1 = new AccountParser("");
        a1.getRecordCount();
        System.out.println(a1.CheckStatusOnAllBills());
        System.out.println(a1.RollOverAllCDs());
        Application_Start_Form app1 = new Application_Start_Form();
        app1.run();
    }//end main
}