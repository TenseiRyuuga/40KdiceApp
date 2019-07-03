package com.mangalog.ryuuga.a40kdiceapp.models.recycler;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mangalog.ryuuga.a40kdiceapp.models.characteristic.Characteristic;
import com.mangalog.ryuuga.a40kdiceapp.R;
import com.mangalog.ryuuga.a40kdiceapp.models.characteristic.Characteristics;
import com.mangalog.ryuuga.a40kdiceapp.models.dicebag.DiceBag;
import com.mangalog.ryuuga.a40kdiceapp.system.Settings;

import java.util.ArrayList;

public class RecyclerAdapterCharacteristic extends RecyclerView.Adapter<RecyclerAdapterCharacteristic.RecyclerItemViewHolder> {
    private Settings settings = Settings.getSettings();
    private Characteristics characteristics;
    private ArrayList<Characteristic> characteristicList;
    private int mLastPosition = 0;
    private DiceBag diceBag;

    public RecyclerAdapterCharacteristic(Characteristics characteristics) {
        this.characteristics = characteristics;
        this.characteristicList = this.characteristics.getCharacteristicsList();
    }

    @Override
    @NonNull
    public RecyclerItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_row_characteristic, parent, false);
        diceBag = DiceBag.getDiceBag();
        return new RecyclerItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerItemViewHolder holder, final int position) {
        Log.d("onBindViewHoler ", characteristicList.size() + "");
        final Characteristic characteristic = characteristicList.get(position);
        characteristic.addButton(holder.subtract_more);
        characteristic.addButton(holder.subtract);
        characteristic.addButton(holder.add);
        characteristic.addButton(holder.add_more);
        characteristic.setNameField(holder.characteristicName);
        characteristic.setValueField(holder.characteristicValue);
        characteristic.addButton(holder.button_diceRoll);

        holder.characteristicName.setText(characteristic.getName());
        final EditText editTextValue = holder.characteristicValue;
        editTextValue.setText(characteristic.getValueAsString());

        final Button button_diceRoll = holder.button_diceRoll;

        editTextValue.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                characteristic.setValueAsString(s.toString());
            }
        });

        holder.subtract_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v ) {
                characteristic.subtract(settings.CHARACTERISTIC_MORE_AMOUNT);
                editTextValue.setText(characteristic.getValueAsString());
            }
        });

        holder.subtract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v ) {
                characteristic.subtract(settings.CHARACTERISTIC_AMOUNT);
                editTextValue.setText(characteristic.getValueAsString());
            }
        });

        holder.add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v ) {
                characteristic.add(settings.CHARACTERISTIC_AMOUNT);
                editTextValue.setText(characteristic.getValueAsString());
            }
        });

        holder.add_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v ) {
                characteristic.add(settings.CHARACTERISTIC_MORE_AMOUNT);
                editTextValue.setText(characteristic.getValueAsString());
            }
        });

        holder.button_diceRoll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v ) {
                int diceRoll = diceBag.getDice(settings.CHARACTERISTIC_DICE_TEST_AMOUNT, settings.CHARACTERISTIC_DICE_TEST_NAME).roll();
                String text = settings.BLANK + diceRoll;
                button_diceRoll.setText(text);
                if(diceRoll > characteristic.getValue()) {
                    button_diceRoll.setBackgroundTintList(button_diceRoll.getResources().getColorStateList(R.color.colorTestFailed, button_diceRoll.getContext().getTheme()));
                }
                else if (diceRoll <= characteristic.getValue()) {
                    button_diceRoll.setBackgroundTintList((button_diceRoll.getResources().getColorStateList(R.color.colorTestPassed, button_diceRoll.getContext().getTheme())));
                }
            }
        });

        mLastPosition = position;
    }

    @Override
    public int getItemCount() {
        return(null != characteristicList ? characteristicList.size():0);
    }

    public void notifyData(Characteristics characteristics) {
        Log.d("notifyData ", characteristics.getCharacteristicsList().size() + "");
        this.characteristicList = characteristics.getCharacteristicsList();
        notifyDataSetChanged();
    }

    public class RecyclerItemViewHolder extends RecyclerView.ViewHolder {
        private final TextView characteristicName;
        private final EditText characteristicValue;
        private final Button subtract_more;
        private final Button subtract;
        private final Button add;
        private final Button add_more;
        private final Button button_diceRoll;
        private LinearLayout mainLayout;

        RecyclerItemViewHolder(final View parent) {
            super(parent);
            mainLayout = parent.findViewById(R.id.recyclerview_row_characteristic);

            characteristicName = parent.findViewById(R.id.characteristicName);
            characteristicValue = parent.findViewById(R.id.characteristicValue);
            subtract_more = parent.findViewById(R.id.button_characteristic_subtract_more);
            subtract = parent.findViewById(R.id.button_characteristic_subtract);
            add = parent.findViewById(R.id.button_characteristic_add);
            add_more = parent.findViewById(R.id.button_characteristic_add_more);
            button_diceRoll = parent.findViewById(R.id.button_dice_roll);
        }
    }

    public Characteristics getCharacteristics() {
        return this.characteristics;
    }
}
