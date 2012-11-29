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
//    private ArrayList AccountNumber = new ArrayList();
//    private ArrayList AccountType = new ArrayList();
//    private ArrayList usernum = new ArrayList();
//    private ArrayList balence = new ArrayList();
//    private ArrayList flags = new ArrayList();
//    private ArrayList dateactivated = new ArrayList();
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
    /**get an ArrayList of all savings accounts @return savings accounts ArrayList*/
    public ArrayList<CreditCards> getCreditCardAccounts() {
        ArrayList<CreditCards> creditcards= new ArrayList<CreditCards>();
        for(int i=0;i<recordcount;i++){
            if(accountobjects.get(i) instanceof CreditCards){
                creditcards.add((CreditCards)accountobjects.get(i));
            }
        }
        return creditcards;
    }
    /** * @param i location in ArrayList 
     * @return Social Security Number */


    //-------------------------------------------------------------
    //          Methods
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
        
        try{
            //reset the usercount
            recordcount=0;
            //sets the file path
            System.out.println("In Account Read file");
            
            File f1 =new File(path);
            if(f1.exists()){
                DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
                Document doc = dBuilder.parse(f1);
                doc.getDocumentElement().normalize();

                //Get the Root element and print it.
                Element root = doc.getDocumentElement();
                String rootName = root.getNodeName();

                //Pulls nodes and attributes
                Node current = root.getFirstChild();
                
                int AccountNumber=0;
                String AccountType ="";
                int usernum=0;
                double balence = 0.00;
                int flags = 0;
                System.out.println("Begin parsing file");
                try{while(current != null){

                        if(current.getNodeType() == Node.ELEMENT_NODE){
                        String nodeName = current.getNodeName();
                            NamedNodeMap attributes = current.getAttributes();
                                if(attributes.getLength()!=0){
                                    for(int i=0; i<attributes.getLength(); i++){
                                        Node attr = attributes.item(i);
                                        //get atribute name
                                        String attName = attr.getNodeName();

                                        //get attribute contents
                                        String attValue= attr.getNodeValue().replace("\t", "");
                                        attValue = attValue.replace("\n", "");
                                        //System.out.println("Found account: "+attValue);
                                        /**
                                         * found account number. store it in a temporary variable 
                                         * until we have enough data to initialize a new account object.
                                         */
                                        AccountNumber = Integer.parseInt(attValue);
                                    }//end for
                                }//end if


                            if(current.getTextContent()!=null){
                                //now it gets the contents of an element

                                //basicaly we found a new record and now we have to go through it and 
                                Node Stats = current.getFirstChild();
                                //System.out.println(Stats.getLength());
                                while (Stats!=null){
                                    String text =Stats.getTextContent().replace("\t", "");
                                    text = text.replace("\n", "");
                                    //get data needed to intialize a class
                                    if(Stats.getNodeName().compareTo("AccountType")==0){ AccountType=text;}
                                    if(Stats.getNodeName().compareTo("CusID")==0){usernum=Integer.parseInt(text);}
                                    if(Stats.getNodeName().compareTo("Balance")==0){balence= Double.parseDouble(text);}
                                    if(Stats.getNodeName().compareTo("AccountFlag")==0){flags=Integer.parseInt(text);}
//                                    if(Stats.getNodeName().compareTo("DateActivated")==0){dateactivated.set(recordcount-1,text);}
                                    //System.out.println(AccountType);
                                              
                                    if(Stats.getNodeName().compareTo("AccountSpecificDetails")==0)try{
                                    //System.out.println("Found account specific details tag");

                                        //TMB
                                        // <editor-fold>
                                        if (AccountType.compareTo("TMB")==0){
                                            //TODO put account setting code here
                                            }//end if(TMB)// </editor-fold>
                                        //G/D
                                        // <editor-fold>
                                        if (AccountType.compareTo("G/D")==0){
                                            //TODO put account setting code here
                                            }//end if (G/D)// </editor-fold>
                                        //SLoan
                                        // <editor-fold>
                                        if (AccountType.compareTo("SLoan")==0){
                                            //TODO put account setting code here
                                            }//end if (SLoan)
                                        // </editor-fold>
                                        //CC
                                        // <editor-fold>
                                        if (AccountType.compareTo("CC")==0){
                                            
                                            System.out.println("Creating CreditCardt for account #"+AccountNumber);
                                            accountobjects.add(new CreditCards(usernum,AccountNumber,balence,flags));
                                            recordcount++;
                                            //reset loop variables:
                                            AccountNumber=0; AccountType=""; usernum=0; balence=0.00; flags=0;
                                            
                                            //branch deaper into the hierarchy to get the account specific data// <editor-fold>
                                                    Node AccountDetails = Stats.getFirstChild();
                                                    while(AccountDetails!=null)try{
                                                        text = AccountDetails.getTextContent().replace("\t", "");
                                                        text = text.replace("\n", "");
                                                        //<editor-fold>
                                                        if(AccountDetails.getNodeName().compareTo("CreditLimit")==0){
                                                            CreditCards a1=(CreditCards)accountobjects.get(recordcount-1);
                                                            a1.setCreditLimit(Double.parseDouble(text));
                                                        }//end if//</editor-fold>
                                                        //found recent debits
                                                        //<editor-fold>
                                                        if(AccountDetails.getNodeName().compareTo("RecentDebits")==0){
                                                            CreditCards a1=(CreditCards)accountobjects.get(recordcount-1);
                                                            
                                                            Node Debits = AccountDetails.getFirstChild();
                                                            
                                                            while(Debits!=null){
                                                                text = Debits.getTextContent().replace("\t", "");;
                                                                text = text.replace("\n", "");

                                                                if(Debits.getNodeName().compareTo("Debit")==0){
                                                                    Node detail = attributes.item(0);
                                                                    String date= detail.getNodeValue().replace("\t", "");
                                                                    date = date.replace("\n", "");
                                                                    a1.addDebititRecord(Double.parseDouble(text), Long.parseLong(date));

                                                                }//end if
                                                                Debits=Debits.getNextSibling();
                                                            }//end while
                                                        }//end RecentDebits//</editor-fold>
                                                        //found recent credits
                                                        //<editor-fold>
                                                        if(AccountDetails.getNodeName().compareTo("RecentCredits")==0){
                                                            CreditCards a1=(CreditCards)accountobjects.get(recordcount-1);
                                                            
                                                             Node Credits = AccountDetails.getFirstChild();
                                                             
                                                            while(Credits!=null){
                                                                text = Credits.getTextContent().replace("\t", "");;
                                                                text = text.replace("\n", "");

                                                                if(Credits.getNodeName().compareTo("Credit")==0){
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
                                                    catch(Exception creditcardsloop){
                                                        System.out.println("Error in adding c account");
                                                        System.out.println(creditcardsloop);
                                                        System.exit(1);
                                                    }
                                        }//end if (CC)// </editor-fold>
                                        // <editor-fold>
                                        if (AccountType.compareTo("Savings")==0)try{
                                            
                                            System.out.println("Creating Savings account for account #"+AccountNumber);
                                            accountobjects.add(new savingsAccount(usernum,AccountNumber,balence,flags));
                                            recordcount++;
                                            //reset loop variables:
                                            AccountNumber=0; AccountType=""; usernum=0; balence=0.00; flags=0;
                                            
                                            //branch deaper into the hierarchy to get the account specific data// <editor-fold>
                                                    Node AccountDetails = Stats.getFirstChild();
                                                    while(AccountDetails!=null)try{
                                                        text = AccountDetails.getTextContent().replace("\t", "");;
                                                        text = text.replace("\n", "");
                                                        //found Overdraft protection status
                                                        //<editor-fold>
                                                        if(AccountDetails.getNodeName().compareTo("OverdraftAccount")==0){
                                                            savingsAccount a1=(savingsAccount)accountobjects.get(recordcount-1);
                                                            if(Integer.parseInt(text)==0){
                                                                a1.setOverdraftAcc(false);
                                                            }else{a1.setOverdraftAcc(true);}
                                                        }//end if//</editor-fold>
                                                        //found Intrest Rate
                                                        //<editor-fold>
                                                        if(AccountDetails.getNodeName().compareTo("InterestRate")==0){
                                                            savingsAccount a1=(savingsAccount)accountobjects.get(recordcount-1);
                                                            a1.setInterestRate(Double.parseDouble(text));
                                                        }//</editor-fold>
                                                        //found Overdraft protected account number
                                                        //<editor-fold>
                                                        if(AccountDetails.getNodeName().compareTo("ProtectedAccount")==0){
                                                            savingsAccount a1=(savingsAccount)accountobjects.get(recordcount-1);
                                                            a1.setProtectingAcc(Integer.parseInt(text));
                                                        }//</editor-fold>
                                                        //found recent debits
                                                        //<editor-fold>
                                                        if(AccountDetails.getNodeName().compareTo("RecentDebits")==0){
                                                            savingsAccount a1=(savingsAccount)accountobjects.get(recordcount-1);
                                                            
                                                             Node Debits = AccountDetails.getFirstChild();
                                                             
                                                            while(Debits!=null){
                                                                text = Debits.getTextContent().replace("\t", "");;
                                                                text = text.replace("\n", "");

                                                                if(Debits.getNodeName().compareTo("Debit")==0){
                                                                    Node detail = attributes.item(0);
                                                                    String date= detail.getNodeValue().replace("\t", "");
                                                                    date = date.replace("\n", "");
                                                                    a1.addDebititRecord(Double.parseDouble(text), Long.parseLong(date));

                                                                }//end if
                                                                Debits=Debits.getNextSibling();
                                                            }//end while
                                                        }//end RecentDebits//</editor-fold>
                                                        //found recent credits
                                                        //<editor-fold>
                                                        if(AccountDetails.getNodeName().compareTo("RecentCredits")==0){
                                                            savingsAccount a1=(savingsAccount)accountobjects.get(recordcount-1);
                                                            
                                                             Node Credits = AccountDetails.getFirstChild();
                                                             
                                                            while(Credits!=null){
                                                                text = Credits.getTextContent().replace("\t", "");;
                                                                text = text.replace("\n", "");

                                                                if(Credits.getNodeName().compareTo("Credit")==0){
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
                System.out.println("\n=====================================\nvery unspecific exception with wich this print-out does not help with at all.");
                System.out.println(e);
                System.exit(1);
            }//end exception
    }//end ReadFile()
 
    //-------------------------------------------------------------
    //          Print Methods
    //-------------------------------------------------------------
    
    /**
     * prints out all the data on all the users in the ArrayLists
     * @return do I even have to say it? nothing!!!
     */
//    public void printAccounts(){
//        for(int i=0;i<recordcount;i++){
//            System.out.println("Account #: "+AccountNumber.get(i)+"\tAccount type: "+
//            AccountType.get(i)+"\tBalence: $"+balence.get(i)+"\tUser: "+
//            usernum.get(i)+"\tDate Activated: "+dateactivated.get(i)+
//            "\tAccount Flag: "+ flags.get(i));
//        }//end for
//    }//end printUsers
}//end AccountParser
