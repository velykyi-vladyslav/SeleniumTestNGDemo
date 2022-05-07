# Selenium demo application
Example of performing tasks with Selenium on  [gmail account](https://www.gmail.com/).

## Initial steps
- Fill credentials.properties file with your credentials.
- Disable two-factor authentication for your gmail account.
- Run tests from SeleniumExampleTest class.

## Task
- Create a Maven project
- Use TestNG & Selenium to develop tests
- One task = one test
- Use property file (s) for project settings
- Use the PageObject template
- Use xpath according to rules (not dynamically built)
- Use DataProvider
- Generate a report

### SubTask 1
1. Open gmail & login
2. Click “Compose” button
3. Fill “To”, “Subject” & “message” fields
4. Click “send” button
5. Verify that message is in “sent” folder
6. Remove message from the “sent” folder
### SubTask 2
1. Open gmail & login
2. Click “Compose” button
3. Fill “To”, “Subject” & “message” fields
4. Close “new message” window
5. Verify that message is saved as draft
6. Open message from the draft folder & send
### SubTask 3
1. Open gmail & login
2. Mark 3 messages from inbox as important
3. Verify that messages are moved to “important” folder
4. Select those messages using checkboxes
5. Click on delete button
6. Verify that messages are deleted
### SubTask 4
1. Open gmail & login
2. Click on compose button
3. Enter incorrect email in “to” field, fill “subject”/”message” fields & press “send” button
4. Verify that warning message appears
5. Click “OK” & enter correct email address & click send
6. Verify that message is moved to “Sent mail” folder

## Useful links
- [Guide to Selenium with JUnit / TestNG](https://www.baeldung.com/java-selenium-with-junit-and-testng)
- [How to handle dynamic changing ID's In XPath](https://sqa.stackexchange.com/questions/18342/how-to-handle-dynamic-changing-ids-in-xpath);
- [Stale element reference: element is not attached to the page document](https://stackoverflow.com/questions/18225997/stale-element-reference-element-is-not-attached-to-the-page-document).
