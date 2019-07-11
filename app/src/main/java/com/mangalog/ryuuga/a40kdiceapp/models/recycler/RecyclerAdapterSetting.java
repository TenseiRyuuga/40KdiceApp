package com.mangalog.ryuuga.a40kdiceapp.models.recycler;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mangalog.ryuuga.a40kdiceapp.R;
import com.mangalog.ryuuga.a40kdiceapp.enums.system.setting;
import com.mangalog.ryuuga.a40kdiceapp.system.Settings;

import java.util.ArrayList;

public class RecyclerAdapterSetting extends RecyclerView.Adapter<RecyclerAdapterSetting.RecyclerItemViewHolder> {
    private Settings settings;
    private ArrayList<setting> settingsList;
    private int mLastPosition = 0;

    public RecyclerAdapterSetting(Settings settings) {
        this.settings = settings;
        this.settingsList = this.settings.getSettingsList();
    }

    @Override
    @NonNull
    public RecyclerItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_row_setting, parent, false);
        return new RecyclerItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerItemViewHolder holder, final int position) {
        Log.d("onBindViewHoler ", settingsList.size() + "");
        final setting setting = settingsList.get(position);
        setting.setNameField(holder.settingNameField);
        setting.setValueField(holder.settingValueField);

        holder.settingNameField.setText(setting.getName());
        holder.settingValueField.setText(setting.getValueAsString());

//        final Button button_diceRoll = holder.button_diceRoll;
//
        holder.settingValueField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                setting.setValue(s.toString());
            }
        });

        mLastPosition = position;
    }

    @Override
    public int getItemCount() {
        return(null != settingsList ? settingsList.size():0);
    }

    public void notifyData(Settings settings) {
        Log.d("notifyData ", settings.getSettingsList().size() + "");
        this.settingsList = settings.getSettingsList();
        notifyDataSetChanged();
    }

    public class RecyclerItemViewHolder extends RecyclerView.ViewHolder {
        private final TextView settingNameField;
        private final EditText settingValueField;
        private String name;
        private String value;
        private LinearLayout mainLayout;

        RecyclerItemViewHolder(final View parent) {
            super(parent);
            mainLayout = parent.findViewById(R.id.recyclerview_row_setting);

            settingNameField = parent.findViewById(R.id.settingName);
            settingValueField = parent.findViewById(R.id.settingValue);
        }
    }


}
