package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends GeneralPage {

    private final By submitWriteMsgLocator = By.xpath(Property.get("submit.write.msg.locator"));

    private final By submitSentLocator = By.xpath(Property.get("submit.sent.locator"));

    private final By submitDraftLocator = By.xpath(Property.get("submit.draft.locator"));

    private final By submitImportantLocator = By.xpath(Property.get("submit.important.locator"));

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public MessagePage submitWriteMsg() {
        waitForElement(submitWriteMsgLocator);
        driver.findElement(submitWriteMsgLocator).click();

        return new MessagePage(driver);
    }

    public SentPage submitSentMessages() {
        waitForElement(submitSentLocator);
        driver.findElement(submitSentLocator).click();

        return new SentPage(driver);
    }

    public DraftPage submitDraftMessages() {
        waitForElement(submitDraftLocator);
        driver.findElement(submitDraftLocator).click();

        return new DraftPage(driver);
    }

    public HomePage markAsImportant(int count) {
        for (int el = 1; el <= count; el++) {
            By elementLocator = By.xpath(Property.get("important.option") + "tr[" + el + "]/td[3]");

            waitForElement(elementLocator);
            driver.findElement(elementLocator).click();
        }
        return this;
    }

    public ImportantPage submitImportantMessages() {
        waitForElement(submitImportantLocator);
        driver.findElement(submitImportantLocator).click();

        return new ImportantPage(driver);
    }
}
