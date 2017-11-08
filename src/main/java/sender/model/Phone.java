package sender.model;

import java.util.Objects;

/**
 * The phone is an object consisting of:
 * [id]=> The ID of the phone
 * [addressbook]=> The address book ID
 * [phone]=> Phone
 * [normalphone]=> Phone converted to international standard *
 * [variables]=> The variables to personalize, separated «;» **
 * [status]=>Статус телефонного номера
 * <p>
 * international standard
 * * for example: Test1;Test2;Test3
 */
public class Phone {
    private long id;
    private long addressbook;
    private String phone;
    private String normalphone;
    private String variables;
    private int status;

    public Phone() {
    }

    public Phone(long id, String phone, String normalphone, String variables, int status) {
        this.id = id;
        this.phone = phone;
        this.normalphone = normalphone;
        this.variables = variables;
        this.status = status;
    }

    public Phone(long id, long addressbook, String phone, String normalphone, String variables, int status) {
        this.id = id;
        this.addressbook = addressbook;
        this.phone = phone;
        this.normalphone = normalphone;
        this.variables = variables;
        this.status = status;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getAddressbook() {
        return addressbook;
    }

    public void setAddressbook(long addressbook) {
        this.addressbook = addressbook;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getNormalphone() {
        return normalphone;
    }

    public void setNormalphone(String normalphone) {
        this.normalphone = normalphone;
    }

    public String getVariables() {
        return variables;
    }

    public void setVariables(String variables) {
        this.variables = variables;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Phone phone1 = (Phone) o;
        return id == phone1.id &&
                addressbook == phone1.addressbook &&
                Objects.equals(phone, phone1.phone) &&
                Objects.equals(normalphone, phone1.normalphone) &&
                Objects.equals(variables, phone1.variables) &&
                Objects.equals(status, phone1.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, addressbook, phone, normalphone, variables, status);
    }

    @Override
    public String toString() {
        return "Phone{" +
                "id=" + id +
                ", addressbook=" + addressbook +
                ", phone='" + phone + '\'' +
                ", normalphone='" + normalphone + '\'' +
                ", variables='" + variables + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
