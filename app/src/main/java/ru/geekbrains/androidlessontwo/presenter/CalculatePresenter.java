package ru.geekbrains.androidlessontwo.presenter;

import ru.geekbrains.androidlessontwo.model.Operate;
import ru.geekbrains.androidlessontwo.ui.CalculateView;

public interface CalculatePresenter {

    void onResume(CalculateView resultView);

    void onDigitClick(Integer digit);

    void onDotClick();

    void onOperateClick(Operate operate);

    void onResetClick();

    void onEqualClick();

}
