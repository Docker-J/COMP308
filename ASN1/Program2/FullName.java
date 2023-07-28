/* Author: Junesung Lee 
 * Student ID: 3643836
 * Date: Jul 17th, 2023
 * 
 * FullName is a class that stores a individual's name, with separate fields, title, first name, middle name, and last name.
 * The class is able to format the name. e.g.) "Title. Firstname Middlename Lastname"
 * 
 * Description:
 * -public FullName(String title, String firstName, String middleName, String lastName) - getting name information with separte fields.
 * -public toString() - format the name info that stored in the object
*/

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
        String fullName = "";

        if (title.length() > 0) {
            fullName += title + ". ";
        }
        fullName += firstName + " ";

        if (middleName.length() > 0) {
            fullName += middleName + " ";
        }
        fullName += lastName;

        return fullName;
    }
}
