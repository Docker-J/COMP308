/* Author: Junesung Lee 
 * Student ID: 3643836
 * Date: Jul 17th, 2023
 * 
 * OrderProcessor class is help to accept, sort and dispatching multiple orders.
 * 
 * Description:
 * -public OrderProcessor(): Default constructor
 * -public <T extends GenericOrder<?>> void accept(T order): Accept Order that extends GenericOrder.
 * -public void process(): Sorting the accepted order through accept(T order) method by the product types(Computer Part, Peripheral, Cheese, Fruit, Service) and removes them from the accepted order list.
 * -public void dispatch*****(): Dispatching the processed order with the product description. Prints out the products and removes them from the lists of sorted products.
*/

import java.util.*;

public class OrderProcessor {
    private ArrayList<GenericOrder<?>> orders;

    private Map<UUID, ArrayList<ComputerPart>> computerPartOrders;
    private Map<UUID, ArrayList<Peripheral>> peripheralOrders;
    private Map<UUID, ArrayList<Cheese>> cheeseOrders;
    private Map<UUID, ArrayList<Fruit>> fruitOrders;
    private Map<UUID, ArrayList<Service>> serviceOrders;

    public OrderProcessor() {
        this.orders = new ArrayList<>();
        this.computerPartOrders = new HashMap<>();
        this.peripheralOrders = new HashMap<>();
        this.cheeseOrders = new HashMap<>();
        this.fruitOrders = new HashMap<>();
        this.serviceOrders = new HashMap<>();
    }

    public <T extends GenericOrder<?>> void accept(T order) {
        orders.add(order);
    }

    public void process() {
        for (GenericOrder<?> order : orders) {
            UUID orderId = order.getOrderId();
            ArrayList<?> items = order.getItems();

            // Sort the items in the order by its type and group by order id
            // (Computer Part, Peripheral, Cheese, Fruit, and Service)
            for (Object item : items) {
                if (item instanceof ComputerPart) {
                    ComputerPart computerPart = (ComputerPart) item;
                    // Add the item into the HashMap. If orderId not exists as a key, initialize an
                    // arraylist for the order
                    computerPartOrders.computeIfAbsent(orderId, k -> new ArrayList<>()).add(computerPart);
                } else if (item instanceof Peripheral) {
                    Peripheral peripheral = (Peripheral) item;
                    peripheralOrders.computeIfAbsent(orderId, k -> new ArrayList<>()).add(peripheral);
                } else if (item instanceof Cheese) {
                    Cheese cheese = (Cheese) item;
                    cheeseOrders.computeIfAbsent(orderId, k -> new ArrayList<>()).add(cheese);
                } else if (item instanceof Fruit) {
                    Fruit fruit = (Fruit) item;
                    fruitOrders.computeIfAbsent(orderId, k -> new ArrayList<>()).add(fruit);
                } else if (item instanceof Service) {
                    Service service = (Service) item;
                    serviceOrders.computeIfAbsent(orderId, k -> new ArrayList<>()).add(service);
                }
            }
        }
        orders.clear();
    }

    public void dispatchComputerParts() {
        System.out.println("----------Computer Parts----------");
        for (Map.Entry<UUID, ArrayList<ComputerPart>> entry : computerPartOrders.entrySet()) {
            UUID orderId = entry.getKey();
            ArrayList<ComputerPart> computerParts = entry.getValue();

            for (ComputerPart computerPart : computerParts) {
                if (computerPart instanceof Motherboard) {
                    Motherboard motherboard = (Motherboard) computerPart;
                    System.out.println("Motherboard     name=" + motherboard.getManufacturer() + ", price="
                            + motherboard.price() + ", order number=" + orderId);
                } else if (computerPart instanceof RAM) {
                    RAM ram = (RAM) computerPart;
                    System.out.println("RAM     name=" + ram.getManufacturer() + ", size=" + ram.size + ", price="
                            + ram.price() + ", order number=" + orderId);
                } else if (computerPart instanceof Drive) {
                    Drive drive = (Drive) computerPart;
                    System.out.println("Drive     type=" + drive.getType() + ", speed=" + drive.getSpeed() + ", price="
                            + drive.price() + ", order number=" + orderId);
                } else {
                    System.out.println(
                            "Some Computer Part     price=" + computerPart.price() + ", order number=" + orderId);
                }
            }
        }
        computerPartOrders.clear();
    }

