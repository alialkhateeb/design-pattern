package com.design.pattern.state;

//this approach does exist but it's not implemented in the industry

//this example will be about a light being switched on/off
class Statesss {
    // this class will give us API for turning on/off of the light

    void on(LightSwitch ls){
        System.out.println("light is already on");
    }

    void off(LightSwitch ls){
        System.out.println("light is already off");

    }
}

/*
classic approach of Gang of Four  is that every single state is a class. When you have a light on you have an on state
which is a class, and similarly for the off state
 */
class OnStatesss extends Statesss {
    public OnStatesss() {
        System.out.println("Light turned on");
    }

    @Override
    void off(LightSwitch ls) {
        System.out.println("switch light off..");
        ls.setState(new OffStatesss());
    }
}

class OffStatesss extends Statesss {
    public OffStatesss() {
        System.out.println("Light turned off");
    }

    void on(LightSwitch ls){
        System.out.println("switch light on..");
        ls.setState(new OnStatesss());
    }
}

class LightSwitch{
    private Statesss state; // this state can be OnState/OffState

    public LightSwitch() {
        state = new OffStatesss();
    }

    public Statesss getState() {
        return state;
    }

    public void setState(Statesss state) {
        this.state = state;
    }

    void on(){
        state.on(this); }
    void off() {
        state.off(this);}
}

public class ClassicImplementation {

    public static void main(String[] args) {
        LightSwitch lightSwitch = new LightSwitch();
        System.out.println(lightSwitch.getState().getClass());
        lightSwitch.on();
        System.out.println(lightSwitch.getState().getClass());
        lightSwitch.off();
        System.out.println(lightSwitch.getState().getClass());
        lightSwitch.off();
        System.out.println(lightSwitch.getState().getClass());
    }
}
