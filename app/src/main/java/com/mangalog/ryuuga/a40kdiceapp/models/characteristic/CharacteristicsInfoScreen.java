package com.mangalog.ryuuga.a40kdiceapp.models.characteristic;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;

import com.mangalog.ryuuga.a40kdiceapp.R;
import com.mangalog.ryuuga.a40kdiceapp.controllers.CharacteristicsMenu;
import com.mangalog.ryuuga.a40kdiceapp.enums.characteristics.characteristicsData;
import com.mangalog.ryuuga.a40kdiceapp.models.recycler.RecyclerAdapter_Characteristic;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class CharacteristicsInfoScreen {
    private CharacteristicsMenu characteristicsMenu;
    private Characteristics characteristics;

    private RecyclerView recyclerView;
    private RecyclerAdapter_Characteristic recyclerAdapter;

    public CharacteristicsInfoScreen(CharacteristicsMenu characteristicsMenu) {
        this.characteristicsMenu = characteristicsMenu;
        this.characteristics = new Characteristics();


        recyclerView = findViewById(R.id.recyclerView);

        recyclerAdapter = new RecyclerAdapter_Characteristic(this.characteristics);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this.characteristicsMenu);
        layoutManager.setOrientation(RecyclerView.VERTICAL);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(recyclerAdapter);

        recyclerAdapter.notifyData(characteristics);
//        setCharacteristics(this.characteristics.getAsJSONArray());
        characteristics.getCharacteristicsList().get(0).setValueAsString("66");
        ArrayList<Characteristic> temp = characteristics.getCharacteristicsList();
        characteristics.setByJSONArray(this.characteristicsMenu.getStorageManager().loadCharacteristics());
    }

    public void onButtonEdit(View view) {
        if(characteristics.toggleAllCharacteristicButtons() == 0) {
            characteristicsMenu.getStorageManager().setCharacteristics(characteristics.getAsJSONArray());
            characteristicsMenu.getStorageManager().saveAll();
        }
    }

    private <T extends View> T findViewById(int id) {
        return this.characteristicsMenu.findViewById(id);
    }

    public boolean setCharacteristics(JSONArray jsonArray) {
        this.characteristics.setByJSONArray(jsonArray);
        return true;
    }

}
