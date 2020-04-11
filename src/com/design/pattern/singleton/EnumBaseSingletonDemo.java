package com.design.pattern.singleton;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * enum does not have the problem of relfection, calling a private constructor with reflection,
 * enum are serializable by default, but it will not let you preserve the sate of the singleton
 * Also you cannot inherit from this class b/c it's an enum
 * Note: you can use this approach b/c you don't need any state to be persisted
 */
enum EnumBasedSingleton{
    INSTANCE;

    /* enum already has a private default constructor, and if you don't add a constructor here it's not going to be a problem
    * People are not going to be able to make new instances often in any way.
    *
    * The problem with this approach is that the the variable 'value' is not going to be serializable
    * The only thing that is serialized is the name of the instance
    *
    * Issues:
    * - you cannot inherit from enum
    * - the fields in the enum are not serialized
    */

    EnumBasedSingleton(){
        System.out.println("constructor");
        value = 42;
    }
    private int value;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}

public class EnumBaseSingletonDemo {

    static void saveToFile(EnumBasedSingleton singleton, String filename) throws Exception{
        try (FileOutputStream fos = new FileOutputStream(filename);
             ObjectOutputStream ois = new ObjectOutputStream(fos)){
            ois.writeObject(singleton);
        }
    }

    static EnumBasedSingleton readFromFile(String filename)  throws Exception{
        try (FileInputStream fis = new FileInputStream(filename);
             ObjectInputStream ois = new ObjectInputStream(fis)){
            return (EnumBasedSingleton) ois.readObject();
        }
    }

    public static void main(String[] args) throws Exception {
        String filename = "file.bin";
//        EnumBasedSingleton singleton = EnumBasedSingleton.INSTANCE;
//        singleton.setValue(10);
//        System.out.println(singleton);
//        saveToFile(singleton, filename);

        EnumBasedSingleton singleton2 = readFromFile(filename);
        System.out.println(singleton2);

    }
}
