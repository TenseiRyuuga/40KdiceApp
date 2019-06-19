package com.mangalog.ryuuga.a40kdiceapp.controllers;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.mangalog.ryuuga.a40kdiceapp.R;
import com.mangalog.ryuuga.a40kdiceapp.models.dicebag.Dice;
import com.mangalog.ryuuga.a40kdiceapp.models.dicebag.DiceBag;
import com.mangalog.ryuuga.a40kdiceapp.enums.dice.calculationModifier;
import com.mangalog.ryuuga.a40kdiceapp.enums.dice.calculationTarget;
import com.mangalog.ryuuga.a40kdiceapp.enums.dice.calculationType;
import com.mangalog.ryuuga.a40kdiceapp.system.Settings;

public class Calculator extends AppCompatActivity {

    private Settings settings = Settings.getSettings();
    private DiceBag diceBag = DiceBag.getDiceBag();
    private calculationModifier modifier = calculationModifier.ADD;
    private StringBuilder result_text = new StringBuilder();
    private String number = settings.BLANK;
    private calculationTarget target = calculationTarget.A;
    private int a = settings.ZERO;
    private int b = settings.ZERO;
    private int result = settings.ZERO;
    private int sides = settings.ZERO;
    private boolean locked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dice);
    }

    /** Called when the user taps the 1 button */
    public void onButtonC(View view) {
        clearAll();
    }

    /** Called when the user taps the 1 button */
    public void onButton1(View view) {
        add(1);
    }

    /** Called when the user taps the 2 button */
    public void onButton2(View view) {
        add(2);
    }

    /** Called when the user taps the 3 button */
    public void onButton3(View view) {
        add(3);
    }

    /** Called when the user taps the 4 button */
    public void onButton4(View view) {
        add(4);
    }

    /** Called when the user taps the 5 button */
    public void onButton5(View view) {
        add(5);
    }

    /** Called when the user taps the 6 button */
    public void onButton6(View view) {
        add(6);
    }

    /** Called when the user taps the 7 button */
    public void onButton7(View view) {
        add(7);
    }

    /** Called when the user taps the 8 button */
    public void onButton8(View view) {
        add(8);
    }

    /** Called when the user taps the 9 button */
    public void onButton9(View view) {
        add(9);
    }

    /** Called when the user taps the 0 button */
    public void onButton0(View view) {
        add(0);
    }

    /** Called when the user taps the d2 button */
    public void onButtonD2(View view) {
        onButtonD(2);
    }

    /** Called when the user taps the d3 button */
    public void onButtonD3(View view) {
        onButtonD(3);
    }

    /** Called when the user taps the d4 button */
    public void onButtonD4(View view) {
        onButtonD(4);
    }

    /** Called when the user taps the d5 button */
    public void onButtonD5(View view) {
        onButtonD(5);
    }

    /** Called when the user taps the d6 button */
    public void onButtonD6(View view) {
        onButtonD(6);
    }

    /** Called when the user taps the d8 button */
    public void onButtonD8(View view) {
        onButtonD(8);
    }

    /** Called when the user taps the d10 button */
    public void onButtonD10(View view) {
        onButtonD(10);
    }

    /** Called when the user taps the d12 button */
    public void onButtonD12(View view) {
        onButtonD(12);
    }

    /** Called when the user taps the d20 button */
    public void onButtonD20(View view) {
        onButtonD(20);
    }

    /** Called when the user taps the d100 button */
    public void onButtonD100(View view) {
        onButtonD(100);
    }

    /** Called when the user taps the - button */
    public void onButtonSubtract(View view) {
        addModifier(calculationModifier.SUBTRACT);
    }

    /** Called when the user taps the + button */
    public void onButtonAdd(View view) {
        addModifier(calculationModifier.ADD);
    }

    /** Called when the user taps the = button */
    public void onButtonIs(View view) {
        if(!locked) {
            calculate(calculationType.NUMBER);
            addIsResult();
            clearNumber();
        }
    }

   private void onButtonD(int sides) {
       this.sides = sides;
       calculate(calculationType.DICE);
       this.locked = false;
       addIsResult();
   }

    private StringBuilder add(int n) {
        locked = false;
        number += settings.BLANK + n;

        if (target.equals(calculationTarget.A)) {
            a = Integer.parseInt(number);
        }
        else if (target.equals(calculationTarget.B)) {
            b = Integer.parseInt(number);
        }

        result_text.append(n);
        refresh();

        return result_text;
    }

    private void addModifier(calculationModifier mod) {
        this.modifier = mod;
        this.result_text.append(mod.getString());
        clearNumber();
        switchTarget(calculationTarget.B);
        refresh();
    }

    private int addUp() {
        return Math.addExact(a, b);
    }

    private int subtract() {
        return Math.subtractExact(a, b);
    }

    private String clearNumber() {
        number = settings.BLANK;

        return number;
    }

    private StringBuilder clearResultText() {
        TextView textView_result = findViewById(R.id.textView_result);
        result_text = new StringBuilder(settings.BLANK);
        textView_result.setText(result_text);

        return result_text;
    }

    private void clearAll() {
        clearResultText();
        clearNumber();
        a = settings.ZERO;
        b = settings.ZERO;
        result = settings.ZERO;
        sides = settings.ZERO;
        locked = false;
    }

    private calculationTarget switchTarget(calculationTarget newCalculationTarget) {
        target = newCalculationTarget;
        return target;
    }

    private int calculateNumber() {
        switch (modifier) {
            case ADD:
                result = addUp();
                break;
            case SUBTRACT:
                result = subtract();
                break;
        }

        return result;
    }

    private int calculateDice() {
        if(target.equals(calculationTarget.A)) {
            if(a == 0) {
                add(1);
            }
            int numberOfDice = a;

            Dice dice = diceBag.getDice(sides, "d" + sides);

            result_text.append(dice.getDiceName());
            result_text.append("(");
            for (int i = 0; numberOfDice > i; i++) {
                if (i == 0) {
                    a = dice.roll();
                    result_text.append(a);
                    calculate(calculationType.NUMBER);
                }
                else {
                    b = dice.roll();
                    result_text.append(",");
                    result_text.append(b);
                    a = calculate(calculationType.NUMBER);
                }
            }
            result_text.append(")");
        }

        return result;
    }

    // calculate the result value based on the specified calculation
    private int calculate(calculationType type) {
        int temp = 0;

        switch (type) {
            case NUMBER:
                result = calculateNumber();
                break;
            case DICE:
                result = calculateDice();
                break;
        }

        return result;
    }

    // show the result by adding
    private StringBuilder addIsResult() {
        if (!locked) {
            result_text.append(" = ");
            result_text.append(result);

            a = result;
            b = settings.ZERO;
            switchTarget(calculationTarget.A);
            clearNumber();
            locked = true;
        }
        refresh();

        return result_text;
    }

    // refresh the screentext
    private StringBuilder refresh() {
        TextView textView_result = findViewById(R.id.textView_result);
        textView_result.setText(result_text);

        return result_text;
    }
}
