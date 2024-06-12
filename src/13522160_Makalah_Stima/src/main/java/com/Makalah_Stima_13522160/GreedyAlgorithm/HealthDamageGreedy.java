package com.Makalah_Stima_13522160.GreedyAlgorithm;

import com.Makalah_Stima_13522160.Card;
import com.Makalah_Stima_13522160.Character;
import com.Makalah_Stima_13522160.DeckClass;
import com.Makalah_Stima_13522160.SpeedDie;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HealthDamageGreedy implements GreedyBase{
    @Override
    public List<SpeedDie> getGreedyResult(List<Character> librarians, List<Character> guests) {
        List<SpeedDie> librariansDice = mergeAllSpeedDice(librarians);

        for (SpeedDie sdLib: librariansDice) {
            Character owner = sdLib.getOwner();
            DeckClass usedDeck = owner.getActiveDeck();

            Card usedCard = null;
            SpeedDie usedTarget = null;
            int maxDamage = 0;
            for (Character cGst: guests) {
                for (int i = 0; i < usedDeck.length(); i++) {
                    int temp = cGst.calculatePossibleHealthDamage(usedDeck.getCard(i));
                    if (maxDamage < temp) {
                        usedCard = usedDeck.getCard(i);
                        maxDamage = temp;
                        usedTarget = cGst.getSpeedDie(0);
                    }
                }
            }

            if (usedCard != null) {
                sdLib.setCard(usedCard);
                usedDeck.removeCard((usedCard));
                sdLib.setTarget(usedTarget);
                if (sdLib.canClash(usedTarget)) {
                    usedTarget.setTarget(sdLib);
                }
                owner.setLight(owner.getLight() - usedCard.getCost());
            }
        }

        List<SpeedDie> stagePlayOrder = new ArrayList<>(librariansDice);
        stagePlayOrder.addAll(mergeAllSpeedDice(guests));

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
}
