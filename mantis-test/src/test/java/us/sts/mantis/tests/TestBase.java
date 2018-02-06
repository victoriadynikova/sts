package us.sts.mantis.tests;

import org.openqa.selenium.remote.BrowserType;
import org.testng.SkipException;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import us.sts.mantis.appmanager.ApplicationManager;

import javax.xml.rpc.ServiceException;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.rmi.RemoteException;


public class TestBase {


    protected static final ApplicationManager app
            = new ApplicationManager(System.getProperty("browser",BrowserType.FIREFOX));

    @BeforeSuite
    public void setUp() throws Exception {
        app.init();
    }

    public boolean isIssueOpen(int issueId) throws RemoteException, ServiceException, MalformedURLException {
        String s = app.soap().getStatus(issueId);
        if (s.equals("closed")){
            return false;
        }else {
            return true;
        }

    }
    public void skipIfNotFixed(int issueId) throws RemoteException, ServiceException, MalformedURLException {
        if (isIssueOpen(issueId)) {
            throw new SkipException("Ignored because of issue " + issueId);
        }
    }

    @AfterSuite (alwaysRun = true)
    public void tearDown() {
        app.stop();
    }

}

