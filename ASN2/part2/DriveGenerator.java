/* Author: Junesung Lee 
 * Student ID: 3643836
 * Date: Jul 21th, 2023
 * 
 * DriveGenerator is a random object generator for Drive class specifically
 * 
 * Description:
 * DriveGenrator(): Get a class as a parameter.
 * T next(): return the random object
 * <T> Genrator<T> create(Class<T> type): helper function to create object
*/

import java.util.concurrent.ThreadLocalRandom;

import net.mindview.util.Generator;

public class DriveGenerator<T> implements Generator<T> {
    private Class<T> type;
    private String[] types = { "HDD", "SSD" };
    private int[] speeds = { 200, 300, 400, 500, 600 };

    public DriveGenerator(Class<T> type) {
        this.type = type;
    }

    public T next() {
        try {
            int typeNumber = ThreadLocalRandom.current().nextInt(0, types.length);
            int speed = ThreadLocalRandom.current().nextInt(0, speeds.length);
            float price = ThreadLocalRandom.current().nextFloat(40f, 80f);
            return type.getDeclaredConstructor(String.class, int.class, float.class).newInstance(types[typeNumber],
                    speeds[speed], price);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> Generator<T> create(Class<T> type) {
        return new DriveGenerator<T>(type);
    }
}