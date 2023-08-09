/* Author: Junesung Lee 
 * Student ID: 3643836
 * Date: Jul 21th, 2023
 * 
 * RAMGenerator is a random object generator for RAM class specifically
 * 
 * Description:
 * RAMGenerator(): Get a class as a parameter.
 * T next(): return the random object
 * <T> Genrator<T> create(Class<T> type): helper function to create object
*/

import java.util.concurrent.ThreadLocalRandom;

import net.mindview.util.Generator;

public class RAMGenerator<T> implements Generator<T> {
    private Class<T> type;
    private String[] manufactures = { "Corsair", "Crucial", "G.SKILL", "Samsung", "Hynix" };
    private int[] sizes = { 8, 16, 32, 64 };

    public RAMGenerator(Class<T> type) {
        this.type = type;
    }

    public T next() {
        try {
            int manufacture = ThreadLocalRandom.current().nextInt(0, manufactures.length);
            int size = ThreadLocalRandom.current().nextInt(0, sizes.length);
            float price = ThreadLocalRandom.current().nextFloat(12f, 20f);
            return type.getDeclaredConstructor(String.class, int.class, float.class)
                    .newInstance(manufactures[manufacture], sizes[size], price * sizes[size] / 8);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> Generator<T> create(Class<T> type) {
        return new RAMGenerator<T>(type);
    }
}