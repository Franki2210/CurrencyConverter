package com.example.user.cbrinfo.courses;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.example.user.cbrinfo.R;
import com.example.user.cbrinfo.model.Valute;

import java.util.List;

public class CoursesActivity extends MvpAppCompatActivity implements CbrCourseInterface, SwipeRefreshLayout.OnRefreshListener {

    @InjectPresenter
    CbrCoursePresenter cbrCoursePresenter;

    CoursesAdapter coursesAdapter ;
    ProgressDialog progressDialog;
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courses);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        swipeRefreshLayout = findViewById(R.id.swipe_refresh_container);
        swipeRefreshLayout.setOnRefreshListener(this);

        coursesAdapter = new CoursesAdapter();
        RecyclerView recyclerView = findViewById(R.id.recyclerViewCourses);
        recyclerView.setAdapter(coursesAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
    }

    //TODO: Баг - при сворачивании приложения и разворачивании, экран с курсами валют закрывается
    //TODO: Баг - при переходе в ландшафтный режим, экран с курсами валют закрывается
    @Override
    protected void onPause() {
        super.onPause();
        showToast("Баг найден!");
        finish();
    }

    //TODO: Баг при нажатии кнопки увеличении громкости приложение закрывается
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event){
        switch (keyCode) {
            case KeyEvent.KEYCODE_VOLUME_UP:
                showToast("Баг найден!");
                return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    public void showToast(String message) {
        Toast toast = Toast.makeText(this, message, Toast.LENGTH_SHORT);
        toast.show();
    }

    private static final String TAG = "CoursesActivity";
    @Override
    public void showInfo(List<Valute> data) {
        coursesAdapter.setCourseList(data);
    }

    @Override
    public void hideProgress() {
        if (progressDialog != null)
            progressDialog.dismiss();
    }

    @Override
    public void showProgress() {
        progressDialog = ProgressDialog.show(this, "", "Загрузка валютных курсов...");
    }

    @Override
    public void onRefresh() {
        cbrCoursePresenter.loadCourses();
        swipeRefreshLayout.setRefreshing(false);
    }
}
