package com.design.pattern.nullObject;

interface Log {
    void info(String msg);

    void warning(String msg);
}

class Console implements Log{

    @Override
    public void info(String msg) {
        System.out.println("Info " + msg);
    }

    @Override
    public void warning(String msg) {
        System.out.println("Warning " + msg);
    }
}

class BankAccount {
    private Log log;
    private int balance;

    public BankAccount(Log log) {
        this.log = log;
    }

    public void deposit(int amount) {
        balance += amount;
        log.info("Deposited " + amount);
    }
}

final class NullLog implements Log{

    @Override
    public void info(String msg) {

    }

    @Override
    public void warning(String msg) {

    }
}

public class NullObject {

    public static void main(String[] args) {
        BankAccount bankAccount = new BankAccount(new NullLog());
        bankAccount.deposit(100);
    }


}
