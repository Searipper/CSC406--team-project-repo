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
 * This class is the interface between the User Data files and the Banking system.
 * </p>
 * @author Sammy Hajeer
 * @version 1.0
 */
public class UserParser {

    

    //-------------------------------------------------------------
    //          Variables
    //-------------------------------------------------------------
    
    private String path;
    private int usercount;
    private ArrayList<Integer> ssn = new ArrayList<Integer>();
    private ArrayList<String> fname = new ArrayList<String>();
    private ArrayList<String> lname = new ArrayList<String>();
    private ArrayList<String> street = new ArrayList<String>();
    private ArrayList<String> city = new ArrayList<String>();
    private ArrayList<String> st = new ArrayList<String>();
    private ArrayList<Integer> zip = new ArrayList<Integer>();
    private ArrayList<Integer> type = new ArrayList<Integer>();
    private ArrayList<String> username = new ArrayList<String>();
    private ArrayList<String> password = new ArrayList<String>();
    //end data variables


    //-------------------------------------------------------------
    //          Constructor
    //-------------------------------------------------------------
    
    
    /**
     * constructor for UserParser class
     * Class {@code Object} is the root of the class hierarchy.
     * @param path an absolute URL giving the base location of the .xml file. the constructor will append the filename users.xml to this String
    */
    public UserParser(String path) {
        this.path = path+"users.xml";//this sets the file path
        ReadFile();//Reads in records
    }

    
    //-------------------------------------------------------------
    //          Getter Methods
    //-------------------------------------------------------------
    
    /**
     * <p>gets the number of records read in from the file.</p>
     * @return integer number of user records in the system. 
     */
    public int getRecordCount(){return usercount;}
    /** * @param i location in ArrayList 
     * @return Social Security Number */
    public int getSSN(int i){return (int)ssn.get(i);}
    /** * @param i location in ArrayList 
     * @return ZIP Code Number*/
    public int getZip(int i){return (int)zip.get(i);}
    /** * @param i location in ArrayList 
     * @return System User Type Integer */
    public int getType(int i){return (int)type.get(i);}
    /** * @param i location in ArrayList 
     * @return System Username String */
    public String getUsername(int i){return (String)username.get(i);}
    /** * @param i location in ArrayList 
     * @return System Password String*/
    public String getPassword(int i){return (String)password.get(i);}
    /** * @param i location in ArrayList 
     * @return City String */
    public String getCity(int i){return (String)city.get(i);}
    /** * @param i location in ArrayList 
     * @return First Name String*/
    public String getFName(int i){return (String)fname.get(i);}
    /** * @param i location in ArrayList 
     * @return Last Name String */
    public String getLName(int i){return (String)lname.get(i);}
    /** * @param i location in ArrayList 
     * @return Street Address String */
    public String getStreet(int i){return (String)street.get(i);}
    /** * @param i location in ArrayList 
     * @return State String */
    public String getState(int i){return (String)st.get(i);}
    

