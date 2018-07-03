package com.example.user.cbrinfo;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.user.cbrinfo.converter.ConverterActivity;
import com.example.user.cbrinfo.courses.CoursesActivity;

public class MainMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //TODO: Баг с лэндскейпом
        if (savedInstanceState == null &&
            getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE ) {
            showToast("Баг найден!");
            finish();
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        //TODO: Баг с интернетом
        if (!isOnline()) {
            showToast("Баг найден!");
            finish();
        }
    }

    public boolean isOnline() {
        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

    public void showToast(String message) {
        Toast toast = Toast.makeText(this, message, Toast.LENGTH_SHORT);
        toast.show();
    }

    public void onClickCourses(View view) {
        Intent intent = new Intent(this, CoursesActivity.class);
        startActivity(intent);
    }

    public void onClickConverter(View view) {
        Intent intent = new Intent(this, ConverterActivity.class);
        startActivity(intent);
    }
}
