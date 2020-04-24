package com.design.pattern.Composite;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class GraphicObject {
    protected String name = "Group";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public GraphicObject() {
    }

    public String color;

    public List<GraphicObject> children = new ArrayList<>();
    //this is the core of composite design pattern is being able to treat individual objects and groups of objects in
    //a uniform fashion

    private void print(StringBuilder sb, int depth){
        sb.append(String.join("", Collections.nCopies(depth, "*")))
                .append(depth > 0 ? " ": "")
                .append((color == null || color.isEmpty() ? "" : color + " "))
                .append(getName())
                .append(System.lineSeparator());
        for(GraphicObject child: children){
            child.print(sb, depth+1);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        print(sb, 0);
        return sb.toString();
    }
}

class Circle extends GraphicObject{
    public Circle(String color) {
        this.color = color;
        this.name = "Circle";
    }
}

class Square extends GraphicObject{
    public Square(String color) {
        this.color = color;
        this.name = "Square";
    }
}

public class CompositeDemo {

    public static void main(String[] args) {
        GraphicObject draw = new GraphicObject();
        draw.setName("My drawing");
        draw.children.add(new Square("Red"));
        draw.children.add(new Circle("Yellow"));

        GraphicObject group = new GraphicObject();
        group.children.add(new Square("Blue"));
        group.children.add(new Circle("Blue"));

        draw.children.add(group);

        System.out.println(draw);
    }
}
