package com.mangalog.ryuuga.a40kdiceapp.models.characteristic;

import androidx.appcompat.app.AppCompatActivity;

import com.mangalog.ryuuga.a40kdiceapp.enums.characteristics.characteristicsData;

import org.json.JSONArray;

import java.util.ArrayList;

public class Characteristics {

    private ArrayList<Characteristic> characteristicsList;

    public Characteristics() {
        characteristicsList = new ArrayList<>();
        ArrayList<characteristicsData> characteristicsDataList = characteristicsData.WS.getCharacteristicsDataList();
        for(characteristicsData characteristic: characteristicsDataList) {
            characteristicsList.add(new Characteristic(characteristic));
        }
    }

    public  ArrayList<Characteristic> getCharacteristicsList() {
        return characteristicsList;
    }

    public void toggleAllCharacteristicButtons() {
        for(Characteristic characteristic: characteristicsList) {
            characteristic.toggleButtonVisibility();
        }
    }

    public JSONArray getAsJSONArray() {
        JSONArray jsonArray = new JSONArray();
        for (Characteristic characteristic: characteristicsList) {
            jsonArray.put(characteristic.getAsJSONObject());
        }
        jsonArray.put(characteristicsList.get(0));
        return jsonArray;
    }
}
