package Program2;

public class FullName {
    private String title, firstName, middleName, lastName;

    public FullName(String title, String firstName, String middleName, String lastName) {
        this.title = title;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
    }
    
    @Override
    public String toString() {
        return title + "." + " " +firstName + " " + middleName + " " +lastName;
    }
}
