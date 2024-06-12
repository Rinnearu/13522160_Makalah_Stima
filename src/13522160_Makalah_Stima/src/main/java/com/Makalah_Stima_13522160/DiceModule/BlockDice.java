package com.Makalah_Stima_13522160.DiceModule;

public class BlockDice extends BaseDice{
    public BlockDice(int minVal, int maxVal) {
        super(minVal, maxVal);
    }

    @Override
    public String getType() {
        return "Block";
    }
}
