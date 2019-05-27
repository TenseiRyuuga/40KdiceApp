package com.mangalog.ryuuga.a40kdiceapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.icu.text.StringSearch;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * Version V0.05
 * Changes since last version:
 *
 */

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /** Called when the user taps the Send button */
    private boolean onSave(View view) {
        // retrieve data from field
        EditText editText = findViewById(R.id.editText);
        String data = editText.getText().toString();

        String filename = this.getFilesDir().getPath() + "40k.txt";

        try {

            Path path = Paths.get(filename);
            Path root = Paths.get(this.getFilesDir().getPath() + "Saves");
            Path dir = Files.createDirectory(root);
            Path fileToCreatePath = dir.resolve("40k.txt");
            System.out.println("File exists: " + Files.exists(fileToCreatePath));
            System.out.println("File to create path: " + fileToCreatePath);
            Path newFilePath = Files.createFile(fileToCreatePath);
            System.out.println("New file created: " + newFilePath);
            System.out.println("New File exists: " + Files.exists(newFilePath));
            System.out.println("New File isReadable: " + Files.isReadable(newFilePath));
            System.out.println("New File isWritable: " + Files.isWritable(newFilePath));
            System.out.println("New File isExecutable: " + Files.isExecutable(newFilePath));
            System.out.println("New File isHidden: " + Files.isHidden(newFilePath));
            System.out.println("New File isRegularFile: " + Files.isRegularFile(newFilePath));
            System.out.println("New File isDirectory: " + Files.isDirectory(newFilePath));

            Files.write(path, data.getBytes());
            return true;
        }
        catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /** Called when the user taps the Send button */
    private boolean onLoad(View view) {
        String filename = this.getFilesDir().getPath() + "40k.txt";
        TextView textview = findViewById(R.id.textView);
        textview.setText("");

//        File directory = this.getFilesDir();
//        File file = new File(directory, filename);

        Path path = Paths.get(filename);
        String data;
        try {
            data = Files.readAllLines(path, Charset.defaultCharset()).get(0);
            textview.setText(data);
            return true;
        }
        catch (IOException e) {
        e.printStackTrace();
        return false;
    }



        // Testing purposes only
//        try {
//            Path root = Paths.get(this.getFilesDir().getPath().toString() + "Saves");
//            Path dir = root;
//            Path fileToCreatePath = dir.resolve("40k.txt");
//            Path newFilePath = fileToCreatePath;
//            System.out.println("New file read: " + newFilePath);
//            System.out.println("New File exists: " + Files.exists(newFilePath));
//            System.out.println("New File isReadable: " + Files.isReadable(newFilePath));
//            System.out.println("New File isWritable: " + Files.isWritable(newFilePath));
//            System.out.println("New File isExecutable: " + Files.isExecutable(newFilePath));
//            System.out.println("New File isHidden: " + Files.isHidden(newFilePath));
//            System.out.println("New File isRegularFile: " + Files.isRegularFile(newFilePath));
//            System.out.println("New File isDirectory: " + Files.isDirectory(newFilePath));
//        }
//        catch (IOException e) {
//            e.printStackTrace();
//        }
        //
    }

    private boolean onDelete(View view) {
        String filename = this.getFilesDir().getPath() + "40k.txt";
        Path path = Paths.get(filename);
        try {
            System.out.println("File exists: " + Files.exists(path));
            Files.deleteIfExists(path);
            System.out.println("File exists: " + Files.exists(path));
        }
        catch(IOException e) {
            e.printStackTrace();
        }

        String dir = this.getFilesDir().getPath() + "40k.txt";
        Path dirPath = Paths.get(dir);
        try {
            System.out.println("File exists: " + Files.exists(dirPath));
            Files.deleteIfExists(dirPath);
            System.out.println("File exists: " + Files.exists(dirPath));
            return true;
        }
        catch(IOException e) {
            e.printStackTrace();
            return false;
        }
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

}
