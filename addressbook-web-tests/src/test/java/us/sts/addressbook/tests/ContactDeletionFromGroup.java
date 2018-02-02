package us.sts.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import us.sts.addressbook.model.ContactData;
import us.sts.addressbook.model.Contacts;
import us.sts.addressbook.model.GroupData;

public class ContactDeletionFromGroup extends TestBase {


    private Contacts contacts;
    private ContactData modifiedContact;

  /*  private void getActualContactList() {
        contacts = app.db().contacts();
        ContactData check = contacts.iterator().next(); //updated modified contact from db
        System.out.println("check = "+check.getId());
        System.out.println("modif = "+ modifiedContact.getId());
        while ((check.getId() != modifiedContact.getId())) {
            contacts.remove(check);
            System.out.println("blblblb");
            check = contacts.iterator().next();
        }
        modifiedContact = check;
    }
    */

    @Test
    private void testContactDeletionFromGroup() {
        contacts = app.db().contacts();
        if (contacts.size() == 0) {
            app.goTo().newContactPage();
            app.contact().creation(new ContactData().withFirstName("Add").withLastName("Bee"));
            contacts = app.db().contacts();
        }
        ContactData modifiedContact = contacts.iterator().next();
        System.out.println("!!!!!!!!!! Modif " +modifiedContact.getId());
        int groupsNumber = modifiedContact.getGroups().size();
        if (modifiedContact.getGroups().size() == 0) {
            ContactAddingToGroupTests.testContactAddintToGroup();
        }

        contacts = app.db().contacts();
        ContactData check = contacts.iterator().next(); //updated modified contact from db
        while ((check.getId() != modifiedContact.getId())) {
            contacts.remove(check);
            check = contacts.iterator().next();
        }
        modifiedContact = check;

        GroupData group = modifiedContact.getGroups().iterator().next();

        app.goTo().homePage();
        app.contact().openGroupList(group);
        app.contact().selectById(modifiedContact);
        app.contact().deleteSelectedContactFromGroup();
        app.goTo().homePage();

        contacts = app.db().contacts();
        check = contacts.iterator().next(); //updated modified contact from db
        while ((check.getId() != modifiedContact.getId())) {
            contacts.remove(check);
            check = contacts.iterator().next();
        }
        modifiedContact = check;


        Assert.assertEquals(modifiedContact.getGroups().size(),groupsNumber-1);


    }
}

