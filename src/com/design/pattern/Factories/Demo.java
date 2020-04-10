package com.design.pattern.Factories;
enum CoordinateSystem{
    CARTESIAN,
    POLAR
}

class Point {
    private double x, y;

    //this approch is consider to cause some issues due to the fact that we can not make clearifcation to the client what kind
    // of data we are requesting, and we need to provide more information for that matter
//    private Point(double a, double b, CoordinateSystem cs) {
//        switch (cs){
//            case CARTESIAN:
//                this.x = a;
//                this.y = b;
//                break;
//            case POLAR:
//                this.x = a * Math.cos(b);
//                this.y = a * Math.sin(b);
//                break;
//        }
//    }


    //the main reason we are using private is to force the user to use our static method to create an object
    private Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "Point{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }



    // the second approche is that we want to group factory methods so that it makes it more organized
    // we can use this class to group those factory methods but we need to take decide whether we want to make Point class constrcutor public or not.
    // if we want to keep it private we need to take this class to be inside Pointer class, otherwise we can leave the class outside of Point class scope
    public static class Factory{
        public static Point newCartesianPoint(double x, double y){
            return new Point(x, y);
        }

        public static Point newPolarPoint(double rho, double theta){
            return new Point((rho * Math.cos(theta)), (rho * Math.sin(theta)));
        }
    }
}

class PointFactoryTwo {

}


public class Demo {

    public static void main(String[] args) {
        Point cart = Point.Factory.newCartesianPoint(10, 10);
//        Point cart = Point.newCartesianPoint(10, 10);
        System.out.println("cart = " + cart);
        Point ploar =  Point.Factory.newPolarPoint(10, 10);
//        Point ploar = Point.newPolarPoint(10, 10);
        System.out.println("ploar = " + ploar);

    }
}
