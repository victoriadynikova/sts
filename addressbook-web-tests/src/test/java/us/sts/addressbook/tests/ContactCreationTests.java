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
                .withHomePhone("1-1").withMobilePhone("(11)111110000").withWorkPhone("33 33")
                .withEmail1("test@test.com").withEmail2("a_jkfdg@gmail.com").withEmail3("q.q.d@mnf.ru");
        app.contact().creation(contact);
        app.goTo().homePage();
        assertThat(app.contact().count(), equalTo(before.size() + 1));

        Contacts after = app.contact().all();
        assertThat(after, equalTo(before.
                withAdded(contact.withId(after.stream().mapToInt(c -> c.getId()).max().getAsInt()))));
    }

}
