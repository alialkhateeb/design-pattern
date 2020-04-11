package com.design.pattern.singleton;

import java.util.function.Supplier;

public class ExerciseSingleton {

    public static boolean isSingleton(Supplier<Object> func)
    {
        // todo

        return func.get() == func.get();
    }

    public static void main(String[] args) {
        BasicSingleton singleton = BasicSingleton.getINSTANCE();
        Object obj = new Object();
        Supplier<Object> f = () -> new Object();


        System.out.println(isSingleton(Object::new));
    }
}
