package com.design.pattern.OCP;

import java.util.List;
import java.util.stream.Stream;

enum Color{
    RED, GREEN, BLUE
}

enum Size{
    SMALL, MED, LARGE, XLARGE
}

public class Product {

    public String name;
    public Color color;
    public  Size size;

    public Product(String name, Color color, Size size) {
        this.name = name;
        this.color = color;
        this.size = size;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", color=" + color +
                ", size=" + size +
                '}';
    }
}

class FilterProduct{
    public Stream<Product> filterByColor (List<Product> products,
                                          Color color){
        return products.stream().filter(p -> p.color == color);
    }

    public Stream<Product> filterBySize(List<Product> products,
                                          Size size){
        return products.stream().filter(p -> p.size == size);
    }

    public Stream<Product> filterByColorANDSize(List<Product> products,
                                                Color color,
                                                Size size){
        return products.stream().filter(p -> p.color == color && p.size == size);
    }
}

//***********************************************
//the better approach to implment the principle and the specification pattern
//***********************************************

interface Specification <T>{
    //return a method to see if a particle satisfied
    boolean isSatisfied (T item);
}
interface Filter<T>{
    Stream<T> filter (List<T> items, Specification<T> specification);
}

class ColorSpecification implements  Specification<Product>{

    private Color color;

    public ColorSpecification(Color color) {
        this.color = color;
    }

    @Override
    public boolean isSatisfied(Product item) {
        return color == item.color;
    }
}

class SizeSpecification implements  Specification<Product>{

    private Size size;

    public SizeSpecification(Size size) {
        this.size = size;
    }

    @Override
    public boolean isSatisfied(Product item) {
        return size == item.size;
    }
}


class BetterFilter implements Filter<Product>{
    @Override
    public Stream<Product> filter(List<Product> items, Specification<Product> specification) {
        return items.stream().filter(p -> specification.isSatisfied(p));
    }
}
class AndSpecificationTwoCondition<T> implements Specification<T>{
    private Specification<T> first,second;

    public AndSpecificationTwoCondition(Specification<T> first, Specification<T> second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public boolean isSatisfied(T item) {
        return first.isSatisfied(item) && second.isSatisfied(item);
    }
}