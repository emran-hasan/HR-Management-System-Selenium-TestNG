package testrunner;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.LoginPage;
import setup.Setup;
import utlis.Utils;

public class LoginTestRunner extends Setup {
    @Test (priority = 4, description = "Verify Successful login with valid user credentials")
    public void doLogin() throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
         loginPage.doLogin("Admin", "admin123");
        Thread.sleep(4000);
        WebElement userName = driver.findElement(By.className("oxd-userdropdown-name"));
        Assert.assertTrue(userName.isDisplayed());
    }
    @Test (priority = 1, description = "Verify Unsuccessful login with invalid user name")
    public void doLoginWithInvalidUserName(){
        LoginPage loginPage = new LoginPage(driver);
        loginPage.doLogin("Adminnn", "admin123");
        String resultActual= driver.findElements(By.className("oxd-text")).get(1).getText();
        String resultExpected = "Invalid credentials";
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(resultActual.contains(resultExpected));
    }
    @Test (priority = 2, description = "Unsuccessful login with invalid password")
    public void doLoginWithInvalidPassword(){
        LoginPage loginPage = new LoginPage(driver);
        loginPage.doLogin("Admin", "admin");
        String resultActual= driver.findElements(By.className("oxd-text")).get(1).getText();
        String resultExpected = "Invalid credentials";
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(resultActual.contains(resultExpected));
    }
    @Test (priority = 3, description = "Unsuccessful login with invalid password")
    public void doLoginWithEmptyPassword(){
        LoginPage loginPage = new LoginPage(driver);
        loginPage.doLogin("", "");
        String resultActual= driver.findElements(By.className("oxd-text")).get(1).getText();
        String resultExpected = "Invalid credentials";
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(resultActual.contains(resultExpected));
    }

}
