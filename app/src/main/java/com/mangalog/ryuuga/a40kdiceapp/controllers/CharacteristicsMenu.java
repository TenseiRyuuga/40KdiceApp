package com.mangalog.ryuuga.a40kdiceapp.controllers;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewStub;

import com.mangalog.ryuuga.a40kdiceapp.R;
import com.mangalog.ryuuga.a40kdiceapp.controllers.main.BasicStorageManager;
import com.mangalog.ryuuga.a40kdiceapp.controllers.main.GeneralStorageManager;
import com.mangalog.ryuuga.a40kdiceapp.models.characteristic.CharacteristicsInfoScreen;
import com.mangalog.ryuuga.a40kdiceapp.models.menus.BasicDrawerMenu;

public class CharacteristicsMenu extends BasicDrawerMenu {

    CharacteristicsInfoScreen characteristicsInfoScreen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Set The View
        ViewStub stub = findViewById(R.id.basic_bar_drawer_stub);
        stub.setLayoutResource(R.layout.characteristics_drawer_menu);
        stub.inflate();
        //Set Parameters
        this.characteristicsInfoScreen = new CharacteristicsInfoScreen(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        super.onCreateOptionsMenu(menu);
        menu.add(Menu.NONE, settings.OPTION_MENU_ITEM_ID_EDIT, settings.OPTION_MENU_ITEM_ORDER_IN_CATEGORY, "Edit");
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
        if (id == settings.OPTION_MENU_ITEM_ID_EDIT) {
                onButtonEdit(item.getActionView());
        }

        return super.onOptionsItemSelected(item);
    }

    public void onButtonEdit(View view) {
        this.characteristicsInfoScreen.onButtonEdit(view);
    }

    public GeneralStorageManager getStorageManager() {
        return this.storageManager;
    }


}
