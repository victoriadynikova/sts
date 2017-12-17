package us.sts.addressbook.tests;

import org.testng.annotations.Test;
import us.sts.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase {

    @Test
    public void testContactModification(){

        app.getNavigationHelper().goToHomePage();
        app.getContactHelper().initContactModification();
        app.getContactHelper().fillContactForm(new ContactData("Q", "z", "z", "z", "z","j","q", "123", "a@n.ru"));
        app.getContactHelper().submitContactModification();
        app.getNavigationHelper().goToHomePage();

    }
}
