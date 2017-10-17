
/**
 * Write a description of class Scan here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.Scanner;

public class Scan
{
    String response;
    
    public Scan()
    {
        
    }
    
    public String scan()
    {
        Scanner scan = new Scanner(System.in);
        String resp = scan.nextLine();
        response = resp.toLowerCase();
        return response;
    }
}
