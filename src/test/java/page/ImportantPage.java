package page;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;

public class ImportantPage extends GeneralPage{

    private boolean present = false;

    private boolean deleted = false;

    private final By deleteMessageLocator = By.xpath(Property.get("delete.message.important.locator"));

    private final By notificationLocator = By.className("bAq");

    public ImportantPage(WebDriver driver) {
        super(driver);
    }

    /**
     * The reason of implemented try/catch block is that javascript loaded the element one more time after referring,
     * so selector is pointed to a nonexistent object even if it was right there in the UI.
     *
     * @param count number of messages
     * @return self
     */
    public ImportantPage selectMessages(int count) {
        for (int el = 1; el <= count; el++) {
            By elementLocator = By.xpath(Property.get("select.option") + "tr[" + el + "]/td[2]");
            waitForElement(elementLocator);
            try {
                driver.findElement(elementLocator).click();
            }catch (StaleElementReferenceException ex){
                driver.findElement(elementLocator).click();
            }
        }
        present = true;
        return this;
    }

    public ImportantPage submitDelete(int count) {
        waitForElement(deleteMessageLocator);
        driver.findElement(deleteMessageLocator).click();

        waitForElement(notificationLocator);
        deleted = driver.getPageSource().contains(count + " ланцюжки повідомлень переміщено в кошик.");

        return this;
    }

     public boolean isPresent() {
        return this.present;
    }

    public boolean isDeleted() {
        return this.deleted;
    }
}
