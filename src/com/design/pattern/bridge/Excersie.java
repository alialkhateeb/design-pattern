package com.design.pattern.bridge;

public class Excersie {
}


//abstract class Shape
//{
//    public abstract String getName();
//}
//
//class Triangle extends Shape
//{
//    private Renderer renderer;
//    public Triangle(Renderer renderer) {
//        this.renderer = renderer;
//    }
//
//    @Override
//    public String getName()
//    {
//        return "Triangle";
//    }
//}
//
//class Square extends Shape
//{
//    private Renderer renderer;
//
//    public Square(Renderer renderer) {
//        this.renderer = renderer;
//    }
//
//    @Override
//    public String getName()
//    {
//        return "Square";
//    }
//}
//
//class VectorSquare extends Square
//{
//    @Override
//    public String toString()
//    {
//        return String.format("Drawing %s as lines", getName());
//    }
//}
//
//class RasterSquare extends Square
//{
//    @Override
//    public String toString()
//    {
//        return String.format("Drawing %s as pixels", getName());
//    }
//}

// imagine VectorTriangle and RasterTriangle are here too

interface Renderer{
    public String whatToRenderAs();
}


class VectorTriangle implements Renderer{

    @Override
    public String whatToRenderAs() {
        return "lines";
    }

    @Override
    public String toString() {
        return "Drawing Triangle as lines";
    }
}

class RasterTriangle implements Renderer{

    @Override
    public String whatToRenderAs() {
        return "pixels";
    }

    @Override
    public String toString() {
        return "Drawing Triangle as pixels";
    }
}

abstract class Shape
{
    private Renderer renderer;
    public String name;
    public Shape(Renderer renderer) {
        this.renderer = renderer;
    }


    @Override
    public String toString() {
        return "Drawing " + " as %s "+ this.renderer.whatToRenderAs() ;
    }

}
class Square extends Shape{

    public Square(Renderer renderer) {
        super(renderer);
        this.name="Square";
    }
}

class Triangle extends Shape{

    public Triangle(Renderer renderer) {
        super(renderer);
        this.name="Triangle";
    }
}