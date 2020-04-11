package com.design.pattern.singleton;

import java.io.*;

class BasicSingleton implements Serializable {

    private BasicSingleton(){

    }

    private static final BasicSingleton INSTANCE = new BasicSingleton();

    public static BasicSingleton getINSTANCE() {
        return INSTANCE;
    }

    private int value = 0;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    protected Object readResolve(){
        return INSTANCE;
    }
}


class Demo{

    static void saveToFile(BasicSingleton singleton, String filename) throws Exception{
        try(FileOutputStream fileOut = new FileOutputStream(filename);
            ObjectOutputStream out = new ObjectOutputStream(fileOut)){

            out.writeObject(singleton);
        }
    }

    static BasicSingleton readFromFile(String filename) throws Exception{
        try(FileInputStream fileIn = new FileInputStream(filename);
            ObjectInputStream in = new ObjectInputStream(fileIn)){
            return (BasicSingleton) in.readObject();
        }
    }
    public static void main(String[] args) throws Exception {

        //what is the problem with this approach?
        //1. reflection: when we use reflection, we can pass the private constructor and call it directly which violate this concept
        //2. serialization: when we deserlize the object, JVM doesn't care if the constructor is private

//        BasicSingleton basic = BasicSingleton.getINSTANCE();
//        basic.setValue(123);
//        System.out.println("value = "+basic.getValue());

        //proof why this approach can be violated

        BasicSingleton singleton = BasicSingleton.getINSTANCE();
        singleton.setValue(111);


        //create file
        String filename = "singleton.bin";
        saveToFile(singleton, filename);

        singleton.setValue(222);
        BasicSingleton singleton2 = readFromFile(filename);

        System.out.println(singleton == singleton2);
        System.out.println(singleton.getValue());
        System.out.println(singleton2.getValue());
    }
}