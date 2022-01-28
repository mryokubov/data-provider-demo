package pages;

import com.github.javafaker.Faker;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.asserts.SoftAssert;
import utils.ConfigReader;

/**
 * Created by tairovich_jr on 2022-01-27.
 */
public class LoginPage {

    public WebDriver driver;
    public SoftAssert softAssert;
    public Faker faker;

    public LoginPage(WebDriver driver, SoftAssert softAssert){
        this.driver = driver;
        this.softAssert=softAssert;
        this.faker=new Faker();
        PageFactory.initElements(driver,this); //142
    }

    @FindBy(id = "username")
    private WebElement id;

    @FindBy(id="password")
    private  WebElement passW;

    @FindBy(id="Inpatient Ward")
    private  WebElement location;

    @FindBy(id="loginButton")
    private  WebElement loginBtn;

    public void login(){
        userBox(ConfigReader.getProperty("username"));
        passBox(ConfigReader.getProperty("password"));
        locationSelect();
        loginClick();
    }

    private  void userBox(String username){
        id.sendKeys(username);
    }
    private  void passBox(String password){
        passW.sendKeys(password);
    }
    private  void locationSelect(){
        location.click();
    }
    private  void loginClick(){
        loginBtn.click();
    }
}
