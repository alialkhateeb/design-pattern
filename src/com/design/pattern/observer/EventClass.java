package com.design.pattern.observer;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

/*
  An event is going to encapsulate this idea of something happening and you're going to be able to subscribe to this event
  to get notifications.
  You're also  going to be able to unsubscribe and the unsubscribe is going to be really neat.

  Final Note: Basically event is a container of a subscriptions, and whenever you need to fire an event you just go through every single
  subscription and you fire on it and let the handlers actually receive the argument and do something with it.
 */
class Event<TArgs>{ //<TArgs> that's representing the actual data being fired as the event gets fired.
    private int count = 0;
    //the fact that we are using consumer means that I want the users to be able to effectively provide lambda functions
    // in order to subscribe to this particular event
    private Map<Integer, Consumer<TArgs>> handlers = new HashMap<>();

    public Subscription addHandler(Consumer<TArgs> handler){
        int i = this.count;
        handlers.put(this.count++, handler);
        return new Subscription(this, i);
    }

    //take each of the consumer in the map and firing with the appropriate arguments
    public void fire(TArgs args){
        for (Consumer<TArgs> handler:handlers.values()) {
            handler.accept(args);

        }
    }

    public class Subscription implements AutoCloseable{
        //this is so that we can put it in a try resource approach and make it available for limited time to avoid memory-leak

        private Event<TArgs> event;
        private int id; // we will use this id to put it in the map in event class and use it as key

        public Subscription() {
            System.out.println("create new subscription");
        }

        public Subscription(Event<TArgs> event, int id) {
            this.event = event;
            this.id = id;
        }

        @Override
        public void close(){
            event.handlers.remove(id);
        }
    }

}

class PropertyChangedEventArgs2<T> {
    public T source; //reference to the source, the object whose property has changed
    public String properyName; //name of the property being changed
    public Object newValue; //the new value of the property after the change has occurred

    public PropertyChangedEventArgs2(T source, String properyName, Object newValue) {
        this.source = source;
        this.properyName = properyName;
        this.newValue = newValue;
    }
}
class Person2 {
    //there's an event here on person that can be triggered and if you want to notification from this event you can
    //subscribe to it.
    public Event<PropertyChangedEventArgs2> propertyChaned = new Event<>();

    private int age;

    public Person2() {

        System.out.println("create new  person");
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        if (this.age == age) return;
        this.age = age;
        propertyChaned.fire(new PropertyChangedEventArgs2(this, "age", age));
    }
}
public class EventClass {
    public static void main(String[] args) {
        Person2 person = new Person2();
        Event<PropertyChangedEventArgs2>.Subscription subscription = person.propertyChaned.addHandler(x -> {
            System.out.println("person " + x.properyName + " has changed with the value " + x.newValue + " and the source " + x.source);
        }); // the inside part of addHandler will be the value stored in Hashmap and it will be the event triggered
        // after storing the event, if we make a change to the object itself, it will fire the event and print the message
        // it mean that event can be dynamic at this point
        person.setAge(10);
        person.setAge(20);
        subscription.close();
        person.setAge(30);
    }
}
