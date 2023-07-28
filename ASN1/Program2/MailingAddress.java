/* Author: Junesung Lee 
 * Student ID: 3643836
 * Date: Jul 17th, 2023
 * 
 * MailingAddress is a class that stores a individual's mailing address, with separate fields, fullName object, stree address, city, province, and postal code.
 * The class is able to format the address.
 * e.g.) "FullName
 * Street Address, City, Province, Postal Code"
 * 
 * Description:
 * -public MailingAddress(FullName fullName, String streeAddress, String city, String province, String postalcode) - getting address information with separte fields.
 * -public toString() - format the address info that stored in the object
*/

public class MailingAddress {
    private FullName fullName;
    private String streetAddress;
    private String city;
    private String province;
    private String postalCode;

    public MailingAddress(FullName fullName, String streetAddress, String city, String province, String postalCode) {
        this.fullName = fullName;
        this.streetAddress = streetAddress;
        this.city = city;
        this.province = province;
        this.postalCode = postalCode;
    }

    @Override
    public String toString() {
        return fullName + "\n" + streetAddress + ", " + city + ", " + province + ", " + postalCode;
    }
}
