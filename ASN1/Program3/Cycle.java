package Program3;

public class Cycle {
    int wheels;

    Cycle(int wheels) {
        this.wheels = wheels;
    }

    public void ride() {
        System.out.println("Riding a cycle.");
        System.out.println("Number of wheels: " + this.wheels());
    }

    public int wheels() {
        return this.wheels;
    }

    public static void main(String[] args) {
        Cycle uni = new Unicycle();
        Cycle bi = new Bicycle();
        Cycle tri = new Tricycle();

        uni.ride();
        bi.ride();
        tri.ride();
    }
}

class Unicycle extends Cycle{
    int wheels;
    super(wheels);
    // @Override
    // public void ride() {
    //     System.out.println("Riding a unicycle.");
    // }
}

class Bicycle extends Cycle{
    int wheels = 2;
    // @Override
    // public void ride() {
    //     System.out.println("Riding a bicycle.");
    // }
}

class Tricycle extends Cycle{
    int wheels = 3;
    // @Override
    // public void ride() {
    //     System.out.println("Riding a tricycle.");
    // }
}
