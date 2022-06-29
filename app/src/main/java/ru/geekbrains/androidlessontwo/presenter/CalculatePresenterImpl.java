package ru.geekbrains.androidlessontwo.presenter;

import ru.geekbrains.androidlessontwo.model.Operate;
import ru.geekbrains.androidlessontwo.model.SimpleCalculate;
import ru.geekbrains.androidlessontwo.ui.CalculateView;

public class CalculatePresenterImpl implements CalculatePresenter {

    /** Поле - реализация простого калькулятора */
    private SimpleCalculate calculate;

    /** Поле-интерфейс для вывода результатов вычислений во вне */
    private CalculateView resultView;

    /** Поле - последняя вызванная операция */
    private Operate operate;

    /** Публичный конструктор, получающий необходимые для работы обьекты
     * @param calculate Реалмизация простого калькулятора, умеющая выполнять простые
     * бинарные операции над действительными числами.
     * @param resultView Реализация интерфейса для вывода результатов вычислений во вне.
     */
    public CalculatePresenterImpl(SimpleCalculate calculate, CalculateView resultView) {
        this.calculate = calculate;
        this.resultView = resultView;
    }

    /** Поле - первый аргумент */
    private double arg01;
    /** Поле - второй аргумент */
    private double arg02;

    @Override
    public void onDigitClick(Integer digit) {

    }

    @Override
    public void onDotClick() {

    }

    @Override
    public void onOperateClick(Operate operate) {

    }

    @Override
    public void onResetClick() {

    }

    @Override
    public void onEqualClick() {

    }
}
