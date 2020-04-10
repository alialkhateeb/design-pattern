package com.design.pattern.Factories;


import org.javatuples.Pair;

import java.util.ArrayList;
import java.util.List;

//we can use this pattern if we have hierarchyt of types
interface HotDrink{
    void consume();
}

class Tea implements HotDrink{

    @Override
    public void consume() {

        System.out.println("This tea is good!");
    }
}

class Coffee implements HotDrink{

    @Override
    public void consume() {
        System.out.println("This coffee is delicious!");
    }
}

interface HotDrinkFactory{
    HotDrink prepare(int amount);
}

class TeaFactory implements HotDrinkFactory{

    @Override
    public HotDrink prepare(int amount) {
        System.out.println("prepare tea + amount =" + amount);
        return new Tea();
    }
}

class CoffeeFactory implements HotDrinkFactory{

    @Override
    public HotDrink prepare(int amount) {
        System.out.println("prepare coffee + amount = " + amount);
        return new Coffee();
    }
}

class HotDrinkMachine{
    private List<Pair<String, HotDrinkFactory>> namedFactories = new ArrayList<>();

    public HotDrinkMachine(){

    }
}