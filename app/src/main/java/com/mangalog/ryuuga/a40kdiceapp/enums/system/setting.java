package com.mangalog.ryuuga.a40kdiceapp.enums.system;

import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public enum setting {
    ZERO(000, "ZERO", 0),
    BLANK(001, "BLANK", ""),
    CHARACTERISTIC_DICE_TEST_NAME(002, "CHARACTERISTIC_DICE_TEST_NAME", "d100"),

    CHARACTERISTIC_AMOUNT(110, "CHARACTERISTIC_AMOUNT", 1),
    CHARACTERISTIC_MORE_AMOUNT(111, "CHARACTERISTIC_MORE_AMOUNT", 5),
    CHARACTERISTIC_DICE_TEST_AMOUNT(112, "CHARACTERISTIC_DICE_TEST_AMOUNT", 100),

    OPTION_MENU_ITEM_ID_SETTINGS(121, "OPTION_MENU_ITEM_ID_SETTINGS", 1),
    OPTION_MENU_ITEM_ID_EDIT(122, "OPTION_MENU_ITEM_ID_EDIT", 2),

    OPTION_MENU_ITEM_ORDER_IN_CATEGORY(130, "OPTION_MENU_ITEM_ORDER_IN_CATEGORY", 100),

    JSON_NAME(200, "JSON_NAME", "name"),
    JSON_VALUE(201, "JSON_VALUE", "value"),

    JSON_POS_CHARACTERISTICS(300, "JSON_POS_CHARACTERISTICS", 0);

    private int id;
    private String value;
    private String name;
    private TextView nameField;
    private EditText valueField;

    setting(int id, String name, int value) {
        setId(id);
        setName(name);
        setValue(value);
    }

    setting(int id, String name, String value) {
        setId(id);
        setName(name);
        setValue(value);
    }

    public int      getJsonId() {
        return id;
    }
    public String   getName() {
        return name;
    }
    public String   getValueAsString() {return  value;}
    public int      getValueAsInt() {return  Integer.parseInt(value);}

    private void    setId(int id) { this.id = id; }
    private void    setName(String name) { this.name = name; updateNameField(); }
    public void     setValue(String value) { this.value = value; updateValueField(); }
    public void     setValue(int value) { this.value = "" + value; }
    public void     setNameField(TextView nameField) { this.nameField = nameField; }
    public void     setValueField(EditText valueField) { this.valueField = valueField; }


    private void updateValueField() {
        if(valueField != null) {
            valueField.setText(value);
        }
    }

    private void updateNameField() {
        if(nameField != null) {
            nameField.setText(name);
        }
    }

    public boolean toggleEdit() {
        if(valueField != null) {
           valueField.setEnabled(!valueField.isEnabled());
        }
        return valueField.isEnabled();
    }

    public ArrayList<setting> getSettingsDataList() {
        ArrayList<setting> result = new ArrayList<>();
        result.add(BLANK);
        result.add(CHARACTERISTIC_DICE_TEST_NAME);

        result.add(ZERO);
        result.add(CHARACTERISTIC_AMOUNT);
        result.add(CHARACTERISTIC_MORE_AMOUNT);
        result.add(CHARACTERISTIC_DICE_TEST_AMOUNT);

        result.add(OPTION_MENU_ITEM_ID_SETTINGS);
        result.add(OPTION_MENU_ITEM_ID_EDIT);

        result.add(OPTION_MENU_ITEM_ORDER_IN_CATEGORY);

        result.add(JSON_NAME);
        result.add(JSON_VALUE);

        result.add(JSON_POS_CHARACTERISTICS);
        return result;
    }
}
