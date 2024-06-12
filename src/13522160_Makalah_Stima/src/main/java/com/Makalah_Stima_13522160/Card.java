package com.Makalah_Stima_13522160;

import com.Makalah_Stima_13522160.DiceModule.Dice;

import java.util.List;
import java.util.ArrayList;
import java.util.Objects;

public class Card {
    private final String type;
    private final String name;
    private final int cost; // This can change but under specific condition
    List<Dice> dice = new ArrayList<>();

    public Card(String name, int cost, String type) {
        this.type = type;
        this.name = name;
        this.cost = cost;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public int getCost() {
        return cost;
    }

    public List<Dice> getDice() {
        return dice;
    }

    public void addDice(Dice d) {
        dice.add(d);
    }

    public double calculateWinrateSumAgainst(Card other) {
        if (other == null) {return 0.0;}
        double winrateSum = 0.0;
        List<Dice> otherDice = other.getDice();

        int i = 0;

        if (Objects.equals(this.type, "Melee") && Objects.equals(other.getType(), "Ranged")) {
            while (i < otherDice.size()) {
                int j = i % dice.size();
                winrateSum += dice.get(j).calculateWinrate(otherDice.get(i));
                i++;
            }
        } else if (Objects.equals(this.type, "Ranged") && Objects.equals(other.getType(), "Melee")) {
            while (i < dice.size()) {
                int j = i % otherDice.size();
                winrateSum += dice.get(i).calculateWinrate(otherDice.get(j));
                i++;
            }
        } else {
            while (i < dice.size() && i < otherDice.size()) {
                winrateSum += dice.get(i).calculateWinrate(otherDice.get(i));
                i++;
            }
        }
        return winrateSum;
    }

    @Override
    public String toString() {
        StringBuilder s_builder = new StringBuilder();
        s_builder.append(this.name);
    //.append("[");

//        for (Dice d : dice) {
//            s_builder.append(d.getType()).append("(").append(d.getMinVal()).append("-").append(d.getMaxVal()).append(") ");
//        }
//
//        s_builder.append("]");

        return s_builder.toString();
    }
}
