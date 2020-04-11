package com.design.pattern.singleton;

/*
 * @ Lazy init - the problem with lazy init is that sometimes you only want the singleton to be init whenever some of the
 * calls get instance and you don't want it to be create in a static block/static context. You only want singleton to be
 * made whenever somebody actually needs it
 * @ Thread safety -
 */

class LazySingleton{
    private static LazySingleton instance;

    /* In this process we are waiting for someone to call the instance and then we check whether the instance
    * has been created or not
    */

    /* the problem with this approach is that it would intrduce a race-condition problem b/c we might have multiple threads
    *  calling the same method
    */
//    public static LazySingleton getInstance(){
//        //here where we check whether or not the instance has been created
//        if (instance == null){
//            instance = new LazySingleton();
//        }
//        return instance;
//    }

    /*
     *
     * Alternative approach to make LazySingleton getInstnce synchronized to avoid race-condition when creating
     * instance for the class is to use synchronized keyword in the method to make it thread-safe
     * REMEMBER: we are taking the alternative approach because it might create two instance at the same time which
     * violate the Singleton pattern principle
     */

    public static synchronized LazySingleton getInstance(){
        if (instance == null){
            instance = new LazySingleton();
        }
        return instance;
    }

    public static  LazySingleton getInstanceDoubleCheck(){
        if (instance == null) {
            synchronized (LazySingleton.class){
                instance = new LazySingleton();
            }
        }
        return instance;
    }

    private LazySingleton(){
        System.out.println("init a lazy singleton");
    }
}

public class LazinessAndThreadSafe {
}
