package com.mangalog.ryuuga.a40kdiceapp.controllers;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewStub;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mangalog.ryuuga.a40kdiceapp.R;
import com.mangalog.ryuuga.a40kdiceapp.controllers.main.SettingsStorageManager;
import com.mangalog.ryuuga.a40kdiceapp.models.menus.BasicDrawerMenu;
import com.mangalog.ryuuga.a40kdiceapp.models.recycler.RecyclerAdapterSetting;

import org.json.JSONArray;

public class SettingsMenu extends BasicDrawerMenu {

    private SettingsStorageManager settingsStorageManager;

    private RecyclerView recyclerView;
    private RecyclerAdapterSetting recyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Set The View
        ViewStub stub = findViewById(R.id.basic_bar_drawer_stub);
        stub.setLayoutResource(R.layout.activity_settings_menu);
        stub.inflate();
        //Set Parameters

        settingsStorageManager = SettingsStorageManager.getSettingsStorageManager(this);
        JSONArray settingsJson = settingsStorageManager.loadSettings();
        if(settingsJson == null) {
            settingsJson = settings.getJSONArray();
        }
        settings.setJSONArray(settingsJson);

        recyclerView = findViewById(R.id.recyclerViewSettings);

        recyclerAdapter = new RecyclerAdapterSetting(this.settings);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(recyclerAdapter);

        recyclerAdapter.notifyData(settings);
    }

    public void onButtonEdit(View view) {
        if(settings.toggleEdit() == false) {
            settingsStorageManager.save(settings.getJSONArray());
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        super.onCreateOptionsMenu(menu);
        menu.add(Menu.NONE, settings.OPTION_MENU_ITEM_ID_EDIT(), settings.OPTION_MENU_ITEM_ORDER_IN_CATEGORY(), "Edit");
        getMenuInflater().inflate(R.menu.basic_drawer_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == settings.OPTION_MENU_ITEM_ID_EDIT()) {
            onButtonEdit(item.getActionView());
        }

        return super.onOptionsItemSelected(item);
    }
}
