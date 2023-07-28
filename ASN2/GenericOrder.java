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

import java.util.ArrayList;
import java.util.UUID;

public class GenericOrder<T> {
    private ArrayList<T> items;
    private UUID orderNumber;

    public GenericOrder() {
        this.orderNumber = UUID.randomUUID();
        this.items = new ArrayList<>();
    }

    public UUID getOrderId() {
        return orderNumber;
    }

    public void addItem(T item) {
        items.add(item);
    }

    public ArrayList<T> getItems() {
        return items;
    }
}

class ComputerOrder extends GenericOrder<IComputerOrder> {
    public ComputerOrder() {
        super();
    }
}

class PartyTrayOrder extends GenericOrder<IPartyTrayOrder> {
    public PartyTrayOrder() {
        super();
    }
}

class ComputerPartyOrder extends GenericOrder<IComputerPartyOrder> {
    public ComputerPartyOrder() {
        super();
    }
}
