package com.mangalog.ryuuga.a40kdiceapp;

import android.content.res.Resources;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.IdRes;
import androidx.appcompat.app.AppCompatActivity;

import com.mangalog.ryuuga.a40kdiceapp.enums.characteristics.characteristicsData;
import com.mangalog.ryuuga.a40kdiceapp.enums.system.Settings;

import java.util.ArrayList;

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
    private boolean buttonsVisible = false;

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

        characteristicButtons = new ArrayList<>();
        characteristicFields = new ArrayList<>();

        // set button names
        characteristicButtons.add(button + subtract + more + characteristic.getShortName().toLowerCase());
        characteristicButtons.add(button + subtract + characteristic.getShortName().toLowerCase());
        characteristicButtons.add(button + add + characteristic.getShortName().toLowerCase());
        characteristicButtons.add(button + add + more + characteristic.getShortName().toLowerCase());

        // set field names
        characteristicFields.add("characteristicName_" + characteristic.getShortName().toLowerCase());
        characteristicFields.add("characteristicValue_" + characteristic.getShortName().toLowerCase());

        //set fields to correct value
        name = findViewByString(characteristicFields.get(0));
        value = findViewByString(characteristicFields.get(1));

    }

    public int add(int value) {
        int number = Math.addExact(getValue(), value);
        String result = Settings.getSettings().BLANK + number;
        this.value.setText(result);
        return getValue();
    }

    public int subtract (int value) {
        this.value.setText(Math.subtractExact(getValue(), value));
        return getValue();
    }
    
    private int toggleButtonVisibility() {
        return toggleButtonsVisibility(characteristicButtons);
    }

    private int toggleButtonsVisibility (ArrayList<String> buttons) {
        int result = -1;
        for (String buttonName: buttons) {
            result = toggleButtonVisibility(buttonName);
        }
        return result;
    }

    private int toggleButtonVisibility (String buttonName) {
        String packageName = getPackageName();
        String defType = "id";
        int id = getResources().getIdentifier(buttonName, defType, packageName);
        Button button = findViewById(id);
        int visibility = button.getVisibility();
        switch(visibility) {
            case View.VISIBLE:
                button.setVisibility(View.GONE);
                name.setText(characteristic.getLongName());
                break;

            case View.GONE:
                button.setVisibility(View.VISIBLE);
                name.setText(characteristic.getShortName());
                break;
        }
        return button.getVisibility();
    }

    // getters and setters
    private int getValue() {
        return Integer.parseInt(this.value.getText().toString());
    }
    
    // AppCompatActivity methods
    private Resources getResources() {
        return  this.appCompatActivity.getResources();
    }

    private <T extends View> T findViewByString(String string) {
        int id = getResources().getIdentifier(string, defType, packageName);
        return findViewById(id);
    }

    private <T extends View> T findViewById(@IdRes int id) {
        return this.appCompatActivity.findViewById(id);
    }
    
    private String getPackageName() {
        return this.appCompatActivity.getPackageName();
    }

    private boolean containsButtonId(int buttonId) {
        for(String buttonName: characteristicButtons) {
            Button button = findViewByString(buttonName);
            Button listButton = findViewById(buttonId);
            int listId = button.getId();
            if(buttonId == listId) {
                return true;
            }
        }
        return false;
    }

    // getters and setters

}
