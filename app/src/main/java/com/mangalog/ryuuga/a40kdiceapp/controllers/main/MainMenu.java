package com.mangalog.ryuuga.a40kdiceapp.controllers.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewStub;
import android.widget.EditText;
import android.widget.TextView;

import com.mangalog.ryuuga.a40kdiceapp.controllers.CharacteristicsMenu;
import com.mangalog.ryuuga.a40kdiceapp.R;
import com.mangalog.ryuuga.a40kdiceapp.models.menus.BasicDrawerMenu;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class MainMenu extends BasicDrawerMenu {

    String dirname;
    String fileName;
    String dirPathString;
    Path dirPath;
    Path filePath;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Set The View
        ViewStub stub = findViewById(R.id.basic_bar_drawer_stub);
        stub.setLayoutResource(R.layout.activity_main);
        stub.inflate();

        //Set Parameters
        this.dirname = "Saves";
        this.fileName = "40k.txt";
        this.dirPathString = this.getFilesDir().getPath() + "/" + dirname;
        this.dirPath = Paths.get(dirPathString);
        this.filePath = dirPath.resolve(fileName);


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
            System.out.println("DELETE File found: " + Files.exists(this.filePath));
            Files.deleteIfExists(this.filePath);
            System.out.println("DELETE File deleted: " + !Files.exists(this.filePath));
        }
        catch(IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    private void onNav(View view) {
        Intent intent = new Intent(this, CharacteristicsMenu.class);
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
