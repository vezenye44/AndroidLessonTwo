package ru.geekbrains.androidlessontwo;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;
import java.util.Map;

public class CalculateActivity extends AppCompatActivity implements CalculateView {

    private CalculatePresenter presenter;
    private TextView resultView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculate);

        resultView = findViewById(R.id.key_data_out);

        Map<Integer, Integer> digits = new HashMap<>();
        digits.put(R.id.key_0, 0);
        digits.put(R.id.key_1, 1);
        digits.put(R.id.key_2, 2);
        digits.put(R.id.key_3, 3);
        digits.put(R.id.key_4, 4);
        digits.put(R.id.key_5, 5);
        digits.put(R.id.key_6, 6);
        digits.put(R.id.key_7, 7);
        digits.put(R.id.key_8, 8);
        digits.put(R.id.key_9, 9);

        View.OnClickListener digitClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.onDigitClick(digits.get(view.getId()));
            }
        };

        findViewById(R.id.key_0).setOnClickListener(digitClickListener);
        findViewById(R.id.key_1).setOnClickListener(digitClickListener);
        findViewById(R.id.key_2).setOnClickListener(digitClickListener);
        findViewById(R.id.key_3).setOnClickListener(digitClickListener);
        findViewById(R.id.key_4).setOnClickListener(digitClickListener);
        findViewById(R.id.key_5).setOnClickListener(digitClickListener);
        findViewById(R.id.key_6).setOnClickListener(digitClickListener);
        findViewById(R.id.key_7).setOnClickListener(digitClickListener);
        findViewById(R.id.key_8).setOnClickListener(digitClickListener);
        findViewById(R.id.key_9).setOnClickListener(digitClickListener);

        Map<Integer, Operate> operators = new HashMap<>();
        operators.put(R.id.key_add, Operate.ADD);
        operators.put(R.id.key_sub, Operate.SUB);
        operators.put(R.id.key_mul, Operate.MUL);
        operators.put(R.id.key_div, Operate.DIV);
        operators.put(R.id.key_equal, Operate.EQUAL);

        View.OnClickListener operateClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.onOperateClick(operators.get(view.getId()));
            }
        };

        findViewById(R.id.key_add).setOnClickListener(operateClickListener);
        findViewById(R.id.key_sub).setOnClickListener(operateClickListener);
        findViewById(R.id.key_mul).setOnClickListener(operateClickListener);
        findViewById(R.id.key_div).setOnClickListener(operateClickListener);
        findViewById(R.id.key_equal).setOnClickListener(operateClickListener);

    }

    @Override
    public void showResult(String result) {
        resultView.setText(result);
    }
}