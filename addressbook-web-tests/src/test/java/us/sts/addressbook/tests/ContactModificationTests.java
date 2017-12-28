package us.sts.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import us.sts.addressbook.model.ContactData;
import us.sts.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class ContactModificationTests extends TestBase {

    @Test
    public void testContactModification() {

        app.getNavigationHelper().goToHomePage();
        if (!app.getContactHelper().isThereAContact()) {
            app.getNavigationHelper().goToNewContactPage();
            app.getContactHelper().contactCreation(new ContactData("Name", "Middle", "Last", "Nick", "user", "Company", "Moscow Prospect Mira 44 ", "+11111110000", "test@test.com", null));
            app.getNavigationHelper().goToHomePage();
        }
        List<ContactData> before = app.getContactHelper().getContactList();
        app.getContactHelper().initContactModification(before.size());
        ContactData contact = new ContactData(before.get(before.size() - 1).getId(), "AA", "z", "A", "z", "z", "j", "q", "123", "a@n.ru", null);
        app.getContactHelper().fillContactForm(contact, false);
        app.getContactHelper().submitContactModification();
        app.getNavigationHelper().goToHomePage();
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size());

        before.remove(before.size() - 1);
        before.add(contact);

        Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
        before.sort(byId);
        after.sort(byId);

        Assert.assertEquals(after, before);
    }
}
