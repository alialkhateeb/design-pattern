package com.design.pattern.singleton;

/*
 * if you currently have exceptions inside of singleton constructor you are in real trouble
 * but if you use a static blog then things are a bit different
 *
 */

import java.io.File;
import java.io.IOException;

class StaticBlockSingleton {
    private StaticBlockSingleton() throws IOException {
        System.out.println("singleton is init");
        File.createTempFile(".", ".");
    }
    private static StaticBlockSingleton instance;

    //this can be treated as a static constructor, static block singleton
    static {
        try{
            instance = new StaticBlockSingleton();
        }catch (Exception e){
            System.err.println("Failed to create singleton");
        }
    }

    public static StaticBlockSingleton getInstance(){
        return instance;
    }
}

class StaticSingletonDemo{
    public static void main(String[] args) {

        StaticBlockSingleton temp = StaticBlockSingleton.getInstance();

        System.out.println(temp);
    }
}