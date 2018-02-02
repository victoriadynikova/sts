package us.sts.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import us.sts.addressbook.model.ContactData;
import us.sts.addressbook.model.Contacts;
import us.sts.addressbook.model.GroupData;

import java.util.List;

public class ContactHelper extends HelperBase {

    private Contacts contactsCache = null;

    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void submitContactCreation() {
        click(By.xpath("//div[@id='content']/form/input[21]"));
    }

    public void fillContactForm(ContactData contactData, boolean creation) {
        type(By.name("firstname"), contactData.getFirstName());
        type(By.name("middlename"), contactData.getMiddleName());
        type(By.name("lastname"), contactData.getLastName());
        type(By.name("nickname"), contactData.getNickname());
        type(By.name("title"), contactData.getTitle());
        type(By.name("company"), contactData.getCompany());
        type(By.name("address"), contactData.getAddress());
        type(By.name("home"), contactData.getHomePhone());
        type(By.name("mobile"), contactData.getMobilePhone());
        type(By.name("work"), contactData.getWorkPhone());
        type(By.name("email"), contactData.getEmail1());
//        attach(By.name("photo"),contactData.getPhoto());

        if (creation) {
            if (wd.findElement(By.name("new_group")).findElements(By.tagName("option")).size() == 1) {
                new Select(wd.findElement(By.name("new_group"))).selectByValue("[none]");
            } else {
                String value = wd.findElement(By.name("new_group")).findElements(By.tagName("option")).get(1).getAttribute("value");
                new Select(wd.findElement(By.name("new_group"))).selectByValue(value);
            }
        } else {
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }
    }

    public void initContactModificationById(int id) {
        wd.findElement(By.cssSelector(String.format("a[href='edit.php?id=%d']", id))).click();
    }

    public void initGroupAddingByName(GroupData group){
        String value = String.valueOf(group.getId());
        new Select(wd.findElement(By.name("to_group"))).selectByValue(value);
        click(By.name("add"));
    }

    public void openGroupList(GroupData group){
        String value = String.valueOf(group.getId());
        new Select(wd.findElement(By.name("group"))).selectByValue(value);
    }

    public void deleteSelectedContactFromGroup(){
        click(By.name("remove"));
    }




    public void submitContactModification() {
        click(By.name("update"));
    }


    public void selectById(ContactData contact) {
        wd.findElement(By.cssSelector("input[value='" + contact.getId() + "']")).click();
    }

    public void delete() {
        click(By.xpath("//div[@id='content']/form[2]/div[2]/input"));
        cleanContactCache();
    }

    public boolean isThereAContact() {
        return isElementPresent(By.name("selected[]"));
    }

    public void creation(ContactData contact) {
        fillContactForm(contact, true);
        submitContactCreation();
        cleanContactCache();

    }

    public void modify(ContactData selectedContact, ContactData modifiedContact) {
        initContactModificationById(selectedContact.getId());
        fillContactForm(modifiedContact, false);
        cleanContactCache();
        submitContactModification();
    }

    public void cleanContactCache() {
        contactsCache = null;
    }

    public int count() {
        return wd.findElements(By.name("selected[]")).size();
    }


    public Contacts all() {
        if (contactsCache != null) {
            return new Contacts(contactsCache);
        }
        contactsCache = new Contacts();
        List<WebElement> elements = wd.findElements(By.name("entry"));
        int index = 2;
        for (WebElement element : elements) {
            int id = Integer.parseInt(element.findElement(By.name("selected[]")).getAttribute("value"));
            String lastName = element.findElement(By.xpath("//div/div[4]/form[2]/table/tbody/tr[" + index + "]/td[2]")).getText();
            String firstName = element.findElement(By.xpath("//div/div[4]/form[2]/table/tbody/tr[" + index + "]/td[3]")).getText();
            String address = element.findElement(By.xpath("//div/div[4]/form[2]/table/tbody/tr[" + index + "]/td[4]")).getText();
            String allEmails = element.findElement(By.xpath("//div/div[4]/form[2]/table/tbody/tr[" + index + "]/td[5]")).getText();
            String allPhones = element.findElement(By.xpath("//div/div[4]/form[2]/table/tbody/tr[" + index + "]/td[6]")).getText();
            contactsCache.add(new ContactData().withId(id).withFirstName(firstName).withLastName(lastName)
               .withAddress(address).withAllEmails(allEmails).withAllPhones(allPhones));
            index++;
        }

        return new Contacts(contactsCache);
    }

    public ContactData infoFromEditForm(ContactData contact) {
        initContactModificationById(contact.getId());
        String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
        String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
        String address = wd.findElement(By.name("address")).getAttribute("value");
        String email1 = wd.findElement(By.name("email")).getAttribute("value");
        String email2 = wd.findElement(By.name("email2")).getAttribute("value");
        String email3 = wd.findElement(By.name("email3")).getAttribute("value");
        String home = wd.findElement(By.name("home")).getAttribute("value");
        String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
        String work = wd.findElement(By.name("work")).getAttribute("value");
        wd.navigate().back();
        return new ContactData().withId(contact.getId()).withFirstName(firstname).withLastName(lastname)
                .withHomePhone(home).withMobilePhone(mobile).withWorkPhone(work).withAddress(address).
                        withEmail1(email1).withEmail2(email2).withEmail3(email3);

    }

}
