package com.Makalah_Stima_13522160;

import com.Makalah_Stima_13522160.DiceModule.*;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;
import java.util.Collections;

public class Character {
    private final String name;
    private final List<Double> slashResistance;
    private final List<Double> pierceResistance;
    private final List<Double> bluntResistance;
    private final int maxHealth;
    private final int maxStagger;

    private List<SpeedDie> speedDice = new ArrayList<>();
    private DeckClass activeDeck = new DeckClass();
    private int health;
    private int stagger;
    private int maxLight;
    private int light;

    public Character(String name, int maxHealth, int maxStagger, int maxLight,
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
        this.maxLight = maxLight;
        setHealth(this.maxHealth);
        setStagger(this.maxStagger);
        setLight(this.maxLight);
    }

    public String getName() {
        return this.name;
    }

    public int getHealth() {
        return health;
    }

    public int getStagger() {
        return stagger;
    }

    public int getLight() {
        return light;
    }

    public SpeedDie getSpeedDie(int i) {
        return this.speedDice.get(i);
    }

    public List<SpeedDie> getSpeedDice() {
        return speedDice;
    }

    public DeckClass getActiveDeck() {
        return activeDeck;
    }

    public void setHealth(int health) {
        this.health = Math.min(health, maxHealth);
    }

    public void setStagger(int stagger) {
        this.stagger = Math.min(stagger, maxStagger);
    }

    public void setLight(int light) {
        this.light = Math.min(light, maxLight);
    }

    public void setSpeedDie(int i, SpeedDie target, Card card) {
        speedDice.get(i).setTarget(target);
        speedDice.get(i).setCard(card);
    }

    public void addSpeedDie() {
        speedDice.add(new SpeedDie(this));
    }

    public void rollSpeedDice() {
        Random random = new Random();
        for (SpeedDie sd : speedDice) {
            sd.setSpeed(random.nextInt(2,8)); // Assuming that's the speed range for all characters
        }
        Collections.sort(speedDice);
        Collections.reverse(speedDice);
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

    public int calculatePossibleHealthDamage(Card card) {
        if (card == null) {return 0;}
        int result = 0;
        for (Dice d: card.getDice()) {
            result += calculatePossibleHealthDamage(d);
        }
        return result;
    }

    public int calculatePossibleStaggerDamage(Dice dice) {
        if (stagger <= 0) {return 0;}
        Integer average_roll = dice.getMaxVal()-dice.getMinVal() / 2;
        return switch (dice.getType()) {
            case "Slash" -> (int) (average_roll * this.slashResistance.get(1));
            case "Pierce" -> (int) (average_roll * this.pierceResistance.get(1));
            case "Blunt" -> (int) (average_roll * this.bluntResistance.get(1));
            case "Block" -> average_roll;
            default -> 0;
        };
    }

    public int calculatePossibleStaggerDamage(Card card) {
        if (card == null) {return 0;}
        int result = 0;
        for (Dice d: card.getDice()) {
            result += calculatePossibleStaggerDamage(d);
        }
        return result;
    }
}
