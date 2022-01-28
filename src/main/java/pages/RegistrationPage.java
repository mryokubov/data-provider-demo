package pages;

import com.github.javafaker.Faker;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.asserts.SoftAssert;
import utils.ConfigReader;

import java.util.List;

/**
 * Created by tairovich_jr on 2022-01-27.
 */
public class RegistrationPage {

    private WebDriver driver;
    private Faker faker;
    private SoftAssert softAssert;
    private Select select;

    public RegistrationPage(WebDriver driver, SoftAssert softAssert){
       this.driver = driver;
        this.softAssert = softAssert;
        faker=new Faker();
        PageFactory.initElements(driver,this);
    }

    @FindBy(name="givenName")
    private WebElement firstNameBox;

    @FindBy(name="middleName")
    private WebElement middleNameBox;

    @FindBy(name="familyName")
    private WebElement familyNameBox;

    @FindBy(id="next-button")
    private WebElement greenClick;

    @FindBy(xpath = "//option[@value='M']")
    private WebElement maleGender;

    @FindBy(xpath = "//input[starts-with(@class,'day number numeri')]")
    private WebElement dayBox;

    @FindBy(name = "birthdateMonth")
    private WebElement monthSelect; //select

    @FindBy(id="birthdateYear-field")
    private WebElement yearBox;

    @FindBy(id ="address1")
    private WebElement addressBox;

    @FindBy(name = "phoneNumber")
    private WebElement phoneNumberBox;

    @FindBy(id = "relationship_type")
    private WebElement relationshipTypeSelect; //select

    @FindBy(xpath = "//input[starts-with(@class,'person-typeahead')]")
    private WebElement personNameBox;

    @FindBy(id= "submit")
    private WebElement confirmButton;

    @FindBy(xpath = "//div[@class='info-header']")
    private List<WebElement> registrationElements;

    String[] registrationElementsArray ={"DIAGNOSES","VITALS","LATEST OBSERVATIONS",
            "HEALTH TREND SUMMARY","WEIGHT GRAPH","APPOINTMENTS","RECENT VISITS","FAMILY","CONDITIONS",
            "ATTACHMENTS","ALLERGIES"};

//    @FindBy(xpath = "//a[@href='/openmrs/index.htm']")
//    private WebElement homeBtn;

    @FindBy(xpath = "//a[contains(@id,'coreapps-activeVisit')][1]")
    private WebElement findPatientTab;

    @FindBy(xpath = "//td[2]")
    private WebElement verificationText;

    @FindBy(xpath = "//i[@class='icon-sticky-note']")
    private WebElement yellowStickyNote;

    @FindBy(xpath = "//div[@class='toast-item-wrapper']")
    private WebElement popUpTab;

    @FindBy(xpath = "//form[contains(@class,'form-inline editable-wrap edi')]//textarea")
    private WebElement noteEditArea;

    @FindBy(xpath = "//span[@class='icon-ok icon-white']")
    private WebElement checkMark;


//    @FindBy(xpath = "//span[@class='icon-ok icon-white']")
//    private WebElement nameCheck;
//    @FindBy(xpath = "//span[@class='icon-ok icon-white']")
//    private WebElement genderCheck;
//    @FindBy(xpath = "//span[@class='icon-ok icon-white']")
//    private WebElement birthDayCheck;
//    @FindBy(xpath = "//div/p[4]")
//    private WebElement addressCheck;
//    @FindBy(xpath = "//div/p[4]")
//    private WebElement phoneNumberCheck;
//    @FindBy(xpath = "//div/p[4]")
//    private WebElement relativesCheck;   Specific Items Check


    public void nextBottonClick(){
        greenClick.click();
    }

    public void patientsNameSet() throws Exception {
        String first=faker.name().firstName();
        String middle=faker.name().firstName();
        String family=faker.name().lastName();
        firstNameBox.sendKeys(first);
        middleNameBox.sendKeys(middle);
        familyNameBox.sendKeys(family);
        ConfigReader.setProperties("firstName",first);
        Thread.sleep(1500);
        ConfigReader.setProperties("middleName",middle);
        Thread.sleep(1500);
        ConfigReader.setProperties("familyName",family);


    }

    public void genderSelect() {
        maleGender.click();
    }

    public void birthDateSelect(){
        dayBox.sendKeys("10");
        select = new Select(monthSelect);
        select.selectByIndex(5);
        yearBox.sendKeys("1990");
    }

    public void addressSelect(){
        String address = faker.address().streetAddress();
        addressBox.sendKeys(address);
    }

    public void phoneNumberSelect(){
        String phoneNumber= "3015278995";
        phoneNumberBox.sendKeys(phoneNumber);
    }

    public void relationTabSelect(){
        select = new Select(relationshipTypeSelect);
        select.selectByIndex(5);
        String firstName=faker.name().firstName();
        String lastName=faker.name().lastName();
        personNameBox.sendKeys(firstName+""+lastName);
    }

    public void confirmClick(){
        confirmButton.click();
    }
}
