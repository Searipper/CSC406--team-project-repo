package databaseparser;

import java.util.ArrayList;
import java.io.File;
import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import javax.xml.parsers.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.*;

/**
 * <p>
 * This class is the interface between the Account Data files and the Banking system.
 * </p>
 * @author Sammy Hajeer
 * @version 1.0
 */
public class AccountParser {
       

    //-------------------------------------------------------------
    //          Variables
    //-------------------------------------------------------------
    
    private String path;
    private int recordcount;
    private ArrayList<AbstractAccount> accountobjects = new ArrayList<AbstractAccount>();
    //end data variables


    //-------------------------------------------------------------
    //          Constructor
    //-------------------------------------------------------------
    
    
    /**
     * constructor for UserParser class
     * Class {@code Object} is the root of the class hierarchy.
     * @param path an absolute URL giving the base location of the .xml file. the constructor will append the filename users.xml to this String
    */
    public AccountParser(String path) {
        this.path = path+"Accounts.xml";//this sets the file path
        ReadFile();//Reads in records
    }

    
    //-------------------------------------------------------------
    //          Getter Methods
    //-------------------------------------------------------------
    
    /**
     * <p>gets the number of records read in from the file.</p>
     * @return integer number of user records in the system. 
     */
    public int getRecordCount(){return recordcount;}
    /**get an ArrayList of all Accounts*/
    public ArrayList<AbstractAccount> getAccountobjects() {
        return accountobjects;
    }
    /**get an ArrayList of all savings accounts @return savings accounts ArrayList*/
    public ArrayList<savingsAccount> getSavingsAccounts() {
        ArrayList<savingsAccount> savings= new ArrayList<savingsAccount>();
        for(int i=0;i<recordcount;i++){
            if(accountobjects.get(i) instanceof savingsAccount){
                savings.add((savingsAccount)accountobjects.get(i));
            }
        }
        return savings;
    }
    /**get an ArrayList of all credit card accounts @return accountlist accounts ArrayList*/
    public ArrayList<CreditCards> getCreditCardAccounts() {
        ArrayList<CreditCards> accountlist= new ArrayList<CreditCards>();
        for(int i=0;i<recordcount;i++){
            if(accountobjects.get(i) instanceof CreditCards){
                accountlist.add((CreditCards)accountobjects.get(i));
            }
        }
        return accountlist;
        
    }
     /**get an ArrayList of all Checking accounts @return accountlist checking accounts ArrayList*/
    public ArrayList<Checking> getCheckingAccounts() // <editor-fold>
    { 
       
        ArrayList<Checking> accountlist= new ArrayList<Checking>();
        for(int i=0;i<recordcount;i++){
            if(accountobjects.get(i) instanceof Checking){
                accountlist.add((Checking)accountobjects.get(i));
            }
        }
        return accountlist;
    }// </editor-fold>
     /**get an ArrayList of all Loan accounts @return accountlist loan accounts ArrayList*/
    public ArrayList<LoanAccounts> getLoanAccounts() // <editor-fold>
    { 
        ArrayList<LoanAccounts> accountlist= new ArrayList<LoanAccounts>();
        for(int i=0;i<recordcount;i++){
            if(accountobjects.get(i) instanceof LoanAccounts){
                accountlist.add((LoanAccounts)accountobjects.get(i));
            }
        }
        return accountlist;
    }// </editor-fold>
    /**
     * searches the records for accounts owned by a specific Customer and returns an ArrayList of Accounts
     * @param ssn Customer's Social Security Number
     * @return usersAccounts: an ArrayList of accounts that are owned by this Customer 
     */
    public ArrayList<AbstractAccount> getCustomerAccounts(int ssn){//<editor-fold  defaultstate="collapsed">
        ArrayList<AbstractAccount>usersAccounts= new ArrayList<AbstractAccount>();
            //finds accounts owned by this user
            for(int i=0;i<this.recordcount;i++){
                if(this.accountobjects.get(i).getCustomerID()==ssn)
                    usersAccounts.add(this.accountobjects.get(i));
            }
        return usersAccounts;//</editor-fold>
    }//end getCustomerAccounts
    /**Adds an account object to the account list @param AccountObject a new account object to add to the records*/
    public void CreateAccount(AbstractAccount AccountObject){
        this.accountobjects.add(AccountObject);
        this.recordcount++;
        this.WriteFile();
    }//end CreateAccount
    /**searches for a specified account and closes it if found and the account balance=0 @param AccountNumber account Identifier*/
    public String CloseAccount(int AccountNumber){
        for(int i=0;i<this.recordcount;i++){
            if(this.accountobjects.get(i).getAccountNum()==AccountNumber){
                if(this.accountobjects.get(i).checkBalance()==0){
                    this.accountobjects.remove(i);
                    this.WriteFile();
                    return "Account Closed";
                }else{
                    return "Cannot close an account that still has a balance.";
                }//end if
            }//end if
        }//end for
        return "Could not find account";
    }

    //-------------------------------------------------------------
    //          Methods
    //-------------------------------------------------------------
    
    // <editor-fold>
    /**Finds the index of the account in arraylist by searching for the account 
     * number and returns the index of the account
     * @param accountnumber number of the account we are looking for
     * @return index of the account in the record
     */// </editor-fold>
    public int findAccountIndexByAccountNumber(int accountnumber)// <editor-fold>
    {
        int index=-1;
        for(int i = 0; i<this.recordcount;i++){
            if(this.accountobjects.get(i).getAccountNum()==accountnumber)
                return i;
        }
        System.out.println("Could not find account");
        return index;
    }// </editor-fold>

