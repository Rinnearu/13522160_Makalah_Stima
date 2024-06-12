package com.Makalah_Stima_13522160.GreedyAlgorithm;

import com.Makalah_Stima_13522160.Card;
import com.Makalah_Stima_13522160.Character;
import com.Makalah_Stima_13522160.DeckClass;
import com.Makalah_Stima_13522160.DiceModule.Dice;
import com.Makalah_Stima_13522160.SpeedDie;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class WinrateGreedy implements GreedyBase{
    @Override
    public List<SpeedDie> getGreedyResult(List<Character> librarians, List<Character> guests) {
        List<SpeedDie> librariansDice = mergeAllSpeedDice(librarians);
        List<SpeedDie> guestsDice = mergeAllSpeedDice(guests);
        List<Boolean> targeted = new ArrayList<>();

        for (SpeedDie ignored : guestsDice) {
            targeted.add(false);
        }

        for (SpeedDie sdLib: librariansDice) {
            Character owner = sdLib.getOwner();
            DeckClass usedDeck = owner.getActiveDeck();

            if (!isAllTrue(targeted)) {
                Card usedCard = null;
                SpeedDie usedTarget = null;
                double highestWinrate = -1.0;
                int column = 0;

                for (int j = 0; j < guestsDice.size(); j++) {
                    if (targeted.get(j)) {continue;}
                    SpeedDie sdGst = guestsDice.get(j);
                    if (highestWinrate >= 0.0 && !sdLib.canClash(sdGst)) {continue;}

                    for (int i = 0; i < usedDeck.length(); i++) {
                        Card card = usedDeck.getCard(i);
                        if (owner.getLight() < card.getCost()) {
                            continue;
                        }
                        double result = usedDeck.getCard(i).calculateWinrateSumAgainst(sdGst.getCard());

                        if (result > highestWinrate) {
                            usedCard = usedDeck.getCard(i);
                            usedTarget = sdGst;
                            highestWinrate = result;
                            column = j;
                        }
                    }
                }

                if (highestWinrate >= 0.0) {
                    sdLib.setCard(usedCard);
                    usedDeck.removeCard(usedCard);
                    sdLib.setTarget(usedTarget);
                    targeted.set(column, true);
                    if (sdLib.canClash(usedTarget)) {
                        usedTarget.setTarget(sdLib);
                    }
                    owner.setLight(owner.getLight() - usedCard.getCost());
                }
            } else {
                Card usedCard = null;
                SpeedDie target = guestsDice.get(0);
                int highestDamage = 0;
                Character targetChar = target.getOwner();

                for (int i = 0; i < usedDeck.length(); i++) {
                    Card card = usedDeck.getCard(i);
                    if (owner.getLight() < card.getCost()) {
                        continue;
                    }
                    int damage = 0;
                    for (Dice d: card.getDice()) {
                        damage += targetChar.calculatePossibleHealthDamage(d);
                    }
                    if (damage > highestDamage) {
                        usedCard = card;
                        highestDamage = damage;
                    }
                }
                if (usedCard != null) {
                    sdLib.setCard(usedCard);
                    usedDeck.removeCard(usedCard);
                    sdLib.setTarget(target);
                }
            }
        }

        List<SpeedDie> stagePlayOrder = new ArrayList<>(librariansDice);
        stagePlayOrder.addAll(guestsDice);

        Collections.sort(stagePlayOrder);
        Collections.reverse(stagePlayOrder);

        return stagePlayOrder;
    }

    public List<SpeedDie> mergeAllSpeedDice(List<Character> team) {
        List<SpeedDie> allDice = new ArrayList<>();
        for (Character c: team) {
            allDice.addAll(c.getSpeedDice());
        }
        Collections.sort(allDice);
        Collections.reverse(allDice);
        return allDice;
    }

    public boolean isAllTrue(List<Boolean> target) {
        for (Boolean b: target) {
            if (!b) {return false;}
        }
        return true;
    }
}
