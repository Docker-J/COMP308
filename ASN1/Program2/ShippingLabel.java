package Program2;

public class ShippingLabel {

    private MailingAddress shipFrom;
    private MailingAddress shipTo;

    public ShippingLabel(MailingAddress shipFrom, MailingAddress shipTo) {
        this.shipFrom = shipFrom;
        this.shipTo = shipTo;
    }

    public void printLabel() {
        ShippingLabel label = new ShippingLabel(shipFrom, shipTo);
        System.out.println(label);
    }

    @Override
    public String toString() {
        return "From: " + shipFrom + "\n" + "To: " + shipTo;
    }

    public static void main(String[] args) {
        FullName sender = new FullName("Mr", "Junesung", null, "Lee");
        FullName receiver = new FullName("Mr", "Steve", null, "Leung");

        MailingAddress shipFrom = new MailingAddress(sender, "355 Bulyea Rd NW", "Edmonton", "AB", "T6R 2B1");
        MailingAddress shipTo = new MailingAddress(receiver, null, null, null, null);

        ShippingLabel shippingLabel = new ShippingLabel(shipFrom, shipTo);
        shippingLabel.printLabel();
    }

}