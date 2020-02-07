package com.design.pattern.SOLID.OCP;

import java.util.List;

// OCP + pattern of enterprise engineering = specification, looking at OCP through the prism of implementing the specification of design pattern
public class Main {

    public static void main(String[] args) {

        List<Product> products = List.of(
        new Product("Apple", Color.GREEN, Size.SMALL),
        new Product("Tree", Color.GREEN, Size.LARGE),
        new Product("House", Color.BLUE, Size.LARGE)
        );

        FilterProduct fil = new FilterProduct();

        System.out.println("Green products (old): ");

        fil.filterByColor(products, Color.GREEN).forEach(p -> System.out.println("- " + p.name + " is green"));




        //***********************************
        //better filter
        BetterFilter filter = new BetterFilter();
        System.out.println("Green products (new): ");
        filter.filter(products, new ColorSpecification(Color.GREEN)).forEach(p -> System.out.println("- "+ p.name + " is "+ p.color));


        //***************************************
        //two condition with better approche
        System.out.println("Two condition specification Size and Color");
        filter.filter(products, new AndSpecificationTwoCondition<>(new ColorSpecification(Color.BLUE), new SizeSpecification(Size.LARGE)))
        .forEach(p -> System.out.println("- " + p));
    }
}
