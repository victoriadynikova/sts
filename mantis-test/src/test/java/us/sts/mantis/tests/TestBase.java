package us.sts.mantis.tests;

import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import us.sts.mantis.appmanager.ApplicationManager;


public class TestBase {


    protected static final ApplicationManager app
            = new ApplicationManager(System.getProperty("browser",BrowserType.FIREFOX));

    @BeforeSuite
    public void setUp() throws Exception {
        app.init();
    }

    @AfterSuite (alwaysRun = true)
    public void tearDown() {
        app.stop();
    }

}

