package StepDefinitions;

import Utility.*;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.cucumber.datatable.DataTable;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;

public class HomeCafeSteps extends BaseUtils {

    ConfigFactory obj = new ConfigFactory();

    WebdriverFactory webdriverFactory;
    PageObjectFactory pageObjectFactory;
    TestState testState;

    public HomeCafeSteps(TestState state) {
        testState = state;
        webdriverFactory = testState.getWebdriverFactory();
        pageObjectFactory = testState.getPageObjectFactory();
    }

    @And("user clicks on create button")
    public void userClicksOnCreateButton() throws Exception {
        jsClick(webdriverFactory.getDriver(), pageObjectFactory.getHomePageCafe().createButton);
    }

    @Then("user should able to navigate to add new employee section")
    public void userShouldAbleToNavigateToAddNewEmployeeSection() {
        Assert.assertTrue(pageObjectFactory.getHomePageCafe().firstName.isDisplayed(), "User is unable to open add employee section");
    }

    @When("user fill in registration details and click on Add button")
    public void userFillInRegistrationDetailsAndClickOnAddButton(DataTable arg1) throws Exception {
        List<String> list = arg1.asList(String.class);
        String random = randomAlphaNumeric(3);
        testState.newEmployee = list.get(0) + " " + list.get(1)+random;
        sendKeys(webdriverFactory.getDriver(), pageObjectFactory.getHomePageCafe().firstName, list.get(0));
        sendKeys(webdriverFactory.getDriver(), pageObjectFactory.getHomePageCafe().lastName, list.get(1)+random);
        sendKeys(webdriverFactory.getDriver(), pageObjectFactory.getHomePageCafe().startDate, list.get(2));
        sendKeys(webdriverFactory.getDriver(), pageObjectFactory.getHomePageCafe().email, list.get(3));
        Click(webdriverFactory.getDriver(), pageObjectFactory.getHomePageCafe().addButton);
    }

    @Then("newly added employee should be displayed on home page")
    public void newlyAddedEmployeeShouldBeDisplayedOnHomePage() {
        String empName = null;
        for (int i = 0; i < pageObjectFactory.getHomePageCafe().employeeList.size(); i++) {
            if (pageObjectFactory.getHomePageCafe().employeeList.get(i).getText().equalsIgnoreCase(testState.newEmployee.trim())) {
                empName = pageObjectFactory.getHomePageCafe().employeeList.get(i).getText();
                break;
            }
        }
        Assert.assertEquals(empName, testState.newEmployee, "Employee doesn't exist");
    }

    @And("user selects a employee from cafe and user clicks on edit button")
    public void userSelectsAEmployeeFromCafe() throws Exception {
        testState.editEmployee = pageObjectFactory.getHomePageCafe().employeeList.get(0).getText();
        jsClick(webdriverFactory.getDriver(), pageObjectFactory.getHomePageCafe().employeeList.get(0));
        jsClick(webdriverFactory.getDriver(), pageObjectFactory.getHomePageCafe().editButton);
    }

    @Then("user should be able to see employee details")
    public void userShouldBeAbleToSeeEmployeeDetails() {
        Assert.assertTrue(pageObjectFactory.getHomePageCafe().firstName.isDisplayed(), "User is unable to see employee details");
    }

    @And("user should be able to update the employee email to {string} and click on update button")
    public void userShouldBeAbleToUpdateTheEmployeeLastNameToAndClickOnUpdateButton(String email) throws Exception {
        pageObjectFactory.getHomePageCafe().email.clear();
        testState.updateMail = randomAlphaNumeric(2)+email;
        sendKeys(webdriverFactory.getDriver(), pageObjectFactory.getHomePageCafe().email, testState.updateMail);
    }

    @And("user selects same employee and click on update button")
    public void userSelectsSameEmployeeAndClickOnUpdateButton() throws Exception {
        Click(webdriverFactory.getDriver(), pageObjectFactory.getHomePageCafe().updateButton);
    }

    @And("employee email details should be as updated")
    public void employeeEmailDetailsShouldBeAs() throws Exception {
        for(int i=0;i<pageObjectFactory.getHomePageCafe().employeeList.size();i++){
            if(pageObjectFactory.getHomePageCafe().employeeList.get(i).getText().equalsIgnoreCase(testState.editEmployee)){
                Click(webdriverFactory.getDriver(), pageObjectFactory.getHomePageCafe().employeeList.get(i));
            }
        }
        Click(webdriverFactory.getDriver(),pageObjectFactory.getHomePageCafe().editButton);
        isDisplayedWithWait(webdriverFactory.getDriver(),pageObjectFactory.getHomePageCafe().email);
        Assert.assertEquals(pageObjectFactory.getHomePageCafe().email.getAttribute("value"), testState.updateMail, "Mail address is not updated for employee");
        Click(webdriverFactory.getDriver(), pageObjectFactory.getHomePageCafe().backButton);
    }

    @When("user selects a employee from cafe employee list")
    public void userSelectsAEmployeeFromCafeEmployeeList() {
        testState.deleteEmployee = pageObjectFactory.getHomePageCafe().employeeList.get(0).getText();
        pageObjectFactory.getHomePageCafe().employeeList.get(0).click();
    }

    @And("user clicks on delete button")
    public void userClicksOnDeleteButton() throws Exception {
        jsClick(webdriverFactory.getDriver(), pageObjectFactory.getHomePageCafe().deleteButton);
    }

    @Then("selected employee should get deleted and not available in cafe employee list")
    public void selectedEmployeeShouldGetDeletedAndNotAvailableInCafeEmployeeList() {
        for (int i = 0; i < pageObjectFactory.getHomePageCafe().employeeList.size(); i++) {
            if (pageObjectFactory.getHomePageCafe().employeeList.get(i).getText().equalsIgnoreCase("testState.deleteEmployee"))
                Assert.assertFalse(true, "employee is not deleted from cafe employee list");
        }
    }

    @Then("user should be navigated to add new employee section")
    public void userShouldBeNavigatedToAddNewEmployeeSection() {
        Assert.assertTrue(pageObjectFactory.getHomePageCafe().firstName.isDisplayed());
    }


    @When("user fill in registration form and click on Add button")
    public void userFillInRegistrationFormAndClickOnAddButton(DataTable arg) {
        List<String> list = arg.asList(String.class);
        pageObjectFactory.getHomePageCafe().addEmployeedetail(list.get(0), list.get(1), list.get(2), list.get(3));
    }

    @Then("error message {string} should be displayed for {string}")
    public void errorMessageShouldBeDisplayedFor(String error, String field) throws Exception {
        switch (field) {
            case "firstname":
                String error1 = pageObjectFactory.getHomePageCafe().firstName.getAttribute("validationMessage");
                Assert.assertEquals(error1, error, "error message for username field is not valid");
                break;
            case "lastName":
                String error2 = pageObjectFactory.getHomePageCafe().lastName.getAttribute("validationMessage");
                Assert.assertEquals(error2, error, "error message for username field is not valid");
                break;
            case "StarteDate":
                String error3 = pageObjectFactory.getHomePageCafe().startDate.getAttribute("validationMessage");
                Assert.assertEquals(error3, error, "error message for start date field is not valid");
                break;
            case "email":
                String error4 = pageObjectFactory.getHomePageCafe().email.getAttribute("validationMessage");
                Assert.assertEquals(error4, error, "error message for email field is not valid");
                break;
        }
    }

    @Then("user confirmation alert should appear and user clicks on yes button")
    public void userConfirmationAlertShouldAppearAndUserClicksOnYesButton() throws Exception {
        webdriverFactory.getDriver().switchTo().alert().accept();
    }
}
