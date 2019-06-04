package com.mangalog.ryuuga.a40kdiceapp;

import android.content.res.Resources;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.IdRes;
import androidx.appcompat.app.AppCompatActivity;

import com.mangalog.ryuuga.a40kdiceapp.enums.characteristics.characteristicsData;
import com.mangalog.ryuuga.a40kdiceapp.enums.system.Settings;

import java.util.ArrayList;
import java.util.List;

public class Characteristic {

    private AppCompatActivity appCompatActivity;
    private Settings settings;
    private String button;
    private String subtract;
    private String add;
    private String more;

    private String defType;
    private String packageName;

    private characteristicsData characteristic;
    private ArrayList<String> characteristicButtons;
    private ArrayList<String> characteristicFields;

    private EditText value;
    private TextView name;

    public Characteristic(AppCompatActivity appCompatActivity, characteristicsData characteristic) {
        this.appCompatActivity = appCompatActivity;
        this.characteristic = characteristic;

        this.settings = Settings.getSettings();
        this.button = settings.BUTTON;
        this.subtract = settings.SUBTRACT;
        this.add = settings.ADD;
        this.more = settings.MORE;

        this.defType = "id";
        this.packageName = appCompatActivity.getPackageName();

        ArrayList<String> characteristicButtons = new ArrayList<>();
        ArrayList<String> characteristicFields = new ArrayList<>();

        // set button names
        characteristicButtons.add(button + subtract + more + characteristic.getShortName().toLowerCase());
        characteristicButtons.add(button + subtract + characteristic.getShortName().toLowerCase());
        characteristicButtons.add(button + add + characteristic.getShortName().toLowerCase());
        characteristicButtons.add(button + add + more + characteristic.getShortName().toLowerCase());

        // set field names
        characteristicFields.add("characteristicName_" + characteristic.getShortName().toLowerCase());
        characteristicFields.add("characteristicValue_" + characteristic.getShortName().toLowerCase());

        //set fields to correct value
        int nameId = getResources().getIdentifier(characteristicFields.get(0), defType, packageName);
        name = findViewById(nameId);

        int valueId = getResources().getIdentifier(characteristicFields.get(1), defType, packageName);
        value = findViewById(valueId);

    }

    public int add(int value) {
        this.value.setText(Math.addExact(getValue(), value));
        return getValue();
    }

    public int subtract (int value) {
        this.value.setText(Math.subtractExact(getValue(), value));
        return getValue();
    }

    private int getValue() {
        return Integer.parseInt(this.value.getText().toString());
    }

    private Resources getResources() {
        return  this.appCompatActivity.getResources();
    }

    private <T extends View> T findViewById(@IdRes int id) {
        return this.appCompatActivity.findViewById(id);
    }
}
