package ru.geekbrains.androidlessontwo;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class CalculateActivity extends AppCompatActivity implements CalculateView {

    private TextView resultView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculate);

        resultView = findViewById(R.id.key_data_out);
    }

    @Override
    public void showResult(String result) {
        resultView.setText(result);
    }
}