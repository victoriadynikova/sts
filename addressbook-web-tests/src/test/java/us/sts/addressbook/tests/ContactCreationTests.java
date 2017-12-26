package us.sts.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import us.sts.addressbook.model.ContactData;
import us.sts.addressbook.model.GroupData;

import java.util.List;

public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreation() {
        app.getNavigationHelper().goToHomePage();
        List<ContactData> before = app.getContactHelper().getContactList();
        app.getNavigationHelper().goToNewContactPage();
        app.getContactHelper().contactCreation(new ContactData("Name", "Middle", "Last", "Nick", "user", "Company", "Moscow Prospect Mira 44 ", "+11111110000", "test@test.com", null));
        app.getNavigationHelper().goToHomePage();
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(),before.size() + 1);
    }

}
