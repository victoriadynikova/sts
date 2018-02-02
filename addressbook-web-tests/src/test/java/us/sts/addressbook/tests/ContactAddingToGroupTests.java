package us.sts.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import us.sts.addressbook.model.ContactData;
import us.sts.addressbook.model.Contacts;
import us.sts.addressbook.model.GroupData;
import us.sts.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactAddingToGroupTests extends TestBase {

    @Test
    public static void testContactAddintToGroup(){
        Contacts contacts= app.db().contacts();
        if (contacts.size() == 0){
            app.goTo().newContactPage();
            app.contact().creation(new ContactData().withFirstName("Add").withLastName("Bee"));
            contacts = app.db().contacts();
        }
        ContactData modifiedContact = contacts.iterator().next();

        Groups groups = app.db().groups();
        int groupsNumber = modifiedContact.getGroups().size();

        if (groups.size() == 0 || (groups.size() == groupsNumber)){
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("additional").withHeader("1").withFooter("2"));
            groups = app.db().groups();
        }

        GroupData additionalGroup = groups.iterator().next();

        while (modifiedContact.getGroups().contains(additionalGroup)){
            groups.remove(additionalGroup);
            additionalGroup = groups.iterator().next();
        }

        app.goTo().homePage();
        app.contact().selectById(modifiedContact);
        app.contact().initGroupAddingByName(additionalGroup);
        app.goTo().homePage();

        contacts = app.db().contacts();
        ContactData check = new ContactData(); //updated modified contact from db
        while (!(check.equals(modifiedContact))){
            contacts.remove(check);
            check = contacts.iterator().next();
        }

        Assert.assertEquals(check.getGroups().size(),groupsNumber+1);


    }
}
