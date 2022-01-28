package openmrs;

import com.github.javafaker.Faker;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import utils.BrowserDriver;
import utils.ConfigReader;
import utils.StaticValues;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
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
        driver.findElement(By.id("next-button")).click();


        softAssert.assertAll();
    }


    @DataProvider(name = "patients")
    public Object[][] getData() throws IOException {

//        Object[][] data = new Object[1000][1];
//        //                   number of rows 3
//        for (int i = 0; i < data.length; i++) {
//
//            Map<String, String> map = new HashMap<>();
//
//            String id = String.valueOf(faker.random().nextInt(1000,9999));
//            String firstName = faker.name().firstName();
//            String lastName = faker.name().lastName();
//            String middle = faker.name().nameWithMiddle();
//
//            map.put(id,firstName + " " + middle + " " + lastName);
//
//                          //      number of cols in each row
//            for (int j = 0; j < data[i].length; j++) {
//                data[i][j] = map;
//            }
//        }

        File file = new File(StaticValues.PATIENTS_EXCEL_FILE);
        FileInputStream fis = new FileInputStream(file);

        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        XSSFSheet worksheet = workbook.getSheet("newPatients");

        workbook.close();

        int rows = worksheet.getLastRowNum(); //number of total rows
        int cols = worksheet.getRow(0).getLastCellNum(); //number of total columns

        Object[][] data = new Object[rows][1];

        for (int i = 0; i < rows; i++) {
            Map<Object, Object> map = new LinkedHashMap<>();

            for (int j = 0; j < cols; j++) {

                map.put(worksheet.getRow(0).getCell(j).toString(), worksheet.getRow(i+1).getCell(j).toString() );
            }
            data[i][0] = map;
        }
        return data;
    }

}