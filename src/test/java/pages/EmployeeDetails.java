package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class EmployeeDetails {
    @FindBy(className = "oxd-main-menu-item")
    List<WebElement> maniMenu;
    @FindBy(className = "oxd-radio-input")
    List<WebElement> radioInput;
    @FindBy(className = "oxd-select-text")
    List<WebElement> bloodDropDownBtn;
    @FindBy(className = "oxd-button")
    List<WebElement> SaveBtn;

    public  EmployeeDetails(WebDriver driver){
        PageFactory.initElements(driver, this);
    }
    public void personalInfo() throws InterruptedException {
        maniMenu.get(2).click();
        Thread.sleep(4000);
        radioInput.get(0).click();
        Thread.sleep(3000);
        SaveBtn.get(0).click();
    }
}
