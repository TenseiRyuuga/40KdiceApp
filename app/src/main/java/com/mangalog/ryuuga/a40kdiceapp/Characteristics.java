package com.mangalog.ryuuga.a40kdiceapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.mangalog.ryuuga.a40kdiceapp.enums.characteristics.characteristicsData;
import com.mangalog.ryuuga.a40kdiceapp.enums.system.Settings;

import java.util.ArrayList;

public class Characteristics extends AppCompatActivity {
    private ArrayList<Characteristic> characteristicsList;
    private ArrayList<characteristicsData> characteristicsDataList;

    Context context;
    ConstraintLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        layout = new ConstraintLayout(this);
        context = layout.getContext();
        setContentView(R.layout.activity_characteristics);

        characteristicsList = new ArrayList<>();
        characteristicsDataList = characteristicsData.WS.getCharacteristicsDataList();
        for(characteristicsData characteristic: characteristicsDataList) {
            characteristicsList.add(new Characteristic(this, characteristic));
        }
    }

    public void onButtonEdit(View view) {
        toggleAllCharacteristicButtons();
    }

    private void toggleAllCharacteristicButtons() {
        for(Characteristic characteristic: characteristicsList) {
            characteristic.toggleButtonVisibility();
        }
    }

    public void onButtonMore(View view) {
        int buttonId = view.getId();
        Characteristic characteristic = findCharacteristicByButtonId(buttonId);
        if(!characteristic.equals(null)) {
            characteristic.add(1);
        }
        System.out.println("onButtonMore pressed: " + buttonId);
    }

    public void onButtonMagic(View view) {
        System.out.println("onButtonMagic pressed");
        Button button = new Button(context);
        ConstraintLayout.LayoutParams lp = new ConstraintLayout.LayoutParams(  100,  100 );
        button.setLayoutParams(lp);
        button.setText(Settings.getSettings().BUTTON.toUpperCase());
        layout.addView(button);
    }

    private Characteristic findCharacteristicByButtonId (int buttonId) {
        for(Characteristic characteristic: characteristicsList) {
            if(characteristic.containsButtonId(buttonId)) {
                return characteristic;
            }
        }
        return null;
    }
}
