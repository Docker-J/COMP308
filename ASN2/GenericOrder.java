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
