package us.sts.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import us.sts.addressbook.model.ContactData;

import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactDeletionTests extends TestBase {

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
    public void testContactDeletion() {

        Set<ContactData> before = app.contact().all();
        ContactData deletedContact = before.iterator().next();
        app.contact().selectById(deletedContact);
        app.contact().delete();
        app.getWebDriver().switchTo().alert().accept();
        app.goTo().homePage();
        Set<ContactData> after = app.contact().all();
        assertThat(after.size(), equalTo(before.size() - 1));

        before.remove(deletedContact);
        assertThat(after, equalTo(before));
    }


}
