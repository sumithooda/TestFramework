package StepDefinitions;

import Utility.*;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import java.util.concurrent.TimeUnit;

public class GenericSteps extends BaseUtils{

    private WebDriver driver;
    ConfigFactory obj=new ConfigFactory();
    BaseUtils base =  new BaseUtils();

    WebdriverFactory webdriverFactory;
    PageObjectFactory pageObjectFactory;
    TestState testState;

    public GenericSteps(TestState state) {
        testState = state;
        webdriverFactory = testState.getWebdriverFactory();
        pageObjectFactory = testState.getPageObjectFactory();
    }

    @Given("^Browser is opened and user is navigated to sign in page$")
    public void browserISOpenedAndUserIsNavigatedToApplicationUrl() throws Exception {
        webdriverFactory.getDriver().get(obj.getApplicationUrl());
        webdriverFactory.getDriver().manage().window().maximize();
        webdriverFactory.getDriver().manage().timeouts().implicitlyWait(Long.parseLong(obj.getImplicitlyWait()), TimeUnit.MILLISECONDS);
        String currentPage=getCurrentPageTitle(webdriverFactory.getDriver());
        Assert.assertEquals(currentPage,"CafeTownsend-AngularJS-Rails","user navigation is not proper for sign in page");
    }

    @After
    public void tearDown(Scenario scenario) throws Exception {

        if(scenario.isFailed()){
            base.captureScreenShot(webdriverFactory.getDriver(),scenario);
        }

        if(webdriverFactory.getDriver()!=null) {
            webdriverFactory.closeDriver(webdriverFactory.getDriver());
        }
    }

    @Then("user should be navigated to page with title {string}")
    public void userShouldBeNavigatedToPageWithTitle(String pageTitle) throws Exception {
        String currentPage=getCurrentPageTitle(webdriverFactory.getDriver());
        Assert.assertEquals(currentPage,pageTitle,"user navigation is not proper for "+pageTitle+" page");
    }

}
