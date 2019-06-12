package com.mangalog.ryuuga.a40kdiceapp.system;

public class Settings {

    private static Settings settings;

    // Strings
    public String BLANK;
    public String CHARACTERISTIC_DICE_TEST_NAME;

    // Numbers
    public int ZERO;
    public int CHARACTERISTIC_AMOUNT;
    public int CHARACTERISTIC_MORE_AMOUNT;
    public int CHARACTERISTIC_DICE_TEST_AMOUNT;

    private Settings () {
        super();
        createSettings();
    }

    public static Settings getSettings() {
        if(settings == null) {
            settings = new Settings();
        }
        return settings;
    }

    private void createSettings() {
        // set Strings
        BLANK = "";
        CHARACTERISTIC_DICE_TEST_NAME = "d100";

        // set Numbers
        ZERO = 0;
        CHARACTERISTIC_AMOUNT = 1;
        CHARACTERISTIC_MORE_AMOUNT = 5;
        CHARACTERISTIC_DICE_TEST_AMOUNT = 100;
    }
}
