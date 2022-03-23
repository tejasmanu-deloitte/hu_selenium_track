import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.*;


public class senario2 {
    public static void main(String[] args) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver","C:\\Selenium\\selenium-4.1.2\\chromedriver\\chromedriver.exe");
        WebDriver driver;
        ChromeOptions options = new ChromeOptions();
        options.addArguments("Headless");
        driver = new ChromeDriver(options);

        String title_to_check = "PHPTRAVELS";
        driver.get("https://phptravels.com/demo");
        driver.manage().window().maximize();

        Thread.sleep(3000);

        if(check_title(driver.getTitle(),title_to_check)){
            System.out.println("PASS");
        }
        else{
            System.out.println("FAIL");
        }

        String main_title = driver.getTitle();

        driver.findElement(By.xpath("/html/body/header/div/nav/a[4]")).click();
        String MainWindow = driver.getWindowHandle();

        Set<String> s1 = driver.getWindowHandles();
        Iterator<String> i1 = s1.iterator();


        while(i1.hasNext()) {
            String ChildWindow = i1.next();
            if (!MainWindow.equalsIgnoreCase(ChildWindow)) {
                driver.switchTo().window(ChildWindow);
                if (driver.getTitle().equals(main_title)) {
                    System.out.println("Pass");
                } else {
                    System.out.println("Fail");
                }
            }
            Thread.sleep(3000);
        }

        driver.switchTo().window(MainWindow);
        System.out.println(driver.getCurrentUrl());
        driver.findElement(By.xpath("/html/body/header/div/nav/a[2]")).click();

        Thread.sleep(3000);

        driver.navigate().back();

        Thread.sleep(3000);

        driver.navigate().refresh();
        Thread.sleep(3000);

        driver.quit();

    }

    public static boolean check_title(String title, String check){

        boolean flag = title.contains(check);

        return flag;
    }
}
