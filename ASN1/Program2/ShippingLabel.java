/* Author: Junesung Lee 
 * Student ID: 3643836
 * Date: Jul 17th, 2023
 * 
 * ShippingLabel is a class that stores two mailing addresses, ship-from and ship-to.
 * The class is able to format the shipping label.
 * e.g.) "From: FullName
 * Street Address, City, Province, Postal Code"
 * 
 * "To: FullName
 * Street Address, City, Province, Postal Code"
 * 
 * Description:
 * -public Shippinglabel(MailingAddress shipFrom, MailngAddress shipTo) - getting two MailingAddress objects
 * -public toString() - format the label
*/

public class ShippingLabel {

    private MailingAddress shipFrom;
    private MailingAddress shipTo;

    public ShippingLabel(MailingAddress shipFrom, MailingAddress shipTo) {
        this.shipFrom = shipFrom;
        this.shipTo = shipTo;
    }

    // Print out the shipping label.
    public void printLabel() {
        System.out.println(this);
    }

    @Override
    public String toString() {
        return "From: " + shipFrom + "\n\n" + "To: " + shipTo;
    }

    public static void main(String[] args) {
        FullName sender = new FullName("Mr", "Junesung", "", "Lee");
        FullName receiver = new FullName("Mr", "Steve", "", "Leung");

        MailingAddress shipFrom = new MailingAddress(sender, "355 Bulyea Rd NW", "Edmonton", "AB", "T6R 2B1");
        MailingAddress shipTo = new MailingAddress(receiver, "1 University Drive", "Athabasca", "AB", "T9S 3A3");

        ShippingLabel shippingLabel = new ShippingLabel(shipFrom, shipTo);
        shippingLabel.printLabel();
    }

}