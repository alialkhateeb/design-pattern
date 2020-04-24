package com.design.pattern.Decorate;

class Bird {
    public int age;

    public String fly() {
        return age < 10 ? "flying" : "too old";
    }
}

class Lizard {
    public int age;

    public String crawl() {
        return (age > 1) ? "crawling" : "too young";
    }
}

class Dragon {
    private int age;
    private Lizard lizard = new Lizard();
    private Bird bird = new Bird();


    public void setAge(int age) {
        this.age = age;
        bird.age = age;
        lizard.age = age;
    }

    public String fly() {
        // todo
        return bird.fly();
    }

    public String crawl() {
        // todo
        return lizard.crawl();
    }
}

public class DecoratorExercise {

    public static void main(String[] args) {
        Dragon dragon = new Dragon();
        dragon.setAge(5);
        System.out.println(dragon.fly());
        System.out.println(dragon.crawl());
    }
}
