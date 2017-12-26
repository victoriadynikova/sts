package us.sts.addressbook.tests;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import us.sts.addressbook.appmanager.ApplicationManager;
import us.sts.addressbook.model.ContactData;

import java.util.List;

public class ContactDeletionTests extends TestBase {



    @Test
    public void testContactDeletion(){
        app.getNavigationHelper().goToHomePage();
        if (! app.getContactHelper().isThereAContact()){
            app.getNavigationHelper().goToNewContactPage();
            app.getContactHelper().contactCreation(new ContactData("Name", "Middle", "Last", "Nick", "user", "Company", "Moscow Prospect Mira 44 ", "+11111110000", "test@test.com", "test1"));
            app.getNavigationHelper().goToHomePage();
        }
        List<ContactData> before = app.getContactHelper().getContactList();
        app.getContactHelper().selectContact(before.size());
        app.getContactHelper().deleteSelectedContacts();
        app.getWebDriver().switchTo().alert().accept();
        app.getNavigationHelper().goToHomePage();
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(),before.size() - 1);
    }


}
