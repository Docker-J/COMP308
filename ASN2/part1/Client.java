/* Author: Junesung Lee 
 * Student ID: 3643836
 * Date: Jul 17th, 2023
 * 
 * Client class is created for demonstrating purposes of the ordering process.
 * 
 * Compile: javac *.java
 * Run: java ./Client.java
*/

import net.mindview.util.Generator;

public class Client {
    public static void main(String[] args) {
        OrderProcessor orderProcessor = new OrderProcessor();

        Generator<Motherboard> motherboard = MotherboardGenerator.create(Motherboard.class);
        Generator<RAM> ram = RAMGenerator.create(RAM.class);
        Generator<Drive> drive = DriveGenerator.create(Drive.class);
        Generator<Printer> printer = PeripheralGenerator.create(Printer.class);
        Generator<Monitor> monitor = PeripheralGenerator.create(Monitor.class);
        Generator<DeliveryService> deliveryService = DeliveryServiceGenerator.create(DeliveryService.class);
        Generator<AssemblyService> assemblyService = AssemblyServiceGenerator.create(AssemblyService.class);
        Generator<Cheddar> cheddar = CheeseFruitGenerator.create(Cheddar.class);
        Generator<Mozzarella> mozzarella = CheeseFruitGenerator.create(Mozzarella.class);
        Generator<Apple> apple = CheeseFruitGenerator.create(Apple.class);
        Generator<Orange> orange = CheeseFruitGenerator.create(Orange.class);

        // Accept orders
        ComputerOrder computerOrder = new ComputerOrder();
        computerOrder.addItem(motherboard.next());
        computerOrder.addItem(ram.next());
        computerOrder.addItem(drive.next());
        computerOrder.addItem(printer.next());
        computerOrder.addItem(monitor.next());
        computerOrder.addItem(assemblyService.next());
        computerOrder.addItem(deliveryService.next());
        orderProcessor.accept(computerOrder);

        PartyTrayOrder partyTrayOrder = new PartyTrayOrder();
        partyTrayOrder.addItem(cheddar.next());
        partyTrayOrder.addItem(mozzarella.next());
        partyTrayOrder.addItem(apple.next());
        partyTrayOrder.addItem(orange.next());
        partyTrayOrder.addItem(deliveryService.next());
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
