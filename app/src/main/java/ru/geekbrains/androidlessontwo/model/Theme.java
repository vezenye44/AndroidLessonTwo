package ru.geekbrains.androidlessontwo.model;

import androidx.annotation.StringRes;
import androidx.annotation.StyleRes;
import ru.geekbrains.androidlessontwo.R;

public enum Theme {
    DEFAULT(R.style.Theme_MyCalculateTheme, R.string.name_default_theme, "default_theme"),
    FLORAL(R.style.Theme_MyCalculateTheme_Floral, R.string.name_floral_theme, "floral_theme");

    @StyleRes
    private final int themeResource;
    @StringRes
    private final int themeName;
    private final String themeKey;

    Theme(int themeResource, int themeName, String themeKey) {
        this.themeResource = themeResource;
        this.themeName = themeName;
        this.themeKey = themeKey;
    }

    public int getThemeResource() {
        return themeResource;
    }

    public int getThemeName() {
        return themeName;
    }

    public String getThemeKey() {
        return themeKey;
    }
}
