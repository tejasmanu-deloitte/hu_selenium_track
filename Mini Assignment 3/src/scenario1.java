import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.*;

public class scenario1 {
    public static void main(String[] args) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:\\Selenium\\selenium-4.1.2\\chromedriver\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        driver.get("https://the-internet.herokuapp.com/");
        driver.manage().window().maximize();

        Thread.sleep(3000);

        driver.findElement(By.xpath("//*[@id=\"content\"]/ul/li[22]/a")).click();
        Thread.sleep(3000);

        driver.findElement(By.xpath("//*[@id=\"content\"]/div/ul/li[1]/a")).click();
        Thread.sleep(3000);

        driver.switchTo().frame("frame-top");
        driver.switchTo().frame("frame-left");
        String title=driver.findElement(By.xpath("/html/body")).getText();
        System.out.println(title);


        driver.quit();

    }


}