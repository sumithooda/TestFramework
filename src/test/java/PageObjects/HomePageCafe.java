package PageObjects;

import Utility.BaseUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class HomePageCafe extends BaseUtils {
    WebDriver driver;

    public HomePageCafe(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(how = How.XPATH, using = "//p[text()='Logout']")
    public WebElement logout;

    @FindBy(how = How.XPATH, using = "//a[text()='Create']")
    public WebElement createButton;

    @FindBy(how = How.XPATH, using = "//a[text()='Edit']")
    public WebElement editButton;

    @FindBy(how = How.XPATH, using = "//a[text()='Delete']")
    public WebElement deleteButton;

    @FindBy(how = How.XPATH, using = "//input[@ng-model='selectedEmployee.firstName']")
    public WebElement firstName;

    @FindBy(how = How.XPATH, using = "//input[@ng-model='selectedEmployee.lastName']")
    public WebElement lastName;

    @FindBy(how = How.XPATH, using = "//input[@ng-model='selectedEmployee.startDate']")
    public WebElement startDate;

    @FindBy(how = How.XPATH, using = "//input[@ng-model='selectedEmployee.email']")
    public WebElement email;

    @FindBy(how = How.XPATH, using = "//button[text()='Add']")
    public WebElement addButton;

    @FindBy(how = How.XPATH, using = "//p[@id='greetings']")
    public WebElement userHelloText;

    @FindBy(how = How.XPATH, using = "//ul[@id='employee-list']/li")
    public List<WebElement> employeeList;

    @FindBy(how = How.XPATH, using = "//button[text()='Update']")
    public WebElement updateButton;

    @FindBy(how = How.XPATH, using = "//a[text()='Back']")
    public WebElement backButton;

    public void logoutCafe() throws InterruptedException {
        Click(driver,logout);
    }

    public void addEmployeedetail(String fName,String lName,String sDate,String mailId){
        sendKeys(driver,firstName,fName);
        sendKeys(driver,lastName,lName);
        sendKeys(driver,startDate,sDate);
        sendKeys(driver,email,mailId);
        Click(driver,addButton);
    }




}
