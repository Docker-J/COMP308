/* Author: Junesung Lee 
 * Student ID: 3643836
 * Date: Jul 21th, 2023
 * 
 * AssemblyServiceGenerator is a random object generator for AssemblyService class specifically
 * 
 * Description:
 * AssemblyServiceGenrator(): Get a class as a parameter.
 * T next(): return the random object
 * <T> Genrator<T> create(Class<T> type): helper function to create object
*/

import java.util.concurrent.ThreadLocalRandom;

import net.mindview.util.Generator;

public class AssemblyServiceGenerator<T> implements Generator<T> {
    private Class<T> type;
    private String[] providers = { "A", "B", "C", "D" };

    public AssemblyServiceGenerator(Class<T> type) {
        this.type = type;
    }

    public T next() {
        try {
            int provider = ThreadLocalRandom.current().nextInt(0, providers.length);
            float price = ThreadLocalRandom.current().nextFloat(15f, 33f);
            return type.getDeclaredConstructor(String.class, float.class).newInstance(providers[provider], price);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> Generator<T> create(Class<T> type) {
        return new AssemblyServiceGenerator<T>(type);
    }
}