/* Author: Junesung Lee 
 * Student ID: 3643836
 * Date: Jul 21th, 2023
 * 
 * DeliveryServiceGenerator is a random object generator for DeliveryService class specifically
 * 
 * Description:
 * DeliveryserviceGenrator(): Get a class as a parameter.
 * T next(): return the random object
 * <T> Genrator<T> create(Class<T> type): helper function to create object
*/

import java.util.concurrent.ThreadLocalRandom;

import net.mindview.util.Generator;

public class DeliveryServiceGenerator<T> implements Generator<T> {
    private Class<T> type;
    private String[] couriers = { "Canada Post", "UPS", "Fedex", "Purolator" };

    public DeliveryServiceGenerator(Class<T> type) {
        this.type = type;
    }

    public T next() {
        try {
            int courier = ThreadLocalRandom.current().nextInt(0, couriers.length);
            float price = ThreadLocalRandom.current().nextFloat(15f, 33f);
            return type.getDeclaredConstructor(String.class, float.class).newInstance(couriers[courier], price);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> Generator<T> create(Class<T> type) {
        return new DeliveryServiceGenerator<T>(type);
    }
}