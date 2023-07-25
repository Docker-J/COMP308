import java.util.*;

public class OrderProcessor {
    private List<GenericOrder<?>> orders;

    private Map<UUID, List<ComputerPart>> computerPartOrders;
    private Map<UUID, List<Peripheral>> peripheralOrders;
    private Map<UUID, List<Cheese>> cheeseOrders;
    private Map<UUID, List<Fruit>> fruitOrders;
    private Map<UUID, List<Service>> serviceOrders;

     public OrderProcessor() {
        this.orders = new ArrayList<>();
        this.computerPartOrders = new HashMap<>();
        this.peripheralOrders = new HashMap<>();
        this.cheeseOrders = new HashMap<>();
        this.fruitOrders = new HashMap<>();
        this.serviceOrders = new HashMap<>();
     }

    public void accept(GenericOrder<?> order) {
        orders.add(order);
    }

    public void process() {
        for(GenericOrder<?> order: orders) {
            UUID orderId = order.getOrderId();
            List<?> items = order.getItems();

            for (Object item: items) {
                if (item instanceof ComputerPart) {
                    ComputerPart computerPart = (ComputerPart) item;
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
    }

    public void dispatchComputerParts() {
        for (Map.Entry<UUID, List<ComputerPart>> entry : computerPartOrders.entrySet()) {
            UUID orderId = entry.getKey();
            List<ComputerPart> computerParts = entry.getValue();

            for (ComputerPart computerPart : computerParts) {
                if (computerPart instanceof Motherboard) {
                    Motherboard motherboard = (Motherboard) computerPart;
                    System.out.println("Motherboard     name=" + motherboard.getManufacturer() + ", price=" + motherboard.price() + ", order number=" + orderId);
                } else if (computerPart instanceof RAM) {
                    RAM ram = (RAM) computerPart;
                    System.out.println("RAM     name=" + ram.getManufacturer() + ", size=" + ram.size + ", price=" + ram.price() + ", order number=" + orderId);
                } else if (computerPart instanceof Drive) {
                    Drive drive = (Drive) computerPart;
                    System.out.println("Drive     type=" + drive.getType() + ", speed=" + drive.getSpeed() + ", price=" + drive.price() +", order number=" + orderId);
                }
            }
        }
    }

    public void dispatchPeripherals() {
        for (Map.Entry<UUID, List<Peripheral>> entry : peripheralOrders.entrySet()) {
            UUID orderId = entry.getKey();
            List<Peripheral> peripherals = entry.getValue();

            for (Peripheral peripheral : peripherals) {
                if (peripheral instanceof Printer) {
                    Printer printer = (Printer) peripheral;
                    System.out.println("Printer     model=" + printer.getModel() + ", price=" + printer.price() + ", order number=" + orderId);
                } else if (peripheral instanceof Monitor) {
                    Monitor monitor = (Monitor) peripheral;
                    System.out.println("Monitor     model=" + monitor.getModel() + ", price=" + monitor.price() + ", order number=" + orderId);
                }
            }
        }
    }

    public void dispatchServices() {
        for (Map.Entry<UUID, List<Service>> entry : serviceOrders.entrySet()) {
            UUID orderId = entry.getKey();
            List<Service> services = entry.getValue();

            for (Service service : services) {
                if (service instanceof AssemblyService) {
                    AssemblyService assemblyService = (AssemblyService) service;
                    System.out.println("AssemblyService     provider=" + assemblyService.getProvider() + ", price=" + assemblyService.price() + ", order number=" + orderId);
                } else if (service instanceof DeliveryService) {
                    DeliveryService deliveryService = (DeliveryService) service;
                    System.out.println("DeliveryService     courier=" + deliveryService.getCourier() + ", price=" + deliveryService.price() + ", order number=" + orderId);
                }
            }
        }
    }

    public void dispatchCheese() {
        for (Map.Entry<UUID, List<Cheese>> entry : cheeseOrders.entrySet()) {
            UUID orderId = entry.getKey();
            List<Cheese> cheeses = entry.getValue();

            for (Cheese cheese : cheeses) {
                if (cheese instanceof Cheddar) {
                    System.out.println("Cheddar     price=" + cheese.price() + ", order number=" + orderId);
                } else if (cheese instanceof Mozzarella) {
                    System.out.println("Mozzarella     price=" + cheese.price() + ", order number=" + orderId);
                }
            }
        }
    }

    public void dispatchFruit() {
        for (Map.Entry<UUID, List<Fruit>> entry : fruitOrders.entrySet()) {
            UUID orderId = entry.getKey();
            List<Fruit> fruits = entry.getValue();

            for (Fruit fruit : fruits) {
                if (fruit instanceof Apple) {
                    System.out.println("Apple     price=" + fruit.price() + ", order number=" + orderId);
                } else if (fruit instanceof Orange) {
                    System.out.println("Orange     price=" + fruit.price() + ", order number=" + orderId);
                }
            }
        }
    }
}
