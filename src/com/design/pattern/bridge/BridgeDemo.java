package com.design.pattern.bridge;

/*
 * Let's suppose that you have base class Shape -> you have different type of Shapes (Circle,  Square)
 * Let's suppose you have render class Rendering -> (Vector, Raster)
 * We will end up with 2x2=4 classes that will include different shapes and rendering
 *              (VectorCircleRender, VectorSquareRender, RasterCircleRender, RasterSquareRender)
 */

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;

interface Rendererr {
    void renderCircle(float radius);
}

class VectorRenderer implements Rendererr {

    @Override
    public void renderCircle(float radius) {
        System.out.println("Draw a circle, radius = " + radius);
    }
}

class RasterRenderer implements Rendererr {

    @Override
    public void renderCircle(float radius) {
        System.out.println("drawing pixels for circle of a radius" + radius);
    }
}
//using bridge pattern, the base class shape as well as any inheritor all of it's going to require us to explicitly ...
//specify the kind of renderer that we're going to be using when it comes to the draw method of the shape

abstract class Shapes {
    protected Rendererr renderer;

    public Shapes(Rendererr renderer) {
        this.renderer = renderer;
    }

    public abstract void draw();

    public abstract void resizing(float resizing);
}

class Circle extends Shapes {

    public float radius;

    @Inject // this annotation from google allow for DI
    public Circle(Rendererr renderer) {
        super(renderer);
    }

    public Circle(Rendererr renderer, float radius) {
        super(renderer);
        this.radius = radius;
    }

    @Override
    public void draw() {
        renderer.renderCircle(radius);
    }

    @Override
    public void resizing(float resizing) {
        radius *= resizing;
    }
}

// we will make a module which configures what actually happens when somebody has a dependency on a renderer
class ShapeModule extends AbstractModule{
    @Override
    protected void configure() {
        //here we are going to specify what actually happen
        bind(Rendererr.class).to(VectorRenderer.class);
    }

}

public class BridgeDemo {

    public static void main(String[] args) {
        //the first approach, not using DI
//        RasterRenderer raster = new RasterRenderer();
//        VectorRenderer vector = new VectorRenderer();
//        Circle circle = new Circle(vector, 5);
//        circle.draw();
//        circle.resizing(3);
//        circle.draw();
//
//        Circle circle2 = new Circle(raster, 5);
//        circle.draw();
//        circle.resizing(3);
//        circle.draw();

        //second approach using DI
        Injector injector = Guice.createInjector(new ShapeModule());
        Circle instance = injector.getInstance(Circle.class);
        instance.radius=3;
        instance.draw();
        instance.resizing(2);
        instance.draw();
    }
}
