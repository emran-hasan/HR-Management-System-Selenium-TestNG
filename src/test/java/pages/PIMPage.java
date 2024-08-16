package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import setup.EmpModel;
import utlis.Utils;

import java.util.List;

public class PIMPage {
    @FindBy(className = "oxd-main-menu-item")
    List <WebElement> menuItems;
    @FindBy(className = "oxd-button")
     List<WebElement> button;
    @FindBy(className = "oxd-switch-input")
    WebElement toggleButton;
    @FindBy(className = "oxd-input")
    List<WebElement> inputField;
    @FindBy(tagName = "input")
    List<WebElement> employeeName;
    @FindBy(css = "[role=listbox]")
    WebElement visibleButton;

    public PIMPage(WebDriver driver){
        PageFactory.initElements(driver, this);
    }
    public void createEmployee(String fName, String lName, String username, String password){
        menuItems.get(1).click();
        button.get(2).click();
        toggleButton.click();
        inputField.get(1).sendKeys(fName);
        inputField.get(3).sendKeys(lName);
        inputField.get(5).sendKeys(username);
        inputField.get(6).sendKeys(password);
        inputField.get(7).sendKeys(password);
        button.get(1).click();
    }
    public  void searchEmpByEmpID(String empID){
        menuItems.get(1).click();
        inputField.get(1).sendKeys(empID);
        button.get(1).click();
    }
    public void searchEmpByEmpName(String empName) throws InterruptedException {
        menuItems.get(8).click();
        employeeName.get(1).sendKeys(empName);
        Thread.sleep(10000);
        visibleButton.click();
        button.get(1).click();
    }
}
