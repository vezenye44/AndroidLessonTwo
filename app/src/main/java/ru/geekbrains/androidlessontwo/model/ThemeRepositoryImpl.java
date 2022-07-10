package ru.geekbrains.androidlessontwo.model;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Arrays;
import java.util.List;

public class ThemeRepositoryImpl implements ThemeRepository {

    private final static String SAVED_KEY = "SAVED_KEY";

    private final SharedPreferences preferences;

    private static ThemeRepositoryImpl INSTANCE;

    private ThemeRepositoryImpl(Context context) {
        preferences = context.getSharedPreferences("themes.xml", Context.MODE_PRIVATE);
    }

    public static ThemeRepository getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = new ThemeRepositoryImpl(context);
        }
        return INSTANCE;
    }

    @Override
    public Theme getSaveTheme() {
        String savedTheme = preferences.getString(SAVED_KEY, Theme.DEFAULT.getThemeKey());
        for (Theme theme : Theme.values()) {
            if (theme.getThemeKey().equals(savedTheme)) {
                return theme;
            }
        }
        return Theme.DEFAULT;
    }

    @Override
    public void saveTheme(Theme theme) {
        preferences.edit().putString(SAVED_KEY, theme.getThemeKey()).apply();
    }

    @Override
    public List<Theme> getAllThemes() {
        return Arrays.asList(Theme.values());
    }
}
