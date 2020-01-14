package StepDefinitions;

import Utility.*;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.cucumber.datatable.DataTable;
import org.testng.Assert;

import java.util.List;

public class SignInCafeSteps extends BaseUtils {

    ConfigFactory obj = new ConfigFactory();

    WebdriverFactory webdriverFactory;
    PageObjectFactory pageObjectFactory;
    TestState testState;

    public SignInCafeSteps(TestState state) {
        testState = state;
        webdriverFactory = testState.getWebdriverFactory();
        pageObjectFactory = testState.getPageObjectFactory();
    }

    @When("user enters {string} in username field and {string} in password field and click on login button")
    public void userEntersInUsernameFieldAndInPasswordFieldAndClickOnLoginButton(String uname, String pwd) throws Exception {
        sendKeys(webdriverFactory.getDriver(), pageObjectFactory.getSignInPageCafe().username, uname);
        sendKeys(webdriverFactory.getDriver(), pageObjectFactory.getSignInPageCafe().password, pwd);
        Click(webdriverFactory.getDriver(), pageObjectFactory.getSignInPageCafe().login);
    }

    @Then("user should be navigated to home page and should able to see text Hello {string}")
    public void userShouldBeNavigatedToHomePageAndShouldAbleToSeeTextHello(String userName) {
        Assert.assertEquals(pageObjectFactory.getHomePageCafe().userHelloText.getText(), "Hello " + userName, "Hello user text is not correct");
    }


    @When("user clicks on Logout button and navigated to sign in page")
    public void userClicksOnLogoutButton() throws Exception {
        jsClick(webdriverFactory.getDriver(), pageObjectFactory.getHomePageCafe().logout);
        Assert.assertTrue(isDisplayedWithWait(webdriverFactory.getDriver(),pageObjectFactory.getSignInPageCafe().username), "user is not navigated to sign in page afer logout");
    }

    @When("user try to login with below credentials:")
    public void userTryToLoginWithBelowCredentials(DataTable arg1) {
        List<String> list = arg1.asList(String.class);
        pageObjectFactory.getSignInPageCafe().loginwithCredential(list.get(0), list.get(1));

    }

    @Then("{string} should be displayed on {string}")
    public void shouldBeDisplayedOn(String errorMsg, String field) throws Exception {

        switch (field) {
            case "username":
                String error1 = pageObjectFactory.getSignInPageCafe().username.getAttribute("validationMessage");
                //String error1 = getErrormessage(driver, pageObjectFactory.getSignInPageCafe().username);
                Assert.assertEquals(error1, errorMsg, "error message for username field is not valid");
                break;
            case "password":
                String error2 = pageObjectFactory.getSignInPageCafe().password.getAttribute("validationMessage");
                //String error2 = getErrormessage(driver, pageObjectFactory.getSignInPageCafe().password);
                Assert.assertEquals(error2, errorMsg, "error message for password field is not valid");
                break;
            case "errorlabel":
                String error3 = pageObjectFactory.getSignInPageCafe().errorMessage.getText();
                Assert.assertEquals(error3, errorMsg, "Invalid error message for login action");
        }

    }


}
