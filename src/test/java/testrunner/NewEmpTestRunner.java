package testrunner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.EmployeeDetails;
import pages.LoginPage;
import setup.Setup;
import utlis.Utils;

import java.io.IOException;
import static utlis.Utils.getUser;

public class NewEmpTestRunner extends Setup {;

    @Test (priority = 3, description = "Successfully new employee login with valid credentials")
    public void newEmpLogin() throws IOException, ParseException {
        LoginPage loginPage = new LoginPage(driver);
        JSONArray empArray = getUser();
        JSONObject jsonObject = (JSONObject) empArray.get(empArray.size()-1);
        String username = jsonObject.get("username").toString();
        String password = jsonObject.get("password").toString();
        loginPage.doLogin(username, password);
//        WebElement empProfileName = driver.findElement(By.className("oxd-userdropdown-name"));
//        Assert.assertTrue(empProfileName.isDisplayed());
        String resultActual = driver.findElements(By.className("oxd-text")).get(8).getText();
        String resultExpected = "Dashboard";
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(resultActual.contains(resultExpected));
    }
    @Test (priority = 2, description = "Verify new employee login with invalid credentials")
    public void newEmpLoginWithInvalidCred() throws IOException, ParseException {
        LoginPage loginPage = new LoginPage(driver);
        String username = "Emran.hasan";
        String password ="Emran@12354";
        loginPage.doLogin(username, password);
        String resultActual= driver.findElements(By.className("oxd-text")).get(1).getText();
        String resultExpected = "Invalid credentials";
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(resultActual.contains(resultExpected));
    }
    @Test (priority = 1, description = "Verify new employee login with empty credentials")
    public void newEmpLoginWithEmptyCred() throws IOException, ParseException {
        LoginPage loginPage = new LoginPage(driver);
        String username = "";
        String password ="";
        loginPage.doLogin(username, password);
        String resultActual= driver.findElements(By.className("oxd-text")).get(1).getText();
        String resultExpected = "Invalid credentials";
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(resultActual.contains(resultExpected));
    }
    @Test(priority = 4, description = "Verify new employee full name is showing besides the profile icon")
    public void assertFullName(){
        WebElement assertName = driver.findElement(By.className("oxd-userdropdown-name"));
        Assert.assertTrue(assertName.isDisplayed());
    }
    @Test(priority = 5, description = "Verify to radio button is working properly")
    public void radioInput() throws InterruptedException {
        EmployeeDetails employeeDetails = new EmployeeDetails(driver);
        employeeDetails.personalInfo();
        Utils.windowScroll(driver,500);
    }
    @Test(priority = 6 , description = "Verify to employee blood group button is working properly")
    public void bloodGroup() throws InterruptedException {
        WebElement selectBloodIcon = driver.findElements(By.className("oxd-select-text-input")).get(2);
        selectBloodIcon.click();
        Actions actions = new Actions(driver);
        actions.sendKeys(Keys.DOWN).perform();
        actions.sendKeys(Keys.DOWN).perform();
        actions.sendKeys(Keys.DOWN).perform();
        actions.sendKeys(Keys.DOWN).perform();
        actions.sendKeys(Keys.DOWN).perform();
        actions.sendKeys(Keys.ENTER).perform();
        WebElement saveBtn = driver.findElements(By.className("oxd-button")).get(1);
        saveBtn.click();
        Thread.sleep(7000);
    }
    @AfterTest
    public void doLogout(){
    LoginPage loginPage =new LoginPage(driver);
    loginPage.doLogout();
    }
}
