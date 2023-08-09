/* Author: Junesung Lee 
 * Student ID: 3643836
 * Date: Jul 21th, 2023
 * 
 * MotherboardGenerator is a random object generator for Motherboard class specifically
 * 
 * Description:
 * MotherboardGenerator(): Get a class as a parameter.
 * T next(): return the random object
 * <T> Genrator<T> create(Class<T> type): helper function to create object
*/

import java.util.concurrent.ThreadLocalRandom;

import net.mindview.util.Generator;

public class MotherboardGenerator<T> implements Generator<T> {
    private Class<T> type;
    private String[] manufactures = { "Asus", "MSI", "Gigabyte", "ASRock" };

    public MotherboardGenerator(Class<T> type) {
        this.type = type;
    }

    public T next() {
        try {
            int manufacture = ThreadLocalRandom.current().nextInt(0, manufactures.length);
            float price = ThreadLocalRandom.current().nextFloat(70f, 120f);
            return type.getDeclaredConstructor(String.class, float.class).newInstance(manufactures[manufacture], price);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> Generator<T> create(Class<T> type) {
        return new MotherboardGenerator<T>(type);
    }
}