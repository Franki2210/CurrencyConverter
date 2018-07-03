package com.example.user.cbrinfo.converter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.ArrayAdapter;

import java.util.List;

public class ConverterSpinnerAdapter extends ArrayAdapter<String> {

    public ConverterSpinnerAdapter(@NonNull Context context, int resource) {
        super(context, resource);
    }

    public void setValuteNameList(List<String> valuteNameList) {
        clear();
        addAll(valuteNameList);
        notifyDataSetChanged();
    }
}
