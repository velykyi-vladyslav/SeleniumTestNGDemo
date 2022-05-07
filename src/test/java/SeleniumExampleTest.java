
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;
import page.*;

import static org.testng.AssertJUnit.*;

public class SeleniumExampleTest {

    private LoginPage loginPage;

    private static final String EMAIL_LOGIN = Property.getCredentials("login.email");

    private static final String PASSWORD = Property.getCredentials("login.password");

    private static final String MESSAGE = "Test message";

    private static final String SUBJECT = "Test subject";

    private WebDriver driver;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.get("https://www.gmail.com/");

        loginPage = new LoginPage(driver);
    }

    /**
     * <p>1. Open gmail & login.
     * <p>2. Click “Compose” button.
     * <p>3. Fill “To”, “Subject” & “message” fields.
     * <p>4. Click “send” button.
     * <p>5. Verify that message is in “sent” folder.
     * <p>6. Remove message from the “sent” folder.
     */
    @Test
    public void shouldSendAndVerifyMsg() {
        assertTrue(loginPage.loginAs(EMAIL_LOGIN, PASSWORD)
                            .submitWriteMsg()
                            .sendMsg(EMAIL_LOGIN, SUBJECT, MESSAGE)
                            .submitSentMessages()
                            .submitLatestMsg()
                            .verifySentMessage(MESSAGE)
                            .deleteCurrentMsg()
                            .isVerified());
    }

    /**
     * <p>1. Open gmail & login.
     * <p>2. Click “Compose” button.
     * <p>3. Fill “To”, “Subject” & “message” fields.
     * <p>4. Close “new message” window.
     * <p>5. Verify that message is saved as draft.
     * <p>6. Open message from the draft folder & send.
     */
    @Test
    public void shouldSaveMessageAsDraft() {
        assertTrue(loginPage.loginAs(EMAIL_LOGIN, PASSWORD)
                            .submitWriteMsg()
                            .fillReceiver(EMAIL_LOGIN)
                            .fillSubject(SUBJECT)
                            .fillMessage(MESSAGE)
                            .closeMessageWindow()
                            .submitDraftMessages()
                            .submitLatestMsg()
                            .verifyMessage(MESSAGE)
                            .submitSendMessage()
                            .isVerified());
    }

    /**
     * <p>1. Open gmail & login
     * <p>2. Mark 3 messages from inbox as important
     * <p>3. Verify that messages are moved to “important” folder
     * <p>4. Select those messages using checkboxes
     * <p>5. Click on delete button
     * <p>6. Verify that messages are deleted
     */
    @Test(dataProvider = "message_count")
    public void shouldVerifyCheckboxesProcessing(int count) {
        HomePage homePage = loginPage.loginAs(EMAIL_LOGIN, PASSWORD);
        sendMessages(homePage, count);
        ImportantPage importantPage = homePage.markAsImportant(count)
                                              .submitImportantMessages()
                                              .selectMessages(count);

        assertTrue(importantPage.isPresent());
        assertTrue(importantPage.submitDelete(count).isDeleted());
    }

    /**
     * <p>1. Open gmail & login
     * <p>2. Click on compose button
     * <p>3. Enter incorrect email in “to” field, fill “subject”/”message” fields & press “send” button
     * <p>4. Verify that warning message appears
     * <p>5. Click “OK” & enter correct email address & click send
     * <p>6. Verify that message is moved to “Sent mail” folder
     */
    @Test
    public void shouldVerifyWarningMsgAndThenSendTheMsg() {
        MessagePage messagePage = loginPage.loginAs(EMAIL_LOGIN, PASSWORD)
                                           .submitWriteMsg()
                                           .fillReceiver("@gmail.com")
                                           .fillSubject(SUBJECT)
                                           .fillMessage(MESSAGE)
                                           .submitSendInvalidMessage()
                                           .verifyErrorMessage()
                                           .submitOkButton()
                                           .submitUnhiddenMsg()
                                           .clearReceiver()
                                           .fillReceiver(EMAIL_LOGIN);

        assertTrue(messagePage.isVerified());
        assertTrue(messagePage.submitSendMessage()
                              .submitSentMessages()
                              .verifySentMessage(MESSAGE)
                              .isVerified());
    }

    private void sendMessages(HomePage homePage, int count) {
        for (int i = 0; i < count; i++) {
            homePage.submitWriteMsg().sendMsg(EMAIL_LOGIN, SUBJECT, MESSAGE);
        }
    }

    @DataProvider(name = "message_count")
    Object[][] getMessageCount() {
        return new Object[][] {{3},{2}};
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
