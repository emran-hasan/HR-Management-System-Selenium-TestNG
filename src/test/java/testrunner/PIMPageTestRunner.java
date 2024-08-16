package testrunner;

import com.github.javafaker.Faker;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.LoginPage;
import pages.PIMPage;
import setup.EmpModel;
import setup.Setup;
import utlis.Utils;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import static utlis.Utils.getUser;

public class PIMPageTestRunner extends Setup {
    @BeforeTest
    public void doLogin(){
        LoginPage loginPage = new LoginPage(driver);
        loginPage.doLogin("Admin", "admin123");
        String resultActual = driver.findElements(By.className("oxd-text")).get(8).getText();
        String resultExpected = "Dashboard";
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(resultActual.contains(resultExpected));
    }
    @Test(priority = 1, description = "Verify the employee create successfully")
    public void createEmployee() throws InterruptedException, IOException, ParseException {
        PIMPage pimPage = new PIMPage(driver);
        Faker faker = new Faker();
        String fName = faker.name().firstName();
        String lName = faker.name().lastName();
        String username = faker.name().username();
        String password = "Emran@1234@";
        pimPage.createEmployee(fName,lName,username,password);
        Thread.sleep(9000);
        String result = driver.findElements(By.className("orangehrm-main-title")).get(0).getText();
        Assert.assertTrue(result.contains("Personal Details"));
        List<WebElement> id = driver.findElements(By.className("oxd-input"));
        String empID = id.get(4).getAttribute("value");

        EmpModel empModel = new EmpModel();
        empModel.setfName(fName);
        empModel.setlName(lName);
        empModel.setUsername(username);
        empModel.setEmpID(empID);
        empModel.setPassword(password);
        Utils.saveUsers(empModel);
    }
    @Test(priority = 2, description = "Verify to create employee unsuccessfully with missing information")
    public void createEmployeeWithMissingInfo() throws InterruptedException, IOException, ParseException {
        PIMPage pimPage = new PIMPage(driver);
        Faker faker = new Faker();
        String fName = "";
        String lName = faker.name().lastName();
        String username = faker.name().username();
        String password = "Emran@1234@";
        pimPage.createEmployee(fName,lName,username,password);
        Thread.sleep(9000);
        String resultActual = driver.findElements(By.className("oxd-text")).get(8).getText();
        String resultExpected = "Required";
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(resultActual.contains(resultExpected));
    }


    @Test(priority = 4, description = "Verify unsuccessful creation of a new employee with a duplicate employee ID or username.")
    public void employeeRegistrationWithDuplicateUsername() throws InterruptedException, IOException, ParseException {
        PIMPage pimPage = new PIMPage(driver);
        JSONArray empArray = getUser();
        JSONObject jsonObject = (JSONObject) empArray.get(empArray.size()-1);
        String duplicateUser = (String) jsonObject.get("username");
        Faker faker = new Faker();
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String username = duplicateUser;
        String password = "Emran@1234@";

        pimPage.createEmployee(firstName, lastName, username, password);
        Utils.windowScroll(driver, 500);

        String alertActual = driver.findElements(By.className("oxd-text")).get(16).getText();
        String alertExpected = "Username already exists";

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(alertActual.contains(alertExpected));
    }


    @Test(priority = 3, description = "Verify the employee account is created successfully")
    public void employeeCreateSuccess(){
        List<WebElement> empDetails = driver.findElements(By.className("oxd-text"));
        String asserResult = empDetails.get(13).getText();
        String resultExpected="Personal Details";
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(asserResult.contains(resultExpected));
    }
    @Test(priority = 5, description = "Verify to search employee by valid empId")
    public  void searchEmpByEmpID() throws IOException, ParseException, InterruptedException {
        PIMPage pimPage = new PIMPage(driver);
        JSONArray empArray = getUser();
        JSONObject jsonObject = (JSONObject) empArray.get(empArray.size()-1);
        String empID = (String) jsonObject.get("empID");
        pimPage.searchEmpByEmpID(empID);
        Utils.windowScroll(driver,200);
        Thread.sleep(8000);
        String resultActual = driver.findElements(By.className("oxd-text")).get(14).getText();
        String resultExpected =  "(1) Record Found";
        SoftAssert softAssert =new SoftAssert();
        softAssert.assertTrue(resultActual.contains(resultExpected));

    }
    @Test(priority = 6, description = "Verify to search employee by Invalid empId")
    public  void searchEmpByInvalidEmpID() throws IOException, ParseException, InterruptedException {
        PIMPage pimPage = new PIMPage(driver);
        String empID = "2568";
        pimPage.searchEmpByEmpID(empID);
        Utils.windowScroll(driver,200);
        Thread.sleep(8000);
        String resultActual = driver.findElements(By.className("oxd-text")).get(13).getText();
        String resultExpected ="No Records Found";
        SoftAssert softAssert =new SoftAssert();
        softAssert.assertTrue(resultActual.contains(resultExpected));
    }
    @Test(priority = 7, description = "Verify to search employee by valid Name")
    public void searchEmpByEmpName() throws IOException, ParseException, InterruptedException {
        PIMPage pimPage = new PIMPage(driver);
        JSONArray empArray = getUser();
        JSONObject jsonObject = (JSONObject) empArray.get(empArray.size()-1);
        String empName = (String) jsonObject.get("fName");
        pimPage.searchEmpByEmpName(empName);
        Utils.windowScroll(driver,100);
        Thread.sleep(8000);

        String resultActual = driver.findElements(By.className("oxd-text")).get(13).getText();
        String resultExpected = "(1) Record Found";

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(resultActual.contains(resultExpected));
    }
    @Test(priority = 8, description = "Verify to search employee by Invalid Name")
    public void searchEmpByInvalidEmpName() throws IOException, ParseException, InterruptedException {
        PIMPage pimPage = new PIMPage(driver);
        String empName ="Emran";
        pimPage.searchEmpByEmpName(empName);
        Utils.windowScroll(driver,100);
        Thread.sleep(8000);
        String resultActual = driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[1]/div[2]/form/div[1]/div/div[1]/div/span")).getText();
        String resultExpected = "Invalid";

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(resultActual.contains(resultExpected));
        softAssert.assertAll();
    }
    @AfterTest
    public void doLogout(){
        LoginPage loginPage = new LoginPage(driver);
        loginPage.doLogout();
        String loginTitle = driver.findElement(By.className("orangehrm-login-title")).getText();
        String resultExpected = "Login";
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(loginTitle.contains(resultExpected));
    }
}