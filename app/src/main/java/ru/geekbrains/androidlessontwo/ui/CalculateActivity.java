package ru.geekbrains.androidlessontwo.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import ru.geekbrains.androidlessontwo.R;
import ru.geekbrains.androidlessontwo.model.Operate;
import ru.geekbrains.androidlessontwo.model.SimpleCalculateImpl;
import ru.geekbrains.androidlessontwo.model.ThemeRepository;
import ru.geekbrains.androidlessontwo.model.ThemeRepositoryImpl;
import ru.geekbrains.androidlessontwo.presenter.CalculatePresenter;
import ru.geekbrains.androidlessontwo.presenter.CalculatePresenterImpl;

import java.util.HashMap;
import java.util.Map;

public class CalculateActivity extends AppCompatActivity implements CalculateView {

    private static final String CALCULATE_PRESENTER = "presenter";

    private ActivityResultLauncher<Intent> startSelectTheme;
    private CalculatePresenter presenter;
    private TextView resultView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ThemeRepository repository = ThemeRepositoryImpl.getInstance(this);
        setTheme(repository.getSaveTheme().getThemeResource());

        setContentView(R.layout.activity_calculate);

        resultView = findViewById(R.id.key_data_out);

        if (savedInstanceState == null) {
            presenter = new CalculatePresenterImpl(new SimpleCalculateImpl(), CalculateActivity.this);
        } else {
            presenter = savedInstanceState.getParcelable(CALCULATE_PRESENTER);
            presenter.onResume(new SimpleCalculateImpl(), this);
        }


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

        findViewById(R.id.key_equal).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.onEqualClick();
            }
        });

        findViewById(R.id.key_dot).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.onDotClick();
            }
        });

        findViewById(R.id.key_reset).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.onResetClick();
            }
        });

        startSelectTheme = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == RESULT_OK) {
                            CalculateActivity.this.recreate();
                        }
                    }
                });

        findViewById(R.id.key_select_theme).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startSelectTheme.launch(new Intent(CalculateActivity.this, SelectThemeActivity.class));
            }
        });

    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putParcelable(CALCULATE_PRESENTER, (CalculatePresenterImpl) presenter);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void showResult(String result) {
        resultView.setText(result);
    }
}