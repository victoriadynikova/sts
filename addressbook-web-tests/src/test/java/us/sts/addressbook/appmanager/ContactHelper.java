package us.sts.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import us.sts.addressbook.model.ContactData;

import java.util.ArrayList;
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

    public void initContactModification(int index) {
        //Index has to be incremented in order to include the first row without the checkbox
        index++;
        click(By.xpath("//table[@id='maintable']/tbody/tr[" + index + "]/td[8]/a/img"));
    }

    public void submitContactModification() {
        click(By.name("update"));
    }

    public void selectContact(int index) {
        index++;
        click(By.xpath("//div/div[4]/form[2]/table/tbody/tr[" + index + "]/td[1]/input"));
    }

    public void deleteSelectedContacts() {
        click(By.xpath("//div[@id='content']/form[2]/div[2]/input"));
    }

    public boolean isThereAContact() {
        return isElementPresent(By.name("selected[]"));
    }

    public void contactCreation(ContactData contact) {
        fillContactForm(contact, true);
        submitContactCreation();

    }

    public int getContactCount() {
        return wd.findElements(By.name("selected[]")).size();
    }

    public List<ContactData> getContactList() {
        List<ContactData> contacts = new ArrayList<>();
        List<WebElement> elements = wd.findElements(By.name("entry"));
        int index = 2;
        for (WebElement element : elements) {
            int id = Integer.parseInt(element.findElement(By.name("selected[]")).getAttribute("value"));
            String lastName = element.findElement(By.xpath("//div/div[4]/form[2]/table/tbody/tr[" + index + "]/td[2]")).getText();
            String firstName = element.findElement(By.xpath("//div/div[4]/form[2]/table/tbody/tr[" + index + "]/td[3]")).getText();
            ContactData contact = new ContactData(id, firstName, null, lastName, null, null, null, null, null, null, null);
            contacts.add(contact);
            index++;
        }

        return contacts;
    }
}
