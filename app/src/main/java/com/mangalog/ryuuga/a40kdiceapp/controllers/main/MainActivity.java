package com.mangalog.ryuuga.a40kdiceapp.controllers.main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;
import com.mangalog.ryuuga.a40kdiceapp.controllers.Calculator;
import com.mangalog.ryuuga.a40kdiceapp.models.menus.DrawerMenu;
import com.mangalog.ryuuga.a40kdiceapp.R;
import com.mangalog.ryuuga.a40kdiceapp.system.Settings;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Version V0.05
 * Changes since last version:
 *
 */

public class MainActivity extends AppCompatActivity  implements NavigationView.OnNavigationItemSelectedListener{

    String dirname;
    String fileName;
    String dirPathString;
    Path dirPath;
    Path filePath;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_drawer_menu);
        this.dirname = "Saves";
        this.fileName = "40k.txt";
        this.dirPathString = this.getFilesDir().getPath() + "/" + dirname;
        this.dirPath = Paths.get(dirPathString);
        this.filePath = dirPath.resolve(fileName);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.basic_drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view_main);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
//        navigationView.setNavigationItemSelectedListener(this);
    }

    /** Called when the user taps the Send button */
    private boolean onSave(View view) {
        // retrieve data from field
        EditText editText = findViewById(R.id.editText);
        String data = editText.getText().toString();



        try {
            this.dirPath = Paths.get(dirPathString);
            if(!Files.exists(this.dirPath)) {
                this.dirPath = Files.createDirectory(this.dirPath);
            }
            this.filePath = this.dirPath.resolve(fileName);
            System.out.println("File exists: " + Files.exists(this.filePath));
            System.out.println("File to create path: " + this.filePath);
            Path newFilePath = Files.createFile(this.filePath);
            System.out.println("New file created: " + newFilePath);
            System.out.println("New File exists: " + Files.exists(newFilePath));
            System.out.println("New File isReadable: " + Files.isReadable(newFilePath));
            System.out.println("New File isWritable: " + Files.isWritable(newFilePath));
            System.out.println("New File isExecutable: " + Files.isExecutable(newFilePath));
            System.out.println("New File isHidden: " + Files.isHidden(newFilePath));
            System.out.println("New File isRegularFile: " + Files.isRegularFile(newFilePath));
            System.out.println("New File isDirectory: " + Files.isDirectory(newFilePath));

            Files.write(this.filePath, data.getBytes());
            return true;
        }
        catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /** Called when the user taps the Send button */
    private boolean onLoad(View view) {
        TextView textview = findViewById(R.id.textView);
        textview.setText("");

        String data;
        try {
            data = Files.readAllLines(this.filePath, Charset.defaultCharset()).get(0);
            textview.setText(data);
            return true;
        }
        catch (IOException e) {
        e.printStackTrace();
        return false;
        }
    }

    private boolean onDelete(View view) {
        try {
            System.out.println("File exists: " + Files.exists(this.filePath));
            Files.deleteIfExists(this.filePath);
            System.out.println("File exists: " + Files.exists(this.filePath));
        }
        catch(IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    private void onNav(View view) {
        Intent intent = new Intent(this, DrawerMenu.class);
        startActivity(intent);
    }

    /** Called when the user taps the Save button */
    public void onButtonSave(View view) {
        onSave(view);
    }

    /** Called when the user taps the Load button */
    public void onButtonLoad(View view) {
        onLoad(view);
    }

    /** Called when the user taps the Nav button */
    public void onButtonDelete(View view) {
        onDelete(view);
    }

    /** Called when the user taps the Nav button */
    public void onButtonNav(View view) {
        onNav(view);
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
        switch (id) {
            case R.id.action_settings:
                return  true;
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
                intent = new Intent(this, DrawerMenu.class);
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
}
