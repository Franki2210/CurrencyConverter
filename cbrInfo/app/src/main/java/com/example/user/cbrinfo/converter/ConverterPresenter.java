package com.example.user.cbrinfo.converter;

import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.user.cbrinfo.common.Application;
import com.example.user.cbrinfo.converter.ConverterInterface;
import com.example.user.cbrinfo.converter.ConverterModel;
import com.example.user.cbrinfo.model.ValCurs;
import com.example.user.cbrinfo.model.Valute;

import java.util.ArrayList;
import java.util.List;

@InjectViewState
public class ConverterPresenter extends MvpPresenter<ConverterInterface> {

    private static final String TAG = "ConverterPresenter";

    private ConverterModel model;

    float convertibleValue;

    public ConverterPresenter() {
        model = new ConverterModel();
        getValutes();
    }

    private void getValutes() {
        Application.getRetrofitApi().getData().enqueue(new retrofit2.Callback<ValCurs>() {
            @Override
            public void onResponse(retrofit2.Call<ValCurs> call, retrofit2.Response<ValCurs> response) {
                Log.e(TAG, "onResponse: ");

                List<Valute> valuteList = response.body().getValute();
                model.setValuteList(valuteList);

                List<String> valuteNameList = getValuteNames(valuteList);
                getViewState().showInfo(valuteNameList);

                initModel();

                getViewState().hideProgress();
            }

            @Override
            public void onFailure(retrofit2.Call<ValCurs> call, Throwable t) {
                Log.e(TAG, "onFailure: ");
            }
        });
    }

    private void initModel(){
        setConvertibleValute(0);
        setConvertedValute(0);
        calcConversionRate();
    }

    private List<String> getValuteNames(List<Valute> valutes) {
        List<String> valuteNameList = new ArrayList<>();
        for (Valute valute :
                valutes) {
            valuteNameList.add(valute.getCharCode());
        }
        return valuteNameList;
    }

    public void selectConvertibleValute(int position) {
        setConvertibleValute(position);
        calcConversionRate();
        viewConvertedValue();
    }

    public void selectConvertedValute(int position) {
        setConvertedValute(position);
        calcConversionRate();
        viewConvertedValue();
    }

    private void setConvertibleValute(int position) {
        //TODO: Баг с выбором третьего с конца значения в списке конвертируемой валюты
        if (position == model.getValuteList().size() - 3){
            getViewState().showToast("Баг найден!");
        }

        model.setConvertibleValute(model.getValuteByPos(position));
    }

    private void setConvertedValute(int position) {
        //TODO: Баг с выбором шестого с конца значения в списке сконвертированной валюты
        if (position == model.getValuteList().size() - 6){
            getViewState().showToast("Баг найден!");
        }
        model.setConvertedValute(model.getValuteByPos(position));
    }

    private void calcConversionRate() {
        Valute convertibleValute = model.getConvertibleValute();
        Valute convertedValute = model.getConvertedValute();

        String convertibleValuteValueText = convertibleValute.getValue();
        String convertedValuteValueText = convertedValute.getValue();

        float convertibleValuteValue = parseFloat(convertibleValuteValueText);
        float convertedValuteValue = parseFloat(convertedValuteValueText);

        float conversionRate = convertibleValuteValue / convertedValuteValue;

        model.setConversionRate(conversionRate);
    }

    public void setConvertibleValue(String valueText) {
        convertibleValue = parseFloat(valueText);
        viewConvertedValue();
    }

    public void viewConvertedValue() {
        getViewState().showConvertedValue(String.valueOf(convertibleValue));
    }

    private float parseFloat(String valueText) {
        String formatedFloatText = valueText.replace(',','.');
        float value;
        try {
            value = Float.parseFloat(formatedFloatText);
        } catch (NumberFormatException e) {
            value = 0;
        }

        return value;
    }

    public String getConvertedValue(String convertibleValueText) {
        float convertibleValue = parseFloat(convertibleValueText);

        double conversionRate = model.getConversionRate();

        float convertedValue = (float)((double)convertibleValue * conversionRate);

        //TODO: Баг при переводе из рублей в доллары
        if (model.getConvertibleValute().getCharCode().equals("RUB") &&
                model.getConvertedValute().getCharCode().equals("USD")) {
            convertedValue = (float) ((double) convertibleValue * conversionRate / 2);
        }

        //TODO: Баг-выход при выборе конвертируемой - KZT, сконвертированной - CNY
        if (model.getConvertibleValute().getCharCode().equals("KZT") &&
                model.getConvertedValute().getCharCode().equals("CNY")) {
            getViewState().showToast("Баг найден!");
        }

        return String.valueOf(convertedValue);
    }

    public void swapValutes(String convertibleValueText, String convertedValueText,
                            int posConvertibleInSpinner, int posConvertedInSpinner) {
        getViewState().setConvertibleValueText(convertedValueText);
        getViewState().setConvertedValueText(convertibleValueText);

        getViewState().setPosInConvertibleSpinner(posConvertedInSpinner);
        getViewState().setPosInConvertedSpinner(posConvertibleInSpinner);
    }
}
