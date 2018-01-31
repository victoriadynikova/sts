package us.sts.addressbook.model;

import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import org.hibernate.annotations.Type;
import org.omg.CORBA.CODESET_INCOMPATIBLE;

import javax.persistence.*;
import java.io.File;
import java.util.Objects;


@Entity
@Table (name = "addressbook")
public class ContactData {


    @XStreamOmitField
    @Id
    @Column (name = "id")
    private int id = Integer.MAX_VALUE;

    @Expose
    @Column (name = "firstname")
    private String firstName;

    @Expose
    @Column (name = "middlename")
    private String middleName;

    @Expose
    @Column (name = "lastname")
    private String lastName;

    @Expose
    @Column (name = "nickname")
    private String nickname;

    @Expose
    @Column (name = "title")
    private String title;

    @Expose
    @Column (name = "company")
    private String company;

    @Expose
    @Column (name = "address")
    @Type(type = "text")
    private String address;

    @Expose
    @Column (name = "mobile")
    @Type(type = "text")
    private String mobilePhone;

    @Expose
    @Column (name = "home")
    @Type(type = "text")
    private String homePhone;

    @Expose
    @Column (name = "work")
    @Type(type = "text")
    private String workPhone;

    @Expose
    @Column (name = "email")
    @Type(type = "text")
    private String email1;

    @Expose
    @Column (name = "email2")
    @Type(type = "text")
    private String email2;

    @Expose
    @Column (name = "email3")
    @Type(type = "text")
    private String email3;

    @Expose
    @Transient
    private String group;

    @Expose
    @Transient
    private String allPhones;

    @Expose
    @Transient
    private String allEmails;

    @Expose
    @Column (name = "photo")
    @Type(type = "text")
    private String photo;


    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getNickname() {
        return nickname;
    }

    public String getTitle() {
        return title;
    }

    public String getCompany() {
        return company;
    }

    public String getAddress() {
        return address;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public String getWorkPhone() {
        return workPhone;
    }

    public String getHomePhone() {
        return homePhone;
    }

    public String getEmail1() {
        return email1;
    }

    public String getEmail2() {
        return email2;
    }

    public String getEmail3() {
        return email3;
    }

    public String getAllEmails() {
        return allEmails;
    }


    public String getGroup() {
        return group;
    }

    public String getAllPhones() {
        return allPhones;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactData that = (ContactData) o;
        return id == that.id &&
                Objects.equals(firstName, that.firstName) &&
                Objects.equals(middleName, that.middleName) &&
                Objects.equals(lastName, that.lastName) &&
                Objects.equals(nickname, that.nickname) &&
                Objects.equals(title, that.title) &&
                Objects.equals(company, that.company) &&
                Objects.equals(address, that.address) &&
                Objects.equals(mobilePhone, that.mobilePhone) &&
                Objects.equals(homePhone, that.homePhone) &&
                Objects.equals(workPhone, that.workPhone) &&
                Objects.equals(group, that.group);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, firstName, middleName, lastName, nickname, title, company, address, mobilePhone, homePhone, workPhone, group);
    }

    public File getPhoto() {
        return new File(photo);
    }

    public ContactData withId(int id) {
        this.id = id;
        return this;
    }

    public ContactData withFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public ContactData withMiddleName(String middleName) {
        this.middleName = middleName;
        return this;
    }

    public ContactData withLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public ContactData withNickname(String nickname) {
        this.nickname = nickname;
        return this;
    }

    public ContactData withTitle(String title) {
        this.title = title;
        return this;
    }

    public ContactData withCompany(String company) {
        this.company = company;
        return this;
    }

    public ContactData withAddress(String address) {
        this.address = address;
        return this;
    }

    public ContactData withMobilePhone(String mobilePhoneNumber) {
        this.mobilePhone = mobilePhoneNumber;
        return this;
    }

    public ContactData withHomePhone(String homeNumber) {
        this.homePhone = homeNumber;
        return this;
    }


    public ContactData withWorkPhone(String workNumber) {
        this.workPhone = workNumber;
        return this;
    }

    public ContactData withEmail1(String email1) {
        this.email1 = email1;
        return this;
    }

    public ContactData withEmail2(String email2) {
        this.email2 = email2;
        return this;
    }

    public ContactData withEmail3(String email3) {
        this.email3 = email3;
        return this;
    }

    public ContactData withAllEmails(String allEmails) {
        this.allEmails = allEmails;
        return this;
    }

    public ContactData withGroup(String group) {
        this.group = group;
        return this;
    }

    public ContactData withAllPhones(String allPhones) {
        this.allPhones = allPhones;
        return this;
    }

    public ContactData withPhoto(File photo) {
        this.photo = photo.getPath();
        return this;
    }

    @Override
    public String toString() {
        return "ContactData{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", group='" + group + '\'' +
                '}';
    }

}
