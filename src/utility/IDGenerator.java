package utility;

/**
 *
 * @author User
 */
public class IDGenerator {
    
    public static String generateNextID(String id){
        // Use regex to split prefix and number
        String prefix = id.replaceAll("\\d", "");           // Extracts "TT"
        String numberStr = id.replaceAll("\\D", "");        // Extracts "00001"
        
        int number = Integer.parseInt(numberStr);
        number++;
        
        // Format back with same number of digits
        String newId = String.format("%s%0" + numberStr.length() + "d", prefix, number);
        
        return newId;
    }
}
