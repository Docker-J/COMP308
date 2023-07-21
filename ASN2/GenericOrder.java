import java.util.ArrayList;
import java.util.UUID;

public class GenericOrder<T extends Product, S extends Product, V extends Product> {
    private ArrayList<Product> items;
    private UUID orderNumber;

    public GenericOrder() {
        this.orderNumber = UUID.randomUUID();
        this.items = new ArrayList<>();
    }

    public UUID getOrderId() {
        return orderNumber;
    }

    public void addItem(Product item) {
        items.add(item);
    }

    public ArrayList<Product> getItems() {
        return items;
    }
}

class ComputerOrder extends GenericOrder<ComputerPart, Peripheral, Service> {

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

class PartyTrayOrder extends GenericOrder<Cheese, Fruit, Service> {
    
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

class ComputerPartyOrder extends GenericOrder<Cheese, Fruit, Service> {
    
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
