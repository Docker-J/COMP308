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

public class Client {
    public static void main(String[] args) {
        OrderProcessor orderProcessor = new OrderProcessor();

        // Accept orders
        ComputerOrder computerOrder = new ComputerOrder();
        computerOrder.addItem(new Motherboard("Asus", 37.5f));
        computerOrder.addItem(new RAM("Kingston", 512, 25.0f));
        orderProcessor.accept(computerOrder);

        ComputerOrder order2 = new ComputerOrder();
        order2.addItem(new Motherboard("Asus", 37.5f));
        orderProcessor.accept(order2);

        PartyTrayOrder partyTrayOrder = new PartyTrayOrder();
        partyTrayOrder.addItem(new Cheddar(5));
        partyTrayOrder.addItem(new Apple(10));
        orderProcessor.accept(partyTrayOrder);

        // Process and dispatch orders
        orderProcessor.process();

        orderProcessor.dispatchComputerParts();
        orderProcessor.dispatchCheese();
        orderProcessor.dispatchFruit();
    }
}
