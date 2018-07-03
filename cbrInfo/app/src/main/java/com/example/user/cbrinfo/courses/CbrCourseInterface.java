package com.example.user.cbrinfo.courses;

import com.arellomobile.mvp.MvpView;
import com.example.user.cbrinfo.model.Valute;

import java.util.List;

public interface CbrCourseInterface extends MvpView {
    void showInfo(List<Valute> data);
    void showProgress();
    void hideProgress();
}
