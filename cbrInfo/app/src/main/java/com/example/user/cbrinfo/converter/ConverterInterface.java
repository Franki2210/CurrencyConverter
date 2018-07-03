package com.example.user.cbrinfo.converter;

import com.arellomobile.mvp.MvpView;

import java.util.List;

public interface ConverterInterface extends MvpView {
    void showInfo(List<String> data);
    void showConvertedValue(String value);
    void setPosInConvertibleSpinner(int position);
    void setPosInConvertedSpinner(int position);
    void setConvertibleValueText(String value);
    void setConvertedValueText(String value);
    void hideProgress();
    void showProgress(String message);
    void showToast(String message);
}
