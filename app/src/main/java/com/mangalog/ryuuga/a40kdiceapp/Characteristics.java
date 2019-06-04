package com.mangalog.ryuuga.a40kdiceapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.mangalog.ryuuga.a40kdiceapp.enums.system.Settings;

import java.util.ArrayList;

public class Characteristics extends AppCompatActivity {
    ArrayList<Characteristic> characteristicsList;

    Context context;
    ConstraintLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        layout = findViewById(R.id.LAYOUT_CHARACTERISTIC);
        setContentView(R.layout.activity_characteristics);
    }

    public void onButtonEdit(View view) {
        setAllCharacteristicButtons(View.GONE);
    }

    private void setAllCharacteristicButtons(int viewVisibility) {
        setButtonVisibility("button_add_ws", viewVisibility);

    }

    private void setCharacteristicButton(int viewVisibility) {
        setButtonVisibility("button_add_ws", viewVisibility);

    }

    private void setButtonVisibility (String buttonName, int visibility) {
        String packageName = this.getPackageName();
        String defType = "id";
        int id = getResources().getIdentifier(buttonName, defType, packageName);
        Button button = findViewById(id);
        button.setVisibility(visibility);
    }

    public void onButtonMore(View view) {
        System.out.println("onButtonMore pressed");
    }

    public void onButtonMagic(View view) {
        System.out.println("onButtonMagic pressed");
        Button button = new Button(Characteristics.this);
        ConstraintLayout.LayoutParams lp = new ConstraintLayout.LayoutParams(  ConstraintLayout.LayoutParams.WRAP_CONTENT,  ConstraintLayout.LayoutParams.WRAP_CONTENT );
        button.setLayoutParams(lp);
        button.setText(Settings.getSettings().BUTTON.toUpperCase());
        layout.addView(button);

    }
}
