package com.mangalog.ryuuga.a40kdiceapp.models.characteristic;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.LayoutManager;

import android.view.View;

import com.mangalog.ryuuga.a40kdiceapp.R;
import com.mangalog.ryuuga.a40kdiceapp.controllers.CharacteristicsMenu;
import com.mangalog.ryuuga.a40kdiceapp.enums.characteristics.characteristicsData;
import com.mangalog.ryuuga.a40kdiceapp.models.BasicAppCompatActivity;
import com.mangalog.ryuuga.a40kdiceapp.models.recycler.RecyclerAdapter_Characteristic;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class CharacteristicsInfoScreen {
    private CharacteristicsMenu characteristicsMenu;
    private Characteristics characteristics;

    private RecyclerView recyclerView;
    private RecyclerAdapter_Characteristic recyclerAdapter;

    public CharacteristicsInfoScreen(CharacteristicsMenu characteristicsMenu) {
            this.characteristicsMenu = characteristicsMenu;
//            this.characteristics = this.characteristicsMenu.getStorageManager().load().;
            this.characteristics = new Characteristics();

            recyclerView = findViewById(R.id.recyclerView);

            recyclerAdapter = new RecyclerAdapter_Characteristic(characteristics);
            final LinearLayoutManager layoutManager = new LinearLayoutManager(this.characteristicsMenu);
            layoutManager.setOrientation(RecyclerView.VERTICAL);

            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setAdapter(recyclerAdapter);

            recyclerAdapter.notifyData(characteristics);
    }

    public void onButtonEdit(View view) {
        if(characteristics.toggleAllCharacteristicButtons() == 0) {
            characteristicsMenu.getStorageManager().setCharacteristics(characteristics);
            characteristicsMenu.getStorageManager().save();
        }
    }

    private <T extends View> T findViewById(int id) {
        return this.characteristicsMenu.findViewById(id);
    }

}
