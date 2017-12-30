package us.sts.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import us.sts.addressbook.model.ContactData;
import us.sts.addressbook.model.Contacts;
import us.sts.addressbook.model.Groups;

import java.util.List;

public class ContactHelper extends HelperBase {

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
        type(By.name("mobile"), contactData.getMobilePhoneNumber());
        type(By.name("email"), contactData.getEmail1());

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
        wd.findElement(By.cssSelector("a[href='edit.php?id="+ id + "']")).click();
    }


    public void submitContactModification() {
        click(By.name("update"));
    }



    public void selectById(ContactData contact) {
        wd.findElement(By.cssSelector("input[value='"+ contact.getId() + "']")).click();
    }

    public void delete() {
        click(By.xpath("//div[@id='content']/form[2]/div[2]/input"));
    }

    public boolean isThereAContact() {
        return isElementPresent(By.name("selected[]"));
    }

    public void creation(ContactData contact) {
        fillContactForm(contact, true);
        submitContactCreation();

    }

    public void modify(ContactData selectedContact, ContactData modifiedContact) {
        initContactModificationById(selectedContact.getId());
        fillContactForm(modifiedContact, false);
        submitContactModification();
    }

    public int getContactCount() {
        return wd.findElements(By.name("selected[]")).size();
    }


    public Contacts all() {
        Contacts contacts = new Contacts();
        List<WebElement> elements = wd.findElements(By.name("entry"));
        int index = 2;
        for (WebElement element : elements) {
            int id = Integer.parseInt(element.findElement(By.name("selected[]")).getAttribute("value"));
            String lastName = element.findElement(By.xpath("//div/div[4]/form[2]/table/tbody/tr[" + index + "]/td[2]")).getText();
            String firstName = element.findElement(By.xpath("//div/div[4]/form[2]/table/tbody/tr[" + index + "]/td[3]")).getText();
            ContactData contact = new ContactData().withId(id).withFirstName(firstName).withLastName(lastName);
            contacts.add(contact);
            index++;
        }

        return contacts;
    }
}
