/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package databaseparser;

import java.util.ArrayList;
/**
 * 
 */
abstract class AbstractAccount {
 
    //----------------------------
    //        Variables
    //----------------------------
    
    protected int customerID;//the SSN number of the customer associated with this account
    protected int accountNum;//unique account identifier number
    protected int accountType;//account type identifier
    
    protected double balance;//the current balence of the account
    protected int accountFlag;//any flaggs associated with this account
    
    protected int numofdebits;//number of recent debits
    protected ArrayList debits;//list of recent debits to this account
    protected ArrayList debitdates;//dates associated 
    

    //-----------------------------
    //      Methods
    //-----------------------------
    
    
    public double checkBalance(){return balance;}
    public abstract void updateBalance();
    public abstract void DebitAccount();
    //public abstract void CreditAccount();//not used in all classes
    
}
