package ru.geekbrains.androidlessontwo;

public interface CalculatePresenter {

    void onDigitClick(Integer digit);

    void onDotClick();

    void onOperateClick(Operate operate);

    void onResetClick();

    void onEqualClick();

}
