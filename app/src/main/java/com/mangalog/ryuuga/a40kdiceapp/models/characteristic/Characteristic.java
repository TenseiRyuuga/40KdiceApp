package com.mangalog.ryuuga.a40kdiceapp.models.characteristic;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.mangalog.ryuuga.a40kdiceapp.enums.characteristics.characteristicsData;
import com.mangalog.ryuuga.a40kdiceapp.system.Settings;

import java.util.ArrayList;

public class Characteristic {

    private AppCompatActivity appCompatActivity;
    private Settings settings;

    private characteristicsData characteristicData;
    private ArrayList<Button> characteristicButtons;
    private TextView nameField;

    private int value;
    private String name;

    public Characteristic(AppCompatActivity appCompatActivity, characteristicsData characteristicData) {
        this.appCompatActivity = appCompatActivity;
        this.characteristicData = characteristicData;

        settings = Settings.getSettings();
        characteristicButtons = new ArrayList<>();

        //set fields to correct value
        name = characteristicData.getLongName();
        value = characteristicData.getValue();
    }

    public int add(int value) {
        this.value = Math.addExact(this.value, value);
        return this.value;
    }

    public int subtract (int value) {
        this.value = Math.subtractExact(this.value, value);
        return this.value;
    }

    public ArrayList<Button> addButton(Button button) {
        characteristicButtons.add(button);
        return characteristicButtons;
    }
    
    public int toggleButtonVisibility() {
        return toggleButtonsVisibility(characteristicButtons);
    }

    private int toggleButtonsVisibility (ArrayList<Button> buttons) {
        int result = -1;
        for (Button button: buttons) {
            result = toggleButtonVisibility(button);
        }
        return result;
    }

    private int toggleButtonVisibility (Button button) {
        int visibility = button.getVisibility();
        switch(visibility) {
            case View.VISIBLE:
                button.setVisibility(View.GONE);
                name = characteristicData.getShortName();
                break;

            case View.GONE:
                button.setVisibility(View.VISIBLE);
                name = characteristicData.getLongName();
                break;

            case View.INVISIBLE:
                break;
        }
        refresh();
        return button.getVisibility();
    }

    private void refresh() {
        nameField.setText(name);
    }

    // getters and setters
    public String getValueAsString() {
        return settings.BLANK + getValue();
    }

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }

    public void setValueAsString(String string) {
        setValueAsInt(Integer.parseInt(string));
    }

    private  void setValueAsInt(int value) {
        this.value = value;
    }

    public void setNameField(TextView nameField) {
        this.nameField = nameField;
    }
}