    public void dispatchPeripherals() {
        System.out.println("----------Peripherals----------");
        for (Map.Entry<UUID, ArrayList<Peripheral>> entry : peripheralOrders.entrySet()) {
            UUID orderId = entry.getKey();
            ArrayList<Peripheral> peripherals = entry.getValue();

            for (Peripheral peripheral : peripherals) {
                if (peripheral instanceof Printer) {
                    Printer printer = (Printer) peripheral;
                    System.out.println("Printer     model=" + printer.getModel() + ", price=" + printer.price()
                            + ", order number=" + orderId);
                } else if (peripheral instanceof Monitor) {
                    Monitor monitor = (Monitor) peripheral;
                    System.out.println("Monitor     model=" + monitor.getModel() + ", price=" + monitor.price()
                            + ", order number=" + orderId);
                } else {
                    System.out.println("Some Peripheral     price=" + peripheral.price() + ", order number=" + orderId);
                }
            }
        }
        peripheralOrders.clear();
    }

    public void dispatchServices() {
        System.out.println("----------Services----------");
        for (Map.Entry<UUID, ArrayList<Service>> entry : serviceOrders.entrySet()) {
            UUID orderId = entry.getKey();
            ArrayList<Service> services = entry.getValue();

            for (Service service : services) {
                if (service instanceof AssemblyService) {
                    AssemblyService assemblyService = (AssemblyService) service;
                    System.out.println("AssemblyService     provider=" + assemblyService.getProvider() + ", price="
                            + assemblyService.price() + ", order number=" + orderId);
                } else if (service instanceof DeliveryService) {
                    DeliveryService deliveryService = (DeliveryService) service;
                    System.out.println("DeliveryService     courier=" + deliveryService.getCourier() + ", price="
                            + deliveryService.price() + ", order number=" + orderId);
                } else {
                    System.out
                            .println("Some Service     price=" + service.price() + ", order number=" + orderId);
                }
            }
        }
        serviceOrders.clear();
    }

    public void dispatchCheese() {
        System.out.println("----------Cheeses----------");
        for (Map.Entry<UUID, ArrayList<Cheese>> entry : cheeseOrders.entrySet()) {
            UUID orderId = entry.getKey();
            ArrayList<Cheese> cheeses = entry.getValue();

            for (Cheese cheese : cheeses) {
                if (cheese instanceof Cheddar) {
                    System.out.println("Cheddar     price=" + cheese.price() + ", order number=" + orderId);
                } else if (cheese instanceof Mozzarella) {
                    System.out.println("Mozzarella     price=" + cheese.price() + ", order number=" + orderId);
                } else {
                    System.out.println("Some Cheese     price=" + cheese.price() + ", order number=" + orderId);
                }
            }
        }
        cheeseOrders.clear();
    }

    public void dispatchFruit() {
        System.out.println("----------Fruits----------");
        for (Map.Entry<UUID, ArrayList<Fruit>> entry : fruitOrders.entrySet()) {
            UUID orderId = entry.getKey();
            ArrayList<Fruit> fruits = entry.getValue();

            for (Fruit fruit : fruits) {
                if (fruit instanceof Apple) {
                    System.out.println("Apple     price=" + fruit.price() + ", order number=" + orderId);
                } else if (fruit instanceof Orange) {
                    System.out.println("Orange     price=" + fruit.price() + ", order number=" + orderId);
                } else {
                    System.out.println("Some Fruit     price=" + fruit.price() + ", order number=" + orderId);
                }
            }
        }
        fruitOrders.clear();
    }
}
