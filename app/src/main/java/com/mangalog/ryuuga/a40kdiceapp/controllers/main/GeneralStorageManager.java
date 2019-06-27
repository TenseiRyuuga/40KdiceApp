package com.mangalog.ryuuga.a40kdiceapp.controllers.main;

import androidx.appcompat.app.AppCompatActivity;

import com.mangalog.ryuuga.a40kdiceapp.models.characteristic.Characteristics;

import java.util.ArrayList;
import java.util.List;

public class GeneralStorageManager extends  BasicStorageManager {

    private StringBuilder data;
    private List<String> loadedData;

    private Characteristics characteristics;

    public GeneralStorageManager(AppCompatActivity activity) {
        super(activity);
        data = new StringBuilder();
        loadedData = new ArrayList<>();

        characteristics = new Characteristics();
    }

    public boolean save() {
        prepareData();
        return super.save(data.toString());

    }

    public List<String> load() {
        loadedData = super.load();
        return loadedData;
    }

    private boolean prepareData() {
        data = new StringBuilder();
        data.append(this.characteristics.getAsJSONArray());
        return true;
    }

    public boolean setCharacteristics(Characteristics characteristics) {
        this.characteristics = characteristics;
        return true;
    }
}
