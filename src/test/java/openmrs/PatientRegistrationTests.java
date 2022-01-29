package openmrs;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utils.ConfigReader;
import utils.ExcelReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Map;

/**
 * Created by tairovich_jr on 2022-01-27.
 */
public class PatientRegistrationTests extends BaseTest{


    @Test(dataProvider = "patients")
    public void registerNewPatient(Map<String,String> data) throws Exception{

        driver.get(ConfigReader.getProperty("url"));
        driver.findElement(By.id("username")).sendKeys("Admin");
        driver.findElement(By.id("password")).sendKeys("Admin123");
        driver.findElement(By.id("Inpatient Ward")).click();
        driver.findElement(By.id("loginButton")).click();
        driver.findElement(By.xpath("//a[contains(@id,'registerPatient')]")).click();

        driver.findElement(By.name("givenName")).sendKeys( data.get("givenname"));
        driver.findElement(By.name("middleName")).sendKeys(data.get("middlename"));
        driver.findElement(By.name("familyName")).sendKeys(data.get("familyname"));
        Thread.sleep(1500);
        driver.findElement(By.id("next-button")).click();

        WebElement gender = driver.findElement(By.id("gender-field"));
        Select select = new Select(gender);
        select.selectByVisibleText( data.get("gender") );

        Thread.sleep(1500);
        driver.findElement(By.id("next-button")).click();

        //January 1 1990
        String birthdate = data.get("birthdate"); //from excel sheet
        String[] split = birthdate.split(" ");
        String monthStr = split[0];
        String day = split[1];
        String year = split[2];

        if (data.get("dobOrAge").equals("dob")){

            WebElement dobDayInput = driver.findElement(By.name("birthdateDay"));
            dobDayInput.sendKeys(day);

            WebElement month = driver.findElement(By.id("birthdateMonth-field"));
            Select select2 = new Select(month);
            select2.selectByVisibleText( monthStr );

            WebElement dobYearInput = driver.findElement(By.id("birthdateYear-field"));
            dobYearInput.sendKeys(year);
        }else if (data.get("dobOrAge").equals("age")){
            int currentYear = LocalDate.now().getYear();
            int age = currentYear - Integer.parseInt(year);

            driver.findElement(By.id("birthdateYears-field")).sendKeys(String.valueOf(age));
            SimpleDateFormat inputFormat = new SimpleDateFormat("MMMM");
            Calendar cal = Calendar.getInstance();
            cal.setTime(inputFormat.parse(monthStr));
            SimpleDateFormat outputFormat = new SimpleDateFormat("MM"); // 01-12

            int estMonths =  Integer.parseInt(outputFormat.format(cal.getTime()));
            driver.findElement(By.id("birthdateMonths-field")).sendKeys(String.valueOf(estMonths));

        }

        Thread.sleep(1500);
        driver.findElement(By.id("next-button")).click();

        driver.findElement(By.id("address1")).sendKeys(data.get("address1"));
        driver.findElement(By.id("address2")).sendKeys(data.get("address2"));
        driver.findElement(By.id("cityVillage")).sendKeys(data.get("city"));
        driver.findElement(By.id("stateProvince")).sendKeys(data.get("state"));
        driver.findElement(By.id("country")).sendKeys(data.get("country"));
        driver.findElement(By.id("postalCode")).sendKeys(data.get("postalcode"));


        Thread.sleep(1500);
        driver.findElement(By.id("next-button")).click();

        String phonenumber = data.get("phonenumber");

        driver.findElement(By.name("phoneNumber")).sendKeys(phonenumber);

        Thread.sleep(1500);
        driver.findElement(By.id("next-button")).click();

        WebElement relationship_type = driver.findElement(By.id("relationship_type"));
        Select select2 = new Select(relationship_type);
        select2.selectByVisibleText(data.get("relative"));
        driver.findElement(By.xpath("//input[@placeholder='Person Name']")).sendKeys(data.get("relativename"));

        driver.findElement(By.id("next-button")).click();
        driver.findElement(By.xpath("//input[@value='Confirm']")).click();


        softAssert.assertAll();
    }


    @DataProvider(name = "patients")
    public Object[][] getData() throws IOException {
        ExcelReader reader = new ExcelReader("src/main/resources/testData/patients.xlsx", "newPatients");
        return reader.getData();
    }

}