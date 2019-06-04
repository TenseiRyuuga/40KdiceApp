package com.mangalog.ryuuga.a40kdiceapp.enums.characteristics;

import android.widget.Button;

import java.util.ArrayList;

public enum characteristicsData {
    WS("Weapon Skill", "WS", 20),
    BS("Ballistic Skill", "BS", 20),
    S("Strength", "S", 20),
    T("Toughness", "T", 20),
    Agi("Agility", "Agi", 20),
    Int("Intelligence", "Int", 20),
    Per("Perception", "Per", 20),
    WP("Willpower", "WP", 20),
    Fel("Fellowship", "Fel", 20);

    String longName;
    String shortName;
    int value;

    characteristicsData(String longName, String shortName, int value) {
        this.longName = longName;
        this.shortName =shortName;
        this.value = value;
    }

    public String getLongName() {
        return longName;
    }

    public String getShortName() {
        return shortName;
    }

    public int getValue() {
        return value;
    }

    public ArrayList<characteristicsData> getCharacteristicsDataList() {
        ArrayList<characteristicsData> result = new ArrayList<>();
        result.add(WS);
        result.add(BS);
        result.add(S);
        result.add(T);
        result.add(Agi);
        result.add(Int);
        result.add(Per);
        result.add(WP);
        result.add(Fel);
        return result;
    }
}
