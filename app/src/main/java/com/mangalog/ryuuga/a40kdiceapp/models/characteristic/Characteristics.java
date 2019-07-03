package com.mangalog.ryuuga.a40kdiceapp.models.characteristic;

import com.mangalog.ryuuga.a40kdiceapp.controllers.CharacteristicsMenu;
import com.mangalog.ryuuga.a40kdiceapp.enums.characteristics.characteristicsData;
import com.mangalog.ryuuga.a40kdiceapp.system.Settings;

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
        // prevent setting a non existing jsonArray;
        if(jsonArray != null) {
            // retrieve and walk through all characteristics
            for (Characteristic characteristic : this.getCharacteristicsList()) {
                try {
                    // retrieve the jsonObject for this specific characteristic
                    JSONObject jsonObject = jsonArray.getJSONObject(characteristicsList.indexOf(characteristic));
                    characteristic.setByJSONObject(jsonObject);
                } catch (JSONException e) {
                    e.printStackTrace();
                    return false;
                }
            }
        }
        return true;
    }
}
