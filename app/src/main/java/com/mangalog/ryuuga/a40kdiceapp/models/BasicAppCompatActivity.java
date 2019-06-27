package com.mangalog.ryuuga.a40kdiceapp.models;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.mangalog.ryuuga.a40kdiceapp.controllers.main.BasicStorageManager;
import com.mangalog.ryuuga.a40kdiceapp.controllers.main.GeneralStorageManager;
import com.mangalog.ryuuga.a40kdiceapp.system.Settings;

public class BasicAppCompatActivity extends AppCompatActivity {

    protected Settings settings;
    protected GeneralStorageManager storageManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        settings = Settings.getSettings();
        getStorageManager();
    }

    private GeneralStorageManager getStorageManager() {
        if (storageManager == null) {
            storageManager = new GeneralStorageManager(this);
        }
        return storageManager;
    }
}
