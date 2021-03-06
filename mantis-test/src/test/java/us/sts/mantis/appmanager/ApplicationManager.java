package us.sts.mantis.appmanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class ApplicationManager {

    private WebDriver wd;
    private final Properties properties;

    private String browser;
    private RegistrationHelper registrationHelper;
    private FtpHelper ftp;
    private MailHelper mailHelper;
    private NavigationHelper navigationHelper;
    private SoapHelper soapHelper;


    public ApplicationManager(String browser)  {

        this.browser = browser;
        properties = new Properties();
    }

    public void init() throws IOException {
        String target = System.getProperty("target", "local");
        properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties",target))));

    }

    public void stop() {
        if (wd != null){
            wd.quit();
        }
    }

    public HttpSession newSession(){
        return new HttpSession(this);
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }

    public RegistrationHelper registration() {
        if(registrationHelper == null){
            registrationHelper = new RegistrationHelper(this);
        }
        return registrationHelper;
    }

    public NavigationHelper goTo() {
        if(navigationHelper == null){
            navigationHelper = new NavigationHelper(this);
        }
        return navigationHelper;
    }



    public FtpHelper ftp(){
        if(ftp == null){
            ftp = new FtpHelper(this);
        }
        return ftp;

    }

    public MailHelper mail(){
        if(mailHelper == null){
            mailHelper = new MailHelper(this);
        }
        return mailHelper;
    }

    public SoapHelper soap(){
        if (soapHelper == null){
            soapHelper = new SoapHelper(this);
        }
        return soapHelper;
    }

    public WebDriver getDriver() {
        if (wd == null){
            if (browser.equals(BrowserType.FIREFOX)) {
                wd = new FirefoxDriver(new FirefoxOptions().setLegacy(true));

            } else if (browser.equals(BrowserType.CHROME)) {
                wd = new ChromeDriver();

            } else if (browser.equals(BrowserType.SAFARI)) {
                wd = new SafariDriver(new SafariOptions().setUseTechnologyPreview(true));

            } else if (browser.equals(BrowserType.HTMLUNIT)) {
                wd = new HtmlUnitDriver();
            }

            wd.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
            wd.get(properties.getProperty("web.baseUrl"));
        }
        return wd;
    }
}
