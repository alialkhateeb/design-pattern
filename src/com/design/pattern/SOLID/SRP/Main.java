package com.design.pattern.SOLID.SRP;

public class Main {

    public static void main(String[] args) {
        Journal j = new Journal();
        j.addEntry("qwe1");
        j.addEntry("qwe2");
        j.addEntry("qwe3");
        j.addEntry("qwe4");
        j.addEntry("qwe5");
        System.out.println(j);

    }
}
