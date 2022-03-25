import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;

public class Listeners extends BaseClass implements ITestListener {

    @Override
    public void onTestStart(ITestResult Result) {
        ITestListener.super.onTestStart(Result);
    }
    @Override
    public void onTestSuccess(ITestResult Result) {
        driver.close();
    }
//    @Override
//    public void onTestFailure(ITestResult Result) {
//        if (!Result.isSuccess()) {
//            File imageFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
//
//            File failureImageFile = new File(System.getProperty("user.dir")+"/screenshots/"+"fail.jpg");
//            try {
//                FileUtils.copyFile(imageFile, failureImageFile);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//        ITestListener.super.onTestFailure(Result);
//    }
    @Override
    public void onTestSkipped(ITestResult Result) {
        ITestListener.super.onTestSkipped(Result);
    }

}