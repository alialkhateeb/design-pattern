package com.design.pattern.Decorate;



interface Shape{
    String info();

}

class Circle implements Shape{

    private float radius;

    public Circle() {
    }

    public Circle(float radius) {
        this.radius = radius;
    }

    void resize(float size){
        radius *= size;
    }
    @Override
    public String info() {
        return "circle of radius " + radius;
    }
}

class Square implements Shape{

    private float side;

    public Square() {
    }

    public Square(float side) {
        this.side = side;
    }

    @Override
    public String info() {
        return "square of side " + side;
    }
}

/*
 *  In order for us to to add new functionality without modifying the existed class, we can do the following
 * This would allow us to add new properties without modifying existing classes
 *
 */

class ColorShape implements Shape{

    private Shape shape;
    private String color;

    public ColorShape(Shape shape, String color) {
        this.shape = shape;
        this.color = color;
    }

    @Override
    public String info() {
        return shape.info() + " with the color " + color;
    }
}

class TransparentShape implements Shape{
    private Shape shape;
    private int transparency;

    public TransparentShape(Shape shape, int transparency) {
        this.shape = shape;
        this.transparency = transparency;
    }

    @Override
    public String info() {
        return shape.info() + " has " + transparency + "%";
    }
}

/*
 * Keep in mind that is approach would be useful at runtime where
 */

public class DynamicDecoratorComposition {

    public static void main(String[] args) {
        Circle circle = new Circle(10);
        System.out.println(circle.info());

        ColorShape colorCircle = new ColorShape(circle, "blue");

        System.out.println(colorCircle.info());

        TransparentShape transparentShape = new TransparentShape(colorCircle, 100);
        System.out.println(transparentShape.info());

    }
}