    //-------------------------------------------------------------
    //          Methods
    //-------------------------------------------------------------
    
    
    /**
     * <p>This Function adds a record to the ArrayLists, updates the user counter
     * and then updates the .xml file by calling WriteFile();
     * the if the SSN number passed already exists for one of the records it 
     * does not update.</p>
     * @param ssn Social Security number
     * @param fname First Name
     * @param lname Last Name
     * @param street Street Address
     * @param city Resident City
     * @param state State of residence
     * @param zip Zip Code
     * @param username System Username
     * @param password System Password
     * @param utype User access type
     * @return String message <ul><li>"User Added"</li><li>"User already exist in our database"</li><li>"Error in adding user"</li></ul>
     */
    public String CreateRecord(int ssn,String fname,String lname,String street,
            String city,String state,int zip,String username,String password, int utype){
        //update counter variable
        
        int flag=0;
        for(int i=0;i<usercount;i++){
            if(ssn==getSSN(i))flag++;
        }
        if(flag==0){
        try{
        usercount = usercount+1;
        //add to arraylists
        
        this.ssn.add(ssn);
        this.fname.add(fname);
        this.lname.add(lname);
        this.street.add(street);
        this.city.add(city);
        this.st.add(state);
        this.zip.add(zip);
        this.username.add(username);
        this.password.add(password);
        this.type.add(utype);
        //update file
        WriteFile();
        return "User Added";
        }catch(Exception addingexception){
            System.out.println("Error in adding user");
            System.out.println(addingexception);
            return "error in file write";
        }
        }else{
            return"User already exist in our database";
        }
    }//end Create Record    
    /**
     * Removes a user record from the database.
     * @param userssn Social security number of the user to be removed from the system.
     * @return Message String: <ul>
     * <li>"There are no users in the system to delete"</li>
     * <li>"user does not exist within the system. cannot delete"</li>
     * <li>"could not find user to delete!"</li>
     * <li>"User deleted"</li>
     * </ul>
     */
    public String DeleteRecord(int userssn){
        if(usercount<1){
            return "There are no users in the system to delete";
        }else{
            int record=-1;//will throw an error if user is not in Array
            for(int i=0;i<usercount;i++){
                if (userssn==getSSN(i))record=i;
            }//end for
            if (record>usercount){return "user does not exist within the system. cannot delete";}
            if(record==-1){return "could not find user to delete!";}
            try{
                ssn.remove(record);
                fname.remove(record);
                lname.remove(record);
                street.remove(record);
                city.remove(record);
                st.remove(record);
                zip.remove(record);
                username.remove(record);
                password.remove(record);
                type.remove(record);

                //set the user counter back 1 and update the file.
                usercount = usercount-1;
                WriteFile();

            }catch(Exception deletefail){
                System.out.println("Failed to delete a record");
                System.exit(0);
            }//end catch
            return "User deleted";
        }//end else
    }//end DeleteRecord    

    /**
     * <p>this function takes the data stored in the ArrayLists 
     * and writes it into a properly formed xml file in the 
     * location path declared in the constructor</p>
     * @return 1 Million dollars!!! No, not really!
     */
    private void WriteFile(){
        File f1 = new File(path);
        try{
            //FileOutputStream fileout = new FileOutputStream(f1);
            PrintWriter p1 = new PrintWriter(f1);
            
            if (f1.exists()){
                p1.println("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>");
                p1.println("<users>");
                for(int i=0;i<getRecordCount();i++){
                    
                p1.println("\t<user"+" SSN=\""+getSSN(i)+"\">");
                    p1.println("\t\t<Fname>"+getFName(i)+"</Fname>");
                    p1.println("\t\t<Lname>"+getLName(i)+"</Lname>");
                    p1.println("\t\t<password>"+getPassword(i)+"</password>");
                    p1.println("\t\t<UID>"+getUsername(i)+"</UID>");
                    p1.println("\t\t<StreetAddress>"+getStreet(i)+"</StreetAddress>");
                    p1.println("\t\t<City>"+getCity(i)+"</City>");
                    p1.println("\t\t<State>"+getState(i)+"</State>");
                    p1.println("\t\t<ZIP>"+getZip(i)+"</ZIP>");
                    p1.println("\t\t<AccessType>"+getType(i)+"</AccessType>");
                p1.println("\t</user>");
                }
                p1.println("</users>");
                p1.close();
                
                //update records of change
                ReadFile();
            }
        }catch(Exception e){
            System.out.println("Exception in file output stream");        
        }
    }
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
            usercount=0;
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
                                        
