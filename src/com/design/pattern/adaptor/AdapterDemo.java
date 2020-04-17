package com.design.pattern.adaptor;

import java.util.*;
import java.util.function.Consumer;

/*
 * In example we are given one kind of API and we are given a different set of objects which are incompatible and
 * we have to provide some sort of compatibility
 */

class Point{
    public int x,y;

    public Point(int x, int y) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Point point = (Point) o;

        if (x != point.x) return false;
        return y == point.y;
    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }
}

class Line{
    public Point start,end;

    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Line line = (Line) o;

        if (!start.equals(line.start)) return false;
        return end.equals(line.end);
    }

    @Override
    public int hashCode() {
        int result = start.hashCode();
        result = 31 * result + end.hashCode();
        return result;
    }
}

/*
a vectorObject is anything that is made of bunch of lines
 */
class VectorObject extends ArrayList<Line>{

}
// vector object is just made up of a bunch of lines
class VectorRectangle extends VectorObject{
    public VectorRectangle(int x, int y, int width, int height) {
        add(new Line(new Point(x,y), new Point(x+width, y)));
        add(new Line(new Point(x,y), new Point(x, y+height)));
        add(new Line(new Point(x+width,y), new Point(x+width, y+height)));
        add(new Line(new Point(x,y+height), new Point(x+width, y+height)));
    }

}

/*
Line -> to Point adapter
in this class, we can use the adapter to convert a Line to an array of Points

we will build caching mechanism inside the line to point
 */
class LineToPointAdapter implements Iterable<Point> {

    private static int count = 0;

    private static Map<Integer, List<Point>> caching = new HashMap<>();
    private int currentHash;

    public LineToPointAdapter(Line line) {
        currentHash = line.hashCode();
        if (caching.get(currentHash) != null) return; //because we already have all the information needed

        System.out.println(String.format("%d: Generating points for line [%d, %d]-[%d,%d] (with caching)",
                                        ++count, line.start.x, line.start.y, line.end.x, line.end.y));

        ArrayList<Point> points = new ArrayList<>();

        int left = Math.min(line.start.x, line.end.x);
        int right = Math.max(line.start.x, line.end.x);

        int top = Math.min(line.start.y, line.end.y);
        int bottom = Math.max(line.start.y, line.end.y);
        int dx = right - left;
        int dy = line.end.y - line.start.y;

        if (dx == 0){
            for(int y = top; y <= bottom; ++y){
                points.add(new Point(left, y));
            }
        }else if (dy == 0){
            for(int x = left; x <= bottom; ++x){
                points.add(new Point(x, top));
            }
        }
        caching.put(currentHash, points);
    }

    @Override
    public Iterator<Point> iterator() {
        return caching.get(currentHash).iterator();
    }

    @Override
    public void forEach(Consumer<? super Point> action) {
        caching.get(currentHash).forEach(action);
    }

    @Override
    public Spliterator<Point> spliterator() {
        return caching.get(currentHash).spliterator();
    }
}

public class AdapterDemo {
    private final static List<VectorObject> vectorObject
            = new ArrayList<>(Arrays.asList(new VectorRectangle(1,1,10,10),
                                            new VectorRectangle(3,3,6,6)));

    //let's suppose that this is the only API we have for drawing
    //the issue we have here is that we don't have a way to convvert the vector forms like vector rectangles into points
    //to solve this issue we can use an adapter, our approach is to take a Line and convert it to Points
    public static void drawPoint(Point p) {
        System.out.println(".");
    }

    private static void draw(){
        for (VectorObject vector : vectorObject){
            for(Line line: vector){
                LineToPointAdapter adapter = new LineToPointAdapter(line);
                adapter.forEach(AdapterDemo::drawPoint);
            }
        }
    }
    public static void main(String[] args) {
        draw();
        draw();

    }
}

/*
basiclly we have a class that we want to access it subtypes
in this case it would be VectorObject containing Lines which contain Points
In our case we want to access Points inside Lines by creating another class that extends ArrayList of Points
these

We are trying to adapt Line to Points
 */