package com.design.pattern.chainOfResponsibility;

/*
 * One of the limitation of previous example is that you have to explicitly apply all the modification to a creature.
 * Why isn't possible to simply track the presence of an object in the system and apply  the modifier only while
 * the modifier is actually there.
 * In this example we will use COR + observer + mediator
 *
 */

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

/*
 *
 * This event class will notify us on queries, and the idea we are trying to follow is Command Query Separation (CQS)
 * what we are going to have in the event is the ability to subscribe to an event, unsubscribe, and also fire the event
 * We want to specify who exactly are the consumers of the events.
 */
class Event<Args>{
    private int index = 0; //the key mapped to consumer
    private Map<Integer, Consumer<Args>> handlers = new HashMap<>();

    public int subscribe(Consumer <Args> handler){
        int i = index;
        this.handlers.put(index++, handler);
        return i;
    }

    public void unsubscribe(int key){
        this.handlers.remove(key);
    }

    public void fire(Args args){
        for (Consumer<Args> temp: handlers.values()){
            temp.accept(args);
        }
    }
    /*
     * If we're just about to modifying a creature why do we have consumers?
     * We want to layer the query operation for creature's attack or defense into an event that gets handled by whatever
     * modifier actually wants to apply itself to a creature.
     */
}

class Query{
    public String creatureName;
    enum Argument{
        ATTACK, DEFENSE
    }
    public Argument argument;
    //the value that the handlers can actually modify to their heart's content, and the final result will be given to consumer.
    public int result;

    public Query(String creatureName, Argument argument, int result) {
        this.creatureName = creatureName;
        this.argument = argument;
        this.result = result;
    }
}

//mediator
class Game {
    //why? we want to central location with where the query event is actually kept

    /*
     * the idea behind this is that we now have a central location where any modifier can subscribe itself to queries
     * on the creature and modify the creatures attack or defense value
     */
    public Event<Query> queries = new Event<>();

}

class Creature2 {
    private Game game;
    public String name;
    public int baseAttack, baseDefense;

    public Creature2(Game game, String name, int baseAttack, int baseDefense) {
        this.game = game;
        this.name = name;
        this.baseAttack = baseAttack;
        this.baseDefense = baseDefense;
    }

    int getAttack(){
        Query query = new Query(name, Query.Argument.ATTACK, baseAttack);
        game.queries.fire(query);
        return query.result;
    }

    int getDefense(){
        Query query = new Query(name, Query.Argument.DEFENSE, baseDefense);
        game.queries.fire(query);
        return query.result;
    }

    @Override
    public String toString() {
        return "Creature2{" +
                "name='" + name + '\'' +
                ", baseAttack=" + getAttack() +
                ", baseDefense=" + getDefense() +
                '}';
    }
}

//we will build modifier
public class BrokerChain {
}
