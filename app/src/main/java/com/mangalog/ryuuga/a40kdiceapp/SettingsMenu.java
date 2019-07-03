package com.mangalog.ryuuga.a40kdiceapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.mangalog.ryuuga.a40kdiceapp.models.menus.BasicDrawerMenu;

public class SettingsMenu extends BasicDrawerMenu {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_menu);
    }
}
