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

class ComputerOrder extends GenericOrder<Product> {

    public ComputerOrder() {
        super();
    }
    
    public void addItem(ComputerPart computerPart) {
        super.addItem(computerPart);
    }

    public void addItem(Peripheral peripheral) {
        super.addItem(peripheral);
    }

    public void addItem(Service service) {
        super.addItem(service);
    }
}

class PartyTrayOrder extends GenericOrder<Product> {
    
    public PartyTrayOrder() {
        super();
    }
    
    public void addItem(Cheese cheese) {
        super.addItem(cheese);
    }

    public void addItem(Fruit fruit) {
        super.addItem(fruit);
    }

    public void addItem(Service service) {
        super.addItem(service);
    }
}

class ComputerPartyOrder extends GenericOrder<Product> {
    
    public ComputerPartyOrder() {
        super();
    }

    public void addItem(ComputerPart computerPart) {
        super.addItem(computerPart);
    }

    public void addItem(Peripheral peripheral) {
        super.addItem(peripheral);
    }
    
    public void addItem(Cheese cheese) {
        super.addItem(cheese);
    }

    public void addItem(Fruit fruit) {
        super.addItem(fruit);
    }

    public void addItem(Service service) {
        super.addItem(service);
    }
}
