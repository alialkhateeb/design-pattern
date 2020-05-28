package com.design.pattern.chainOfResponsibility;

/*
 * We are now going to implement the simplest example of COR and it's called method chain, and it basically setting up
 * methods so that one method calls entire chain of methods
 */


class Creature{
    public String name;
    public int attack, defense;

    public Creature(String name, int attack, int defense) {
        this.name = name;
        this.attack = attack;
        this.defense = defense;
    }

    @Override
    public String toString() {
        return "Creature{" +
                "name='" + name + '\'' +
                ", attack=" + attack +
                ", defense=" + defense +
                '}';
    }
}

class CreatureModifier{

    protected Creature creature;
    protected CreatureModifier  next;

    public CreatureModifier(Creature creature) {
        this.creature = creature;
    }

    /*
     * we are going to add some sort of functionality for actually adding an additional creature modifier towards the end
     * of the chain.
     */

    public void add(CreatureModifier endchain){
        if (next != null){
            next.add(endchain);
        }else{
            this.next = endchain;
        }
    }

    /*
     * How do you apply all of these modifications one after another in this big chain?
     * notice that this is a base class, we're not performing any modification on the creature ourselves
     * However, once again we're following the entire chain and we're calling hand through on the entire chain
     *
     * The idea of this setup is for me to inherit from Creature modifier and provide actual functionality in the handle
     * method and that's exactly what're going to do.
     */
    public void handled(){
        if (next != null) next.handled();
    }
}

class DoubleAttackModifier extends CreatureModifier{

    public DoubleAttackModifier(Creature creature) {
        super(creature);
    }

    @Override
    public void handled() {
        System.out.println("Doubling " + creature.name + "'s attack");
        creature.attack *= 2;
        super.handled();
    }
}

class IncreaseDefenseModifier  extends CreatureModifier{

    public IncreaseDefenseModifier(Creature creature) {
        super(creature);
    }

    @Override
    public void handled() {
        System.out.println("Increase " + creature.name + "'s defense");
        creature.defense += 3;
        super.handled();
    }
}

class NoBounceCurs extends CreatureModifier{

    public NoBounceCurs(Creature creature) {
        super(creature);
    }

    @Override
    public void handled() {
        System.out.println("You got cursed! No Bounce");
    }
}
public class ChainDemo {

    public static void main(String[] args) {
        Creature goblin = new Creature("Goblin", 2, 2);

        System.out.println(goblin);

        CreatureModifier root = new CreatureModifier(goblin);

//        System.out.println("you got coursed!!");
//        root.add(new NoBounceCurs(goblin));


        System.out.println("double goblin attack..");
        root.add(new DoubleAttackModifier(goblin));
        System.out.println("increase goblin defense..");
        root.add(new IncreaseDefenseModifier(goblin));

        //will handle traversing through the entire chain to reverse every single modifier and apply it.
        root.handled();
        System.out.println(goblin);

    }
}
