package com.mangalog.ryuuga.a40kdiceapp.system;

import com.mangalog.ryuuga.a40kdiceapp.controllers.main.SettingsStorageManager;
import com.mangalog.ryuuga.a40kdiceapp.enums.system.setting;
import com.mangalog.ryuuga.a40kdiceapp.models.characteristic.Characteristic;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

public class Settings  {

    private static Settings settings;
    private ArrayList<setting> setttingList;
    private SettingsStorageManager settingsStorageManager;

    private Settings () {
        super();
        this.setttingList = setting.ZERO.getSettingsDataList();
    }

    public static Settings getSettings() {
        if(settings == null) {
            settings = new Settings();
        }
        return settings;
    }

    // getterList for all options
    public int      ZERO() {
        return getInt(setting.ZERO);
    }
    public String   BLANK() {
        return getString(setting.BLANK);
    }

    public String   CHARACTERISTIC_DICE_TEST_NAME() { return getString(setting.CHARACTERISTIC_DICE_TEST_NAME); }
    public int      CHARACTERISTIC_AMOUNT() {
        return getInt(setting.CHARACTERISTIC_AMOUNT);
    }
    public int      CHARACTERISTIC_MORE_AMOUNT() { return getInt(setting.CHARACTERISTIC_MORE_AMOUNT); }

    public int      CHARACTERISTIC_DICE_TEST_AMOUNT() { return getInt(setting.CHARACTERISTIC_DICE_TEST_AMOUNT); }

    public int      OPTION_MENU_ITEM_ID_SETTINGS() { return getInt(setting.OPTION_MENU_ITEM_ID_SETTINGS); }
    public int      OPTION_MENU_ITEM_ID_EDIT() {
        return getInt(setting.OPTION_MENU_ITEM_ID_EDIT);
    }
    public int      OPTION_MENU_ITEM_ORDER_IN_CATEGORY() { return getInt(setting.OPTION_MENU_ITEM_ORDER_IN_CATEGORY); }

    public String   JSON_NAME() {
        return getString(setting.JSON_NAME);
    }
    public String   JSON_VALUE() {
        return getString(setting.JSON_VALUE);
    }
    public int      JSON_POS_CHARACTERISTICS() {
        return getInt(setting.JSON_POS_CHARACTERISTICS);
    }

    // methods using multiple settings
    public boolean toggleEdit() {
        // return true so that saving is only triggered when we are truly exiting edit mode
        boolean result = true;
        for(setting setting: setttingList) {
            result = setting.toggleEdit();
        }
        return result;
    }

    // private internal getters for specific retuns
    private int getInt(setting setting) {
        return setting.getValueAsInt();
    }
    private String getString(setting setting) {
        return setting.getValueAsString();
    }

    // JSON related methods fneeded for saving and loading
    public JSONArray getJSONArray() {
        JSONArray result = new JSONArray();
        try {
            result.put(setting.ZERO.getJsonId(), setting.ZERO.getValueAsInt());
            // Strings
            result.put(setting.BLANK.getJsonId(), setting.BLANK.getValueAsString());
            result.put(setting.CHARACTERISTIC_DICE_TEST_NAME.getJsonId(), setting.CHARACTERISTIC_DICE_TEST_NAME.getValueAsString());

            // Numbers
            // characteristic numbers
            result.put(setting.CHARACTERISTIC_AMOUNT.getJsonId(), setting.CHARACTERISTIC_AMOUNT.getValueAsInt());
            result.put(setting.CHARACTERISTIC_MORE_AMOUNT.getJsonId(), setting.CHARACTERISTIC_MORE_AMOUNT.getValueAsInt());
            result.put(setting.CHARACTERISTIC_DICE_TEST_AMOUNT.getJsonId(), setting.CHARACTERISTIC_DICE_TEST_AMOUNT.getValueAsInt());

            // Option menu numbers
            result.put(setting.OPTION_MENU_ITEM_ID_SETTINGS.getJsonId(), setting.OPTION_MENU_ITEM_ID_SETTINGS.getValueAsInt());
            result.put(setting.OPTION_MENU_ITEM_ID_EDIT.getJsonId(), setting.OPTION_MENU_ITEM_ID_EDIT.getValueAsInt());
            result.put(setting.OPTION_MENU_ITEM_ORDER_IN_CATEGORY.getJsonId(), setting.OPTION_MENU_ITEM_ORDER_IN_CATEGORY.getValueAsInt());

            // JSON Strings
            result.put(setting.JSON_NAME.getJsonId(), setting.JSON_NAME.getValueAsString());
            result.put(setting.JSON_VALUE.getJsonId(), setting.JSON_VALUE.getValueAsString());

            //JSON Position Numbers
            result.put(setting.JSON_POS_CHARACTERISTICS.getJsonId(), setting.JSON_POS_CHARACTERISTICS.getValueAsInt());
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
        return result;
    }

    public boolean setJSONArray(JSONArray jsonArray) {
        boolean result = true;
        if (jsonArray != null) {
            try {
                setting.ZERO.setValue((int)jsonArray.get(setting.ZERO.getJsonId()));
                // Strings
                setting.BLANK.setValue(jsonArray.get(setting.BLANK.getJsonId()).toString());
                setting.CHARACTERISTIC_DICE_TEST_NAME.setValue(jsonArray.get(setting.CHARACTERISTIC_DICE_TEST_NAME.getJsonId()).toString());

                // set Numbers
                setting.CHARACTERISTIC_AMOUNT.setValue((int) jsonArray.get(setting.CHARACTERISTIC_AMOUNT.getJsonId()));
                setting.CHARACTERISTIC_MORE_AMOUNT.setValue((int) jsonArray.get(setting.CHARACTERISTIC_MORE_AMOUNT.getJsonId()));
                setting.CHARACTERISTIC_DICE_TEST_AMOUNT.setValue((int) jsonArray.get(setting.CHARACTERISTIC_DICE_TEST_AMOUNT.getJsonId()));

                setting.OPTION_MENU_ITEM_ID_SETTINGS.setValue((int) jsonArray.get(setting.OPTION_MENU_ITEM_ID_SETTINGS.getJsonId()));
                setting.OPTION_MENU_ITEM_ID_EDIT.setValue((int) jsonArray.get(setting.OPTION_MENU_ITEM_ID_EDIT.getJsonId()));

                setting.OPTION_MENU_ITEM_ORDER_IN_CATEGORY.setValue((int) jsonArray.get(setting.OPTION_MENU_ITEM_ORDER_IN_CATEGORY.getJsonId()));

                setting.JSON_NAME.setValue(jsonArray.get(setting.JSON_NAME.getJsonId()).toString());
                setting.JSON_VALUE.setValue(jsonArray.get(setting.JSON_VALUE.getJsonId()).toString());

                setting.JSON_POS_CHARACTERISTICS.setValue((int) jsonArray.get(setting.JSON_POS_CHARACTERISTICS.getJsonId()));
            } catch (JSONException e) {
                e.printStackTrace();
                result = false;
            }
        }
        return result;
    }

    public ArrayList<setting> getSettingsList() {
        return setttingList;
    }
}
