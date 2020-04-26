package com.design.pattern.observer;

import java.util.ArrayList;
import java.util.List;

class PropertyChangedEventArgs<T> {
    public T source; //reference to the source, the object whose property has changed
    public String properyName; //name of the property being changed
    public Object newValue; //the new value of the property after the change has occurred

    public PropertyChangedEventArgs(T source, String properyName, Object newValue) {
        this.source = source;
        this.properyName = properyName;
        this.newValue = newValue;
    }
}

//observer is the interface that you'd expect to implement to be implemented by anyone interested in
// observing an object of type T

interface Observer<T> {
    void handle(PropertyChangedEventArgs<T> args);
}

class Observable<T> {
    //this will contain a list of all the subscribers that are watching a particular class
    private List<Observer<T>> observers = new ArrayList<>();

    //then we are going to have an API to this observable so as to monitor changes
    public void subscribe(Observer<T> observer) {
        observers.add(observer);
    }

    // in addition, we want to notify on the changes so whoever inherits from observable.
    // If it needs to fire some method to actually notify every observver that something has happened
    protected void propertyChange(T source,
                                  String propertyName,
                                  Object newValue) {
        for (Observer<T> observer : observers) {
            observer.handle(new PropertyChangedEventArgs<T>(source, propertyName, newValue));

        }

    }
}


class Person extends Observable<Person>{
    private int age; //both the field and getter&setter are refereed to as property

    public int getAge() {
        return age;
    }

    //we need a change notification whenever someone change the age
    public void setAge(int age) {
        if (this.age == age) return;
        this.age = age;
        propertyChange(this, "age", age);
    }
}

// the object that's going to be looking at person and monitoring the changes that's going to be this class
public class ObserverObservable implements Observer<Person>{

    public static void main(String[] args) {
        new ObserverObservable();
    }

    public ObserverObservable() {
        Person person = new Person();
        person.subscribe(this);
        for (int i= 10; i < 14; i++){
            person.setAge(i);
        }
    }

    @Override
    public void handle(PropertyChangedEventArgs<Person> args) {
        System.out.println("Person " + args.properyName + " has changed to " + args.newValue);
    }
}
/*
 * The issue with this approach is that person extending observable of Person which mean person cannot have a proper base class.
 */