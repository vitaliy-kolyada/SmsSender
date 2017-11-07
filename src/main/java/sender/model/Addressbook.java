package sender.model;

/**
 * Address book is an object consisting of:
 * [id] — The ID
 * [name] => Name
 * [phones] => The number of phones
 * [exceptions] => The number of phones in the exception
 * [creationdate] => Created date
 * [description] => Description
 */
public class Addressbook {
    private int id;
    private String name;
    private int phones;
    private int exceptions;
    private String creationdate;
    private String description;

    public Addressbook() {
    }

    public Addressbook(int id, String name, int phones, String creationdate, String description) {
        this.id = id;
        this.name = name;
        this.phones = phones;
        this.creationdate = creationdate;
        this.description = description;
    }

    public Addressbook(int id, String name, int phones, int exceptions, String creationdate, String description) {
        this.id = id;
        this.name = name;
        this.phones = phones;
        this.exceptions = exceptions;
        this.creationdate = creationdate;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPhones() {
        return phones;
    }

    public void setPhones(int phones) {
        this.phones = phones;
    }

    public int getExceptions() {
        return exceptions;
    }

    public void setExceptions(int exceptions) {
        this.exceptions = exceptions;
    }

    public String getCreationdate() {
        return creationdate;
    }

    public void setCreationdate(String creationdate) {
        this.creationdate = creationdate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Addressbook{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phones=" + phones +
                ", exceptions=" + exceptions +
                ", creationdate='" + creationdate + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
