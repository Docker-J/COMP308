import java.util.*;

public class OrderProcessor {
    private ArrayList<GenericOrder> accepted = new ArrayList<GenericOrder>();

    private ArrayList<ComputerPart> computerParts = new ArrayList<ComputerPart>();
    private ArrayList<Peripheral> peripherals = new ArrayList<Peripheral>();
    private ArrayList<Cheese> cheeses = new ArrayList<Cheese>();
    private ArrayList<Fruit> fruits = new ArrayList<Fruit>();
    private ArrayList<Service> service = new ArrayList<Service>();


    public void accept(GenericOrder order) {
        accepted.add(order);
    }

    public void process() {
        for(GenericOrder order: accepted) {
            switch (order.getClass()) {
                case ComputerOrder:
                
                default:
                    break;
            }

        }
    }

    public void dispatch() {

    }
}
