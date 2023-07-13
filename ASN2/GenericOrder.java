public class GenericOrder<T, S, V> {
    private Product[] products;
    private int orderNumber;

    GenericOrder(Product[] products) {
        this.products = products;
    }
}

class ComputerOrder extends GenericOrder<ComputerPart, Peripheral, Service> {
    ComputerOrder(ComputerPart[] parts) {
        super(parts);
    }
    ComputerOrder(Peripheral[] parts) {
        super(parts);
    }
    ComputerOrder(Service[] parts) {
        super(parts);
    }
}

class PartyTrayOrder extends GenericOrder<Cheese, Fruit, Service> {
    PartyTrayOrder(Cheese[] parts) {
        super(parts);
    }
    PartyTrayOrder(Fruit[] parts) {
        super(parts);
    }
    PartyTrayOrder(Service[] parts) {
        super(parts);
    }
}
