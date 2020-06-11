package com.design.pattern.Iterator;
// keep in mind properties = fields with setters and getters

import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.stream.IntStream;

class SimpleCreature {
    private int strength, agility, intelligence;


    //the issue about this approach is that we don't have flexibility in case we added new field.
    public int max() {
        return Math.max(strength, Math.max(agility, intelligence));
    }

    public int sum() {
        return strength + agility + intelligence;
    }

    public double avg() {
        return sum() / 3;
    }


    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getAgility() {
        return agility;
    }

    public void setAgility(int agility) {
        this.agility = agility;
    }

    public int getIntelligence() {
        return intelligence;
    }

    public void setIntelligence(int intelligence) {
        this.intelligence = intelligence;
    }
}

class Creature implements Iterable<Integer> {

    private int[] stats;

    public Creature(int[] stats) {
        this.stats = stats;
    }

    public int getStrength() {
        return stats[0];
    }

    public void setStrength(int value) {
        stats[0] = value;
    }

    public int sum(){
        return IntStream.of(stats).sum();
    }
    public int max(){
        return IntStream.of(stats).max().getAsInt();
    }
    public double avg(){
        return IntStream.of(stats).average().getAsDouble();
    }

    @Override
    public Iterator<Integer> iterator() {
        return IntStream.of(stats).iterator();
    }

    @Override
    public void forEach(Consumer<? super Integer> action) {
        for (int temp:stats) {
            action.accept(temp);
        }
    }

    @Override
    public Spliterator<Integer> spliterator() {
        return IntStream.of(stats).spliterator();
    }
}

public class ArrayBackedProp {

    public static void main(String[] args) {

        Creature creature = new Creature(new int[]{12,13,17});
        System.out.println("creature max is = " + creature.max());
        System.out.println("creature avg is = " + creature.avg());
        System.out.println("creature sum is = " + creature.sum());
    }
}
