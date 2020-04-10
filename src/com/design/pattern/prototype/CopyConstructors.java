package com.design.pattern.prototype;

/**
 * this approach for deep copy is consider to be better than using clonable approach which was demoed in Prototype
 * keep in mind that this approach relies on creating a constructor that copies the object itself and move it to another one
 */

public class CopyConstructors {

    public static void main(String[] args) {
        Employee max = new Employee("max", new AddressSecond("123 main st", "miami", "usa"));

        Employee chris = new Employee(max);

        chris.name = "chris";
        chris.address.streetAddress = "111 main st";
        System.out.println(max);
        System.out.println(chris);
    }
}


class AddressSecond {
    public String streetAddress, city, country;

    public AddressSecond(String streetAddress, String city, String country) {
        this.streetAddress = streetAddress;
        this.city = city;
        this.country = country;
    }

    //copy constructor
    /* a copy constructor is a constructor which takes another copy of this particular object so in our case that's address
    * it takes this as an argument and then tries to copy each one of its fields
    */

    public AddressSecond(AddressSecond other) {
        this(other.streetAddress, other.city, other.country);
    }
    @Override
    public String toString() {
        return "AddressSecond{" +
                "streetAddress='" + streetAddress + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}


class Employee{
    public String name;
    public AddressSecond address;

    public Employee(String name, AddressSecond address) {
        this.name = name;
        this.address = address;
    }

    public Employee(Employee other) {

        this(other.name, new AddressSecond(other.address));
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", address=" + address +
                '}';
    }
}