                                        if(current.hasAttributes()){
                                        //get attribute contents
                                        String attValue= attr.getNodeValue().replace("\t", "");
                                        attValue = attValue.replace("\n", "");
                                        
                                        ssn.add(Integer.parseInt(attValue));
                                        //set placeholder data for new record
                                        password.add("error-99");
                                        fname.add("error-99");
                                        lname.add("error-99");
                                        username.add("error-99");
                                        street.add("error-99");
                                        city.add("error-99");
                                        st.add("error-99");
                                        zip.add(-99);
                                        type.add(-99);
                                        

                                        //adds 1 record to the list
                                        usercount++;
                                        }else{
                                            //if there is no SSN number then the data in this node is currupt and we don't want it
                                            current = current.getNextSibling();
                                        }
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
                                    if(Stats.getNodeName().compareTo("password")==0){ password.set(usercount-1, text);}
                                    if(Stats.getNodeName().compareTo("Fname")==0){fname.set(usercount-1,text);}
                                    if(Stats.getNodeName().compareTo("Lname")==0){lname.set(usercount-1,text);}
                                    if(Stats.getNodeName().compareTo("UID")==0){username.set(usercount-1,text);}
                                    if(Stats.getNodeName().compareTo("StreetAddress")==0){street.set(usercount-1,text);}
                                    if(Stats.getNodeName().compareTo("City")==0){city.set(usercount-1,text);}
                                    if(Stats.getNodeName().compareTo("State")==0){st.set(usercount-1,text);}
                                    if(Stats.getNodeName().compareTo("ZIP")==0){zip.set(usercount-1,Integer.parseInt(text));}
                                    if(Stats.getNodeName().compareTo("AccessType")==0){type.set(usercount-1,Integer.parseInt(text));}

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
                WriteFile();
            }
            }catch(Exception e){
                System.out.println("\n=====================================\n ReadFile() Exception ");
                System.out.println(e);
                System.exit(1);
            }//end exception
    }//end ReadFile()
    /**
     * Checks a username and password passed to the method. returns a valid or invalid number
     * @param username username
     * @param password password
     * @return Boolean T/F value.
     */
    public int ValidateUserLogin(String username,String password){
        int index = -1;
        for(int i=0;i<usercount;i++){
            System.out.println("looking for user: "+username+" found: "+getUsername(i)+ 
                            " How much it is different from what you are looking for "+getUsername(i).compareTo(username));
            if(this.getUsername(i).compareTo(username)==0){
                    System.out.println("found user match username in arraylist");
                if (this.getPassword(i).compareTo(password)==0){
                    System.out.println("found password match password in arraylist");
                index=i;
                System.out.println("record index = " + i);
                return index;
                }else{System.out.println("Password didn't find match in the arraylist");}
            }{
            System.out.println("username didn't find match in the arraylist");
        }//end if checking username
        }//end for loop
        return index;
    }//
    /**
     * Updates user record. Checks the record for the SSN and updates if it exists.
     * 
     * @param ssn integer social security number
     * @param fname String First Name
     * @param lname String Last Name
     * @param street String Street Address
     * @param city String City
     * @param state String State
     * @param zip integer ZIP Code
     * @param username String Username
     * @param password String Password
     * @param utype integer User Type
     */
    public String UpdateRecord(int ssn,String fname,String lname,String street,
            String city,String state,int zip,String username,String password, int utype){
        int record=-1;
        for(int i=0;i<usercount;i++){
            if(ssn==getSSN(i))record=i;
        }
        if(record==-1){return "User not found";
        }else{
            this.fname.set(record,fname);
            this.lname.set(record,lname);
            this.street.set(record,street);
            this.city.set(record,city);
            this.st.set(record,state);
            this.zip.set(record,zip);
            this.username.set(record,username);
            this.password.set(record,password);
            this.type.set(record,utype);
            WriteFile();
            
            return "user information updated";
        }
    }//end UpdateRecord
    //-------------------------------------------------------------
    //          Print Methods
    //-------------------------------------------------------------
    
    /**
     * prints out all the data on all the users in the ArrayLists
     * @return do I even have to say it? nothing!!!
     */
    public void printusers(){
        for(int i=0;i<usercount;i++){
            System.out.println(getFName(i)+"\t"+getLName(i)+"\t"+getSSN(i)+
                    "\t"+getStreet(i)+" \t"+getCity(i)+"    \t"+getState(i)+"\t"
                    +getZip(i)+"\t"+getUsername(i)+"\t"+getPassword(i));
        }//end for
    }//end printUsers
}//end UserParser class
