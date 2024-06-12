package com.Makalah_Stima_13522160.DiceModule;

public interface Dice {
    int getMinVal();
    int getMaxVal();
    String getType();
    double calculateWinrate(Dice other);
}
