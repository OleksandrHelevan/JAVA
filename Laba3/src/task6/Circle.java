package task6;

public class Circle extends Shape{
    private final double radius;


    public Circle(double radius) {
        this.radius = radius;
    }

    @Override
    public double getArea() {
        return Math.PI * radius * radius;
    }

}