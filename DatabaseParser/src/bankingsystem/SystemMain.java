package bankingsystem;

//import java.util.ArrayList;
//import java.util.Date;

/**System main file for CSC406 banking system.*/
public class SystemMain {
    
    public static void main(String[] args) {
//        AccountParser a1 = new AccountParser("");
//        a1.getRecordCount();
//        Application_Start_Form app1 = new Application_Start_Form();
//        app1.run();
        CdAccount c1 = new CdAccount(1,1,500,1);
        System.out.println("balance: $"+c1.checkBalance()+"maturity date "+c1.getEndDate()+" rollover date"+c1.getRolloverDate());
        c1.setRolloverDate(c1.getStartDate().getTime());
        System.out.println("balance: $"+c1.checkBalance()+"maturity date "+c1.getEndDate()+" rollover date"+c1.getRolloverDate());
        c1.rollOverCD();
        System.out.println("balance: $"+c1.checkBalance()+"maturity date "+c1.getEndDate()+" rollover date"+c1.getRolloverDate());
    }//end main
}