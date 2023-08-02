/* Author: Junesung Lee 
 * Student ID: 3643836
 * Date: Jul 17th, 2023
 * 
 * GenericOrder is class an abstract class which is a generic container of various types of order.
 * 
 * ComputerOrder class only takes the products that implement the IComputerOrder interface.
 * PartyTrayOrder class only takes the products that implement the IPartyTrayOrder interface.
 * ComputerPartyOrder class only takes the products that implement the IComputerPartyOrder interface.
 * 
 * Description:
 * -public Shippinglabel(MailingAddress shipFrom, MailngAddress shipTo) - getting two MailingAddress objects
 * -public UUID getOrderId(): return unique id of the order
 * -public void addItem(T item): add T obejct to the order(list)
 * -public ArrayList<T> getItems(): return items in the order
*/

import java.util.ArrayList;
import java.util.UUID;

public abstract class GenericOrder<T> {
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
