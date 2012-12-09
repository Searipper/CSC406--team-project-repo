package bankingsystem;

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
 * It reads and writes between the account.xml file and searches and returns specified accounts.
 * </p>
 * @author Sammy Hajeer
 * @version 1.8
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
    
    //<editor-fold  defaultstate="collapsed">
    /** 
     * constructor for UserParser class
     * Class {@code Object} is the root of the class hierarchy.
     * @param path an absolute URL giving the base location of the .xml file. the constructor will append the filename users.xml to this String
    *///</editor-fold>
    public AccountParser(String path) {
        this.path = path+"Accounts.xml";//this sets the file path
        ReadFile();//Reads in records
//        EnforceRefEnteg(0);
    }

    
    //-------------------------------------------------------------
    //          Getter Methods
    //-------------------------------------------------------------
    
    /**
     * <p>gets the number of records read in from the file.</p>
     * @return integer number of user records in the system. 
     */
    public int getRecordCount(){return recordcount;}
    
    
    //-------------------------------------
    //  get Arraylist of Accounts Methods
    //-------------------------------------
    
    /**get an ArrayList of all Accounts*/
    public ArrayList<AbstractAccount> getAccountobjects() 
    {//<editor-fold  defaultstate="collapsed">
        return accountobjects;//</editor-fold>
    }
    
    /**get an ArrayList of all Checking accounts @return accountlist checking accounts ArrayList*/
    public ArrayList<Checking> getCheckingAccounts()
    { //<editor-fold  defaultstate="collapsed">
        ArrayList<Checking> accountlist= new ArrayList<Checking>();
        for(int i=0;i<recordcount;i++){
            if(accountobjects.get(i) instanceof Checking){
                accountlist.add((Checking)accountobjects.get(i));
            }//end if
        }//end for
        return accountlist;//</editor-fold>
    }//end getCheckingAccounts
    
     /**get an ArrayList of all Loan accounts @return accountlist loan accounts ArrayList*/
    public ArrayList<LoanAccounts> getLoanAccounts()
    { //<editor-fold  defaultstate="collapsed">
        ArrayList<LoanAccounts> accountlist= new ArrayList<LoanAccounts>();
        for(int i=0;i<recordcount;i++){
            if(accountobjects.get(i) instanceof LoanAccounts){
                accountlist.add((LoanAccounts)accountobjects.get(i));
            }
        }
        return accountlist;// </editor-fold>
    }
    /**get an ArrayList of all CreditCard accounts @return ArrayList of CreditCard accounts*/
    public ArrayList<CreditCards> getCreditCardAccounts() 
    {//<editor-fold  defaultstate="collapsed">
        ArrayList<CreditCards> accountlist= new ArrayList<CreditCards>();
        for(int i=0;i<recordcount;i++){
            if(accountobjects.get(i) instanceof CreditCards){
                accountlist.add((CreditCards)accountobjects.get(i));
            }//edn if
        }//end for
        return accountlist;//</editor-fold>
    }//end getSavingsAccounts
    
    /**get an ArrayList of all CD accounts @return ArrayList of CD Accounts*/
    public ArrayList<CdAccount> getCDAccounts() 
    {//<editor-fold  defaultstate="collapsed">
        ArrayList<CdAccount> accountlist= new ArrayList<CdAccount>();
        for(int i=0;i<recordcount;i++){
            if(accountobjects.get(i) instanceof CdAccount){
                accountlist.add((CdAccount)accountobjects.get(i));
            }//edn if
        }//end for
        return accountlist;//</editor-fold>
    }//end getSavingsAccounts
    
    /**get an ArrayList of all savings accounts @return savings accounts ArrayList*/
    public ArrayList<SavingsAccount> getSavingsAccounts() 
    {//<editor-fold  defaultstate="collapsed">
        ArrayList<SavingsAccount> savings= new ArrayList<SavingsAccount>();
        for(int i=0;i<recordcount;i++){
            if(accountobjects.get(i) instanceof SavingsAccount){
                savings.add((SavingsAccount)accountobjects.get(i));
            }//edn if
        }//end for
        return savings;//</editor-fold>
    }//end getSavingsAccounts
    
    //<editor-fold defaultstate="collapsed">
    /** 
     * searches the records for accounts owned by a specific Customer and returns an ArrayList of Accounts
     * @param ssn Customer's Social Security Number
     * @return usersAccounts: an ArrayList of accounts that are owned by this Customer 
     *///</editor-fold>
    public ArrayList<AbstractAccount> getCustomerAccounts(int ssn)
    {//<editor-fold  defaultstate="collapsed">
        ArrayList<AbstractAccount>usersAccounts= new ArrayList<AbstractAccount>();
            //finds accounts owned by this user
            for(int i=0;i<this.recordcount;i++){
                if(this.accountobjects.get(i).getCustomerID()==ssn)
                    usersAccounts.add(this.accountobjects.get(i));
            }
        return usersAccounts;//</editor-fold>
    }//end getCustomerAccounts
    
    //<editor-fold defaultstate="collapsed">
    /**method that searches records for an account by ATM card number and 
     * returns an ArrayList of ATM card accounts with that card number
     * @param cardnum the card number of the account.
     * @return an arraylist of accounts with the specified account number
     *///</editor-fold>
    public ArrayList<AtmCards> getMyATM(int cardnum)
    {//<editor-fold  defaultstate="collapsed">
        ArrayList<AtmCards> atm = new ArrayList<AtmCards>();
        for(int i=0;i<this.getRecordCount();i++){
            if(this.accountobjects.get(i) instanceof AtmCards){
                AtmCards account =(AtmCards)accountobjects.get(i);
                if(account.getCardNum()==cardnum){
                    System.out.println("Match");
                    atm.add(account);
                }//end if
            }//end if
        }//end fot
        return atm;//</editor-fold>
    }//end getMyATM()
    
    //------------------------------------
    //  get Individual Account Methods
    //------------------------------------
    
    //<editor-fold defaultstate="collapsed">
    /**
     * takes an account number and returns the perspective account if in records.
     * @param accountnumber ID number of the account we are searching for.
     * @return <ul> <li>If found, returns the account searched for</li>
     *              <li>If not found, returns a blank account</li></ul>
     *///</editor-fold>
    public  AbstractAccount getAccount(int accountnumber) 
    {//<editor-fold  defaultstate="collapsed">
        AbstractAccount a1= new SavingsAccount(-1,-1,0.00,1);
        for(int i=0;i<recordcount;i++){
            if(accountobjects.get(i).getAccountNum()==accountnumber){
                a1= accountobjects.get(i);
                System.out.println("Found account now returning");
                return a1;
            }//end if
        }//end for
        System.out.println("Account not found,returning blank account");
        return a1;
        //</editor-fold>
    }//end getAccount
    
    //<editor-fold defaultstate="collapsed">
    /**
     * takes an account number and returns the perspective credit card account if in records.
     * @param accountnumber ID number of the account we are searching for.
     * @return <ul> <li>If found, returns the credit card account searched for</li>
     *              <li>If not found, returns a blank credit card account</li></ul>
     *///</editor-fold>
    public  CreditCards getCreditCardAccount(int accountnumber) 
    {//<editor-fold  defaultstate="collapsed">
        CreditCards a1= new CreditCards(-1,-1,0.00,1);
        for(int i=0;i<recordcount;i++){
            if((accountobjects.get(i).getAccountNum()==accountnumber)&&(accountobjects.get(i) instanceof CreditCards)){
                a1= (CreditCards)accountobjects.get(i);
                System.out.println("Found CreditCard account now returning");
                return a1;
            }//end if
            if((accountobjects.get(i).getAccountNum()==accountnumber)&&!(accountobjects.get(i) instanceof CreditCards)){
                System.out.println("Found account, but it is not a CreditCard account");
            }//end if
        }//end for
        System.out.println("could not retrieve account, returning blank account");
        return a1;
        //</editor-fold>
    }//end getCreditCardAccount
    
    //<editor-fold defaultstate="collapsed">
    /**
     * takes an account number and returns the perspective checking account if in records.
     * @param accountnumber ID number of the account we are searching for.
     * @return <ul> <li>If found, returns the checking account searched for</li>
     *              <li>If not found, returns a blank checking account</li></ul>
     *///</editor-fold>
    public  Checking getCheckingAccount(int accountnumber) 
    {//<editor-fold  defaultstate="collapsed">
        Checking a1= new Checking(-1,-1,0.00,1);
        for(int i=0;i<recordcount;i++){
            if((accountobjects.get(i).getAccountNum()==accountnumber)&&(accountobjects.get(i) instanceof Checking)){
                a1= (Checking)accountobjects.get(i);
                System.out.println("Found Checking account now returning");
                return a1;
            }//end if
            if((accountobjects.get(i).getAccountNum()==accountnumber)&&!(accountobjects.get(i) instanceof Checking)){
                System.out.println("Found account, but it is not a Checking account");
            }//end if
        }//end for
        System.out.println("could not retrieve account, returning blank account");
        return a1;
        //</editor-fold>
    }//end getCheckingAccount
    
    //<editor-fold defaultstate="collapsed">
    /**
     * takes an account number and returns the perspective savings account if in records.
     * @param accountnumber ID number of the account we are searching for.
     * @return <ul> <li>If found, returns the savings account searched for</li>
     *              <li>If not found, returns a blank savings account</li></ul>
     *///</editor-fold>
    public  SavingsAccount getSavingsAccount(int accountnumber) 
    {//<editor-fold  defaultstate="collapsed">
        SavingsAccount a1= new SavingsAccount(-1,-1,0.00,1);
        for(int i=0;i<recordcount;i++){
            if((accountobjects.get(i).getAccountNum()==accountnumber)&&(accountobjects.get(i) instanceof SavingsAccount)){
                a1= (SavingsAccount)accountobjects.get(i);
                System.out.println("Found savings account now returning");
                return a1;
            }//end if
            if((accountobjects.get(i).getAccountNum()==accountnumber)&&!(accountobjects.get(i) instanceof SavingsAccount)){
                System.out.println("Found account, but it is not a savings account");
            }//end if
        }//end for
        System.out.println("could not retrieve account,returning blank savings account");
        return a1;
        //</editor-fold>
    }//end getSavingsAccount
    
    //<editor-fold defaultstate="collapsed">
    /**
     * takes an account number and returns the perspective CD account if in records.
     * @param accountnumber ID number of the account we are searching for.
     * @return <ul> <li>If found, returns the CD account searched for</li>
     *              <li>If not found, returns a blank CD account</li></ul>
     *///</editor-fold>
    public  CdAccount getCDAccount(int accountnumber) 
    {//<editor-fold  defaultstate="collapsed">
        CdAccount a1= new CdAccount(-1,-1,0.00,1);
        for(int i=0;i<recordcount;i++){
            if((accountobjects.get(i).getAccountNum()==accountnumber)&&(accountobjects.get(i) instanceof CdAccount)){
                a1= (CdAccount)accountobjects.get(i);
                System.out.println("Found CD account now returning");
                return a1;
            }//end if
            if((accountobjects.get(i).getAccountNum()==accountnumber)&&!(accountobjects.get(i) instanceof CdAccount)){
                System.out.println("Found account, but it is not a CD account");
            }//end if
        }//end for
        System.out.println("could not retrieve account,returning blank account");
        return a1;
        //</editor-fold>
    }//end getCDAccount
    
    //<editor-fold defaultstate="collapsed">
    /**
     * takes an account number and returns the perspective loan account if in records.
     * @param accountnumber ID number of the account we are searching for.
     * @return <ul> <li>If found, returns the loan account searched for</li>
     *              <li>If not found, returns a blank loan account</li></ul>
     *///</editor-fold>
    public  LoanAccounts getLoanAccount(int accountnumber) 
    {//<editor-fold  defaultstate="collapsed">
        LoanAccounts a1= new LoanAccounts(-1,-1,0.00,1);
        for(int i=0;i<recordcount;i++){
            if((accountobjects.get(i).getAccountNum()==accountnumber)&&(accountobjects.get(i) instanceof LoanAccounts)){
                a1= (LoanAccounts)accountobjects.get(i);
                System.out.println("Found Loan account now returning");
                return a1;
            }//end if
            if((accountobjects.get(i).getAccountNum()==accountnumber)&&!(accountobjects.get(i) instanceof LoanAccounts)){
                System.out.println("Found account, but it is not a Loan account");
            }//end if
        }//end for
        System.out.println("could not retrieve account,returning blank account");
        return a1;
        //</editor-fold>
    }//end getLoanAccount
    //<editor-fold defaultstate="collapsed">
    /**
     * takes an account number and returns the perspective ATM account if in records.
     * @param accountnumber ID number of the account we are searching for.
     * @return <ul> <li>If found, returns the ATM account searched for</li>
     *              <li>If not found, returns a blank loan account</li></ul>
     *///</editor-fold>
    public  AtmCards getAtmAccount(int accountnumber) 
    {//<editor-fold  defaultstate="collapsed">
        AtmCards a1= new AtmCards(-1,-1,0.00,1);
        for(int i=0;i<recordcount;i++){
            if((accountobjects.get(i).getAccountNum()==accountnumber)&&(accountobjects.get(i) instanceof AtmCards)){
                a1= (AtmCards)accountobjects.get(i);
                System.out.println("Found Loan account now returning");
                return a1;
            }//end if
            if((accountobjects.get(i).getAccountNum()==accountnumber)&&!(accountobjects.get(i) instanceof AtmCards)){
                a1.setAccountNum(-2);
                System.out.println("Found account, but it is not a Loan account");
            }//end if
        }//end for
        System.out.println("could not retrieve account,returning blank account");
        return a1;
        //</editor-fold>
    }//end getAtmAccount
    
    //-------------------------------------------------------------
    //          Create & Close Account Methods
    //-------------------------------------------------------------
    
    
    /**Adds an account object to the account list @param AccountObject a new account object to add to the records*/
    public String CreateAccount(AbstractAccount AccountObject)
    {//search to make sure the account does not already exist.//<editor-fold  defaultstate="collapsed">
        int ssncheck=0;
        UserParser up = new UserParser("");
        for(int i =0;i<up.getRecordCount();i++){
            if(AccountObject.getCustomerID()==up.getUserlist(i).getSsn()){
                ssncheck++;
            }//end if
        }//end for
        
        if(ssncheck==0){//user does not exist, cannot create account
            return "Cannot create account, User needs to be created before account can be created!";
        }else{
            int check =0;
            for(int i=0;i<this.recordcount;i++){
                if(AccountObject.getAccountNum()==this.accountobjects.get(i).getAccountNum()){
                    //found an instance of this account in records
                    check++;
                }//end if
            }//end for

            if(check==0){
                //no instance of account on file, go ahead and add it.
                this.accountobjects.add(AccountObject);
                this.recordcount++;
                this.WriteFile();
                return "Account added succesfully";
            }else{
                return "Cannot create account. Account number already exists in records";
            }//end if//</editor-fold>
        }//end else
    }//end CreateAccount
    /**searches for a specified account and closes it if found and the account balance=0 @param AccountNumber account Identifier*/
    public String CloseAccount(int AccountNumber)
    {//<editor-fold  defaultstate="collapsed">
        for(int i=0;i<this.recordcount;i++){
            if(this.accountobjects.get(i).getAccountNum()==AccountNumber){
                if(this.accountobjects.get(i).checkBalance()==0.00){
                    this.accountobjects.remove(i);
                    this.recordcount--;
                    this.WriteFile();
                    return "Account Closed";
                }else{
                    return "Cannot close an account that still has a balance.";
                }//end if
            }//end if
        }//end for
        return "Could not find account";
        //</editor-fold>
    }

    //-------------------------------------------------------------
    //          File Input & Output Methods
    //-------------------------------------------------------------
    
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
                //begin parsing file
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
                                    
                                    if(Stats.getNodeName().compareTo("CusID")==0){
                                        //set the temp variable
                                        usernum=Integer.parseInt(text);
                                        //check if user exists in data
//                                        if(!up.DoesUserExist(usernum)){
//                                            System.out.println("User "+usernum+" does not exist in records. now creating user");
////                                            User user1 = new User(usernum,"Error in user records","Error in user records","","","",0,0);
//                                            String errormsg = "Error in user records"+usernum;
//                                            System.out.println(up.CreateRecord(usernum,errormsg,errormsg,"_","_","_",0,"_","_",0));
//                                        }
                                    }
                                    
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
                                                        //found ATM Card number
                                                        //<editor-fold defaultstate="collapsed">
                                                        if(AccountDetails.getNodeName().compareTo("AtmCardNo")==0){
                                                            AtmCards a1=(AtmCards)accountobjects.get(recordcount-1);
                                                            a1.setCardNum(Integer.parseInt(text));
                                                        }//</editor-fold>
                                                        //found ATM PIN number
                                                        //<editor-fold defaultstate="collapsed">
                                                        if(AccountDetails.getNodeName().compareTo("PIN")==0){
                                                            AtmCards a1=(AtmCards)accountobjects.get(recordcount-1);
                                                            a1.setPIN(Integer.parseInt(text));
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
                                                                text = Debits.getTextContent().replace("\t", "");
                                                                text = text.replace("\n", "");

                                                                if(Debits.getNodeName().compareTo("Debit")==0){
                                                                    attributes = Debits.getAttributes();
                                                                    Node detail = attributes.item(0);
//                                                                    String description=null;
                                                                    String date=null;
                                                                    int checknum=0;
                                                                    double transactionfee=0.00;
                                                                    double transferfee=0.00;
                                                                    
                                                                        for(int i=0; i<attributes.getLength(); i++){
                                                                            Node attr = attributes.item(i);
                                                                            
                                                                            if(attr.getNodeName().compareTo("date")==0){
                                                                            //found date of Debit
                                                                                date = attr.getNodeValue().replace("\t", "");
                                                                            }
                                                                            if(attr.getNodeName().compareTo("checknum")==0){
                                                                            //found Description of Debit
                                                                                checknum = Integer.parseInt(attr.getNodeValue());
                                                                            }
                                                                            if(attr.getNodeName().compareTo("transactionfee")==0){
//                                                                                System.out.println("Found description: "+attr.getNodeValue());
                                                                            //found Description of Debit
                                                                                transactionfee = Double.parseDouble(attr.getNodeValue());
                                                                            }
                                                                            if(attr.getNodeName().compareTo("transferfee")==0){
//                                                                                System.out.println("Found description: "+attr.getNodeValue());
                                                                                //found Description of Debit
                                                                                transferfee = Double.parseDouble(attr.getNodeValue());
                                                                            }
                                                                        }//end for
                                                                    
                                                                    a1.addDebititRecord(Double.parseDouble(text), Long.parseLong(date),checknum,transactionfee,transferfee);

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
                                                        //found ATM Card number
                                                        //<editor-fold defaultstate="collapsed">
                                                        if(AccountDetails.getNodeName().compareTo("AtmCardNo")==0){
                                                            AtmCards a1=(AtmCards)accountobjects.get(recordcount-1);
                                                            a1.setCardNum(Integer.parseInt(text));
                                                        }//</editor-fold>
                                                        //found ATM PIN number
                                                        //<editor-fold defaultstate="collapsed">
                                                        if(AccountDetails.getNodeName().compareTo("PIN")==0){
                                                            AtmCards a1=(AtmCards)accountobjects.get(recordcount-1);
                                                            a1.setPIN(Integer.parseInt(text));
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
                                                                text = Debits.getTextContent().replace("\t", "");
                                                                text = text.replace("\n", "");

                                                                if(Debits.getNodeName().compareTo("Debit")==0){
                                                                    attributes = Debits.getAttributes();
                                                                    Node detail = attributes.item(0);
//                                                                    String description=null;
                                                                    String date=null;
                                                                    int checknum=0;
                                                                    double transactionfee=0.00;
                                                                    double transferfee=0.00;
                                                                    
                                                                        for(int i=0; i<attributes.getLength(); i++){
                                                                            Node attr = attributes.item(i);
                                                                            
                                                                            if(attr.getNodeName().compareTo("date")==0){
                                                                            //found date of Debit
                                                                                date = attr.getNodeValue().replace("\t", "");
                                                                            }
                                                                            if(attr.getNodeName().compareTo("checknum")==0){
                                                                            //found Description of Debit
                                                                                checknum = Integer.parseInt(attr.getNodeValue());
                                                                            }
                                                                            if(attr.getNodeName().compareTo("transactionfee")==0){
//                                                                                System.out.println("Found description: "+attr.getNodeValue());
                                                                            //found Description of Debit
                                                                                transactionfee = Double.parseDouble(attr.getNodeValue());
                                                                            }
                                                                            if(attr.getNodeName().compareTo("transferfee")==0){
//                                                                                System.out.println("Found description: "+attr.getNodeValue());
                                                                                //found Description of Debit
                                                                                transferfee = Double.parseDouble(attr.getNodeValue());
                                                                            }
                                                                        }//end for
                                                                    
                                                                    a1.addDebititRecord(Double.parseDouble(text), Long.parseLong(date),checknum,transactionfee,transferfee);

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
                                                        //found initial loan amount and years of payoff
                                                        //<editor-fold defaultstate="collapsed">
                                                        if(AccountDetails.getNodeName().compareTo("IntitialLoanAmount")==0){
                                                            LoanAccounts a1=(LoanAccounts)accountobjects.get(recordcount-1);
                                                            attributes = AccountDetails.getAttributes();
                                                            Node detail = attributes.item(0);
                                                            String years= detail.getNodeValue().replace("\t", "");
                                                            years = years.replace("\n", "");
                                                            a1.setLoanyears(Integer.parseInt(years));
                                                            a1.setIntitialLoanAmount(Double.parseDouble(text));
                                                        }//end if//</editor-fold>
                                                        //found SLoan bill amount
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
                                            accountobjects.add(new CdAccount(usernum,AccountNumber,balence,flags));
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
                                                        if(AccountDetails.getNodeName().compareTo("InterestRate")==0){
                                                            CdAccount a1=(CdAccount)accountobjects.get(recordcount-1);
                                                            a1.setInterestRate(Double.parseDouble(text));
                                                        }//end if//</editor-fold>
                                                        //found Maturity Date
                                                        // <editor-fold defaultstate="collapsed">
                                                        if(AccountDetails.getNodeName().compareTo("MaturityDate")==0){
                                                            CdAccount a1=(CdAccount)accountobjects.get(recordcount-1);
                                                            a1.setEndDate(Long.parseLong(text));
                                                            a1.setLongEndDate(Long.parseLong(text));
                                                        }//end if//</editor-fold>
                                                        //found RolloverDate
                                                        // <editor-fold defaultstate="collapsed">
                                                        if(AccountDetails.getNodeName().compareTo("RolloverDate")==0){
                                                            CdAccount a1=(CdAccount)accountobjects.get(recordcount-1);
                                                            a1.setRolloverDate(Long.parseLong(text));
                                                            a1.setLongRolloverDate(Long.parseLong(text));
                                                        }//end if//</editor-fold>
                                                        //found StartDate
                                                        // <editor-fold defaultstate="collapsed">
                                                        if(AccountDetails.getNodeName().compareTo("StartDate")==0){
                                                            CdAccount a1=(CdAccount)accountobjects.get(recordcount-1);
                                                            a1.setStartDate(Long.parseLong(text));
                                                            a1.setLongStartDate(Long.parseLong(text));
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
                                                        //found Last Finance charge reset date
                                                        //<editor-fold defaultstate="collapsed">
                                                        if(AccountDetails.getNodeName().compareTo("DateOfFinanceChargeReset")==0){
                                                            CreditCards a1=(CreditCards)accountobjects.get(recordcount-1);
                                                            a1.setDateOfFinanceCharge(Long.parseLong(text));
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
                                            
                                            accountobjects.add(new SavingsAccount(usernum,AccountNumber,balence,flags));
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
                                                        //found ATM Card number
                                                        //<editor-fold defaultstate="collapsed">
                                                        if(AccountDetails.getNodeName().compareTo("AtmCardNo")==0){
                                                            AtmCards a1=(AtmCards)accountobjects.get(recordcount-1);
                                                            a1.setCardNum(Integer.parseInt(text));
                                                        }//</editor-fold>
                                                        //found ATM PIN number
                                                        //<editor-fold defaultstate="collapsed">
                                                        if(AccountDetails.getNodeName().compareTo("PIN")==0){
                                                            AtmCards a1=(AtmCards)accountobjects.get(recordcount-1);
                                                            a1.setPIN(Integer.parseInt(text));
                                                        }//</editor-fold>
                                                        //found Overdraft protection status
                                                        //<editor-fold defaultstate="collapsed">
                                                        if(AccountDetails.getNodeName().compareTo("OverdraftAccount")==0){
                                                            SavingsAccount a1=(SavingsAccount)accountobjects.get(recordcount-1);
                                                            if(Integer.parseInt(text)==0){
                                                                a1.setOverdraftAcc(false);
                                                            }else{a1.setOverdraftAcc(true);}
                                                        }//end if//</editor-fold>
                                                        //found Intrest Rate
                                                        //<editor-fold defaultstate="collapsed">
                                                        if(AccountDetails.getNodeName().compareTo("InterestRate")==0){
                                                            SavingsAccount a1=(SavingsAccount)accountobjects.get(recordcount-1);
                                                            a1.setInterestRate(Double.parseDouble(text));
                                                        }//</editor-fold>
                                                        //found recent debits
                                                        //<editor-fold defaultstate="collapsed">
                                                        if(AccountDetails.getNodeName().compareTo("RecentDebits")==0){
                                                            SavingsAccount a1=(SavingsAccount)accountobjects.get(recordcount-1);
                                                            
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
                                                            SavingsAccount a1=(SavingsAccount)accountobjects.get(recordcount-1);
                                                            
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
 
    //<editor-fold  defaultstate="collapsed">
    /**
     * Takes the data from all accounts and writes it out to the accounts.xml file
     *///</editor-fold>
    public void WriteFile()
    {
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
                        if (this.accountobjects.get(i) instanceof SavingsAccount)p1.print("Savings");
                        if (this.accountobjects.get(i) instanceof Checking){
                            if(this.accountobjects.get(i).getAccountType()==4){p1.print("G/D");}
                            else{p1.print("TMB");}
                        }
                        if (this.accountobjects.get(i) instanceof CdAccount)p1.print("CD");
                        if (this.accountobjects.get(i) instanceof CreditCards)p1.print("CC");
                        if (this.accountobjects.get(i) instanceof LoanAccounts){
                            if(this.accountobjects.get(i).getAccountType()==5){p1.print("LLoan");}
                            else{p1.print("SLoan");}}
                    /* </editor-fold>*/}
                    p1.println("</AccountType>");
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
                        if (this.accountobjects.get(i) instanceof SavingsAccount){
                            SavingsAccount a1=(SavingsAccount)this.accountobjects.get(i);
                            int over=0;
                            if(a1.isOverdraftAcc){over=1;}else{over=0;}
                            //ATM card Number
                            p1.println("\t\t\t<AtmCardNo>"+a1.getCardNum()+"</AtmCardNo>");
                            //pin number
                            p1.println("\t\t\t<PIN>"+a1.getPIN()+"</PIN>");
                            //overdraft check
                            p1.println("\t\t<OverdraftAccount>"+over+"</OverdraftAccount>");
                            //intrest rate
                            p1.println("\t\t<InterestRate>"+a1.getInterestRate()+"</InterestRate>");
                            //recent debits
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
                        if(this.accountobjects.get(i) instanceof CdAccount){
                            CdAccount a1=(CdAccount)this.accountobjects.get(i);
                            p1.println("\t\t\t<MaturityDate>"+a1.getLongEndDate()+"</MaturityDate>");
                            p1.println("\t\t\t<StartDate>"+a1.getLongStartDate()+"</StartDate>");
                            p1.println("\t\t\t<RolloverDate>"+a1.getLongRolloverDate()+"</RolloverDate>");
                            p1.println("\t\t\t<InterestRate>"+a1.getInterestRate()+"</InterestRate>");
                        }//end if CDs
                        // </editor-fold>
                        //if Checking account
                        // <editor-fold defaultstate="collapsed">
                        if(this.accountobjects.get(i) instanceof Checking){
                            Checking a1=(Checking)this.accountobjects.get(i);
                            
                            //overdraft protection status
                            int protection=0; if(a1.getHasOverDraftProtection()){protection=1;}
                            p1.println("\t\t\t<OverdraftProtection>"+protection+"</OverdraftProtection>");
                            //ATM card Number
                            p1.println("\t\t\t<AtmCardNo>"+a1.getCardNum()+"</AtmCardNo>");
                            //pin number
                            p1.println("\t\t\t<PIN>"+a1.getPIN()+"</PIN>");
                            //overdraft account number
                            if(a1.getHasOverDraftProtection()){
                                p1.println("\t\t\t<OverdraftAccount>"+a1.getBackupAccountNumber()+"</OverdraftAccount>");
                            }
                            //G/D intrest rate
                            if(a1.getAccountType()==4){
                                p1.println("\t\t\t<InterestRate>"+a1.getGDinterestRate()+"</InterestRate>");
                            }
                            //debits
                            p1.println("\t\t\t<RecentDebits>");
                                try{
                                    for(int j=0;j<a1.getNumOfDebits();j++){
                                    p1.println("\t\t\t\t<Debit date=\""+a1.getDebitDates(j) +"\" checknum=\""+a1.getCheckNumbers(j)+
                                            "\" transactionfee=\""+a1.getTransactionChargeList(j) +"\" transferfee=\""+
                                            a1.getTransferChargeList(j) +"\">"+a1.getDebitAmounts(j) +"</Debit>");
                                }//end debits
                            }catch(Exception Checkingdebits){System.out.println("Error in adding Checking Debits");}
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
                            //IntitialLoanAmount and years of length
                            p1.println("\t\t\t<IntitialLoanAmount loanyears=\""+a1.getLoanyears()
                                        +"\">"+a1.getIntitialLoanAmount()+"</IntitialLoanAmount>");
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
                            //Last Finance Charge Date
                            p1.println("\t\t\t<DateOfFinanceChargeReset>"+a1.getDateOfFinanceCharge()+"</DateOfFinanceChargeReset>");
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
//                ReadFile(); //Redundent code. the data on file is already up-to-date at this point
                
            }//end WriteFile//</editor-fold>
        }catch(Exception e){
            System.out.println("Exception in file output stream");
            System.out.println(e);
        }
    }
    
    //<editor-fold  defaultstate="collapsed">
    /**this is a method that goes through the accounts and checks to see if their 
     * account owners exist in the user file. if the user does not exist the method 
     * creates one and then recursively calls itself and starts searching for more 
     * unmatched user accounts from the last searched point in the array\
     * @param arraylocation location to start searching from
     * @return String message
     *///</editor-fold>
    private String EnforceRefEnteg(int arraylocation)
    {//<editor-fold  defaultstate="collapsed">
        //call in the userparser so we have accounts on hand
        UserParser up = new UserParser("");
        if(arraylocation<this.recordcount){
            for(int i=arraylocation;i<recordcount;i++){
                //check if account has a user, if they don't do this
                if(!up.DoesUserExist(accountobjects.get(i).getCustomerID())){
                    System.out.println("Found account without a user. missing ssn account: "+accountobjects.get(i).getCustomerID());
                    //create an error message to let user know account data needs updated
                    String errormsg = "Error in user records"+accountobjects.get(i).getCustomerID();
                    //create a new record
                    up.CreateRecord(accountobjects.get(i).getCustomerID(),errormsg,errormsg,"####","####","##",0,"####","####",0);
                    //use recursion and call this method again starting from this point in the loop
                    if(arraylocation<this.recordcount){System.out.println(EnforceRefEnteg(i));}
                    //return
                    return"user added";
                }//end if
            }//end for
        }//end if
        //the data is secure
        return "Referential entegrity secure";//</editor-fold>
    }//end EnforceRefEnteg
    
    //-------------------------------------------------------------
    //          Print Methods
    //-------------------------------------------------------------
    
    
}//end AccountParser
