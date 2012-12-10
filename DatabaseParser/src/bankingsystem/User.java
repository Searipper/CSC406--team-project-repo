package bankingsystem;

//import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Class for storing data for an individual user
 *
 * @author Arron
 * @author Sammy
 */
public class User {
    //----------------------------------
    //Variables for the user
    //----------------------------------

    private int ssn;
    private String fname;
    private String lname;
    private String street;
    private String city;
    private String state;
    private int zip;
    private int accesstype;
    private String username;
    private String password;
    private int cardnum;
    private int PIN;
    private long strike1;
    private long strike2;

    //----------------------------------
    //  Constructors
    //----------------------------------
    //ssn only
    public User(int ssn) {
        this.ssn = ssn;
    }
    //everything but username and password

    public User(int ssn, String fname, String lname, String street, String city,
            String state, int zip, int accestype) {
        this.ssn = ssn;
        this.fname = fname;
        this.lname = lname;
        this.street = street;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.accesstype = accestype;
    }
    //everything

    public User(int ssn, String fname, String lname, String street, String city,
            String state, int zip, int accestype, String username, String password) {
        this.ssn = ssn;
        this.fname = fname;
        this.lname = lname;
        this.street = street;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.accesstype = accestype;
        this.username = username;
        this.password = password;
    }

    //----------------------------------
    //  Methods
    //----------------------------------
    //getter & setter methods

    //access type
    public int getAccestype() {return accesstype;}
    public void setAccestype(int accestype) {this.accesstype = accestype;}
    //city
    public String getCity() {return city;}
    public void setCity(String city) {this.city = city;}
    //first name
    public String getFname() {return fname;}
    public void setFname(String fname) {this.fname = fname;}
    //last name
    public String getLname() {return lname;}
    public void setLname(String lname) {this.lname = lname;}
    //password
    public String getPassword() {return password;}
    public void setPassword(String password) {this.password = password;}
    //SSN
    public int getSsn() {return ssn;}
    public void setSsn(int ssn) {this.ssn = ssn;}
    //state
    public String getState() {return state;}
    public void setState(String state) {this.state = state;}
    //street
    public String getStreet() {return street;}
    public void setStreet(String street) {this.street = street;}
    //username
    public String getUsername() {return username;}
    public void setUsername(String username) {this.username = username;}
    //zip
    public int getZip() {return zip;}
    public void setZip(int zip) {this.zip = zip;}
    //PIN
    public int getPIN() {return PIN;}
    public void setPIN(int PIN) {this.PIN = PIN;}
    //ATM Card number
    public int getCardnum() {return cardnum;}
    public void setCardnum(int cardnum) {this.cardnum = cardnum;}
    //usage strike 1
    public long getStrike1() {return strike1;}
    public void setStrike1(long strike1) {this.strike1 = strike1;}
    //usage strike 2
    public long getStrike2() {return strike2;}
    public void setStrike2(long strike2) {this.strike2 = strike2;}
    
    //end setter and getter

    public String RegisterForATMCard(int card, int pin) {
        if (cardnum == 0 && PIN == 0) {
            cardnum = card;
            PIN = pin;
            return "Successfully registered for ATM";
        } else {
            return "User already has an ATM account";
        }
    }
    /**checks the last two ATM usages and records the current usage. if the last two usages where during today, it denys usage.
     @return <ul><li>-1: if over usage limit</li><li>1: if within usage limit</li></ul>*/
    public int UseATM() {
        /*
         * TODO insert code here to record the ATM usage. if the user has used
         * the ATM twice already today return -1 else set one of the two
         * strikes(I have two longs created at the top to hold usage dates and I
         * called them strikes) to the current date. and return 1 be sure to
         * alternate between strikes by writing over the latest date.
         */

        Calendar today = Calendar.getInstance(); //create a calendar object to set today's date for date checking


        Date currDate = new Date();
        Date yestDate = new Date();
        Date temp = new Date();

        currDate.setTime(today.getTime().getTime()); //creates a date set to today
        yestDate.setTime(currDate.getTime() - 86400000); //creates a date, set to 24 hours ago for comparison

        if (strike1 == 0) { //atm has not been used today
            strike1 = today.getTimeInMillis(); //first strike is set to the current usage        
            System.out.println(currDate + ": Processing ATM transaction, one use left today");
            return 1; //user has not used atm more than twice today, successful return
        } else if (strike1 != 0 && strike2 == 0) { //if the first strike is occupied but not the second, one use left today
            strike2 = strike1; //pass second strike to the first
            strike1 = today.getTimeInMillis(); //first strike is set to the current usage
            System.out.println(currDate + ": Processing ATM transaction, none left today");
            return 1;
        } else if (strike1 != 0 && strike2 != 0) { //if both strikes are not empty, do a range check
            System.out.println("Range check:");
            System.out.println(yestDate + " to " + currDate);
            temp.setTime(strike2);
            System.out.print(temp + " and ");
            temp.setTime(strike1);
            System.out.print(temp+"\n");
            if (strike1 <= currDate.getTime() && strike1 > yestDate.getTime()) {//check if strike1 is between range, if it is, goto strike 2 check
                if (strike2 <= currDate.getTime() && strike2 > yestDate.getTime()) {//check if strike2 is between range, if it is, game over
                    System.out.println(currDate + ": Today's usage is greater than two, no more transactions allowed!");
                    return -1;
                } //end both strike1 + 2 falling in range check and being full
            } else { //range check passes but the previous strikes need to be overwritten
                strike2 = strike1; //pass second strike to the first
                strike1 = today.getTimeInMillis(); //first strike is set to the current usage
                System.out.println(currDate + ": Processing ATM transaction, overwriting previous date, none left today");
                return 1;
            } //end else pass
        }//end else if (both strikes filled)
        System.out.println("You shouldn't have gotten this far, error?");
        return 0;
    }//end UseATM
}//end class user
