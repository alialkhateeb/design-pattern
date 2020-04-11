package com.design.pattern.singleton;

// keep in mind that the goal of singleton is one, and it wants you to have a single instance of a particular object
// while a multition tries to do is that there is a finit set of instances and for every single instance you can also implement
// additional benefits such as lazy loading for ex
// what we really want out of it is to have number of restrict instances that are created
// we can associated the instance with some enumeration for type differences

/*
 * it's not as strong pattern as singleton, it doesn't enforce things so strongly but it's still forcing lifetime effectively
 *  It's enforcing how many instances are created
 *
 */

import java.util.HashMap;

enum SubSystem{
    PRIMARY,
    AUXILIARY,
    FALLBACK
}

class Printer {

    private static int instanceCount = 0;
    private Printer(){
        instanceCount++;
        System.out.println("number of instances = " + instanceCount);
    }

    private static HashMap<SubSystem, Printer> instances = new HashMap<>();

    //this approach is lazy loading which it will create an instance whenever it's requested
    public static Printer get(SubSystem ss){
        if (instances.containsKey(ss)){
            return instances.get(ss);
        }else{
            Printer print = new Printer();
            instances.put(ss, print);
            return print;
        }
    }

    public int numOfInstances(){
        return instanceCount;
    }
}
public class Multition {

    public static void main(String[] args) {

        Printer main = Printer.get(SubSystem.PRIMARY);
        Printer aux = Printer.get(SubSystem.AUXILIARY);
        Printer fallback = Printer.get(SubSystem.FALLBACK);

        Printer main2 = Printer.get(SubSystem.PRIMARY);
        System.out.println(main2.numOfInstances());
    }
}
