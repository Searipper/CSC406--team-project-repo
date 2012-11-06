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
       
//
    /*
     
     */
    //-------------------------------------------------------------
    //          Variables
    //-------------------------------------------------------------
    
    private String path;
    private int recordcount;
    private ArrayList AccountNumber = new ArrayList();
    private ArrayList AccountType = new ArrayList();
    private ArrayList usernum = new ArrayList();
    private ArrayList balence = new ArrayList();
    private ArrayList flags = new ArrayList();
    private ArrayList dateactivated = new ArrayList();
    private ArrayList accountobjects = new ArrayList();
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
            System.out.println("In Read file");
            
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
                                        this.AccountNumber.add(attValue);

                                        //adds 1 record to the list
                                        recordcount++;
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
                                    switch(Stats.getNodeName()){
                                            case "AccountType": AccountType.add(text);
                                                break; 
                                            case "CusID": usernum.add(text);
                                                break;
                                            case "Balence": balence.add(text);
                                                break;
                                            case "AccountFlag": flags.add(text);
                                                break;
                                            case "DateActivated":dateactivated.add(text);
                                                break;
                                                /*
                                            case "AccountSpecificDetails": switch((String)AccountType.get(recordcount)){
                                                    case "SimpleSavings":
                                                        /**
                                                         * create object for account and add it to accountobjects.
                                                         * 
                                                         * example: this.accountobjects.add(new SimpleSavings(balence.get(recordcount)));
                                                         * 
                                                         * afterwords set the account data with the account specific information
                                                         * example: 
                                                         * 
                                                         * Node AccountDetails = Stats.getFirstChild();
                                                         * while(AccountDetails!=null){
                                                         *      text = AccountDetails.getTextContents().replace("\t", "");
                                                         *      text = text.replace("\n", "");
                                                         * 
                                                         * }//end while
                                                         * 
                                                         * (SimpleSavings)accountobjects.get(recordcount).set()
                                                         */
                                                /*
                                                        break;
                                                    case "CDs":
                                                        break;
                                                    case"ThatsMyBank":
                                                        break;
                                                    case "GoldDimond":
                                                        break;
                                                    case "ShortTermLoan":
                                                        break;
                                                    case "LongTermMorgage":
                                                        break;
                                                    case "CreditCards":
                                                        break;
                                                }
                                                break;*/
                                    }
                                    //System.out.print(text);
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
    public void printAccounts(){
        for(int i=0;i<recordcount;i++){
            System.out.println("Account #: "+AccountNumber.get(i)+"\tAccount type: "+
            AccountType.get(i)+"\tBalence: $"+balence.get(i)+"\tUser: "+
            usernum.get(i)+"\tDate Activated: "+dateactivated.get(i)+
            "\tAccount Flag: "+ flags.get(i));
        }//end for
    }//end printUsers
}//end AccountParser
