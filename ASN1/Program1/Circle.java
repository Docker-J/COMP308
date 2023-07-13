package Program1;

public class Circle {
    private double x, y;
    private double radius;

    public Circle() {}

    public Circle(double x, double y, double radius) {
        this.x = x;
        this.y = y;
        this.radius = radius;
    }

    public double circumference() {
        return 2 * this.radius * Math.PI;
    }

    public double area() {
        return Math.PI * this.radius * this.radius;
    }

    public void setRadius(double r) {
        this.radius = r;
    }

    public void printAttributes() {
        System.out.println("Coordinates(X, Y): " + x + ", " + y);
        System.out.println("Radius: " + radius);
        System.out.println("Circumference: " + this.circumference());
        System.out.println("Area: " + this.area());
    }

    public boolean isInside(double x, double y) {
        return this.x - radius <= x  && x <= this.x + radius ? true : false;
    }

    public void move(int x, int y) {
        this.x += x;
        this.y += y;
    }

}