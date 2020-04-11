package com.design.pattern.singleton;


/*
 * this approach is similar approach with thread safety, and it does not require synchronized keyword
 * this approach is called initialization-on-demand, encapsulating classes do not automatically initialize inner class
 * So inner class only gets initialized by getInstance. Then again, class initlization is guaranteed to be sequential in
 * java, so the JVM implicitly renders it thread-safe
 *
 * Java guarantees that a class is only loaded when it's used the first time. So the inner class won't be loaded twice,
 * as a result, the class will not construct new objects twice.
 */
class InnerStaticSingleton {

    public int x = 1;
    private InnerStaticSingleton(){

        System.out.println("innner static singleton constructor");
    }

    private static class Impl {

        private static final InnerStaticSingleton INSTANCE = new InnerStaticSingleton();
    }

    //we use this method to expose the instance
    public static InnerStaticSingleton getInstance(){
        return Impl.INSTANCE;
    }
}

//the chain calls in this situation would be as follow
// getInstance() -> Impl [to create new object of InnerStaticSingleton] -> call the constructor of InnerStaticSingleton
class DemoClass {
    public static void main(String[] args) {
        InnerStaticSingleton test = InnerStaticSingleton.getInstance();

        System.out.println(test);
        InnerStaticSingleton test2 = InnerStaticSingleton.getInstance();
        test2.x=2;
        System.out.println(test2);


    }
}
