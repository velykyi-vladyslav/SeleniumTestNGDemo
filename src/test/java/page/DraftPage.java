package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DraftPage extends GeneralPage {

    private boolean verified = false;

    private final By latestMessageLocator = By.xpath(Property.get("latest.message.locator"));

    public DraftPage(WebDriver driver) {
        super(driver);
    }

    public DraftPage submitLatestMsg() {
        waitForElement(latestMessageLocator);
        driver.findElement(latestMessageLocator).click();

        return this;
    }

    public DraftPage submitSendMessage() {
        waitForElement(sendMsgLocator);
        driver.findElement(sendMsgLocator).click();

        return this;
    }

    public DraftPage verifyMessage(String msg) {
        this.verified = driver.getPageSource().contains(msg);

        return this;
    }

    public boolean isVerified() {
        return verified;
    }
}
