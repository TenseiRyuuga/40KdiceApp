package com.mangalog.ryuuga.a40kdiceapp.enums.dice;

public enum calculationModifier {
    SUBTRACT(" - "),
    ADD(" + ");

    String string;

    calculationModifier(String string) {
        this.string = string;
    }

    public String getString() {
        return string;
    }
}
