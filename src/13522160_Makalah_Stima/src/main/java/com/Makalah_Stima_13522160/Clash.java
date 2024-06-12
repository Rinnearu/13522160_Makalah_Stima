package com.Makalah_Stima_13522160;

public class Clash {
    private static boolean canRedirect(SpeedDie chosenDie, SpeedDie targetDie) {
        return chosenDie.getSpeed() > targetDie.getSpeed();
    }

    private static boolean canClash(SpeedDie chosenDie, SpeedDie targetDie) {
        return canRedirect(chosenDie, targetDie) || targetDie.getTarget() == chosenDie;
    }
}
