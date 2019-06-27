package com.mangalog.ryuuga.a40kdiceapp.models.characteristic;

import com.mangalog.ryuuga.a40kdiceapp.enums.characteristics.characteristicsData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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

    int toggleAllCharacteristicButtons() {
        // return 8 when edit buttons appear or 0 when they disappear
        int result = -1;
        for(Characteristic characteristic: characteristicsList) {
            result = characteristic.toggleButtonVisibility();
        }
        return result;
    }

    public JSONArray getAsJSONArray() {
        JSONArray jsonArray = new JSONArray();
        for (Characteristic characteristic: characteristicsList) {
            try {
                jsonArray.put(characteristicsList.indexOf(characteristic), characteristic.getAsJSONObject());
            }
            catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return jsonArray;
    }

    public boolean setByJSONArray(JSONArray jsonArray) {
        for (Characteristic characteristic: characteristicsList) {
            try {
                characteristic.setByJSONObject(jsonArray.getJSONObject(characteristicsList.indexOf(characteristic)));
            }
            catch (JSONException e) {
                e.printStackTrace();
                return false;
            }
        }
        jsonArray.put(characteristicsList.get(0));
        return true;
    }
}
