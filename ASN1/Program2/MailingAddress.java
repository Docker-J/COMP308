package Program2;

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
