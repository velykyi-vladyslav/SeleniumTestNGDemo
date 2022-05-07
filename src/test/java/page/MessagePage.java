package page;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;

public class MessagePage extends GeneralPage {

    private boolean verified = false;

    private final By receiverLocator = By.name("to");

    private final By subjectLocator = By.name("subjectbox");

    private final By okLocator = By.name("ok");

    private final By messageLocator = By.xpath(Property.get("message.locator"));

    private final By closeMsgLocator = By.xpath(Property.get("close.msg.locator"));

    private final By hiddenMsgLocator = By.xpath(Property.get("hidden.msg.locator"));

    private final By clearReceiverLocator = By.className("vM");

    private final By filledReceiverLocator = By.xpath(Property.get("filled.receiver.locator"));

    public MessagePage(WebDriver driver) {
        super(driver);
    }

    public MessagePage fillReceiver(String receiver) {
        waitForElement(receiverLocator);
        driver.findElement(receiverLocator).sendKeys(receiver);

        return this;
    }

    public MessagePage fillSubject(String subject) {
        waitForElement(subjectLocator);
        driver.findElement(subjectLocator).sendKeys(subject);

        return this;
    }

    public MessagePage fillMessage(String msg) {
        waitForElement(messageLocator);
        driver.findElement(messageLocator).sendKeys(msg);

        return this;
    }

    public HomePage submitSendMessage() {
        waitForElement(sendMsgUnhiddenLocator);
        driver.findElement(sendMsgUnhiddenLocator).click();

        return new HomePage(driver);
    }

    public MessagePage submitSendInvalidMessage() {
        waitForElement(subjectLocator);
        driver.findElement(sendMsgLocator).click();

        return this;
    }

    public HomePage closeMessageWindow() {
        waitForElement(closeMsgLocator);
        driver.findElement(closeMsgLocator).click();

        return new HomePage(driver);
    }

    public HomePage sendMsg(String receiver, String subject, String msg) {
        fillReceiver(receiver);
        fillSubject(subject);
        fillMessage(msg);

        return submitSendMessage();
    }

    public MessagePage submitOkButton() {
        driver.findElement(okLocator).click();

        return this;
    }

    public MessagePage submitUnhiddenMsg() {
        waitForElement(hiddenMsgLocator);
        driver.findElement(hiddenMsgLocator).click();

        return this;
    }

    /**
     * The reason of implemented try/catch block is that javascript loaded the element one more time after referring,
     * so selector is pointed to a nonexistent object even if it was right there in the UI.
     *
     * @return self
     */
    public MessagePage clearReceiver() {
        try {
            waitForElementPresence(filledReceiverLocator);
            driver.findElement(filledReceiverLocator).click();
        }catch (StaleElementReferenceException e) {
            driver.findElement(filledReceiverLocator).click();
        }

        waitForElement(clearReceiverLocator);
        driver.findElement(clearReceiverLocator).click();

        return this;
    }

    public MessagePage verifyErrorMessage() {
        this.verified = driver.getPageSource().contains("Помилка");

        return this;
    }

    public boolean isVerified() {
        return this.verified;
    }
}
