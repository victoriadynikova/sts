package us.sts.addressbook.tests;

import org.testng.annotations.Test;
import us.sts.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreation() {

        app.getNavigationHelper().goToNewContactPage();
        app.getContactHelper().fillContactForm(new ContactData("Name", "Middle", "Last", "Nick", "user", "Company", "Moscow Prospect Mira 44 ", "+11111110000", "test@test.com"));
        app.getContactHelper().submitContactCreation();
    }
}