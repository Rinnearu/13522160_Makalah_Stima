package com.Makalah_Stima_13522160;

import com.Makalah_Stima_13522160.DiceModule.Dice;

import java.util.List;
import java.util.ArrayList;

public class Card {
    List<Dice> dice = new ArrayList<Dice>();

    public List<Dice> getDice() {
        return dice;
    }

    public void add(Dice d) {
        dice.add(d);
    }
}
