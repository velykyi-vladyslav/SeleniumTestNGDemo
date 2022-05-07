package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SentPage extends GeneralPage {

    private boolean verified = false;

    private final By latestMessageLocator = By.xpath(Property.get("latest.message.locator.sent"));

    private final By deleteMessageLocator = By.xpath(Property.get("delete.message.locator"));

    public SentPage(WebDriver driver) {
        super(driver);
    }

    public SentPage submitLatestMsg() {
        waitForElement(latestMessageLocator);
        driver.findElement(latestMessageLocator).click();

        return this;
    }

    public SentPage verifySentMessage(String msg) {
        this.verified = driver.getPageSource().contains(msg);

        return this;
    }

    public SentPage deleteCurrentMsg() {
        driver.findElement(deleteMessageLocator).click();

        return this;
    }

    public boolean isVerified() {
        return this.verified;
    }
}
