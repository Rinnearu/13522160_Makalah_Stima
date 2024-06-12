package com.Makalah_Stima_13522160.DiceModule;

public class OffensiveDice extends BaseDice{
    String type;

    public OffensiveDice(int minVal, int maxVal, String type) {
        super(minVal, maxVal);
        this.type = type;
    }

    @Override
    public String getType() {
        return type;
    }
}
