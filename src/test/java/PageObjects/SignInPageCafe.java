package PageObjects;

import Utility.BaseUtils;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class SignInPageCafe  extends BaseUtils {
    WebDriver driver;

    public SignInPageCafe(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }
    @FindBy(how = How.XPATH, using = "//input[@ng-model='user.name']")
    public WebElement username;

    @FindBy(how = How.XPATH, using = "//input[@ng-model='user.password']")
    public WebElement password;

    @FindBy(how = How.XPATH, using = "//button[text()='Login']")
    public WebElement login;

    @FindBy(how = How.XPATH, using = "//p[text()='Invalid username or password!']")
    public WebElement errorMessage;

    public void loginwithCredential(String uName, String pwd){
        sendKeys(driver,username,uName);
        sendKeys(driver,password,pwd);
        Click(driver,login);
    }
}
