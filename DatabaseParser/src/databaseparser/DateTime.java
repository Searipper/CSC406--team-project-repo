package fall2012_csc406_banking_system;

import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
 
 
public class DateTime {
  public static void main(String[] args) {
 
	   DateFormat dateFormat = new SimpleDateFormat("E MM/dd/yyyy HH:mm:ss a");
	   //get current date time with Date()
	   Date date = new Date();
	   System.out.println(dateFormat.format(date));Calendar cal = Calendar.getInstance();
	   System.out.println(dateFormat.format(cal.getTime()));
 
  }
}