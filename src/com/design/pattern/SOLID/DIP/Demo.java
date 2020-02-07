package com.design.pattern.SOLID.DIP;

//DIP
// A. High-level modules should not depend on low-level modules.
// Both should depend on abstractions.

//B. Abstractions should not depend on details
// Details should depend on abstractions.
// this part allow us to  work with interface and make it easy to change implemenation b/c we are working with signatures

//this part demonstrate part A

import org.javatuples.Triplet;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

enum Relationship {
    PARENT,
    CHILD,
    SIBLING
}

//class represent person
class Person{
    public String name;

    public Person(String name) {
        this.name = name;
    }
}
//to solve this issue we need to provide the abstraction (interface)
interface RelationshipsBrowser{
    List<Person> findAllChildrenOf(String name);
}


//to module the relationship btw people we can create another class
class Relationships implements RelationshipsBrowser
//this is a low-level module - bc it's related to data storage, it provides a list or keeps a list gives us some sort of access to that list so it's
//it's a low level implementation that doesn't have any business logic as you would say, and it simply allow you to manipulate the list and that is  it's a Single Responsibility
// here we have encounter the single responsibility principle which is the relationship class, it's to allow manipulation of the list that we have here
{
    private List<Triplet<Person, Relationship, Person>> relations = new ArrayList<>();

    public List<Triplet<Person, Relationship, Person>> getRelations() {
        return relations;
    }

    public void addParentAndChild(Person parent, Person child){
        relations.add(new Triplet<>(parent, Relationship.PARENT, child));
        relations.add(new Triplet<>(child, Relationship.CHILD, parent));
    }

    @Override
    public List<Person> findAllChildrenOf(String name) {

        return relations.stream().filter(x -> Objects.equals(x.getValue0().name, name) && x.getValue1() == Relationship.PARENT).map(Triplet::getValue2).collect(Collectors.toList());
    }
}

class Research // high-level module - it allows us to perform some sort operations on those low level constructs
    //and so it's a kind of higher to the end-user
    // end-user doesn't care about the storage implementation, they care about the research, they want the result. Who is John? Where is John children
    // the rule state that high-level module should not depend on low-level, but here we have broken the rule!
{
//    public Research (Relationships relationships){
//        // we want to be able to find information about the people itself, so we will use a varable using the getter and perform the condition on it
//        List<Triplet<Person, Relationship, Person>> relations = relationships.getRelations();
//
//        relations.stream().filter(x -> x.getValue0().name.equals("John") && x.getValue1() == Relationship.PARENT)
//        .forEach(ch -> System.out.println("John has a child called " + ch.getValue2().name));
//    }

    public Research(RelationshipsBrowser relationships){
        List<Person> children = relationships.findAllChildrenOf("John");
        for (Person temp: children) {
            System.out.println("John have a child named " + temp.name);
        }
    }
}
// in the implmentation up ^ there is a major issue which is exposing the storage for our data to be used by the getter itself,

//****************************
//main
//**************************
class Demo {
    public static void main(String[] args) {
        Person parent = new Person("John");
        Person child = new Person("Chris");
        Person child2 = new Person("Max");

        Relationships relationships = new Relationships();
        relationships.addParentAndChild(parent, child);
        relationships.addParentAndChild(parent, child2);

        new Research(relationships);


    }
}