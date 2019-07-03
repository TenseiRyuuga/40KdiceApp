package com.mangalog.ryuuga.a40kdiceapp.controllers.main;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;
import com.mangalog.ryuuga.a40kdiceapp.R;
import com.mangalog.ryuuga.a40kdiceapp.models.BasicAppCompatActivity;

import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class BasicStorageManager {

    private String dirname;
    private String fileName;
    private String dirPathString;
    private Path dirPath;
    private Path filePath;

    private BasicAppCompatActivity activity;

    BasicStorageManager(BasicAppCompatActivity activity, String dirname, String fileName) {
        this.activity = activity;
        this.dirname = dirname;
        this.fileName = fileName;
        updateDirPath();
        updateFilePath();
    }

    /** Called when the user taps the Send button */
    protected boolean save(String string) {
        // retrieve data from field
        try {
            this.dirPath = Paths.get(dirPathString);
            if(!Files.exists(this.dirPath)) {
                this.dirPath = Files.createDirectory(this.dirPath);
            }
            if(!Files.exists(this.filePath)) {
                this.filePath = Files.createFile(this.filePath);
            }
            Files.write(this.filePath, string.getBytes());
            return true;
        }
        catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /** Called when the user taps the Send button */
    protected List<String> load() {
        List<String> data = null;
        try {
            data = Files.readAllLines(this.filePath, Charset.defaultCharset());
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }

    public boolean delete() {
        try {
            Files.deleteIfExists(this.filePath);
        }
        catch(IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    private <T extends View> T findViewById(int id) {
        return activity.findViewById(id);
    }

    private void updateDirPath() {
        this.dirPathString = this.activity.getExternalFilesDir(null).getPath() + "/" + dirname;
        this.dirPath = Paths.get(dirPathString);
    }

    private void updateFilePath() {
        this.filePath = dirPath.resolve(fileName);
    }

    //Setters and Getters
    protected void setDirname(String dirname) {
        this.dirname = dirname;
        updateDirPath();
        updateFilePath();
    }

    protected void setFilename(String dirname) {
        this.dirname = dirname;
        updateFilePath();
    }

    protected String getDirname() {
        return this.dirname;
    }

    protected String getFilename() {
        return this.fileName;
    }
}
