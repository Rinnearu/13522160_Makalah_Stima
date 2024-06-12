package com.Makalah_Stima_13522160.DiceModule;

public abstract class BaseDice implements Dice {
    protected int minVal;
    protected int maxVal;

    public BaseDice(int minVal, int maxVal) {
        this.minVal = minVal;
        this.maxVal = maxVal;
    }

    @Override
    public int getMinVal() {
        return minVal;
    }

    @Override
    public int getMaxVal() {
        return maxVal;
    }
}
