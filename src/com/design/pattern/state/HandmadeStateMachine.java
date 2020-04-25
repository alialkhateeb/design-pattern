package com.design.pattern.state;


import org.javatuples.Pair;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

enum StateEnum{
    OFF_HOOK, // starting state, starting making a phone call
    ON_HOOK, // terminal/final state, when the execution should stop
    CONNECTING,
    CONNECTED,
    ON_HOLD
}


/*
 * we transition from one state to another and transitions happen with thanks to triggers
 */
enum Trigger{
    CALL_DIALED,
    HUNG_UP,
    CALL_CONNECTED,
    PLACED_ON_HOLD,
    PLACED_OFF_HOLD,
    LEAVE_MESSAGE,
    STOP_USING_PHONE
}



public class HandmadeStateMachine {
    private static Map<StateEnum, List<Pair<Trigger, StateEnum>>> rules = new HashMap<>();

    static {
        rules.put(StateEnum.OFF_HOOK, List.of(
                new Pair<>(Trigger.CALL_DIALED, StateEnum.CONNECTING),
                new Pair<>(Trigger.STOP_USING_PHONE, StateEnum.ON_HOOK)
        ));
        //
        rules.put(StateEnum.CONNECTING, List.of(
                new Pair<>(Trigger.HUNG_UP, StateEnum.OFF_HOOK),
                new Pair<>(Trigger.CALL_CONNECTED, StateEnum.CONNECTED)
        ));
        rules.put(StateEnum.CONNECTED, List.of(
                new Pair<>(Trigger.LEAVE_MESSAGE, StateEnum.OFF_HOOK),
                new Pair<>(Trigger.HUNG_UP, StateEnum.OFF_HOOK),
                new Pair<>(Trigger.PLACED_ON_HOLD, StateEnum.ON_HOLD)
        ));
        rules.put(StateEnum.ON_HOLD, List.of(
                new Pair<>(Trigger.PLACED_OFF_HOLD, StateEnum.CONNECTED),
                new Pair<>(Trigger.HUNG_UP, StateEnum.OFF_HOOK)
        ));
    }

    private static StateEnum currentState = StateEnum.OFF_HOOK; //we are going to start with this state first
    private static StateEnum exitState = StateEnum.ON_HOOK; // this is where we stop executing the state machine/entire app

    public static void main(String[] args) {
        BufferedReader terminal = new BufferedReader(new InputStreamReader(System.in));

        while (true){
            System.out.println("current state phone " + currentState);
            System.out.println("select trigger ..");
            for (int i = 0; i < rules.get(currentState).size(); ++i){
                Trigger trigger = rules.get(currentState).get(i).getValue0();
                System.out.println(i + ". " + trigger);
            }

            boolean parseOK = false;
            int choice = 0;
            do {
                try {
                    System.out.println("Please enter your choice: ");
                    choice = Integer.parseInt(terminal.readLine());
                    parseOK = choice >= 0 && choice < rules.get(currentState).size();
                } catch (Exception e) {
                    e.printStackTrace();
                    parseOK = false;
                }
            }while (!parseOK);
            currentState = rules.get(currentState).get(choice).getValue1();
            if (currentState == exitState) break;

        }
        System.out.println("done");
    }
}
