/* Author: Junesung Lee 
 * Student ID: 3643836
 * Date: Jul 21th, 2023
 * 
 * CheeseFruitGenerator is a random object generator for Cheese, and Fruit classes specifically.
 * 
 * Description:
 * CheeseFruitGenrator(): Get a class as a parameter.
 * T next(): return the random object
 * <T> Genrator<T> create(Class<T> type): helper function to create object
*/

import java.util.concurrent.ThreadLocalRandom;

import net.mindview.util.Generator;

public class CheeseFruitGenerator<T> implements Generator<T> {
    private Class<T> type;

    public CheeseFruitGenerator(Class<T> type) {
        this.type = type;
    }

    public T next() {
        try {
            float price = ThreadLocalRandom.current().nextFloat(3f, 15f);
            return type.getDeclaredConstructor(float.class).newInstance(price);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> Generator<T> create(Class<T> type) {
        return new CheeseFruitGenerator<T>(type);
    }
}