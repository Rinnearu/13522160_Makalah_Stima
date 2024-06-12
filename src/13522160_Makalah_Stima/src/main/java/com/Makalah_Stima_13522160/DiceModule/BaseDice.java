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

    @Override
    public double calculateWinrate(Dice other) {
        int nx = maxVal - minVal + 1;
        int ny = other.getMaxVal() - other.getMinVal() + 1;

        double space = nx * ny;

        double sigma = 0.0;
        for (int x = minVal; x <= maxVal; x++) {
            sigma += Math.max(0, Math.min(other.getMaxVal(), x-1) - other.getMinVal() + 1);
        }

        return sigma / space;
    }
}
