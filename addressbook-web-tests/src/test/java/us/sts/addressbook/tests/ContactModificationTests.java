package us.sts.addressbook.tests;

import org.testng.annotations.Test;
import us.sts.addressbook.model.ContactData;
import us.sts.addressbook.model.GroupData;

public class ContactModificationTests extends TestBase {

    @Test
    public void testContactModification(){

        app.getNavigationHelper().goToHomePage();
        if (! app.getContactHelper().isThereAContact()){
            app.getNavigationHelper().goToNewContactPage();
            app.getContactHelper().contactCreation(new ContactData("Name", "Middle", "Last", "Nick", "user", "Company", "Moscow Prospect Mira 44 ", "+11111110000", "test@test.com", "test1"));
            app.getNavigationHelper().goToHomePage();
        }
        app.getContactHelper().initContactModification();
        app.getContactHelper().fillContactForm(new ContactData("Q", "z", "z", "z", "z","j","q", "123", "a@n.ru",null), false);
        app.getContactHelper().submitContactModification();
        app.getNavigationHelper().goToHomePage();

    }
}
