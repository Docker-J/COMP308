/* Author: Junesung Lee 
 * Student ID: 3643836
 * Date: Jul 17th, 2023
 * 
 * TIJ Page 286, Exercise 5
*/

public abstract class Cycle {
    private int wheels;
    private String type;

    Cycle(int wheels, String type) {
        this.wheels = wheels;
        this.type = type;
    }

    public void ride() {
        System.out.println("Riding a " + this.type + ".");
        System.out.println("Number of wheel(s): " + this.wheels());
    }

    public int wheels() {
        return this.wheels;
    }

    public static void main(String[] args) {
        Unicycle uni = new Unicycle();
        Bicycle bi = new Bicycle();
        Tricycle tri = new Tricycle();

        uni.ride();
        bi.ride();
        tri.ride();
    }
}

class Unicycle extends Cycle {
    Unicycle() {
        super(1, "Unicycle");
    }
}

class Bicycle extends Cycle {
    Bicycle() {
        super(2, "Bicycle");
    }
}

class Tricycle extends Cycle {
    Tricycle() {
        super(3, "Tricycle");
    }
}
