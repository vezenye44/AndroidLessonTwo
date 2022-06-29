package ru.geekbrains.androidlessontwo.presenter;

import ru.geekbrains.androidlessontwo.model.Operate;

public interface CalculatePresenter {

    void onDigitClick(Integer digit);

    void onDotClick();

    void onOperateClick(Operate operate);

    void onResetClick();

    void onEqualClick();

}
