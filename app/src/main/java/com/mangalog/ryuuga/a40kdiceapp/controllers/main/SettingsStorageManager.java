package com.mangalog.ryuuga.a40kdiceapp.controllers.main;

import com.mangalog.ryuuga.a40kdiceapp.models.BasicAppCompatActivity;
import com.mangalog.ryuuga.a40kdiceapp.system.Settings;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.List;

public class SettingsStorageManager extends  BasicStorageManager {

    private StringBuilder savingData;
    private List<String> loadedData;
    private static SettingsStorageManager settingsStorageManager;

    private JSONArray characteristics;

    private SettingsStorageManager(BasicAppCompatActivity basicAppCompatActivity) {
        super(basicAppCompatActivity, "System", "settings.cfg");
    }

    public static SettingsStorageManager getSettingsStorageManager(BasicAppCompatActivity basicAppCompatActivity) {
        if(settingsStorageManager == null) {
            settingsStorageManager = new SettingsStorageManager(basicAppCompatActivity);
        }
        return settingsStorageManager;
    }

    public boolean save(JSONArray jsonArray) {
        return super.save(jsonArray.toString());
    }

    public JSONArray loadSettings() {
        JSONArray result = null;
        try {
            List<String> loadedData = super.load();
            if(loadedData != null) {
                result = new JSONArray(loadedData.get(0));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return result;
    }
}
