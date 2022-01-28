package pages;

import com.github.javafaker.Faker;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.asserts.SoftAssert;

import java.util.List;

public class HomePage {

    public WebDriver driver;
    public SoftAssert softAssert;
    private Faker faker ;

    public HomePage(WebDriver driver, SoftAssert softAssert){
        this.driver = driver;
        this.softAssert= softAssert;
        this.faker = new Faker();
        PageFactory.initElements(driver,this);
    }

//    @FindBy(className = "nav-item identifier")
//    private WebElement admin;
//
//    @FindBy(id = "selected-location")
//    private WebElement iW;
//
//    @FindBy(linkText = "Logout")
//    private WebElement logOut;
//
//    @FindBy(xpath = "//a[contains(@id,'referenceapplication')][1]")
//    private WebElement patientRegisterBtn;
//
//    @FindBy(xpath = "//div[@id='apps']/a")
//    private List<WebElement> tabs;
//    String[] tabsArray = {"Find Patient Record", "Active Visits", "Register a Patient", "Capture Vitals",
//            "Appointment Scheduling", "Reports", "Data Management", "Configure Metadata", "System Administration"};
//
//    @FindBy(name="givenName")
//    private WebElement firstNameBox;
//
//    @FindBy(name="middleName")
//    private WebElement middleNameBox;
//
//    @FindBy(name="familyname")
//    private WebElement familyNameBox;
//
//    public void adminVerify(){
//        softAssert.assertTrue(admin.isDisplayed());
//    }
//    public void iWVerify(){
//        softAssert.assertTrue(iW.isDisplayed());
//    }
//    public void logOutVerify(){
//        softAssert.assertTrue(logOut.isDisplayed());
//    }
//
//    public void verifyTabs() {
//        for (int i = 0; i < this.tabs.size(); i++) {
//            softAssert.assertEquals(this.tabs.get(i).getText().trim(), tabsArray[i]);
//        }
//    }
//    public void registerClick(){
//        softAssert.assertTrue(patientRegisterBtn.isEnabled());
//        patientRegisterBtn.click();
//    }
//    public void patientsNameSet(String first,String middle,String family){
//        firstNameBox.sendKeys(first);
//        middleNameBox.sendKeys(middle);
//        familyNameBox.sendKeys(family);
//    }

}