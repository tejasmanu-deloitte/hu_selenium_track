import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;



import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class BaseClass {
    static WebDriver driver;
    static String username="",password="",first_name="",last_name="",pin_code="600600";
    static boolean flag = true;
    @Test(priority = 2)
    public static void excel_reader() throws Exception {
        String line = "";
        String splitBy = ",";
        BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\tejasmas\\Desktop\\hu_selenium_track\\hu_selenium_track\\Mini Assignment 4_5\\login_credentials.csv"));
        while ((line = br.readLine()) != null)
        {
            String[] login = line.split(splitBy);
            username=login[0];
            password=login[1];

            SampleRunTest.verify_login(username,password);
//            break;
        }
        BufferedReader br2 = new BufferedReader(new FileReader("C:\\Users\\tejasmas\\Desktop\\hu_selenium_track\\hu_selenium_track\\Mini Assignment 4_5\\booking_details.csv"));
        while ((line = br2.readLine()) != null)
        {
            String[] cust = line.split(splitBy);
            first_name=cust[0];
            last_name=cust[1];
            pin_code=cust[2];
            break;
        }
    }







}
