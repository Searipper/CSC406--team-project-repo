package bankingsystem;

//import java.util.ArrayList;
//import java.util.Date;

/**System main file for CSC406 banking system.*/
public class SystemMain {
    
    public static void main(String[] args) {
        UserParser u1 = new UserParser("");
        System.out.println(u1.RegisterUserForATM(559555555, 561234, 1234));
//        AccountParser a1 = new AccountParser("");
        System.out.println(u1.LoginToATM(561234, 1234));
//        a1.getRecordCount();
        
    }//end main
}