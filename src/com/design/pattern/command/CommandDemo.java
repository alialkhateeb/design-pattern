package com.design.pattern.command;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class BankAccount {
    private int balance;
    private int overdraftLimit = -1000;

    public void deposit(int amount) {
        balance += amount;
        System.out.println("amount deposit = " + amount + ", and the total balance = " + balance);
    }

    public boolean withdraw(int amount) {
        if (balance - amount >= overdraftLimit) {
            balance -= amount;
            System.out.println("balance = " + balance + ", and amount withdraw = " + amount);
            return true;
        }else{
            return false;
        }
    }

    @Override
    public String toString() {
        return "BankAccount{" +
                "balance=" + balance +
                '}';
    }
}

interface Command {
    void execute();
    void reverse();
}

class BankAccountCommand implements Command {

    private BankAccount account;
    private Action action;
    private int amount;
    private boolean executionCond;

    public enum Action {
        DEPOSIT, WITHDRAW
    }

    public BankAccountCommand(BankAccount account, Action action, int amount) {
        this.account = account;
        this.action = action;
        this.amount = amount;
    }

    @Override
    public void execute() {
        switch (action) {
            case DEPOSIT:
                executionCond = true;
                account.deposit(amount);
                break;
            case WITHDRAW:
                executionCond = account.withdraw(amount);
                break;
        }
    }

    @Override
    public void reverse() {
        if (executionCond) {
            switch (action) {
                case DEPOSIT:
                    account.withdraw(amount);
                    break;
                case WITHDRAW:
                    account.deposit(amount);
                    break;
            }
        }
    }
}

public class CommandDemo {

    public static void main(String[] args) {
        BankAccount b = new BankAccount();
        System.out.println(b);

        List<Command> cmd = new ArrayList<>();

        cmd.add(new BankAccountCommand(b, BankAccountCommand.Action.DEPOSIT, 10));
        cmd.add(new BankAccountCommand(b, BankAccountCommand.Action.WITHDRAW, 2000));

        System.out.println("execute");
        for (Command temp: cmd) {
            temp.execute();
            System.out.println(b);
        }

        Collections.reverse(cmd);

        System.out.println("reverse");

        for (Command temp: cmd) {
            temp.reverse();
            System.out.println(b);
        }
    }
}
