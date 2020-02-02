package com.design.pattern.SRP;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


/**
 * SPR concept, a class should have one single responsability and does not break it
 * when you add more more of what it suppose to do logically, it will break the principle
 */
public class Journal {

    private final List<String> entries = new ArrayList<>();

    private static int count = 0;

    public void addEntry(String text){
        entries.add("" + (++count) + ": " +text);
    }

    public void removeEntry(int index){
        entries.remove(index);
    }

    @Override
    public String toString() {
        return String.join(System.lineSeparator(), entries);
    }

    //whatever comes next is about another functionality which break the rule, in order for us to acheive the SPR we can just put these things in another file

//    public void save (String filename) throws FileNotFoundException {
//        try (PrintStream out = new PrintStream(filename)){
//            out.println(toString());
//        }
//    }
//    public void load (String filename){ }
//    public void load (URL url) {}
}

class Persistance {
    public void save (String filename) throws FileNotFoundException {
        try (PrintStream out = new PrintStream(filename)){
            out.println(toString());
        }
    }
    public void load (String filename){ }
    public void load (URL url) {}
}
