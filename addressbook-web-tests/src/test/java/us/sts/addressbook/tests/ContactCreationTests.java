package us.sts.addressbook.tests;

import org.testng.annotations.Test;
import us.sts.addressbook.model.ContactData;
import us.sts.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreation() {
        app.goTo().homePage();
        Contacts before = app.contact().all();
        app.goTo().newContactPage();
        ContactData contact = new ContactData().withFirstName("Name").withMiddleName("Middle").withLastName("Last")
                .withNickname("Nick").withTitle("user").withCompany("Company").withAddress("Moscow Prospect Mira 44 ")
                .withMobilePhoneNumber("+11111110000").withEmail1("test@test.com");
        app.contact().creation(contact);
        app.goTo().homePage();
        Contacts after = app.contact().all();
        assertThat(after.size(), equalTo(before.size() + 1));


        assertThat(after, equalTo(before.
                withAdded(contact.withId(after.stream().mapToInt(c -> c.getId()).max().getAsInt()))));
    }

}
