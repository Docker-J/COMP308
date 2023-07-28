/* Author: Junesung Lee 
 * Student ID: 3643836
 * Date: Jul 17th, 2023
 * 
 * Circle is a class that stores circle's coordinates and radius.
 * The class also provides some methods to modify the values and calculation with them.
 * 
 * Description:
 * -public Circle() - supplies default values for the coordinates and the radius. 
 * -public Circle(double x, double y, double radius) - takes three parameters corresponding to the X and Y coordinates and the radius.
 * -public double circumference() - returns the circumference of the circle.
 * -public double area() - returns the area of the circle.
 * -public void setRadius(double r) - is called in the constructor and checks the radius against a maximum. If the radius is greater than the maximum, setRadius resets it to the maximum (using the ternary conditional operator). You may set your own maximum value.
 * -public void printAttributes() - prints the coordinates, the radius, the circumference, and the area.
 * -public boolean isInside(double x, double y) - return true if a point represented in the parameters falls inside the circle, false otherwise.
 * -public void move(int x, int y) - moves the origin by a specified amount.
*/

public class Circle {
    private double x, y;
    private double radius;
    private double MAX_RADIUS = 100;

    public Circle() {
        this.x = 0;
        this.y = 0;
        this.setRadius(10);
    }

    public Circle(double x, double y, double radius) {
        this.x = x;
        this.y = y;
        this.setRadius(radius);
    }

    public double circumference() {
        return 2 * this.radius * Math.PI;
    }

    public double area() {
        return Math.PI * this.radius * this.radius;
    }

    public void setRadius(double r) {
        this.radius = r > MAX_RADIUS ? MAX_RADIUS : r;
    }

    public void printAttributes() {
        System.out.println("Coordinates(X, Y): " + x + ", " + y);
        System.out.println("Radius: " + radius);
        System.out.println("Circumference: " + this.circumference());
        System.out.println("Area: " + this.area());
    }

    public boolean isInside(double x, double y) {
        return (this.x - radius <= x && x <= this.x + radius) && (this.y - radius <= y && y <= this.y + radius) ? true
                : false;
    }

    public void move(int x, int y) {
        this.x += x;
        this.y += y;
    }
}