package com.design.pattern.Builder;

import java.util.ArrayList;
import java.util.Collections;


class HtmlElement {
    public String name, text;
    //name: the name of the tag itself (e.g. li, p)
    //text: the value btw them provided

    public ArrayList<HtmlElement> elements = new ArrayList<>();
    //recurssive approach,  this allow us to have infinite elements depth

    private final int indentSize = 2; // two characters by default
    // the size of the indentation, to have a nice indentation by having the size indenting of how many in depth

    private final String newLine = System.lineSeparator();

    public HtmlElement() {

    }


    public HtmlElement(String name, String text) {
        this.name = name;
        this.text = text;
    }

    //now we have to implement a recurrsive way to solve the amount of elements we have in the arraylist and print them out.

    //this method will take the level of depth for the element itself
    //traverse throught the tree and print it nicely
    private String toStringImpl(int indent) {
        StringBuilder str = new StringBuilder(); //new string builder to limit the scope
        String i = String.join("", Collections.nCopies(indent * indentSize, " ")); //build the indentation correct
        str.append(String.format("%s<%s>%s", i, name, newLine));
        if (text != null && !text.isEmpty()) {
            str.append(String.join("", Collections.nCopies(indentSize * (indent + 1), " ")))
                    .append(text)
                    .append(newLine);
        }
        for (HtmlElement e : elements) {
            str.append(e.toStringImpl(indent + 1));
        }
        str.append(String.format("%s</%s>%s", i, name, newLine));
        return str.toString();
    }

    @Override
    public String toString() {
        return toStringImpl(0);
    }
}

//the idea of this class is that it has some root element which contain everything
class HtmlBuilder {

    private String rootName; // the root name would be the tag root like <p> <ul>
    private HtmlElement root = new HtmlElement();

    public HtmlBuilder(String rootName) {
        this.rootName = rootName;
        this.root.name = rootName;
    }

    //add childern to the root, we will not allow to add children of children bc that would require more changes to the builder
    public void addChild(String childName, String childText) {
        HtmlElement ele = new HtmlElement(childName, childText);
        root.elements.add(ele);
    }


    public HtmlBuilder addChildFluent(String childName, String childText) {
        HtmlElement e = new HtmlElement(childName, childText);
        root.elements.add(e);
        return this;
    }

    public void clear() {
        root = new HtmlElement();
        this.root.name = rootName;
    }

    // we need to generate the actual object from the builder bc a builder is like construction element it has to at some point have a method which returns the final object


    @Override
    public String toString() {
        return root.toString();
    }
}

//    @Override
//    public String toString() {
//        return toStringImpl(0);
//    }


public class Demo {

    /*
    in order for us to make more robust for html tags that allow us to use them as a builder and make them more general but do a lot more.
     */


//    public static void main(String[] args) {
//        //the issue implementation, we cannot extend it to handle multiple variables (not concenient) which lead to issues later on
//        String hello = "Hello";
//        System.out.println("<p>" + hello + "</p>");
//
//        System.out.println("**********************************");
//
//        //a better approch using Builder pattern in java using jdk
//        String [] words = {"Hello", "world"};
//        StringBuilder str = new StringBuilder();
//        str.append("<ul>\n");
//
//        for (String word:words) {
//            str.append(String.format("       <li>%s</li>\n", word));
//        }
//        str.append("</ul>\n");
//        System.out.println(str);
//    }

    public static void main(String[] args) {
//        HtmlBuilder builder = new HtmlBuilder("ul");
//        builder.addChild("li", "hello");
//        builder.addChild("li", "world");
//        System.out.println(builder);

        // we want to build a simple HTML paragraph
        System.out.println("Testing");
//        String hello = "hello";
//        StringBuilder sb = new StringBuilder();
//        sb.append("<p>")
//                .append(hello)
//                .append("</p>"); // a builder!
//        System.out.println(sb);
//
//        // now we want to build a list with 2 words
//        String [] words = {"hello", "world"};
//        sb.setLength(0); // clear it
//        sb.append("<ul>\n");
//        for (String word: words)
//        {
//            // indentation management, line breaks and other evils
//            sb.append(String.format("  <li>%s</li>\n", word));
//        }
//        sb.append("</ul>");
//        System.out.println(sb);

        // ordinary non-fluent builder
        HtmlBuilder builder = new HtmlBuilder("ul");
        builder.addChild("li", "hello");
        builder.addChild("li", "world");
        System.out.println(builder);

        // fluent builder
        builder.clear();
        builder.addChildFluent("li", "hello")
                .addChildFluent("li", "world");
        System.out.println(builder);
    }
}
