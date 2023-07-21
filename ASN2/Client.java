public class Client {
    public static void main(String[] args) {
        OrderProcessor orderProcessor = new OrderProcessor();

        // Accept orders
        ComputerOrder order1 = new ComputerOrder();
        order1.addItem(new Motherboard("Asus", 37.5f));
        order1.addItem(new RAM("Kingston", 512, 25.0f));
        orderProcessor.accept(order1);

        ComputerOrder order2 = new ComputerOrder();
        order2.addItem(new Motherboard("Asus", 37.5f));
        orderProcessor.accept(order2);

        // Process and dispatch orders
        orderProcessor.process();

        orderProcessor.dispatchComputerParts();
    }
}
