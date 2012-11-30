package databaseparser;

import java.util.ArrayList;

/**
 *Class for storing data for an individual user
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
    public int getAccestype() {return accesstype;}
    public void setAccestype(int accestype) {this.accesstype = accestype;}
    public String getCity() {return city;}
    public void setCity(String city) {this.city = city;}
    public String getFname() {return fname;}
    public void setFname(String fname) {this.fname = fname;}
    public String getLname() {return lname;}
    public void setLname(String lname) {this.lname = lname;}
    public String getPassword() {return password;}
    public void setPassword(String password) {this.password = password;}
    public int getSsn() {return ssn;}
    public void setSsn(int ssn) {this.ssn = ssn;}
    public String getState() {return state;}
    public void setState(String state) {this.state = state;}
    public String getStreet() {return street;}
    public void setStreet(String street) {this.street = street;}
    public String getUsername() {return username;}
    public void setUsername(String username) {this.username = username;}
    public int getZip() {return zip;}
    public void setZip(int zip) {this.zip = zip;}
    //end setter and getter
    
}//end class user
