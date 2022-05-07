package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends GeneralPage {

    private final By usernameLocator = By.name("identifier");

    private final By submitNextLocator = By.className("VfPpkd-vQzf8d");

    private final By passwordInputLocator = By.xpath(Property.get("login.password.locator"));

    private final By loginButtonLocator = By.xpath("//*[@id=\"passwordNext\"]/div/button/span");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public LoginPage typeEmail(String email) {
        waitForElement(usernameLocator);
        driver.findElement(usernameLocator).sendKeys(email);

        return this;
    }

    public LoginPage submitNext() {
        waitForElement(submitNextLocator);
        driver.findElement(submitNextLocator).click();

        return this;
    }

    public LoginPage typePassword(String password) {
        waitForElement(passwordInputLocator);
        driver.findElement(passwordInputLocator).sendKeys(password);
        return this;
    }

    public HomePage submitLogin() {
        waitForElement(loginButtonLocator);
        driver.findElement(loginButtonLocator).click();

        return new HomePage(driver);
    }

    public HomePage loginAs(String email, String password) {
        typeEmail(email);
        submitNext();
        typePassword(password);

        return submitLogin();
    }
}
