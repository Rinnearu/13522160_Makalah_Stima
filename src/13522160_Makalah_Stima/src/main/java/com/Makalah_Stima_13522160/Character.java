package com.Makalah_Stima_13522160;

import com.Makalah_Stima_13522160.DiceModule.*;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

public class Character {
    private final String name;
    private final List<Double> slashResistance;
    private final List<Double> pierceResistance;
    private final List<Double> bluntResistance;
    private final int maxHealth;
    private final int maxStagger;

    private List<SpeedDie> speedDice = new ArrayList<SpeedDie>();
    private List<Card> activeDeck = new ArrayList<Card>();
    private int health;
    private int stagger;

    public Character(String name, int maxHealth, int maxStagger,
                     Double slashDmgRes, Double slashStgRes,
                     Double pierceDmgRes, Double pierceStgRes,
                     Double bluntDmgRes, Double bluntStgRes)
    {
        this.name = name;
        this.slashResistance = Arrays.asList(slashDmgRes, slashStgRes);
        this.pierceResistance = Arrays.asList(pierceDmgRes, pierceStgRes);
        this.bluntResistance = Arrays.asList(bluntDmgRes, bluntStgRes);
        this.maxHealth = maxHealth;
        this.maxStagger = maxStagger;
        setHealth(this.maxHealth);
        setStagger(this.stagger);
    }

    public Integer getHealth() {
        return health;
    }

    public Integer getStagger() {
        return stagger;
    }

    public SpeedDie getSpeedDie(int i) {
        return this.speedDice.get(i);
    }

    public void setHealth(int health) {
        this.health = Math.min(health, maxHealth);
    }

    public void setStagger(int stagger) {
        this.stagger = Math.min(stagger, maxStagger);
    }

    public void addSpeedDie(SpeedDie sd) {
        speedDice.add(sd);
    }

    public int calculatePossibleHealthDamage(Dice dice) {
        Integer average_roll = dice.getMaxVal()-dice.getMinVal() / 2;
        if (stagger <= 0) {
            return switch (dice.getType()) {
                case "Slash", "Blunt", "Pierce" -> (int) (average_roll * 2);
                default -> 0;
            };
        }
        return switch (dice.getType()) {
            case "Slash" -> (int) (average_roll * this.slashResistance.get(0));
            case "Pierce" -> (int) (average_roll * this.pierceResistance.get(0));
            case "Blunt" -> (int) (average_roll * this.bluntResistance.get(0));
            default -> 0;
        };
    }

    public int calculatePossibleStaggerDamage(Dice dice) {
        if (stagger <= 0) {return 0;}
        Integer average_roll = dice.getMaxVal()-dice.getMinVal() / 2;
        return switch (dice.getType()) {
            case "Slash" -> (int) (average_roll * this.slashResistance.get(1));
            case "Pierce" -> (int) (average_roll * this.pierceResistance.get(1));
            case "Blunt" -> (int) (average_roll * this.bluntResistance.get(1));
            case "Block" -> (int) average_roll;
            default -> 0;
        };
    }
}
