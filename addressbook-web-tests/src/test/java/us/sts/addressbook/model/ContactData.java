package us.sts.addressbook.model;

import java.util.Objects;

public class ContactData {
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactData that = (ContactData) o;
        return id == that.id &&
                Objects.equals(firstName, that.firstName) &&
                Objects.equals(lastName, that.lastName) &&
                Objects.equals(group, that.group);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, firstName, lastName, group);
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

    public void setId(int id) {
        this.id = id;
    }

    private int id;
    private final String firstName;
    private final String middleName;
    private final String lastName;
    private final String nickname;
    private final String title;
    private final String company;
    private final String address;
    private final String mobilePhoneNumber;
    private final String email1;
    private final String group;


    public ContactData(int id, String firstName, String middleName, String lastName, String nickname, String title, String company, String address, String mobilePhoneNumber, String email1, String group) {
        this.id = id;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.nickname = nickname;
        this.title = title;
        this.company = company;
        this.address = address;
        this.mobilePhoneNumber = mobilePhoneNumber;
        this.email1 = email1;
        this.group = group;
    }

    public ContactData(String firstName, String middleName, String lastName, String nickname, String title, String company, String address, String mobilePhoneNumber, String email1, String group) {
        this.id = 0;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.nickname = nickname;
        this.title = title;
        this.company = company;
        this.address = address;
        this.mobilePhoneNumber = mobilePhoneNumber;
        this.email1 = email1;
        this.group = group;
    }

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

    public String getMobilePhoneNumber() {
        return mobilePhoneNumber;
    }

    public String getEmail1() {
        return email1;
    }

    public String getGroup() {
        return group;
    }
}
