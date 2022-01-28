package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;
import utils.ConfigReader;

import java.time.Duration;
import java.util.List;

/**
 * Created by tairovich_jr on 2022-01-27.
 */
public class PatientDetailsPage {

    private WebDriver driver;
    private SoftAssert softAssert;

    public PatientDetailsPage(WebDriver driver, SoftAssert softAssert) {
        this.driver = driver;
        this.softAssert = softAssert;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//a[@href='/openmrs/index.htm']")
    private WebElement homeBtn;

    @FindBy(xpath = "//span[@class='icon-ok icon-white']")
    private WebElement checkMark;

    @FindBy(xpath = "//div[@class='toast-item-wrapper']")
    private WebElement popUpTab;

    @FindBy(xpath = "//div[@class='info-header']")
    private List<WebElement> registrationElements;

    @FindBy(xpath = "//input[@class='form-control']")
    private WebElement searchPatientBox;

    @FindBy(linkText = "Logout")
    private WebElement logOutBtn;

    @FindBy(xpath = "//td[2]")
    private WebElement verificationText;

    String[] registrationElementsArray ={"DIAGNOSES","VITALS","LATEST OBSERVATIONS",
            "HEALTH TREND SUMMARY","WEIGHT GRAPH","APPOINTMENTS","RECENT VISITS","FAMILY","CONDITIONS",
            "ATTACHMENTS","ALLERGIES"};

    public void registrationPageTabsCheck() {
        for (int index = 0; index < registrationElements.size(); index++) {
            softAssert.assertEquals(registrationElements.get(index).getText().trim(),
                    registrationElementsArray[index]);
        }

    }
    public void goHome () {
        homeBtn.click();
    }

    public void enterStickyNote()throws InterruptedException{
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//i[@class='icon-sticky-note']"))));
        driver.findElement(By.xpath("//i[@class='icon-sticky-note']")).click();
        driver.findElement(By.xpath("//form[contains(@class,'form-inline editable-wrap edi')]//textarea"))
                .sendKeys("some random message");
        Thread.sleep(2000);
        checkMark.click();
        Thread.sleep(500);
        softAssert.assertTrue(popUpTab.isDisplayed(),"pop verification failed");
    }

    public void searchPatientBox()throws InterruptedException{
        String first = ConfigReader.getProperty("firstName");
        String middle =ConfigReader.getProperty("middleName");
        String family = ConfigReader.getProperty("familyName");
        searchPatientBox.sendKeys(first+" "+middle+" "+family);
        Thread.sleep(3000);
        String verifyText = verificationText.getText();
        softAssert.assertEquals(verifyText,first+" "+middle+" "+family,"name didn't match");

    }
    public void logOut(){
        logOutBtn.click();
    }
}
