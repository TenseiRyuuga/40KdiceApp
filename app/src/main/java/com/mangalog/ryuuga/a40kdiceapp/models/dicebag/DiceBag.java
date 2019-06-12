package com.mangalog.ryuuga.a40kdiceapp.models.dicebag;

import java.util.ArrayList;

public class DiceBag {

    private static DiceBag diceBag;
    private ArrayList<Dice> diceArrayList;

    private DiceBag() {
        super();
        this.diceArrayList = new ArrayList<>();
    }

    public static DiceBag getDiceBag() {
        if(diceBag == null) {
            diceBag = new DiceBag();
        }
        return diceBag;
    }

    public Dice getDice(int sides, String name) {
        Dice result = getDiceByName(name);
        if(result == null) {
            result = new Dice(sides, name);
            addDice(result);
        }
        return result;
    }

    private void addDice(Dice dice) {
        if (getDiceByName(dice.getDiceName()) == null) {
            diceArrayList.add(dice);
        }
    }

    private Dice getDiceByName(String diceName) {
        for(Dice dice: diceArrayList) {
            if (dice.getDiceName().equals(diceName)) {
                return dice;
            }
        }
        return null;
    }
}
