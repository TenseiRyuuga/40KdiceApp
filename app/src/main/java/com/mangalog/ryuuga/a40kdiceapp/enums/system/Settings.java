package com.mangalog.ryuuga.a40kdiceapp.enums.system;

import com.mangalog.ryuuga.a40kdiceapp.Characteristic;
import com.mangalog.ryuuga.a40kdiceapp.enums.characteristics.characteristicsData;

import java.util.ArrayList;
import java.util.List;

public class Settings {

    private static Settings settings;

    // Strings
    public String BLANK;
    public String BUTTON;
    public String SUBTRACT;
    public String ADD;
    public String MORE;

    // Numbers
    public int ZERO = 0;

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
        BUTTON = "button_";
        SUBTRACT = "subtract_";
        ADD = "add_";
        MORE = "more_";

        // set Numbers
        ZERO = 0;
    }
}
