package com.mangalog.ryuuga.a40kdiceapp.models;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.mangalog.ryuuga.a40kdiceapp.R;
import com.mangalog.ryuuga.a40kdiceapp.controllers.main.StorageManager;
import com.mangalog.ryuuga.a40kdiceapp.system.Settings;

public class BasicAppCompatActivity extends AppCompatActivity {

    protected Settings settings;
    protected StorageManager storageManager;
    private static StorageManager privateStorageManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        settings = Settings.getSettings();
        getStorageManager();
//        privateStorageManager.save("test");
    }

    private StorageManager getStorageManager() {
        if (privateStorageManager == null) {
            privateStorageManager = new StorageManager(this);
        }
        return privateStorageManager;
    }
}
