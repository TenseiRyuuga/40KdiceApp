package com.mangalog.ryuuga.a40kdiceapp.models.menus;

import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.navigation.NavigationView;
import com.mangalog.ryuuga.a40kdiceapp.R;
import com.mangalog.ryuuga.a40kdiceapp.controllers.Calculator;
import com.mangalog.ryuuga.a40kdiceapp.controllers.main.MainActivity;
import com.mangalog.ryuuga.a40kdiceapp.models.BasicAppCompatActivity;
import com.mangalog.ryuuga.a40kdiceapp.system.Settings;

public class BasicDrawerMenu extends BasicAppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.basic_drawer_menu);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.basic_drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.basic_drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        menu.add(Menu.NONE, settings.OPTION_MENU_ITEM_ID_SETTINGS, settings.OPTION_MENU_ITEM_ORDER_IN_CATEGORY, "Settings");
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
        if (id == settings.OPTION_MENU_ITEM_ID_SETTINGS) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Intent intent = new Intent(this, MainActivity.class);
        switch(id) {
            case R.id.nav_home:
                intent = new Intent(this, MainActivity.class);
                break;
            case R.id.nav_dice:
                intent = new Intent(this, Calculator.class);
                break;
            case R.id.nav_characteristics:
                intent = new Intent(this, BasicDrawerMenu.class);
                break;
            case R.id.nav_test:
                intent = new Intent(this, MainActivity.class);
                break;
            case R.id.nav_settings:
                intent = new Intent(this, Settings.class);
                break;
        }

        startActivity(intent);

        DrawerLayout drawer = findViewById(R.id.basic_drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public boolean checkIfInMenu(String string) {
//        for()
        return true;
    }
}
