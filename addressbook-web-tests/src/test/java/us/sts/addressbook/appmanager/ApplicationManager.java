package us.sts.addressbook.appmanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;

import java.util.concurrent.TimeUnit;

public class ApplicationManager {

    WebDriver wd;

    private NavigationHelper navigationHelper;
    private GroupHelper groupHelper;
    private SessionHelper sessionHelper;
    private ContactHelper contactHelper;
    private String browser;

    public ApplicationManager(String browser) {
        this.browser = browser;
    }

    public void init() {

        if(browser.equals(BrowserType.FIREFOX)){
            wd = new FirefoxDriver(new FirefoxOptions().setLegacy(true));

        }else if(browser.equals(BrowserType.CHROME)) {
            wd = new ChromeDriver();

        }else if(browser.equals(BrowserType.SAFARI)) {
            wd = new SafariDriver(new SafariOptions().setUseTechnologyPreview(true));

        } else if(browser.equals(BrowserType.HTMLUNIT)) {
            wd = new HtmlUnitDriver();
        }

        wd.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        wd.get("http://localhost/addressbook/index.php");
        groupHelper = new GroupHelper(wd);
        navigationHelper = new NavigationHelper(wd);
        sessionHelper = new SessionHelper(wd);
        contactHelper = new ContactHelper(wd);
        sessionHelper.login("admin", "secret");
    }

    public void stop() {
        wd.quit();
    }

    public GroupHelper group() {
        return groupHelper;
    }

    public NavigationHelper goTo() {
        return navigationHelper;
    }

    public ContactHelper contact() {
        return contactHelper;
    }

    public WebDriver getWebDriver() {
        return wd;
    }
}
