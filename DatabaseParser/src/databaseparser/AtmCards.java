package databaseparser;

import java.util.ArrayList;
import java.util.Date;


public class AtmCards extends Checking {
    
    //---------------------------------
    //  Variable decleration
    //---------------------------------
    
    public int pin;

    //-------------------------------------
    //  Constructor
    //-------------------------------------

    public AtmCards(int customerID, int accountNum, double balance, int accountFlag) {
        super(customerID, accountNum, balance, accountFlag);
    }

    //-----------------------------
    //      Methods
    //-----------------------------

    public int getPin() { return pin;}
    public void setPin(int pin) {this.pin = pin;}
    
    
    
    
}