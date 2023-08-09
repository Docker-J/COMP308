/* Author: Junesung Lee 
 * Student ID: 3643836
 * Date: Jul 21th, 2023
 * 
 * PeripheralGenerator is a random object generator for Peripheral class specifically
 * 
 * Description:
 * PeripheralGenerator(): Get a class as a parameter.
 * T next(): return the random object
 * <T> Genrator<T> create(Class<T> type): helper function to create object
*/

import java.util.concurrent.ThreadLocalRandom;

import net.mindview.util.Generator;

public class PeripheralGenerator<T> implements Generator<T> {
    private Class<T> type;
    private String[] models = { "Samsung's", "LG's", "Dell's", "Asus's", "MSI's", "HP's" };

    public PeripheralGenerator(Class<T> type) {
        this.type = type;
    }

    public T next() {
        try {
            int model = ThreadLocalRandom.current().nextInt(0, models.length);
            float price = ThreadLocalRandom.current().nextFloat(100f, 350f);
            return type.getDeclaredConstructor(String.class, float.class).newInstance(models[model], price);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> Generator<T> create(Class<T> type) {
        return new PeripheralGenerator<T>(type);
    }
}