package com.design.pattern.command;

class Command2
{
    enum Action
    {
        DEPOSIT, WITHDRAW
    }

    public Action action;
    public int amount;
    public boolean success;

    public Command2(Action action, int amount)
    {
        this.action = action;
        this.amount = amount;
    }
}

class Account
{
    public int balance;

    public void process(Command2 c)
    {
        // todo
        switch (c.action){
            case DEPOSIT:
                c.success = true;
                balance += c.amount;
                break;
            case WITHDRAW:
                if (c.amount <= balance){
                    balance -= c.amount;
                    c.success = true;
                }
                break;
        }
    }
}

public class Exercise {

    public static void main(String[] args) {
        Command2 cmd1 = new Command2(Command2.Action.DEPOSIT, 100);
        Command2 cmd2 = new Command2(Command2.Action.WITHDRAW, 100);
        Command2 cmd3 = new Command2(Command2.Action.WITHDRAW, 100);

        Account account = new Account();
        account.process(cmd1);
        account.process(cmd2);
        account.process(cmd3);
    }
}
