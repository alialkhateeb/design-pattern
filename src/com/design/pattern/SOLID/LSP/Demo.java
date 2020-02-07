package com.design.pattern.SOLID.LSP;


/**
 * this concept Liskov Subsistuation Priniciple (LSP) used to enforce chaning the logic of the code by understanding that modification implmentation in subclass from baseclass itself
 * take in consideration that if we use a method that has the same signature in subclass and override it can cause violation of the principle itself. the way to solve it is by making an implmentation in the base class, OR using factory pattern
 */
class Rectangle{
    protected int width, height;

    public Rectangle() {
    }

    public Rectangle(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getArea(){

        return this.height * this.width;
    }
    @Override
    public String toString() {
        return "Rectangle{" +
                "width=" + width +
                ", height=" + height +
                '}';
    }


}

class Square extends Rectangle{
    //all what we have done here violate the Liskov Substitution Principle (LSP)
    public Square() {

    }

    public Square(int size) {
        width = height = size;
    }

    @Override
    public void setWidth(int width) {
        super.setWidth(width);
        super.setHeight(width);
    }

    @Override
    public void setHeight(int height) {
        super.setHeight(height);
        super.setWidth(height);
    }
}

