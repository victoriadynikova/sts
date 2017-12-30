package us.sts.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import us.sts.addressbook.model.ContactData;

import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().homePage();
        if (app.contact().all().size() == 0) {
            app.goTo().newContactPage();
            app.contact().creation(new ContactData().withFirstName("Name").withMiddleName("Middle").withLastName("Last")
                    .withNickname("Nick").withTitle("user").withCompany("Company").withAddress("Moscow Prospect Mira 44 ")
                    .withMobilePhoneNumber("+11111110000").withEmail1("test@test.com"));
            app.goTo().homePage();
        }
    }

    @Test
    public void testContactModification() {

        Set<ContactData> before = app.contact().all();
        ContactData modifiedContact = before.iterator().next();
        ContactData contact = new ContactData()
                .withId(modifiedContact.getId()).withFirstName("AA").withMiddleName("z").withLastName("A")
                .withNickname("z").withTitle("z").withCompany("z").withAddress("j").withMobilePhoneNumber("123").withEmail1("a@n.ru");
        app.contact().modify(modifiedContact, contact);
        app.goTo().homePage();
        Set<ContactData> after = app.contact().all();
        assertThat(after.size(), equalTo(before.size()));

        before.remove(modifiedContact);
        before.add(contact);
        assertThat(after, equalTo(before));
    }


}
