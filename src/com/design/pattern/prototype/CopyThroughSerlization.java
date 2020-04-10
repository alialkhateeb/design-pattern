package com.design.pattern.prototype;

import org.apache.commons.lang3.SerializationUtils;

import java.io.Serializable;

public class CopyThroughSerlization {

    public static void main(String[] args) {
        Foo foo1 = new Foo(11, "as");
        Foo foo2 = SerializationUtils.roundtrip(foo1);

        foo2.stuff = 22;
        foo2.whatever = "whatever";
        System.out.println(foo1);
        System.out.println(foo2);
    }

}


class Foo implements Serializable {
    public int stuff;
    public String whatever;

    public Foo(int stuff, String whatever) {
        this.stuff = stuff;
        this.whatever = whatever;
    }

    @Override
    public String toString() {
        return "Foo{" +
                "stuff=" + stuff +
                ", whatever='" + whatever + '\'' +
                '}';
    }
}