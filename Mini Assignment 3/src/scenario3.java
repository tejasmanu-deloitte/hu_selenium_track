import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class scenario3 {
    public static void main(String[] args) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver","C:\\Selenium\\selenium-4.1.2\\chromedriver\\chromedriver.exe");
        WebDriver driver=new ChromeDriver();
        driver.manage().window().maximize();

        driver.get("https://the-internet.herokuapp.com/");

        Thread.sleep(3000);

        driver.findElement(By.xpath("//*[@id=\"content\"]/ul/li[29]/a")).click();

        Thread.sleep(3000);

        driver.findElement(By.xpath("//*[@id=\"content\"]/div/ul/li[3]/button")).click();

        Thread.sleep(3000);

        String input="Ex-Test";
        driver.switchTo().alert().sendKeys(input);
        driver.switchTo().alert().accept();

        Thread.sleep(3000);

        String text=driver.findElement(By.xpath("//*[@id=\"result\"]")).getText();

        System.out.println(text);
        if(text.contains(input))
        {
            System.out.println("true");
        }else{
            System.out.println("false");
        }



    }
}