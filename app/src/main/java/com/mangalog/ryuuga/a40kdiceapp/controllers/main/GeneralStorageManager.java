package com.mangalog.ryuuga.a40kdiceapp.controllers.main;

import androidx.appcompat.app.AppCompatActivity;

import com.mangalog.ryuuga.a40kdiceapp.models.BasicAppCompatActivity;
import com.mangalog.ryuuga.a40kdiceapp.models.characteristic.Characteristics;
import com.mangalog.ryuuga.a40kdiceapp.system.Settings;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class GeneralStorageManager extends  BasicStorageManager {

    private BasicAppCompatActivity activity;
    private Settings settings;

    private StringBuilder savingData;
    private List<String> loadedData;

    private JSONArray characteristics;

    public GeneralStorageManager(BasicAppCompatActivity activity) {
        super(activity);
        this.activity = activity;
        settings = activity.getSettings();

        savingData = new StringBuilder();
        loadedData = new ArrayList<>();

        characteristics = new JSONArray();
    }

    public boolean saveAll() {
        prepareData();
        return super.save(savingData.toString());

    }

    public boolean saveCharacteristics(Characteristics characteristics) {
        return true;
    }

    public List<String> load() {
        loadedData = super.load();
        return loadedData;
    }

    private boolean prepareData() {
        savingData = new StringBuilder();
        savingData.insert(settings.JSON_POS_CHARACTERISTICS,this.characteristics);
        return true;
    }

    public boolean loadAll() {
        loadCharacteristics();
        return true;
    }

    public JSONArray loadCharacteristics(){
        load();
        getCharacteristics();
        return characteristics;
    }

    // Setters and Getters
    public boolean setCharacteristics(JSONArray characteristics) {
        this.characteristics = characteristics;
        return true;
    }


    private JSONArray getCharacteristics() {
        try {
            characteristics = new JSONArray(loadedData.get(settings.JSON_POS_CHARACTERISTICS));
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
        return characteristics;
    }
}
