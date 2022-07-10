package ru.geekbrains.androidlessontwo.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import ru.geekbrains.androidlessontwo.R;
import ru.geekbrains.androidlessontwo.model.Theme;
import ru.geekbrains.androidlessontwo.model.ThemeRepository;
import ru.geekbrains.androidlessontwo.model.ThemeRepositoryImpl;

import java.util.ArrayList;

public class SelectThemeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_theme);

        ListView layout = findViewById(R.id.theme_list);

        ThemeRepository repository = ThemeRepositoryImpl.getInstance(this);

        ArrayList<String> themeList = new ArrayList<>();
        for (Theme theme : repository.getAllThemes()) {
            themeList.add(getString(theme.getThemeName()));
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, themeList);

        layout.setAdapter(adapter);

        layout.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Theme[] themes = Theme.values();
                if (repository.getSaveTheme().equals(themes[i])) {
                    setResult(RESULT_CANCELED, new Intent());
                } else {
                    repository.saveTheme(themes[i]);
                    setResult(RESULT_OK, new Intent());
                }
                finish();
            }
        });
    }
}