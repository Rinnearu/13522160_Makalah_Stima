package com.Makalah_Stima_13522160;

import com.Makalah_Stima_13522160.DiceModule.BlockDice;
import com.Makalah_Stima_13522160.DiceModule.EvadeDice;
import com.Makalah_Stima_13522160.DiceModule.OffensiveDice;
import com.Makalah_Stima_13522160.GreedyAlgorithm.*;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main (String [] args) {
        Character Roland = new Character("Roland", 60, 50, 4,
                1.0, 1.5,
                0.5, 1.0,
                1.5, 1.0);
        Character Angela = new Character("Angela", 85, 40, 5,
                1.0, 1.0,
                1.0, 1.0,
                1.0, 1.0);
        Character Pete = new Character("Pete", 50, 45, 4,
                1.0, 1.0,
                1.0, 1.0,
                1.5, 1.5);
        Character Olivier = new Character("Olivier", 70, 45, 4,
                1.0, 1.0,
                0.5, 1.5,
                1.5, 1.0);
        Character Olga = new Character("Olga", 55, 40, 5,
                1.0, 1.5,
                0.5, 1.0,
                1.5, 1.0);

        Roland.addSpeedDie();
        Roland.addSpeedDie();
        Roland.addSpeedDie();
        Angela.addSpeedDie();
        Angela.addSpeedDie();

        List<Character> librarians = new ArrayList<>();
        librarians.add(Roland);
        librarians.add(Angela);

        Olivier.addSpeedDie();
        Olivier.addSpeedDie();
        Olga.addSpeedDie();
        Olga.addSpeedDie();
        Pete.addSpeedDie();

        List<Character> guests = new ArrayList<>();
        guests.add(Olivier);
        guests.add(Olga);
        guests.add(Pete);

        for (Character librarian: librarians) {
            librarian.rollSpeedDice();
        }
        for (Character guest: guests) {
            guest.rollSpeedDice();
        }

        Card olivier1 = new Card("Impugnatio Ultima", 4, "Melee");
        olivier1.addDice(new OffensiveDice(5,9,"Slash"));
        olivier1.addDice(new OffensiveDice(5,9,"Pierce"));
        olivier1.addDice(new OffensiveDice(5,9,"Blunt"));
        Card olivier2 = new Card("Brace Up", 0, "Melee");
        olivier2.addDice(new OffensiveDice(3,8,"Pierce"));

        Card olga1 = new Card("Daring Decision", 3, "Ranged"); // Not actually ranged
        olga1.addDice(new OffensiveDice(4,8,"Slash"));
        olga1.addDice(new OffensiveDice(4,8,"Pierce"));
        olga1.addDice(new OffensiveDice(3,5,"Blunt"));
        Card olga2 = new Card("Wait Up!", 0, "Melee");
        olga2.addDice((new OffensiveDice(2,5,"Pierce")));

        Card pete1 = new Card("Solemn Lament", 4, "Ranged"); // Not even his page
        pete1.addDice((new OffensiveDice(2,3,"Pierce")));
        pete1.addDice((new OffensiveDice(2,5,"Pierce")));
        pete1.addDice((new OffensiveDice(2,5,"Pierce")));
        pete1.addDice((new OffensiveDice(2,5,"Pierce")));
        pete1.addDice((new OffensiveDice(2,5,"Pierce")));
        pete1.addDice((new OffensiveDice(2,5,"Pierce")));
        pete1.addDice((new OffensiveDice(2,5,"Pierce")));
        pete1.addDice((new OffensiveDice(2,5,"Pierce")));

        Card roland1 = new Card("Durandal", 2, "Melee");
        roland1.addDice(new OffensiveDice(5,9,"Slash"));
        roland1.addDice(new OffensiveDice(5,9,"Slash"));

        Card roland2 = new Card("Atelier Logic", 2, "Ranged");
        roland2.addDice(new OffensiveDice(4,8,"Pierce"));
        roland2.addDice(new OffensiveDice(5,8,"Pierce"));
        roland2.addDice(new OffensiveDice(7,12,"Blunt"));

        Card roland3 = new Card("Old Boys Workshop", 1, "Melee");
        roland3.addDice(new BlockDice(5,9));
        roland3.addDice(new OffensiveDice(4,8,"Blunt"));

        Card furioso = new Card("Furioso", 3, "Melee");
        furioso.addDice(new OffensiveDice(20,39,"Slash"));

        Card angela1 = new Card("Shyness", 0, "Melee");
        angela1.addDice(new BlockDice(5,8));
        angela1.addDice(new BlockDice(5,8));

        Card angela2 = new Card("Coffin", 4, "Ranged");
        angela2.addDice(new OffensiveDice(10,15,"Pierce"));
        angela2.addDice(new EvadeDice(4,8));
        angela2.addDice(new EvadeDice(4,8));

        Olivier.setSpeedDie(0,Roland.getSpeedDie(0), olivier1);
        Olivier.setSpeedDie(1,Roland.getSpeedDie(1), olivier2);

        Olga.setSpeedDie(0, Angela.getSpeedDie(1), olga1);
        Olga.setSpeedDie(1, Angela.getSpeedDie(1), olga2);

        Pete.setSpeedDie(0, Roland.getSpeedDie(2), pete1);

        Roland.getActiveDeck().addCard(roland1);
        Roland.getActiveDeck().addCard(roland2);
        Roland.getActiveDeck().addCard(roland3);
        Roland.getActiveDeck().addCard(furioso);

        Angela.getActiveDeck().addCard(angela1);
        Angela.getActiveDeck().addCard(angela2);
        Angela.getActiveDeck().addCard(pete1);
        Angela.getActiveDeck().addCard(olga1);

//        GreedyBase greedySolution = new WinrateGreedy();
//        GreedyBase greedySolution = new HealthDamageGreedy();
        GreedyBase greedySolution = new StaggerDamageGreedy();

        List<SpeedDie> greedyResult = greedySolution.getGreedyResult(librarians, guests);

        printResult(greedyResult);
    }

    public static void printResult(List<SpeedDie> result) {
        while (!result.isEmpty()) {
            SpeedDie entry = result.get(0);
            result.remove(entry);
            SpeedDie target = entry.getCard() != null ? entry.getTarget() : null;
            System.out.print(entry);

            if (target != null) {
                System.out.print(" targeting " + target);
                if (target.getTarget() == entry) {
                    System.out.println(" CLASHING!!!");
                    result.remove(target);
                } else {
                    System.out.println(" ONE-SIDED ATTACK!!!");
                }
            } else {
                System.out.println(" is empty");
            }
        }
    }
}
