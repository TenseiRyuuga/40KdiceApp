package com.mangalog.ryuuga.a40kdiceapp.models.dicebag;

import java.util.Random;

public class Dice {
    private int sides;
    private String diceName;
    private Random random;

    Dice (int sides, String diceName) {
        super();
        this.sides = sides;
        this.diceName = diceName;
        this.random = new Random();
    }

    public int roll() {
        return random.nextInt(sides) + 1;
    }

    public String getDiceName() {
        return diceName;
    }
}
