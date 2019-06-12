package com.mangalog.ryuuga.a40kdiceapp.models.characteristic;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.LayoutManager;

import android.view.View;

import com.mangalog.ryuuga.a40kdiceapp.R;
import com.mangalog.ryuuga.a40kdiceapp.enums.characteristics.characteristicsData;
import com.mangalog.ryuuga.a40kdiceapp.models.recycler.RecyclerAdapter_Characteristic;

import java.util.ArrayList;

public class CharacteristicsInfoScreen {
    private AppCompatActivity appCompatActivity;
    private ArrayList<Characteristic> characteristicsList;

    private RecyclerView recyclerView;
    private RecyclerAdapter_Characteristic recyclerAdapter;
    private LayoutManager layoutManager;

    public CharacteristicsInfoScreen(AppCompatActivity appCompatActivity) {
            this.appCompatActivity = appCompatActivity;

            recyclerView = findViewById(R.id.recyclerView);

            recyclerAdapter = new RecyclerAdapter_Characteristic(characteristicsList);
            final LinearLayoutManager layoutManager = new LinearLayoutManager(this.appCompatActivity);
            layoutManager.setOrientation(RecyclerView.VERTICAL);

            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setAdapter(recyclerAdapter);

            characteristicsList = new ArrayList<>();
            ArrayList<characteristicsData> characteristicsDataList = characteristicsData.WS.getCharacteristicsDataList();
            for(characteristicsData characteristic: characteristicsDataList) {
                characteristicsList.add(new Characteristic(this.appCompatActivity, characteristic));
            }

            recyclerAdapter.notifyData(characteristicsList);
    }

    public void onButtonEdit(View view) {
        toggleAllCharacteristicButtons();
    }

    private void toggleAllCharacteristicButtons() {
        for(Characteristic characteristic: characteristicsList) {
            characteristic.toggleButtonVisibility();
        }
    }

    private <T extends View> T findViewById(int id) {
        return appCompatActivity.findViewById(id);
    }

}
