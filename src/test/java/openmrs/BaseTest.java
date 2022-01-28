package openmrs;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.asserts.SoftAssert;
import utils.BrowserDriver;

/**
 * Created by tairovich_jr on 2022-01-27.
 */
public abstract class BaseTest {

    protected WebDriver driver;
    protected SoftAssert softAssert;

    @BeforeMethod
    public void driverSetup(){
        driver = BrowserDriver.getDriver();
        softAssert = new SoftAssert();
    }

    @AfterMethod
    public void driverQuit(){
        BrowserDriver.quitBrowser();
    }


}
