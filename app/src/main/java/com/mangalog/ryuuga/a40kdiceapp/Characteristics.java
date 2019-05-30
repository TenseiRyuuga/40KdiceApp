package com.mangalog.ryuuga.a40kdiceapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.view.LayoutInflater;
import android.view.View;

import androidx.core.view.GravityCompat;
import androidx.appcompat.app.ActionBarDrawerToggle;

import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mangalog.ryuuga.a40kdiceapp.enums.characteristics.characteristicsData;

import java.util.ArrayList;
import java.util.List;

public class Characteristics extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer_menu);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.drawer_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_dice) {
            Intent intent = new Intent(this, Dice.class);
            startActivity(intent);
        } else if (id == R.id.nav_slideshow) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_tools) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void onCreateAndAppendListLayout()
    {
        ArrayList<characteristicsData> characteristicList = new ArrayList<>(); //populate it...
        characteristicList.add(characteristicsData.WS);
        characteristicList.add(characteristicsData.BS);
        characteristicList.add(characteristicsData.S);
        characteristicList.add(characteristicsData.T);
        characteristicList.add(characteristicsData.Agi);
        characteristicList.add(characteristicsData.Int);
        characteristicList.add(characteristicsData.Per);
        characteristicList.add(characteristicsData.WP);
        characteristicList.add(characteristicsData.Fel);
        TextView characteristicName = findViewById(R.id.characteristicName);
        EditText characteristicValue = findViewById(R.id.characteristicValue);
        characteristicName.setText(characteristicList.get(0).getShortName());
        characteristicValue.setText(characteristicList.get(0).getValue());
//        LinearLayout mainLayout = (LinearLayout) findViewById(R.id.drawer_layout);
//        LayoutInflater li =  (LayoutInflater)getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        for (int i = 0; i < characteristicList.size();  i++){
//            View tempView = li.inflate(R.layout.characteristic_template, null);
//            TextView textMain = (TextView) tempView.findViewById(R.id.characteristicName);
//
//            textMain.setText(characteristicList.get(i).getShortName());
//            mainLayout.addView(tempView);
//        }
    }
}
