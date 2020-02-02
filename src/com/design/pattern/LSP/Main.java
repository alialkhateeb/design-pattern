package com.design.pattern.LSP;

public class Main {
    public static void main(String[] args) {

        Rectangle r = new Rectangle(2 , 3);
        useIt(r);

        Rectangle sq = new Square();
        sq.setWidth(5);
        useIt(sq);

    }
    static void useIt(Rectangle r){
        int width = r.getWidth();
        r.setHeight(10);

        System.out.println("Expected Area of " + (width * 10) + ", got " + r.getArea());


    }
}
