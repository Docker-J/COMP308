/* Author: Junesung Lee 
 * Student ID: 3643836
 * Date: Jul 17th, 2023
 * 
 * Client class demonstrates the ordering process.
 * 
*/

public class Client {
    public static void main(String[] args) {
        OrderProcessor orderProcessor = new OrderProcessor();

        // Accept orders
        ComputerOrder computerOrder = new ComputerOrder();
        computerOrder.addItem(new Motherboard("Asus", 37.5f));
        computerOrder.addItem(new RAM("Kingston", 512, 25.0f));
        computerOrder.addItem(new AssemblyService("Memory Express", 50));
        orderProcessor.accept(computerOrder);

        ComputerOrder order2 = new ComputerOrder();
        order2.addItem(new Motherboard("Asus", 37.5f));
        order2.addItem(new Printer("HP", 132.0f));
        orderProcessor.accept(order2);

        PartyTrayOrder partyTrayOrder = new PartyTrayOrder();
        partyTrayOrder.addItem(new Cheddar(5));
        partyTrayOrder.addItem(new Apple(10));
        orderProcessor.accept(partyTrayOrder);

        // Process and dispatch orders
        orderProcessor.process();

        orderProcessor.dispatchComputerParts();
        orderProcessor.dispatchPeripherals();
        orderProcessor.dispatchServices();
        orderProcessor.dispatchCheese();
        orderProcessor.dispatchFruit();
    }
}
