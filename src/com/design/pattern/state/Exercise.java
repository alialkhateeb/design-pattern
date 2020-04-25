package com.design.pattern.state;

class CombinationLock {
    private int[] combination;
    public String status;
    private int index = 0;

    public CombinationLock(int[] combination) {
        this.combination = combination;
        status = "LOCKED";
    }

    public void enterDigit(int digit) {
        // todo: check digit and update status here
        if (status == "LOCKED") {
            status = "";
        }

        if (combination[index] == digit){
            status += digit;
            index++;
        }else{
            status = "ERROR";
        }
        if (combination.length == status.length()){
            status = "OPEN";
        }

    }
}

public class Exercise {
    public static void main(String[] args) {
        CombinationLock combinationLock = new CombinationLock(new int[] {1,2,3,4});
        combinationLock.enterDigit(1);
        combinationLock.enterDigit(2);
        combinationLock.enterDigit(3);
        combinationLock.enterDigit(4);
    }
}
