package us.sts.mantis.tests;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import us.sts.mantis.appmanager.DbHelper;
import us.sts.mantis.appmanager.HttpSession;
import us.sts.mantis.model.MailMessage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class PasswordChangingTests extends TestBase {

    private String username;
    private String email;
    private String newPassword = "newpassword";

    @BeforeMethod
    public void startMailServer(){
        app.mail().start();
    }

    @Test
    public void testPasswordChanging() throws IOException, InterruptedException {
        HttpSession session = new HttpSession(app);
        chooseUserForChanging();
        session.easyLogin("administrator","root");
        app.goTo().manage().users();
        app.goTo().chooseUser(username).resetPassword();
        String confirmationLink = receiveConfirmationLink();
        app.goTo().confirmation(confirmationLink,username,newPassword);
        assertTrue(session.login(username, newPassword));
    }

    public String receiveConfirmationLink() {
        List<MailMessage> mailMessages = app.mail().waitForMail(1, 10000);
        return findConfirmationLink(mailMessages, email);
    }

    public void chooseUserForChanging() {
        List<String> users = DbConnectionTests.testDbConnection();
        users.remove("administrator");
        username = users.iterator().next();
        email = String.format("%s@localhost",username);
        System.out.println("user = "+username);
        System.out.println("email = "+email);
    }

    private String findConfirmationLink(List<MailMessage> mailMessages, String email) {
        MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
        VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
        return regex.getText(mailMessage.text);
    }

    @AfterMethod(alwaysRun = true)
    public void stopMailServer(){
        app.mail().stop();
    }
}
