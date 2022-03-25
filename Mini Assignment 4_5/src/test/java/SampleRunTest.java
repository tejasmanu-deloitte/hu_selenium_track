import org.openqa.selenium.By;

import java.io.IOException;
import java.lang.Double;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import org.testng.Assert;
import org.testng.annotations.Test;

public class SampleRunTest extends BaseClass {

    static int item_count=0;
    static String username="standard_user",password="secret_sauce",first_name="Tejas",last_name="Manu S",pin_code="560017";
    static double item_1_price,item_2_price;

    public static void verify_login(String username, String pass) throws Exception {
            driver.get("https://www.saucedemo.com/");
            send_keys("(//input[@class='input_error form_input'])[1]", username);
            send_keys("(//input[@class='input_error form_input'])[2]", pass);
            click("//input[@type='submit']");
            String actualUrl = "https://www.saucedemo.com/inventory.html";
            String expectedUrl = driver.getCurrentUrl();

        if(actualUrl.equalsIgnoreCase(expectedUrl)) {
            System.out.println("Passed");
        } else {
            System.out.println("Failed");
        }

            driver.navigate().back();
    }

    @Test(priority = 1)
    public static void base() throws Exception
    {
        System.setProperty("webdriver.chrome.driver", "C:\\Selenium\\selenium-4.1.2\\chromedriver\\chromedriver.exe");
        driver=new ChromeDriver();
        driver.get("https://www.saucedemo.com/");
        driver.manage().window().maximize();

    }

    @Test(priority = 3)
    public static void login() throws Exception
    {
        driver.get("https://www.saucedemo.com/");
        send_keys("(//input[@class='input_error form_input'])[1]",username);
        send_keys("(//input[@class='input_error form_input'])[2]",password);
        click("//input[@type='submit']");
    }
    @Test(priority = 4)
    public static void selection_and_conformation() throws Exception{

        container("//select[@class='product_sort_container']", "Price (high to low)");

        item_1_price = item_price("//div[@class='inventory_item_price']");
        if (item_1_price >= 100)
            throw new Exception("price is too high");

        click("(//div[@class='inventory_item'])[1]//button");
        item_count += 1;

        String remove = driver.findElement(By.xpath("(//div[@class='inventory_item'])[1]//button")).getText();
        if (!remove.equals("REMOVE"))
            throw new Exception("remove option not available");
        item_count -= 1;

        click("(//div[@class='inventory_item'])[1]//button");

        click("(//div[@class='inventory_item'])[1]//button");
        item_count += 1;

        click("//a[@class='shopping_cart_link']");

        click("//button[@id='continue-shopping']");

        container("//select[@class='product_sort_container']", "Price (low to high)");

        item_2_price = item_price("//div[@class='inventory_item_price']");

        click("(//div[@class='inventory_item'])[1]//button");
        item_count += 1;

        String item_count_value = driver.findElement(By.xpath("//span[@class='shopping_cart_badge']")).getText();
        if (!(item_count_value.toString()).equals("2")) {
            driver.navigate().refresh();
            Thread.sleep(1000);
        }

        click("//a[@class='shopping_cart_link']");

        if(!(item_count==Integer.parseInt(item_count_value)))
            throw new Exception("item not matches");
        click("//button[@id='checkout']");
    }
    @Test(priority = 5)
    public static void price_conformation() throws Exception
    {
        customer_details();

        String s3=driver.findElement(By.xpath("//div[@class='summary_subtotal_label']")).getText();
        String  s4=s3.replace("$","");
        String total_price=s4.replace("Item total: ","");
        double total_cost_price=Double.parseDouble(total_price);
        if(!(Double.compare(total_cost_price,item_1_price+item_2_price)==0))
            throw new Exception("amount is different in total cost");
    }
    @Test(priority = 6)
    public static void finish_checking() throws Exception
    {
        click("//button[@id='finish']");

        String final_message=driver.findElement(By.xpath("//h2[text()='THANK YOU FOR YOUR ORDER']")).getText();
        if(final_message.equals("THANK YOU FOR YOUR ORDER"))
            System.out.println("ordered successfully");
    }

    @Test(priority = 7)
    public static void closing() throws Exception
    {
        driver.close();
    }

    public static void click(String x_path) throws Exception{
        driver.findElement(By.xpath(x_path)).click();
        Thread.sleep(1000);
    }
    public static double item_price(String x_path)
    {
        String s1=driver.findElement(By.xpath(x_path)).getText();
        String  price=s1.replace("$","");
        double item_price= Double.parseDouble(price);
        return item_price;
    }
    public static void container(String x_path,String option) throws Exception
    {
        Select list2=new Select(driver.findElement(By.xpath(x_path)));
        list2.selectByVisibleText(option);
        Thread.sleep(1000);
    }
    public static void send_keys(String x_path,String value) throws Exception
    {
        driver.findElement(By.xpath(x_path)).sendKeys(value);
        Thread.sleep(1000);
    }
    public static void customer_details() throws Exception{

        send_keys("//input[@id='first-name']",first_name);
        send_keys("//input[@id='last-name']",last_name);
        send_keys("//input[@id='postal-code']",pin_code);
        click("//input[@id='continue']");
    }


}
