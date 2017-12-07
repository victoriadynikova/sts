package us.sts.addressbook;

public class ContactData {
    private final String firstName;
    private final String middleName;
    private final String lastName;
    private final String nickname;
    private final String title;
    private final String company;
    private final String address;
    private final String mobilePhoneNumber;
    private final String email1;

    public ContactData(String firstName, String middleName, String lastName, String nickname, String title, String company, String address, String mobilePhoneNumber, String email1) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.nickname = nickname;
        this.title = title;
        this.company = company;
        this.address = address;
        this.mobilePhoneNumber = mobilePhoneNumber;
        this.email1 = email1;
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
}
