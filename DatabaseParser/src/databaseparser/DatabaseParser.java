//
package databaseparser;

public class DatabaseParser {

    public static void main(String[] args) {
        String path= "C:/Users/sjhajeer/Desktop/Object_Oriented/Project_folder/Parser/";
        System.out.println(path);
        /*UserParser file1 = new  UserParser(path);
        
        System.out.println(file1.CreateRecord(121212121, "Granola", "Bar", "123 4th Street", "Anywhere", "OH", 12345, "GBar", "abcdefg", 2));
        file1.printusers();
        System.out.println();
        
        System.out.println(file1.UpdateRecord(121212121, "Hershey", "Park", "100 W. Hersheypark Drive", "Hershey", "PA", 17033, "HBar", "chocolate", 3));
        file1.printusers();

        System.out.println(file1.DeleteRecord(121212121));
        file1.printusers();
        */
       AccountParser file2 = new AccountParser(path);
       file2.getRecordCount();
       file2.printAccounts();
    }
}
