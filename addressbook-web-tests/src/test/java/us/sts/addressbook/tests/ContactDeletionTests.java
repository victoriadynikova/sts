package us.sts.addressbook.tests;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;
import us.sts.addressbook.appmanager.ApplicationManager;

public class ContactDeletionTests extends TestBase {



    @Test
    public void testContactDeletion(){
        app.getNavigationHelper().goToHomePage();
        app.getGroupHelper().selectContact();
        app.getGroupHelper().deleteSelectedContacts();
        app.getWebDriver().switchTo().alert().accept();
        app.getNavigationHelper().goToHomePage();
    }


}
