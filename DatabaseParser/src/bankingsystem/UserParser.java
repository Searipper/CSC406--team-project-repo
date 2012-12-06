package bankingsystem;

import java.util.ArrayList;
import java.io.File;
import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Date;
import javax.xml.parsers.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.*;
import java.util.concurrent.locks.*;//needs a lock in this class
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
    private ArrayList<User> userlist = new ArrayList<User>();
    private Lock lock = new ReentrantLock();
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
    public int getSSN(int index){return userlist.get(index).getSsn();}
    /** * @param i location in ArrayList 
     * @return ZIP Code Number*/
    public int getZip(int index){return userlist.get(index).getZip();}
    /** * @param i location in ArrayList 
     * @return System User Type Integer */
    public int getType(int index){return userlist.get(index).getAccestype();}
    /** * @param i location in ArrayList 
     * @return System Username String */
    public String getUsername(int index){return userlist.get(index).getUsername();}
    /** * @param i location in ArrayList 
     * @return System Password String*/
    public String getPassword(int index){return userlist.get(index).getPassword();}
    /** * @param i location in ArrayList 
     * @return City String */
    public String getCity(int index){return userlist.get(index).getCity();}
    /** * @param i location in ArrayList 
     * @return First Name String*/
    public String getFName(int index){return userlist.get(index).getFname();}
    /** * @param i location in ArrayList 
     * @return Last Name String */
    public String getLName(int index){return userlist.get(index).getLname();}
    /** * @param i location in ArrayList 
     * @return Street Address String */
    public String getStreet(int index){return userlist.get(index).getStreet();}
    /** * @param i location in ArrayList 
     * @return State String */
    public String getState(int index){return userlist.get(index).getState();}
    /**Returns a user object. @param index the user location in the Array.*/
    public User getUserlist(int index) {return userlist.get(index);}
    

    //-------------------------------------------------------------
    //          Methods
    //-------------------------------------------------------------
    
    // <editor-fold defaultstate="collapsed">
    /**Searches the array of users for a user with the ssn number equal to the ssn search parameter.
     @param ssn social security number we are searching for.
     @return <ul><li>false if user does not exist in records</li>
     * <li>true if user is found</li></ul>*/// </editor-fold>
    public boolean DoesUserExist(int ssn)
    {// <editor-fold defaultstate="collapsed">
        lock.lock();//acquire the processor
        boolean check=false;
        int counter=0;
        try{
            for(int i=0;i<this.usercount;i++){
                if(ssn==this.userlist.get(i).getSsn()){
                    counter++;
                }//end if
            }//end loop
            if(counter!=0){check= true;}
        }catch(Exception locking){
        }finally{
        lock.unlock();
        return check;// </editor-fold>
        }
    }//end DoesUserExist;
    
    public int getIndexFromSSN(int ssn)
    {// <editor-fold defaultstate="collapsed">
        int index =-1;//negative number means not in the system files
        System.out.println("in getIndexFromSSN() looking for "+ssn);
        for(int i=0;i<usercount;i++){//loop through the system files
            System.out.println("looking for "+ssn+" found: "+this.getSSN(i));
            if((this.getSSN(i))==ssn){//if ssn number found return the index of located record
                index = i;
                 System.out.println("found a match, index = " + index);
                return index;
            }
        }
        return index;// </editor-fold>
    }//end getIndexFromSSN
    
    // <editor-fold defaultstate="collapsed">
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
     */// </editor-fold>
    public String CreateRecord(int ssn,String fname,String lname,String street,
            String city,String state,int zip,String username,String password, int accestype)
    {// <editor-fold defaultstate="collapsed">
        //update counter variable
        System.out.println(ssn);
        int flag=-1;
        for(int i=0;i<usercount;i++){
            if(ssn==getSSN(i)){flag=1;}
        }
        if(flag!=-1){
            return"User already exist in our database";
        }else {
            try{
                User u1 = new User(ssn,fname,lname,street,city,state,zip,accestype);
                this.userlist.add(u1);
                usercount = usercount+1;
                //add to arraylists
                this.userlist.get(usercount-1).setUsername(username);
                this.userlist.get(usercount-1).setPassword(password);
                //update file
                WriteFile();
                return "User Added";
            }catch(Exception addingexception){
                System.out.println("Error in adding user");
                System.out.println(addingexception);
                return "error in file write";
            }
        }// </editor-fold>
    }//end Create Record 
    
    // <editor-fold defaultstate="collapsed">
    /**
     * <p>This Function adds a record to the ArrayLists, updates the user counter
     * and then updates the .xml file by calling WriteFile();
     * the if the SSN number passed already exists for one of the records it 
     * does not update.</p>
     * @param newuser new user object
     * @return String message <ul><li>"User Added"</li><li>"User already exist in our database"</li><li>"Error in adding user"</li></ul>
     */// </editor-fold>
    public String CreateRecord(User newuser)
    {// <editor-fold defaultstate="collapsed">
        lock.lock();//acquire the processor
        String reString="";
        try{
        //update counter variable
        int flag=0;
        for(int i=0;i<usercount;i++){
            if(newuser.getSsn()==this.userlist.get(i).getSsn()){
                flag++;
            }
        }
        if(flag==0){
            try{
                usercount = usercount+1;
                //add to arraylists
                this.userlist.add(newuser);
                //update file
                WriteFile();
                reString = "User Added";
            }catch(Exception addingexception){
                System.out.println("Error in adding user");
                System.out.println(addingexception);
                reString= "error in file write";
            }
        }else{
            reString="User already exist in our database";
        }// </editor-fold>
        }catch(Exception e){
        }finally{
        lock.unlock();
        return reString;
        }
    }//end CreateRecord
    
    // <editor-fold defaultstate="collapsed">
    /**
     * Removes a user record from the database.
     * @param userssn Social security number of the user to be removed from the system.
     * @return Message String: <ul>
     * <li>"There are no users in the system to delete"</li>
     * <li>"user does not exist within the system. cannot delete"</li>
     * <li>"could not find user to delete!"</li>
     * <li>"User deleted"</li>
     * </ul>
     */// </editor-fold>
    public String DeleteRecord(int userssn)
    {// <editor-fold defaultstate="collapsed">
        System.out.println(userssn);
        if(usercount<1){
            return "There are no users in the system to delete";
        }else{
            int record=-1;//will throw an error if user is not in Array
            for(int i=0;i<usercount;i++){
                if (userssn==getSSN(i)){record=i;}
            }//end for
            if (record>usercount){return "user does not exist within the system. cannot delete";}
            if(record==-1){return "could not find user to delete!";}
            try{
                this.userlist.remove(record);//done, its gone
                //set the user counter back 1 and update the file.
                usercount = usercount-1;
                WriteFile();

            }catch(Exception deletefail){
                System.out.println("Failed to delete a record");
                System.exit(0);
            }//end catch
            return "User deleted";
        }//end else// </editor-fold>
    }//end DeleteRecord    
    
    // <editor-fold defaultstate="collapsed">
    /**
     * <p>this function takes the data stored in the ArrayLists 
     * and writes it into a properly formed xml file in the 
     * location path declared in the constructor</p>
     * @return 1 Million dollars!!! No, not really!
     */// </editor-fold>
    private void WriteFile()
    {// <editor-fold defaultstate="collapsed">
        File f1 = new File(path);
        try{// <editor-fold defaultstate="collapsed">
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
            }// </editor-fold>
        }catch(Exception e){
            System.out.println("Exception in file output stream");
            System.out.println(e);
        }// </editor-fold>
    }//end WriteFile
    
    // <editor-fold defaultstate="collapsed">
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
     */// </editor-fold>
    private void ReadFile()
    {// <editor-fold defaultstate="collapsed">
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
                                        
                                        this.userlist.add(new User(Integer.parseInt(attValue)));
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
                                    if(Stats.getNodeName().compareTo("password")==0){ this.userlist.get(this.usercount-1).setPassword(text);}
                                    if(Stats.getNodeName().compareTo("Fname")==0){this.userlist.get(this.usercount-1).setFname(text);}
                                    if(Stats.getNodeName().compareTo("Lname")==0){this.userlist.get(this.usercount-1).setLname(text);}
                                    if(Stats.getNodeName().compareTo("UID")==0){this.userlist.get(this.usercount-1).setUsername(text);}
                                    if(Stats.getNodeName().compareTo("StreetAddress")==0){this.userlist.get(this.usercount-1).setStreet(text);}
                                    if(Stats.getNodeName().compareTo("City")==0){this.userlist.get(this.usercount-1).setCity(text);}
                                    if(Stats.getNodeName().compareTo("State")==0){this.userlist.get(this.usercount-1).setState(text);}
                                    if(Stats.getNodeName().compareTo("ZIP")==0){this.userlist.get(this.usercount-1).setZip(Integer.parseInt(text));}
                                    if(Stats.getNodeName().compareTo("AccessType")==0){this.userlist.get(this.usercount-1).setAccestype(Integer.parseInt(text));}

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
                //this Exception will occur when there is file, but no content in the file
                System.out.println("\n=====================================\n ReadFile() Exception ");
                System.out.println(e);
                System.exit(1);
                //To get rid of this exception, we need to consider the empty file situation and put another if else statement
            }//end exception
        // </editor-fold>
    }//end ReadFile()
    // <editor-fold defaultstate="collapsed">
    /**
     * Checks a username and password passed to the method. returns a valid or invalid number
     * @param username username
     * @param password password
     * @return Boolean T/F value.
     */// </editor-fold>
    
    public int ValidateUserLogin(String username,String password)
    {// <editor-fold defaultstate="collapsed">
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
        // </editor-fold>
    }
    
    public int getUserType(String username)
    {// <editor-fold defaultstate="collapsed">
        int index = -1;
        for(int i=0;i<usercount;i++){
            if(this.getUsername(i).compareTo(username)==0){index = i;}           
        }
        System.out.println(index);
        return this.getType(index);// </editor-fold>
    }
    // <editor-fold defaultstate="collapsed">
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
     */// </editor-fold>
    
    public String UpdateRecord(int ssn,String fname,String lname,String street,
            String city,String state,int zip,String username,String password, int utype)
    {// <editor-fold defaultstate="collapsed">
        int record=-1;
        for(int i=0;i<usercount;i++){
            if(ssn==getSSN(i)){record=i;}
        }
        if(record==-1){return "User not found";
        }else{
            //System.out.println(this.userlist.get(record));
            this.userlist.get(record).setSsn(ssn);
            this.userlist.get(record).setFname(fname);
            this.userlist.get(record).setLname(lname);
            this.userlist.get(record).setStreet(street);
            this.userlist.get(record).setCity(city);
            this.userlist.get(record).setState(state);
            this.userlist.get(record).setZip(zip);
            this.userlist.get(record).setUsername(username);
            this.userlist.get(record).setPassword(password);
            this.userlist.get(record).setAccestype(utype);
            WriteFile();
            
            return "user information updated";
        }// </editor-fold>
    }//end UpdateRecord
    
    //-------------------------------------------------------------
    //          Print Methods
    //-------------------------------------------------------------
    
    /**
     * prints out all the data on all the users in the ArrayLists
     * @return do I even have to say it? nothing!!!
     */
    public String printUser(int ssn)
    {// <editor-fold defaultstate="collapsed">
        String fn = null;
        String ln = null;
        String sn = null;
        for(int i=0;i<usercount;i++){
            if((this.getSSN(i))==ssn){
                fn = this.getFName(i);
                ln = this.getLName(i);
                sn = Integer.toString(this.getSSN(i));
                System.out.println(this.getFName(i) + " " + this.getLName(i) + " USER TYPE:" + this.getType(i));
            }
        }
        return (fn + " "+ ln + " " + sn);// </editor-fold>
    }//end printUser
    
    public void printUserList()
    {// <editor-fold defaultstate="collapsed">
        for(int i=0;i<usercount;i++){
            System.out.println(getFName(i)+"\t"+getLName(i)+"\t"+getSSN(i)+
                    "\t"+getStreet(i)+" \t"+getCity(i)+"    \t"+getState(i)+"\t"
                    +getZip(i)+"\t"+getUsername(i)+"\t"+getPassword(i));
        }//end for// </editor-fold>
    }//end printUsers
}//end UserParser class