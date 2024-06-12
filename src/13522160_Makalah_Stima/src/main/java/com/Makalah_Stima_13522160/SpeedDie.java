package com.Makalah_Stima_13522160;

public class SpeedDie implements Comparable<SpeedDie> {
    private final Character owner;
    private int speed;
    private Card card;
    private SpeedDie target;

    public SpeedDie(Character owner) {
        this.owner = owner;
    }

    public Character getOwner() {
        return owner;
    }

    public int getSpeed() {
        return speed;
    }

    public Card getCard() {
        return card;
    }

    public SpeedDie getTarget() {
        return target;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    public void setTarget(SpeedDie target) {
        this.target = target;
    }

    public boolean canClash(SpeedDie other) {
        return this.speed > other.getSpeed() || this == other.getTarget();
    }

    public Card popCard() {
        Card popped = this.card;
        this.card = null;
        return popped;
    }

    @Override
    public int compareTo(SpeedDie other) {
        return Integer.compare(this.speed, other.speed);
    }

    @Override
    public String toString() {
        if (card != null) {
            return owner.getName() + "{speed=" + speed + ", card=" + card + "}";
        } else {
            return owner.getName() + "{speed=" + speed + ", card=None}";
        }
    }
}
