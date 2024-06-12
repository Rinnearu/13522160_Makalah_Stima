package com.Makalah_Stima_13522160.DiceModule;

public class EvadeDice extends BaseDice{
    public EvadeDice(int minVal, int maxVal) {
        super(minVal, maxVal);
    }

    @Override
    public String getType() {
        return "Evade";
    }
}
