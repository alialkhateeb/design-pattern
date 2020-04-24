package com.design.pattern.Decorate;

/*
 * the dynamic decorator allows to build decorator at runtime, but it might cause some issues when you want multiple types
 * into one single type, if we count on generics we might loss some information
 */

import java.util.function.Supplier;

interface Shape2 {
    String info();

}

class Circle2 implements Shape2 {

    private float radius;

    public Circle2() {
    }

    public Circle2(float radius) {
        this.radius = radius;
    }

    void resize(float size) {
        radius *= size;
    }

    @Override
    public String info() {
        return "circle of radius " + radius;
    }

    public void CircleTest() {
        System.out.println("CircleTest");
    }
}

class Square2 implements Shape2 {

    private float side;

    public Square2() {
    }

    public Square2(float side) {
        this.side = side;
    }

    @Override
    public String info() {
        return "square of side " + side;
    }

    public void squareTest() {
        System.out.println("squareTest");
    }
}

class ColorShape2<T extends Shape2> implements Shape2 {
    private Shape2 shape;
    private String color;

    // we provided as a lambda function, which takes a supplier
    //so instead of passing the actual type in here we're passing teh constructor to that type
    public ColorShape2(Supplier<? extends T> constructor, String color) {
        this.color = color;
        //essentially we are constructing the shape from the supplier that we're actually given
        //someone gives us a way of building a particular shape and then we use that to actually call the constructor
        // and init the shape
        this.shape = constructor.get();

    }

    @Override
    public String info() {
        return shape.info() + " with the color " + color;
    }

    public void colorShape() {
        System.out.println("ColorShape2");
    }

}

class TransparentShape2<T extends Shape2> implements Shape2 {

    private Shape2 shape;
    private int transparency;

    public TransparentShape2(Supplier<? extends T> constructor, int transparency) {
        this.shape = constructor.get();
        this.transparency = transparency;
    }

    @Override
    public String info() {
        return shape.info() + " has " + transparency + "%";
    }

    public void transparentShape() {
        System.out.println("transparentShape");
    }
}

public class StaticDecoratorComposition {

    public static void main(String[] args) {

        ColorShape2<Square2> blueSquare = new ColorShape2<>(
                () -> new Square2(10),
                "blue"
        );

        System.out.println(blueSquare.info());


        TransparentShape2<ColorShape2<Circle2>> theCircle =
                new TransparentShape2<>(() ->
                        new ColorShape2<>(() -> new Circle2(3), "yellow")
                , 70);
        System.out.println(theCircle.info());
    }
}
