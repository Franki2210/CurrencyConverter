package com.example.user.cbrinfo.courses;

import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.user.cbrinfo.common.Application;
import com.example.user.cbrinfo.model.ValCurs;


@InjectViewState
public class CbrCoursePresenter extends MvpPresenter<CbrCourseInterface> {
    private static final String TAG = "CbrCoursePresenter";

    public CbrCoursePresenter() {
        loadCourses();
    }

    public void loadCourses() {
        getViewState().showProgress();
        Application.getRetrofitApi().getData().enqueue(new retrofit2.Callback<ValCurs>() {
            @Override
            public void onResponse(retrofit2.Call<ValCurs> call, retrofit2.Response<ValCurs> response) {
                Log.e(TAG, "onResponse: ");
                getViewState().showInfo(response.body().getValute());
                getViewState().hideProgress();
            }

            @Override
            public void onFailure(retrofit2.Call<ValCurs> call, Throwable t) {
                Log.e(TAG, "onFailure: ");
            }
        });
    }
}