    /**
     * <t/><p>reads in an xml file</p>
     * <t/><p>
     * ---------------------------------<br/>
     * this function reads in the data from an .xml file<br/>
     * from the path declared in the constructor. the method<br/>
     * then adds the data to the correct ArrayLists and updates<br/> 
     * the user count variable. if the file path does not exist<br/>
     * it creates it by calling WriteFile()<br/>
     * ---------------------------------<br/>
     * </p>
     * @return  not a damn thing....
     */
    private void ReadFile(){
        try{// <editor-fold defaultstate="collapsed">
            //reset the usercount
            recordcount=0;
            //sets the file path
            System.out.println("In Account Read file");
            
            File f1 =new File(path);
            if(f1.exists()){
                //dom parser decleration
                // <editor-fold defaultstate="collapsed">
                DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
                Document doc = dBuilder.parse(f1);
                doc.getDocumentElement().normalize();
                // </editor-fold>
                //Get the Root element and print it.
                Element root = doc.getDocumentElement();
                String rootName = root.getNodeName();
                //Pulls nodes and attributes
                Node current = root.getFirstChild();
                //temp variables to grab data from loop
                // <editor-fold defaultstate="collapsed">
                int AccountNumber=0;
                String AccountType ="";
                int usernum=0;
                double balence = 0.00;
                int flags = 0;
                long activedate=0;
                // </editor-fold>
                System.out.println("Begin parsing file");
                try{while(current != null){

                        if(current.getNodeType() == Node.ELEMENT_NODE){
                        String nodeName = current.getNodeName();
                            NamedNodeMap attributes = current.getAttributes();
                                if(attributes.getLength()!=0){
                                    //grab account number from attribute of node object
                                    for(int i=0; i<attributes.getLength(); i++){
                                        // <editor-fold defaultstate="collapsed">
                                        Node attr = attributes.item(i);
                                        //get atribute name
                                        String attName = attr.getNodeName();
                                        //get attribute contents
                                        String attValue= attr.getNodeValue().replace("\t", "");
                                        attValue = attValue.replace("\n", "");
                                        /**
                                         * found account number. store it in a temporary variable 
                                         * until we have enough data to initialize a new account object.
                                         */
                                        AccountNumber = Integer.parseInt(attValue);
                                        // </editor-fold>
                                    }//end for
                                }//end if


                            if(current.getTextContent()!=null){
                                //now it gets the contents of an element

                                //basicaly we found a new record and now we have to go through it and 
                                Node Stats = current.getFirstChild();
                                //System.out.println(Stats.getLength());
                                while (Stats!=null){
                                    
                                    //getting basic data required by all accounts
                                    // <editor-fold defaultstate="collapsed">
                                    String text =Stats.getTextContent().replace("\t", "");
                                    text = text.replace("\n", "");
                                    //get data needed to intialize a class
                                    if(Stats.getNodeName().compareTo("AccountType")==0){ AccountType=text;}
                                    if(Stats.getNodeName().compareTo("CusID")==0){usernum=Integer.parseInt(text);}
                                    if(Stats.getNodeName().compareTo("Balance")==0){balence= Double.parseDouble(text);}
                                    if(Stats.getNodeName().compareTo("AccountFlag")==0){flags=Integer.parseInt(text);}
                                    if(Stats.getNodeName().compareTo("DateActivated")==0){activedate = Long.parseLong(text);}
                                    // </editor-fold>
                                    
                                    if(Stats.getNodeName().compareTo("AccountSpecificDetails")==0)try{
                                    //System.out.println("Found account specific details tag");

                                        //TMB
                                        // <editor-fold defaultstate="collapsed">
                                        if (AccountType.compareTo("TMB")==0)try{
//                                            System.out.println("Creating ThatsMyBank account for account #"+AccountNumber);
                                            accountobjects.add(new Checking(usernum,AccountNumber,balence,flags));
                                            recordcount++;
                                            accountobjects.get(recordcount-1).setAccountType(3);
                                            accountobjects.get(recordcount-1).setDateOfActivation(activedate);
                                            //reset loop variables:
                                            AccountNumber=0; AccountType=""; usernum=0; balence=0.00; flags=0;activedate=0;
                                            //branch deaper into the hierarchy to get the account specific data
                                            // <editor-fold>
                                                    Node AccountDetails = Stats.getFirstChild();
                                                    while(AccountDetails!=null)try{
                                                        text = AccountDetails.getTextContent().replace("\t", "");;
                                                        text = text.replace("\n", "");
                                                        
                                                        //found Overdraft protected account number
                                                        //<editor-fold defaultstate="collapsed">
                                                        if(AccountDetails.getNodeName().compareTo("ProtectedAccount")==0){
                                                            Checking a1=(Checking)accountobjects.get(recordcount-1);
                                                            a1.setProtectingAcc(Integer.parseInt(text));
                                                        }//</editor-fold>
                                                        //found Overdraft protection status
                                                        //<editor-fold defaultstate="collapsed">
                                                        if(AccountDetails.getNodeName().compareTo("OverdraftAccount")==0){
                                                            Checking a1=(Checking)accountobjects.get(recordcount-1);
                                                            if(Integer.parseInt(text)==0){
                                                                a1.setHasOverDraftProtection(false);
                                                            }else{a1.setHasOverDraftProtection(true);}
                                                        }//end if//</editor-fold>
                                                         //found recent debits
                                                        //<editor-fold defaultstate="collapsed">
                                                        if(AccountDetails.getNodeName().compareTo("RecentDebits")==0){
                                                            Checking a1=(Checking)accountobjects.get(recordcount-1);
                                                            
                                                             Node Debits = AccountDetails.getFirstChild();
                                                             
                                                            while(Debits!=null){
                                                                text = Debits.getTextContent().replace("\t", "");;
                                                                text = text.replace("\n", "");

                                                                if(Debits.getNodeName().compareTo("Debit")==0){
                                                                    attributes = Debits.getAttributes();
                                                                    Node detail = attributes.item(0);
                                                                    String date= detail.getNodeValue().replace("\t", "");
                                                                    date = date.replace("\n", "");
                                                                    a1.addDebititRecord(Double.parseDouble(text), Long.parseLong(date));

                                                                }//end if
                                                                Debits=Debits.getNextSibling();
                                                            }//end while
                                                        }//end RecentDebits//</editor-fold>
                                                        //found recent credits
                                                        //<editor-fold defaultstate="collapsed">
                                                        if(AccountDetails.getNodeName().compareTo("RecentCredits")==0){
                                                            Checking a1=(Checking)accountobjects.get(recordcount-1);
                                                            
                                                             Node Credits = AccountDetails.getFirstChild();
                                                             
                                                            while(Credits!=null){
                                                                text = Credits.getTextContent().replace("\t", "");;
                                                                text = text.replace("\n", "");

                                                                if(Credits.getNodeName().compareTo("Credit")==0){
                                                                    attributes = Credits.getAttributes();
                                                                    Node detail = attributes.item(0);
                                                                    String date= detail.getNodeValue().replace("\t", "");
                                                                    date = date.replace("\n", "");
                                                                    a1.addCreditRecord(Double.parseDouble(text), Long.parseLong(date));

                                                                }//end if
                                                                Credits=Credits.getNextSibling();
                                                            }//end while
                                                        }//end RecentCredits//</editor-fold>
                                                    AccountDetails=AccountDetails.getNextSibling();
                                                    }//end while
                                                    catch(Exception savingsloop){
                                                        System.out.println("Error in adding savings account");
                                                        System.out.println(savingsloop);
                                                        System.exit(1);
                                                    }// </editor-fold>
                                            }catch(Exception checking){
                                                System.out.println("Error in adding TMB checking account");
                                                System.out.println(checking);
                                                System.exit(1);
                                            }//end if(TMB)// </editor-fold>
                                        //G/D
                                        // <editor-fold defaultstate="collapsed">
                                        if (AccountType.compareTo("G/D")==0)try{
//                                            System.out.println("Creating Gold/Dimond account for account #"+AccountNumber);
                                            accountobjects.add(new Checking(usernum,AccountNumber,balence,flags));
                                            recordcount++;
                                            accountobjects.get(recordcount-1).setAccountType(4);
                                            accountobjects.get(recordcount-1).setDateOfActivation(activedate);
                                            //reset loop variables:
                                            AccountNumber=0; AccountType=""; usernum=0; balence=0.00; flags=0; activedate=0;
                                            //branch deaper into the hierarchy to get the account specific data
                                            // <editor-fold>
                                                    Node AccountDetails = Stats.getFirstChild();
                                                    while(AccountDetails!=null)try{
                                                        text = AccountDetails.getTextContent().replace("\t", "");;
                                                        text = text.replace("\n", "");
                                                        //found Intrest Rate
                                                        //<editor-fold defaultstate="collapsed">
                                                        if(AccountDetails.getNodeName().compareTo("InterestRate")==0){
                                                            Checking a1=(Checking)accountobjects.get(recordcount-1);
                                                            a1.setGDinterestRate(Double.parseDouble(text));
                                                        }//</editor-fold>
                                                        //found Overdraft protected account number
                                                        //<editor-fold defaultstate="collapsed">
                                                        if(AccountDetails.getNodeName().compareTo("ProtectedAccount")==0){
                                                            Checking a1=(Checking)accountobjects.get(recordcount-1);
                                                            a1.setProtectingAcc(Integer.parseInt(text));
                                                        }//</editor-fold>
                                                        //found Overdraft protection status
                                                        //<editor-fold defaultstate="collapsed">
                                                        if(AccountDetails.getNodeName().compareTo("OverdraftAccount")==0){
                                                            Checking a1=(Checking)accountobjects.get(recordcount-1);
                                                            if(Integer.parseInt(text)==0){
                                                                a1.setHasOverDraftProtection(false);
                                                            }else{a1.setHasOverDraftProtection(true);}
                                                        }//end if//</editor-fold>
                                                         //found recent debits
                                                        //<editor-fold defaultstate="collapsed">
                                                        if(AccountDetails.getNodeName().compareTo("RecentDebits")==0){
                                                            Checking a1=(Checking)accountobjects.get(recordcount-1);
                                                            
                                                             Node Debits = AccountDetails.getFirstChild();
                                                             
                                                            while(Debits!=null){
                                                                text = Debits.getTextContent().replace("\t", "");;
                                                                text = text.replace("\n", "");

                                                                if(Debits.getNodeName().compareTo("Debit")==0){
                                                                    attributes = Debits.getAttributes();
                                                                    Node detail = attributes.item(0);
                                                                    String date= detail.getNodeValue().replace("\t", "");
                                                                    date = date.replace("\n", "");
                                                                    a1.addDebititRecord(Double.parseDouble(text), Long.parseLong(date));

                                                                }//end if
                                                                Debits=Debits.getNextSibling();
                                                            }//end while
                                                        }//end RecentDebits//</editor-fold>
                                                        //found recent credits
                                                        //<editor-fold defaultstate="collapsed">
                                                        if(AccountDetails.getNodeName().compareTo("RecentCredits")==0){
                                                            Checking a1=(Checking)accountobjects.get(recordcount-1);
                                                            
                                                             Node Credits = AccountDetails.getFirstChild();
                                                             
                                                            while(Credits!=null){
                                                                text = Credits.getTextContent().replace("\t", "");;
                                                                text = text.replace("\n", "");

                                                                if(Credits.getNodeName().compareTo("Credit")==0){
                                                                    attributes = Credits.getAttributes();
                                                                    Node detail = attributes.item(0);
                                                                    String date= detail.getNodeValue().replace("\t", "");
                                                                    date = date.replace("\n", "");
                                                                    a1.addCreditRecord(Double.parseDouble(text), Long.parseLong(date));

                                                                }//end if
                                                                Credits=Credits.getNextSibling();
                                                            }//end while
                                                        }//end RecentCredits//</editor-fold>
                                                    AccountDetails=AccountDetails.getNextSibling();
                                                    }//end while
                                                    catch(Exception savingsloop){
                                                        System.out.println("Error in adding savings account");
                                                        System.out.println(savingsloop);
                                                        System.exit(1);
                                                    }// </editor-fold>
                                            }catch(Exception checkingGD){
                                                System.out.println("Error in adding Gold/Dimond checking account");
                                                System.out.println(checkingGD);
                                                System.exit(1);
                                            }//end if (G/D)// </editor-fold>
                                        //SLoan
                                        // <editor-fold defaultstate="collapsed">
                                        if (AccountType.compareTo("SLoan")==0){
//                                            System.out.println("Creating Long-Term Morgage for account #"+AccountNumber);
                                            accountobjects.add(new LoanAccounts(usernum,AccountNumber,balence,flags));
                                            recordcount++;
                                            accountobjects.get(recordcount-1).setAccountType(6);
                                            accountobjects.get(recordcount-1).setDateOfActivation(activedate);
                                            //reset loop variables:
                                            AccountNumber=0; AccountType=""; usernum=0; balence=0.00; flags=0; activedate=0;
                                            //branch deaper into the hierarchy to get the account specific data
                                                // <editor-fold>
                                                Node AccountDetails = Stats.getFirstChild();
                                                    while(AccountDetails!=null)try{
                                                        text = AccountDetails.getTextContent().replace("\t", "");
                                                        text = text.replace("\n", "");
                                                        
                                                        //found IntrestRate
                                                        //<editor-fold defaultstate="collapsed">
                                                        if(AccountDetails.getNodeName().compareTo("InterestRate")==0){
                                                            LoanAccounts a1=(LoanAccounts)accountobjects.get(recordcount-1);
                                                            a1.setInterestRate(Double.parseDouble(text));
                                                        }//</editor-fold>
                                                        //found CC bill amount
                                                        //<editor-fold defaultstate="collapsed">
                                                        if(AccountDetails.getNodeName().compareTo("BillAmount")==0){
                                                            LoanAccounts a1=(LoanAccounts)accountobjects.get(recordcount-1);
                                                            a1.setBillamount(Double.parseDouble(text));
                                                        }//end if//</editor-fold>
                                                        //found bill sent out date
                                                        //<editor-fold defaultstate="collapsed">
                                                        if(AccountDetails.getNodeName().compareTo("BillSentOutDate")==0){
                                                            LoanAccounts a1=(LoanAccounts)accountobjects.get(recordcount-1);
                                                            a1.setBillSentOut(Long.parseLong(text));
                                                        }//end if//</editor-fold>
                                                        //found bill due date
                                                        //<editor-fold defaultstate="collapsed">
                                                        if(AccountDetails.getNodeName().compareTo("BillDueDate")==0){
                                                            LoanAccounts a1=(LoanAccounts)accountobjects.get(recordcount-1);
                                                            a1.setBillDue(Long.parseLong(text));
                                                        }//end if//</editor-fold>
                                                        //found recent debits
                                                        //<editor-fold defaultstate="collapsed">
                                                        if(AccountDetails.getNodeName().compareTo("RecentDebits")==0){
                                                            LoanAccounts a1=(LoanAccounts)accountobjects.get(recordcount-1);
                                                            
                                                            Node Debits = AccountDetails.getFirstChild();
                                                            
                                                            while(Debits!=null){
                                                                text = Debits.getTextContent().replace("\t", "");;
                                                                text = text.replace("\n", "");

                                                                if(Debits.getNodeName().compareTo("Debit")==0){
                                                                    attributes = Debits.getAttributes();
                                                                    Node detail = attributes.item(0);
                                                                    double intrestgained = 0.00;
                                                                    String date=null;
                                                                    
                                                                        for(int i=0; i<attributes.getLength(); i++){
                                                                            Node attr = attributes.item(i);
                                                                            
                                                                            if(attr.getNodeName().compareTo("date")==0){
                                                                            //found date of Credit
                                                                                date = attr.getNodeValue().replace("\t", "");
                                                                            }
                                                                            if(attr.getNodeName().compareTo("intrestgained")==0){
                                                                            //found Description of credit
                                                                                intrestgained = Double.parseDouble(attr.getNodeValue());
                                                                            }
                                                                        }//end for                                       
                                                                    a1.addDebititRecord(Double.parseDouble(text), Long.parseLong(date),intrestgained);
                                                                    

                                                                }//end if
                                                                Debits=Debits.getNextSibling();
                                                            }//end while
                                                        }//end RecentDebits
                                                        //</editor-fold>
                                                        
                                                        AccountDetails=AccountDetails.getNextSibling();
                                                    }//end while
                                                    catch(Exception LLoan){
                                                        System.out.println("Error in adding SLoan account");
                                                        System.out.println(LLoan);
                                                        System.exit(1);
                                                    }// </editor-fold>
                                            }//end if (SLoan)
                                        // </editor-fold>
                                        //LLoan
                                        // <editor-fold defaultstate="collapsed">
                                        if (AccountType.compareTo("LLoan")==0){
//                                            System.out.println("Creating Long-Term Morgage for account #"+AccountNumber);
                                            accountobjects.add(new LoanAccounts(usernum,AccountNumber,balence,flags));
                                            recordcount++;
                                            accountobjects.get(recordcount-1).setAccountType(5);
                                            accountobjects.get(recordcount-1).setDateOfActivation(activedate);
                                            //reset loop variables:
                                            AccountNumber=0; AccountType=""; usernum=0; balence=0.00; flags=0; activedate=0;
                                            //branch deaper into the hierarchy to get the account specific data
                                                // <editor-fold>
                                                Node AccountDetails = Stats.getFirstChild();
                                                    while(AccountDetails!=null)try{
                                                        text = AccountDetails.getTextContent().replace("\t", "");
                                                        text = text.replace("\n", "");
                                                        
                                                        //found IntrestRate
                                                        //<editor-fold defaultstate="collapsed">
                                                        if(AccountDetails.getNodeName().compareTo("InterestRate")==0){
                                                            LoanAccounts a1=(LoanAccounts)accountobjects.get(recordcount-1);
                                                            a1.setInterestRate(Double.parseDouble(text));
                                                        }//</editor-fold>
                                                        //found CC bill amount
                                                        //<editor-fold defaultstate="collapsed">
                                                        if(AccountDetails.getNodeName().compareTo("BillAmount")==0){
                                                            LoanAccounts a1=(LoanAccounts)accountobjects.get(recordcount-1);
                                                            a1.setBillamount(Double.parseDouble(text));
                                                        }//end if//</editor-fold>
                                                        //found bill sent out date
                                                        //<editor-fold defaultstate="collapsed">
                                                        if(AccountDetails.getNodeName().compareTo("BillSentOutDate")==0){
                                                            LoanAccounts a1=(LoanAccounts)accountobjects.get(recordcount-1);
                                                            a1.setBillSentOut(Long.parseLong(text));
                                                        }//end if//</editor-fold>
                                                        //found bill due date
                                                        //<editor-fold defaultstate="collapsed">
                                                        if(AccountDetails.getNodeName().compareTo("BillDueDate")==0){
                                                            LoanAccounts a1=(LoanAccounts)accountobjects.get(recordcount-1);
                                                            a1.setBillDue(Long.parseLong(text));
                                                        }//end if//</editor-fold>
                                                        //found recent debits
                                                        //<editor-fold defaultstate="collapsed">
                                                        if(AccountDetails.getNodeName().compareTo("RecentDebits")==0){
                                                            LoanAccounts a1=(LoanAccounts)accountobjects.get(recordcount-1);
                                                            
                                                            Node Debits = AccountDetails.getFirstChild();
                                                            
                                                            while(Debits!=null){
                                                                text = Debits.getTextContent().replace("\t", "");;
                                                                text = text.replace("\n", "");

                                                                if(Debits.getNodeName().compareTo("Debit")==0){
                                                                    attributes = Debits.getAttributes();
                                                                    Node detail = attributes.item(0);
                                                                    double intrestgained = 0.00;
                                                                    String date=null;
                                                                    
                                                                        for(int i=0; i<attributes.getLength(); i++){
                                                                            Node attr = attributes.item(i);
                                                                            
                                                                            if(attr.getNodeName().compareTo("date")==0){
                                                                            //found date of Credit
                                                                                date = attr.getNodeValue().replace("\t", "");
                                                                            }
                                                                            if(attr.getNodeName().compareTo("intrestgained")==0){
                                                                            //found Description of credit
                                                                                intrestgained = Double.parseDouble(attr.getNodeValue());
                                                                            }
                                                                        }//end for                                        
                                                                    a1.addDebititRecord(Double.parseDouble(text), Long.parseLong(date),intrestgained);
                                                                    

                                                                }//end if
                                                                Debits=Debits.getNextSibling();
                                                            }//end while
                                                        }//end RecentDebits
                                                        //</editor-fold>
                                                        AccountDetails=AccountDetails.getNextSibling();
                                                    }//end while
                                                    catch(Exception LLoan){
                                                        System.out.println("Error in adding LLoan account");
                                                        System.out.println(LLoan);
                                                        System.exit(1);
                                                    }// </editor-fold>
                                            }//end if (LLoan)
                                        // </editor-fold>
                                        //CDs
                                        // <editor-fold defaultstate="collapsed">
                                        if (AccountType.compareTo("CD")==0){
//                                            System.out.println("Creating CD for account #"+AccountNumber);
                                            accountobjects.add(new cdAccount(usernum,AccountNumber,balence,flags));
                                            recordcount++;
                                            accountobjects.get(recordcount-1).setAccountType(2);
                                            accountobjects.get(recordcount-1).setDateOfActivation(activedate);
                                            //reset loop variables:
                                            AccountNumber=0; AccountType=""; usernum=0; balence=0.00; flags=0; activedate=0;
                                            //branch deaper into the hierarchy to get the account specific data
                                            // <editor-fold>
                                                    Node AccountDetails = Stats.getFirstChild();
                                                    while(AccountDetails!=null)try{
                                                        text = AccountDetails.getTextContent().replace("\t", "");
                                                        text = text.replace("\n", "");
                                                        
                                                        //found IntrestRate
                                                        // <editor-fold defaultstate="collapsed">
                                                        if(AccountDetails.getNodeName().compareTo("MaturityDate")==0){
                                                            cdAccount a1=(cdAccount)accountobjects.get(recordcount-1);
                                                            a1.setEndDate(Long.parseLong(text));
                                                        }//end if//</editor-fold>
                                                        //found Maturity Date
                                                        // <editor-fold defaultstate="collapsed">
                                                        if(AccountDetails.getNodeName().compareTo("BillAmount")==0){
                                                            cdAccount a1=(cdAccount)accountobjects.get(recordcount-1);
                                                            a1.setInterestRate(Double.parseDouble(text));
                                                        }//end if//</editor-fold>
                                                        
                                                        AccountDetails=AccountDetails.getNextSibling();
                                                    }//end while
                                                    catch(Exception creditcardsloop){
                                                        System.out.println("Error in adding creditcard account");
                                                        System.out.println(creditcardsloop);
                                                        System.exit(1);
                                                    }// </editor-fold>
                                            }
                                        //end if (CD)
                                        // </editor-fold>
                                        //CreditCards
                                        // <editor-fold defaultstate="collapsed">
                                        if (AccountType.compareTo("CC")==0){
                                            
//                                            System.out.println("Creating CreditCardt for account #"+AccountNumber);
                                            accountobjects.add(new CreditCards(usernum,AccountNumber,balence,flags));
                                            recordcount++;
                                            accountobjects.get(recordcount-1).setAccountType(7);
                                            accountobjects.get(recordcount-1).setDateOfActivation(activedate);
                                            //reset loop variables:
                                            AccountNumber=0; AccountType=""; usernum=0; balence=0.00; flags=0; activedate=0;
                                            //branch deaper into the hierarchy to get the account specific data
                                            // <editor-fold>
                                                    Node AccountDetails = Stats.getFirstChild();
                                                    while(AccountDetails!=null)try{
                                                        text = AccountDetails.getTextContent().replace("\t", "");
                                                        text = text.replace("\n", "");
                                                        
                                                        //found CC bill amount
                                                        //<editor-fold defaultstate="collapsed">
                                                        if(AccountDetails.getNodeName().compareTo("BillAmount")==0){
                                                            CreditCards a1=(CreditCards)accountobjects.get(recordcount-1);
                                                            a1.setBillamount(Double.parseDouble(text));
                                                        }//end if//</editor-fold>
                                                        //found bill sent out date
                                                        //<editor-fold defaultstate="collapsed">
                                                        if(AccountDetails.getNodeName().compareTo("BillSentOutDate")==0){
                                                            CreditCards a1=(CreditCards)accountobjects.get(recordcount-1);
                                                            a1.setBillSentOut(Long.parseLong(text));
                                                        }//end if//</editor-fold>
                                                        //found bill due date
                                                        //<editor-fold defaultstate="collapsed">
                                                        if(AccountDetails.getNodeName().compareTo("BillDueDate")==0){
                                                            CreditCards a1=(CreditCards)accountobjects.get(recordcount-1);
                                                            a1.setBillDue(Long.parseLong(text));
                                                        }//end if//</editor-fold>
                                                        //found credit limit
                                                        //<editor-fold defaultstate="collapsed">
                                                        if(AccountDetails.getNodeName().compareTo("CreditLimit")==0){
                                                            CreditCards a1=(CreditCards)accountobjects.get(recordcount-1);
                                                            a1.setCreditLimit(Double.parseDouble(text));
                                                        }//end if//</editor-fold>
                                                        //found recent debits
                                                        //<editor-fold defaultstate="collapsed">
                                                        if(AccountDetails.getNodeName().compareTo("RecentDebits")==0){
                                                            CreditCards a1=(CreditCards)accountobjects.get(recordcount-1);
                                                            
                                                            Node Debits = AccountDetails.getFirstChild();
                                                            
                                                            while(Debits!=null){
                                                                text = Debits.getTextContent().replace("\t", "");;
                                                                text = text.replace("\n", "");

                                                                if(Debits.getNodeName().compareTo("Debit")==0){
                                                                    attributes = Debits.getAttributes();
                                                                    Node detail = attributes.item(0);
                                                                    String date= detail.getNodeValue().replace("\t", "");
                                                                    date = date.replace("\n", "");
                                                                    a1.addDebititRecord(Double.parseDouble(text), Long.parseLong(date));

                                                                }//end if
                                                                Debits=Debits.getNextSibling();
                                                            }//end while
                                                        }//end RecentDebits//</editor-fold>
                                                        //found recent credits
                                                        //<editor-fold defaultstate="collapsed">
                                                        if(AccountDetails.getNodeName().compareTo("RecentCredits")==0){
                                                            CreditCards a1=(CreditCards)accountobjects.get(recordcount-1);
                                                            
                                                             Node Credits = AccountDetails.getFirstChild();
                                                             
                                                            while(Credits!=null){
                                                                text = Credits.getTextContent().replace("\t", "");
                                                                text = text.replace("\n", "");

                                                                if(Credits.getNodeName().compareTo("Credit")==0){
                                                                    attributes = Credits.getAttributes();
                                                                    Node detail = attributes.item(0);
                                                                    String description=null;
                                                                    String date=null;
                                                                    
                                                                        for(int i=0; i<attributes.getLength(); i++){
                                                                            Node attr = attributes.item(i);
                                                                            
                                                                            if(attr.getNodeName().compareTo("date")==0){
//                                                                                System.out.println("Found date");
                                                                            //found date of Credit
                                                                                date = attr.getNodeValue().replace("\t", "");
                                                                            }
                                                                            if(attr.getNodeName().compareTo("description")==0){
//                                                                                System.out.println("Found description: "+attr.getNodeValue());
                                                                            //found Description of credit
                                                                                description = attr.getNodeValue();
                                                                            }
                                                                        }//end for
                                                                    
                                                                    a1.addCreditRecord(Double.parseDouble(text), Long.parseLong(date),description);

                                                                }//end if
                                                                Credits=Credits.getNextSibling();
                                                            }//end while
                                                        }//end RecentCredits//</editor-fold>
                                                     
                                                        AccountDetails=AccountDetails.getNextSibling();
                                                    }//end while
                                                    catch(Exception creditcardsloop){
                                                        System.out.println("Error in adding creditcard account");
                                                        System.out.println(creditcardsloop);
                                                        System.exit(1);
                                                    }// </editor-fold>
                                        }//end if (CC)
                                        // </editor-fold>
                                        //Savings
                                        // <editor-fold defaultstate="collapsed">
                                        if (AccountType.compareTo("Savings")==0)try{
                                            
//                                            System.out.println("Creating Savings account for account #"+AccountNumber);
                                            accountobjects.add(new savingsAccount(usernum,AccountNumber,balence,flags));
                                            recordcount++;
                                            accountobjects.get(recordcount-1).setAccountType(1);
                                            accountobjects.get(recordcount-1).setDateOfActivation(activedate);
                                            //reset loop variables:
                                            AccountNumber=0; AccountType=""; usernum=0; balence=0.00; flags=0; activedate=0;
                                            //branch deaper into the hierarchy to get the account specific data
                                            // <editor-fold>
                                                    Node AccountDetails = Stats.getFirstChild();
                                                    while(AccountDetails!=null)try{
                                                        text = AccountDetails.getTextContent().replace("\t", "");;
                                                        text = text.replace("\n", "");
                                                        //found Overdraft protection status
                                                        //<editor-fold defaultstate="collapsed">
                                                        if(AccountDetails.getNodeName().compareTo("OverdraftAccount")==0){
                                                            savingsAccount a1=(savingsAccount)accountobjects.get(recordcount-1);
                                                            if(Integer.parseInt(text)==0){
                                                                a1.setOverdraftAcc(false);
                                                            }else{a1.setOverdraftAcc(true);}
                                                        }//end if//</editor-fold>
                                                        //found Intrest Rate
                                                        //<editor-fold defaultstate="collapsed">
                                                        if(AccountDetails.getNodeName().compareTo("InterestRate")==0){
                                                            savingsAccount a1=(savingsAccount)accountobjects.get(recordcount-1);
                                                            a1.setInterestRate(Double.parseDouble(text));
                                                        }//</editor-fold>
                                                        //found recent debits
                                                        //<editor-fold defaultstate="collapsed">
                                                        if(AccountDetails.getNodeName().compareTo("RecentDebits")==0){
                                                            savingsAccount a1=(savingsAccount)accountobjects.get(recordcount-1);
                                                            
                                                             Node Debits = AccountDetails.getFirstChild();
                                                             
                                                            while(Debits!=null){
                                                                text = Debits.getTextContent().replace("\t", "");;
                                                                text = text.replace("\n", "");

                                                                if(Debits.getNodeName().compareTo("Debit")==0){
                                                                    attributes = Debits.getAttributes();
                                                                    Node detail = attributes.item(0);
                                                                    String date= detail.getNodeValue().replace("\t", "");
                                                                    date = date.replace("\n", "");
                                                                    a1.addDebititRecord(Double.parseDouble(text), Long.parseLong(date));

                                                                }//end if
                                                                Debits=Debits.getNextSibling();
                                                            }//end while
                                                        }//end RecentDebits//</editor-fold>
                                                        //found recent credits
                                                        //<editor-fold defaultstate="collapsed">
                                                        if(AccountDetails.getNodeName().compareTo("RecentCredits")==0){
                                                            savingsAccount a1=(savingsAccount)accountobjects.get(recordcount-1);
                                                            
                                                             Node Credits = AccountDetails.getFirstChild();
                                                             
                                                            while(Credits!=null){
                                                                text = Credits.getTextContent().replace("\t", "");;
                                                                text = text.replace("\n", "");

                                                                if(Credits.getNodeName().compareTo("Credit")==0){
                                                                    attributes = Credits.getAttributes();
                                                                    Node detail = attributes.item(0);
                                                                    String date= detail.getNodeValue().replace("\t", "");
                                                                    date = date.replace("\n", "");
                                                                    a1.addCreditRecord(Double.parseDouble(text), Long.parseLong(date));

                                                                }//end if
                                                                Credits=Credits.getNextSibling();
                                                            }//end while
                                                        }//end RecentCredits//</editor-fold>
                                                        AccountDetails=AccountDetails.getNextSibling();
                                                    }//end while
                                                    catch(Exception savingsloop){
                                                        System.out.println("Error in adding savings account");
                                                        System.out.println(savingsloop);
                                                        System.exit(1);
                                                    }// </editor-fold>
                                        }//end if (simple savings)
                                        catch(Exception savings){
                                            System.out.println("Error in adding savings account");
                                            System.out.println(savings);
                                            System.exit(1);
                                            
                                        }// </editor-fold>
                                        
                                        }//end if (AccountSpecificDetails)
                                        catch(Exception AddingAccountError){
                                            System.out.println("Error in adding account");
                                            System.out.println(AddingAccountError);
                                            System.exit(1);
                                        }
                                    //go to the next account
                                    Stats = Stats.getNextSibling();
                                }//end while
                            }//end if
                        }//end if
                        current = current.getNextSibling();
                    }//end while
                }catch(Exception e){
                    System.out.println("\n=========================\nexception in parsing file");
                    System.out.println(e);
                    System.exit(1);
                }//end exception
            }else{
                //No file was found so we will make one
                //WriteFile();
            }
            }catch(Exception e){
                System.out.println("\n=====================================\nError");
                System.out.println(e);
                System.exit(1);
            }//end exception
        // </editor-fold>
    }//end ReadFile()
 
    
    public void WriteFile(){
        File f1 = new File(path);
        try{//<editor-fold  defaultstate="collapsed">
            //FileOutputStream fileout = new FileOutputStream(f1);
            PrintWriter p1 = new PrintWriter(f1);
            
            if (f1.exists()){
                p1.println("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>");
                p1.println("<Accounts>");
                for(int i=0;i<this.recordcount;i++){
                    
                p1.println("\t<Account"+" AccountNum=\""+this.accountobjects.get(i).accountNum +"\">");
                    //sets the account typ text
                    p1.print("\t\t<AccountType>");{/* <editor-fold defaultstate="collapsed">*/
                        if (this.accountobjects.get(i) instanceof savingsAccount)p1.print("Savings");
                        if (this.accountobjects.get(i) instanceof Checking){
                            if(this.accountobjects.get(i).getAccountType()==4){p1.print("G/D");}
                            else{p1.print("TMB");}
                        }
                        if (this.accountobjects.get(i) instanceof cdAccount)p1.print("CD");
                        if (this.accountobjects.get(i) instanceof CreditCards)p1.print("CC");
                        if (this.accountobjects.get(i) instanceof LoanAccounts){
                            if(this.accountobjects.get(i).getAccountType()==5){p1.print("LLoan");}
                            else{p1.print("SLoan");}}
                    /* </editor-fold>*/}
                    p1.print("</AccountType>\n");
                    //sets the customer ssn associated with account
                    p1.println("\t\t<CusID>"+this.accountobjects.get(i).getCustomerID()+"</CusID>");
                    //sets account balence
                    p1.println("\t\t<Balance>"+this.accountobjects.get(i).checkBalance()+"</Balance>");
                    //sets account flags
                    p1.println("\t\t<AccountFlag>"+this.accountobjects.get(i).getAccountFlag()+"</AccountFlag>");
                    //sets activation date
                    p1.println("\t\t<DateActivated>"+this.accountobjects.get(i).getDateOfActivation()+"</DateActivated>");
                    //sets data specific to account types
                    p1.println("\t\t<AccountSpecificDetails>");
                    // <editor-fold defaultstate="collapsed">
                        //if Savings account
                        // <editor-fold defaultstate="collapsed">
                        if (this.accountobjects.get(i) instanceof savingsAccount){
                            savingsAccount a1=(savingsAccount)this.accountobjects.get(i);
                            int over=0;
                            if(a1.isOverdraftAcc){over=1;}else{over=0;}
                            p1.println("\t\t<OverdraftAccount>"+over+"</OverdraftAccount>");
                            p1.println("\t\t<InterestRate>"+a1.getInterestRate()+"</InterestRate>");
                            p1.println("\t\t<RecentDebits>");
                                for(int j=0;j<a1.getNumOfDebits();j++){
                                    p1.println("\t\t\t<Debit date=\""+a1.getDebitDates(j) +"\">"+a1.getDebitAmounts(j) +"</Debit>");
                                }//end debits
                            p1.println("\t\t</RecentDebits>");
                            
                            p1.println("\t\t<RecentCredits>");
                                for(int j=0;j<a1.getNumOfCredits();j++){
                                    p1.println("\t\t\t<Credit date=\""+a1.getCreditDates(j) +"\">"+a1.getCreditAmounts(j) +"</Credit>");
                                }//end Credits
                            p1.println("\t\t</RecentCredits>");
                        }//end if savings
                        // </editor-fold>
                        //if CD account
                        // <editor-fold defaultstate="collapsed">
                        if(this.accountobjects.get(i) instanceof cdAccount){
                            cdAccount a1=(cdAccount)this.accountobjects.get(i);
                            p1.println("\t\t\t<MaturityDate>"+a1.getEndDate()+"</MaturityDate>");
                            p1.println("\t\t\t<InterestRate>"+a1.getInterestRate()+"</InterestRate>");
                        }//end if CDs
                        // </editor-fold>
                        //if Checking account
                        // <editor-fold defaultstate="collapsed">
                        if(this.accountobjects.get(i) instanceof Checking){
                            Checking a1=(Checking)this.accountobjects.get(i);
                            
                            //overdraft protection status
                            int protection=0; if(a1.isHasOverDraftProtection()){protection=1;}
                            p1.println("\t\t\t<OverdraftProtection>"+protection+"</OverdraftProtection>");
                            //overdraft account number
                            if(a1.isHasOverDraftProtection()){
                                p1.println("\t\t\t<OverdraftAccount>"+a1.getBackupAccountNumber()+"</OverdraftAccount>");
                            }
                            //G/D intrest rate
                            if(a1.getAccountType()==4){
                                p1.println("\t\t\t<InterestRate>"+a1.getGDinterestRate()+"</InterestRate>");
                            }
                            //debits
                            p1.println("\t\t\t<RecentDebits>");
                                for(int j=0;j<a1.getNumOfDebits();j++){
                                    p1.println("\t\t\t\t<Debit date=\""+a1.getDebitDates(j) +"\">"+a1.getDebitAmounts(j) +"</Debit>");
                                }//end debits
                            p1.println("\t\t\t</RecentDebits>");
                            //credits
                            p1.println("\t\t\t<RecentCredits>");
                                for(int j=0;j<a1.getNumOfCredits();j++){
                                    p1.println("\t\t\t\t<Credit date=\""+a1.getCreditDates(j) +"\">"+a1.getCreditAmounts(j) +"</Credit>");
                                }//end Credits
                            p1.println("\t\t\t</RecentCredits>");
                        }//end checking// </editor-fold>
                        //if Loans
                        // <editor-fold defaultstate="collapsed">
                        if(this.accountobjects.get(i) instanceof LoanAccounts){
                            LoanAccounts a1=(LoanAccounts)this.accountobjects.get(i);
                            //account intrest rate
                            p1.println("\t\t\t<InterestRate>"+a1.getInterestRate()+"</InterestRate>");
                            //bill senout date
                            p1.println("\t\t\t<BillSentOutDate>"+a1.getBillSentOut()+"</BillSentOutDate>");
                            //bill amount
                            p1.println("\t\t\t<BillAmount>"+a1.getBillamount()+"</BillAmount>");
                            //bill due date
                            p1.println("\t\t\t<BillDueDate>"+a1.getBillDue()+"</BillDueDate>");
                            //debits
                            p1.println("\t\t\t<RecentDebits>");
                                for(int j=0;j<a1.getNumOfDebits();j++){
                                    p1.println("\t\t\t\t<Debit date=\""+a1.getDebitDates(j) +"\" intrestgained=\""+a1.getIntrestGained(j) +"\">"+a1.getDebitAmounts(j) +"</Debit>");
                                }//end debits
                            p1.println("\t\t\t</RecentDebits>");
                        }//end loans// </editor-fold>
                        //if CreditCards
                        // <editor-fold defaultstate="collapsed">
                        if(this.accountobjects.get(i) instanceof CreditCards){
                            CreditCards a1=(CreditCards)this.accountobjects.get(i);
                            //credit limit
                            p1.println("\t\t\t<CreditLimit>"+a1.getCreditLimit()+"</CreditLimit>");
                            //bill sendout
                            p1.println("\t\t\t<BillSentOutDate>"+a1.getBillSentOut()+"</BillSentOutDate>");
                            //bill amount
                            p1.println("\t\t\t<BillAmount>"+a1.getBillamount()+"</BillAmount>");
                            //bill due date
                            p1.println("\t\t\t<BillDueDate>"+a1.getBillDue()+"</BillDueDate>");
                            //debits
                            p1.println("\t\t\t<RecentDebits>");
                                for(int j=0;j<a1.getNumOfDebits();j++){
                                    p1.println("\t\t\t\t<Debit date=\""+a1.getDebitDates(j) +"\">"+a1.getDebitAmounts(j) +"</Debit>");
                                }//end debits
                            p1.println("\t\t\t</RecentDebits>");
                            //credits
                            p1.println("\t\t\t<RecentCredits>");
                                for(int j=0;j<a1.getNumOfCredits();j++){
                                    p1.println("\t\t\t\t<Credit date=\""+a1.getCreditDates(j) +"\""+" description=\""+
                                            a1.getCreditDescriptions(j)+"\">"+a1.getCreditAmounts(j) +"</Credit>");
                                }//end Credits
                            p1.println("\t\t\t</RecentCredits>");
                        }//end if CreditCards
                        // </editor-fold>
                        // </editor-fold>
                    p1.println("\t\t</AccountSpecificDetails>");
                    p1.println("\t</Account>");
                }//end loop
                p1.println("</Accounts>");
                p1.close();
                
                //update records of change
                ReadFile();
                
            }//end WriteFile//</editor-fold>
        }catch(Exception e){
            System.out.println("Exception in file output stream");
            System.out.println(e);
        }
    }
    //-------------------------------------------------------------
    //          Print Methods
    //-------------------------------------------------------------
    
    
}//end AccountParser
