package com.Makalah_Stima_13522160;

import java.util.ArrayList;
import java.util.List;

public class DeckClass {
    private List<Card> deck = new ArrayList<>();

    public void addCard(Card c) {
        deck.add(c);
    }

    public Card getCard(int i) {
        return deck.get(i);
    }

    public int length() {
        return deck.size();
    }

    public void removeCard(Card c) {
        deck.remove(c);
    }
}
