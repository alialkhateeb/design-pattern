package com.design.pattern.prototype;

import java.util.Arrays;

class Address implements Cloneable{
    public String streetName;
    public int houseNumber;

    public Address(String streetName, int houseNubmer){
        this.streetName = streetName;
        this.houseNumber = houseNubmer;
    }

    @Override
    public String toString() {
        return "Address{" +
                "streetName='" + streetName + '\'' +
                ", houseNumber=" + houseNumber +
                '}';
    }

    //this approach is deep copy
    @Override
    public Object clone() throws CloneNotSupportedException {
        return new Address(streetName, houseNumber);
    }
}

class Person implements Cloneable{
    public String[] names;
    public Address address;

    public Person(String[] names, Address address) {
        this.names = names;
        this.address = address;
    }

    @Override
    public String toString() {
        return "Person{" +
                "names=" + Arrays.toString(names) +
                ", address=" + address +
                '}';
    }

    //deep copy
    @Override
    public Object clone() throws CloneNotSupportedException {
        return new Person(names, address);
    }
}

public class Prototype {

    public static void main(String[] args) throws Exception {
        Person james = new Person(new String[]{"John", "Smith"}, new Address("Abi Said", 2688));

        System.out.println("1. "+ james);
        //The issue with this approach is that jane will be pointing to james, any change to jane will reflect the change on james as well
        //This approach will be a shallow copy, and it will make those two variables pointing to the same object
//        Person jane = james;
//        jane.names[0] = "jane";
//        jane.address.houseNumber = 2689;

        //alternative approach would be copying the object by using
        Person jane = (Person) james.clone();
        jane.names[0] = "jane";
        jane.address.houseNumber=124;

        System.out.println("2" + james);
        System.out.println("3" + jane);
    }
}
