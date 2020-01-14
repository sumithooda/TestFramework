package Utility;

import PageObjects.*;
import org.openqa.selenium.WebDriver;

/**
 * Holds instance of all the page classes and make sure only single object is present of particular page for the execution
 *
 * @author Sumit
 * @version 1.0
 */
public class PageObjectFactory {

    private WebDriver driver;
    private HomePageCafe homePageCafe;
    private SignInPageCafe signInPageCafe;

    public PageObjectFactory(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Gets instance of HompageCafe class and if not present then creates one
     * @return
     */
    public HomePageCafe getHomePageCafe() {
        return (homePageCafe == null) ? homePageCafe = new HomePageCafe(driver) : homePageCafe;
    }

    /**
     * Gets instance of HompageCafe class and if not present then creates one
     * @return
     */
    public SignInPageCafe getSignInPageCafe() {
        return (signInPageCafe == null) ? signInPageCafe = new SignInPageCafe(driver) : signInPageCafe;
    }



}
