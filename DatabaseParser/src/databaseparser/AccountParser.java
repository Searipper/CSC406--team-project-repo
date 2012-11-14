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
    private ArrayList<Integer> AccountNumber = new ArrayList<Integer>();
    private ArrayList<String> AccountType = new ArrayList<String>();
    private ArrayList<Integer> usernum = new ArrayList<Integer>();
    private ArrayList<Double> balence = new ArrayList<Double>();
    private ArrayList<Integer> flags = new ArrayList<Integer>();
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
    /**<p>returns account number for [index] account</p>
     * @param index index for specific record
     */
    public int getAccountNumber(int index) {
        return AccountNumber.get(index);
    }
    /**<p>returns account type for [index] account</p>
     * @param index index for specific record
     */
    public String getAccountType(int index) {
        return AccountType.get(index);
    }

   /* public ArrayList getAccountobjects(int index) {
        return accountobjects;
    }*/
    /**<p>returns balance for [index] account</p>
     * @param index index for specific record
     */
    public double getBalence(int index) {
        return balence.get(index);
    }
/*
    public ArrayList getDateactivated(int index) {
        return dateactivated;
    }
*/
    /**<p>returns account flags for [index] account</p>
     * @param index index for specific record
     */
    public int getFlags(int index) {
        return flags.get(index);
    }
    /**<p>returns usernumber for [index] account</p>
     * @param index index for specific record
     */
    public int getUsernum(int index) {
        return usernum.get(index);
    }

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
                                        this.AccountNumber.add(Integer.parseInt(attValue));
                                        //set placeholder data
                                        AccountType.add("-99");
                                        usernum.add(-99);
                                        balence.add(0.00);
                                        flags.add(0);
                                        dateactivated.add(-99);
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
                                    if(Stats.getNodeName().compareTo("AccountType")==0){ AccountType.set(recordcount-1,text);}
                                    if(Stats.getNodeName().compareTo("CusID")==0){usernum.set(recordcount-1,Integer.parseInt(text));}
                                    if(Stats.getNodeName().compareTo("Balance")==0){balence.set(recordcount-1,Double.parseDouble(text));}
                                    if(Stats.getNodeName().compareTo("AccountFlag")==0){flags.set(recordcount-1,Integer.parseInt(text));
                                    if(Stats.getNodeName().compareTo("DateActivated")==0){dateactivated.set(recordcount-1,text);}
                                    
                                                /*
                                            if(Stats.getNodeName().compareTo("AccountSpecificDetails")==0){ 
                                                * 
                                                *if AccountType.get(recordcount-1).compareTo("TMB")==0){
                                                *   //TODO put account setting code here
                                                * }//end if(TMB)
                                                *if AccountType.get(recordcount-1).compareTo("G/D")==0){
                                                *   //TODO put account setting code here
                                                * }//end if (G/D)
                                                *if AccountType.get(recordcount-1).compareTo("SLoan")==0){
                                                *   //TODO put account setting code here
                                                * }//end if (SLoan)
                                                *if AccountType.get(recordcount-1).compareTo("CC")==0){
                                                *   //TODO put account setting code here
                                                * }//end if (CC)
                                                *if AccountType.get(recordcount-1).compareTo("SimpleSavings")==0){
                                                    * //TODO put account setting code here
                                                        * //branch deaper into the hierarchy to get the account specific data
                                                        * Node AccountDetails = Stats.getFirstChild();
                                                        * while(AccountDetails!=null){
                                                        *      text = AccountDetails.getTextContents().replace("\t", "");
                                                        *      text = text.replace("\n", "");
                                                        * 
                                                        * }//end while
                                                        * 
                                                        * if(AccountDetails.getNodeName().compareTo()==0){
                                                            * accountobjects.set[method](recordcount-1,[Dattype].parse[Datatype](text));
                                                        * }//end if
                                                * }//end if (simple savings)
                                             * }//end if (AccountSpecificDetails)
                                        */
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
    public void printAccounts(){
        for(int i=0;i<recordcount;i++){
            System.out.println("Account #: "+AccountNumber.get(i)+"\tAccount type: "+
            AccountType.get(i)+"\tBalence: $"+balence.get(i)+"    \tUser: "+
            usernum.get(i)+"\tDate Activated: "+dateactivated.get(i)+
            "\tAccount Flag: "+ flags.get(i));
        }//end for
    }//end printUsers
}//end AccountParser
