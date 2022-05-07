package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class GeneralPage {

    protected final By sendMsgLocator = By.xpath(Property.get("send.msg.button"));

    protected final By sendMsgUnhiddenLocator = By.className("dC");

    protected final WebDriver driver;

    public GeneralPage(WebDriver driver) {
       this.driver = driver;
    }

    protected void waitForElement(By locator) {
        new WebDriverWait(driver, Duration.ofSeconds(6))
                .until(ExpectedConditions.elementToBeClickable(locator));
    }

    protected void waitForElementPresence(By locator) {
        new WebDriverWait(driver, Duration.ofSeconds(6))
                .until(ExpectedConditions.presenceOfElementLocated(locator));
    }
}
