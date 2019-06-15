package com.mangalog.ryuuga.a40kdiceapp.models.characteristic;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.LayoutManager;

import android.view.View;

import com.mangalog.ryuuga.a40kdiceapp.R;
import com.mangalog.ryuuga.a40kdiceapp.enums.characteristics.characteristicsData;
import com.mangalog.ryuuga.a40kdiceapp.models.BasicAppCompatActivity;
import com.mangalog.ryuuga.a40kdiceapp.models.recycler.RecyclerAdapter_Characteristic;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class CharacteristicsInfoScreen {
    private BasicAppCompatActivity basicAppCompatActivity;
    private Characteristics characteristics;

    private RecyclerView recyclerView;
    private RecyclerAdapter_Characteristic recyclerAdapter;

    public CharacteristicsInfoScreen(BasicAppCompatActivity basicAppCompatActivity) {
            this.basicAppCompatActivity = basicAppCompatActivity;
            this.characteristics = new Characteristics();

            recyclerView = findViewById(R.id.recyclerView);

            recyclerAdapter = new RecyclerAdapter_Characteristic(characteristics);
            final LinearLayoutManager layoutManager = new LinearLayoutManager(this.basicAppCompatActivity);
            layoutManager.setOrientation(RecyclerView.VERTICAL);

            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setAdapter(recyclerAdapter);

            recyclerAdapter.notifyData(characteristics);
    }

    public void onButtonEdit(View view) {
        characteristics.toggleAllCharacteristicButtons();
    }

    public JSONObject getJSONObject() {
        JSONObject result = new JSONObject();
        return  result;
    }

    private <T extends View> T findViewById(int id) {
        return this.basicAppCompatActivity.findViewById(id);
    }

}
