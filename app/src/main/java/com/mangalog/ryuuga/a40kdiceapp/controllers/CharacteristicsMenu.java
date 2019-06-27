package com.mangalog.ryuuga.a40kdiceapp.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewStub;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;
import com.mangalog.ryuuga.a40kdiceapp.controllers.Calculator;
import com.mangalog.ryuuga.a40kdiceapp.controllers.main.MainActivity;
import com.mangalog.ryuuga.a40kdiceapp.R;
import com.mangalog.ryuuga.a40kdiceapp.models.characteristic.CharacteristicsInfoScreen;
import com.mangalog.ryuuga.a40kdiceapp.models.menus.BasicDrawerMenu;
import com.mangalog.ryuuga.a40kdiceapp.system.Settings;

public class CharacteristicsMenu extends BasicDrawerMenu {

    CharacteristicsInfoScreen characteristicsInfoScreen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewStub stub = findViewById(R.id.basic_bar_drawer_stub);
        stub.setLayoutResource(R.layout.characteristics_drawer_menu);
        stub.inflate();
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


}
