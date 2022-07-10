package ru.geekbrains.androidlessontwo.model;

import java.util.List;

public interface ThemeRepository {

    Theme getSaveTheme();

    void saveTheme(Theme theme);

    List<Theme> getAllThemes();
}
