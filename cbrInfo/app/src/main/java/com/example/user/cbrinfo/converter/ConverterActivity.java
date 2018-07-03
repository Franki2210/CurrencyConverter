package com.example.user.cbrinfo.converter;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.app.ProgressDialog;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.example.user.cbrinfo.R;
import com.example.user.cbrinfo.converter.ConverterSpinnerAdapter;
import com.example.user.cbrinfo.converter.ConverterInterface;
import com.example.user.cbrinfo.converter.ConverterPresenter;

import java.util.List;

public class ConverterActivity extends MvpAppCompatActivity implements ConverterInterface {

    private static final String TAG = "ConverterActivity";

    @InjectPresenter
    ConverterPresenter presenter;

    EditText convertibleEditText;
    TextView convertedTextView;

    Spinner convertibleSpinner;
    Spinner convertedSpinner;

    private ProgressDialog progressDialog;

    ConverterSpinnerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_converter);

        showProgress("Загрузка валютных курсов");

        init();
    }

    private void init() {
        adapter = new ConverterSpinnerAdapter(this, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        convertibleEditText = findViewById(R.id.convertible_text);
        convertedTextView = findViewById(R.id.converted_text);

        convertibleEditText.addTextChangedListener(convertibleTextWatcher);

        convertibleSpinner = findViewById(R.id.convertible_spinner);
        convertedSpinner = findViewById(R.id.converted_spinner);

        convertibleSpinner.setOnItemSelectedListener(onConvertibleSelectedListener);
        convertedSpinner.setOnItemSelectedListener(onConvertedSelectedListener);

        convertibleSpinner.setAdapter(adapter);
        convertedSpinner.setAdapter(adapter);
    }

    @Override
    public void hideProgress() {
        if (progressDialog != null)
            progressDialog.dismiss();
    }

    @Override
    public void showProgress(String message) {
        progressDialog = ProgressDialog.show(this, "", message);
    }

    @Override
    public void showToast(String message) {
        Toast toast = Toast.makeText(this, message, Toast.LENGTH_SHORT);
        toast.show();
    }

    TextWatcher convertibleTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            presenter.setConvertibleValue(s.toString());
        }

        @Override
        public void afterTextChanged(Editable s) {
        }
    };

    OnItemSelectedListener onConvertibleSelectedListener = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            presenter.selectConvertibleValute(position);
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {
        }
    };

    OnItemSelectedListener onConvertedSelectedListener = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            presenter.selectConvertedValute(position);
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {
        }
    };

    public void setPosInConvertibleSpinner(int position) {
        convertibleSpinner.setSelection(position);
    }

    public void setPosInConvertedSpinner(int position) {
        convertedSpinner.setSelection(position);
    }

    @Override
    public void setConvertibleValueText(String value) {
        convertibleEditText.setText(value);
    }

    @Override
    public void setConvertedValueText(String value) {
        convertedTextView.setText(value);
    }

    @Override
    public void showInfo(List<String> data) {
        adapter.setValuteNameList(data);
    }

    @Override
    public void showConvertedValue(String value) {
        convertedTextView.setText(presenter.getConvertedValue(value));
    }


    public void onClickSwap(View view) {
        presenter.swapValutes(
                convertibleEditText.getText().toString(),
                convertedTextView.getText().toString(),
                convertibleSpinner.getSelectedItemPosition(),
                convertedSpinner.getSelectedItemPosition());
    }
}
