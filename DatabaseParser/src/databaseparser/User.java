/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package databaseparser;

import java.util.ArrayList;

/**
 *
 * @author Arron
 */
public class User {
    //prototype for user
    private ArrayList<String> ssn = new ArrayList<String>();
    private ArrayList<String> fname = new ArrayList<String>();
    private ArrayList<String> lname = new ArrayList<String>();
    private ArrayList<String> street = new ArrayList<String>();
    private ArrayList<String> city = new ArrayList<String>();
    private ArrayList<String> state = new ArrayList<String>();
    private ArrayList<String> zip = new ArrayList<String>();
    private ArrayList<String> type = new ArrayList<String>();
    private ArrayList<String> username = new ArrayList<String>();
    private ArrayList<String> password = new ArrayList<String>();
    
    //constructor
    public User(String ssn,String fname,String lname,String street,String city,
                String state,String zip,String type,String username,
                String password) {
        this.ssn.add(ssn);
        this.fname.add(fname);
        this.lname.add(lname);
        this.street.add(street);
        this.city.add(city);
        this.state.add(state);
        this.zip.add(zip);
        this.type.add(type);
        this.username.add(username);
        this.password.add(password);
    }
    //end of constructor

    
    //setter and getter
    public ArrayList<String> getSsn() {
        return ssn;
    }
    public void setSsn(ArrayList<String> ssn) {
        this.ssn = ssn;
    }
    public ArrayList<String> getFname() {
        return fname;
    }
    public void setFname(ArrayList<String> fname) {
        this.fname = fname;
    }
    public ArrayList<String> getLname() {
        return lname;
    }
    public void setLname(ArrayList<String> lname) {
        this.lname = lname;
    }
    public ArrayList<String> getStreet() {
        return street;
    }
    public void setStreet(ArrayList<String> street) {
        this.street = street;
    }
    public ArrayList<String> getCity() {
        return city;
    }
    public void setCity(ArrayList<String> city) {
        this.city = city;
    }
    public ArrayList<String> getState() {
        return state;
    }
    public void setState(ArrayList<String> state) {
        this.state = state;
    }
    public ArrayList<String> getZip() {
        return zip;
    }
    public void setZip(ArrayList<String> zip) {
        this.zip = zip;
    }
    public ArrayList<String> getType() {
        return type;
    }
    public void setType(ArrayList<String> type) {
        this.type = type;
    }
    public ArrayList<String> getUsername() {
        return username;
    }
    public void setUsername(ArrayList<String> username) {
        this.username = username;
    }
    public ArrayList<String> getPassword() {
        return password;
    }
    public void setPassword(ArrayList<String> password) {
        this.password = password;
    }
    
    
    //end setter and getter
}//end class user
