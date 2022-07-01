package ru.geekbrains.androidlessontwo.presenter;

import ru.geekbrains.androidlessontwo.model.Operate;
import ru.geekbrains.androidlessontwo.model.SimpleCalculate;
import ru.geekbrains.androidlessontwo.ui.CalculateView;

public class CalculatePresenterImpl implements CalculatePresenter {

    /**
     * Поле - реализация простого калькулятора
     */
    private SimpleCalculate calculate;

    /**
     * Поле-интерфейс для вывода результатов вычислений во вне
     */
    private CalculateView resultView;

    /**
     * Поле - последняя вызванная операция
     */
    private Operate operate;

    /**
     * Публичный конструктор, получающий необходимые для работы обьекты
     *
     * @param calculate  Реалмизация простого калькулятора, умеющая выполнять простые
     *                   бинарные операции над действительными числами.
     * @param resultView Реализация интерфейса для вывода результатов вычислений во вне.
     */
    public CalculatePresenterImpl(SimpleCalculate calculate, CalculateView resultView) {
        this.calculate = calculate;
        this.resultView = resultView;
    }

    /**
     * Поле - первый аргумент
     */
    private String argument01 = "";
    /**
     * Поле - второй аргумент
     */
    private String argument02 = "";
    /**
     * Поле - результат последнего вычисления(оператора или равно)
     */
    private String result = "";
    /**
     * Поле-флаг - сообщает, был ли введен первый аргумент(true).
     * Если да, то нажатия на цифры калькулятора будут заполнять второй аргумент.
     * Если нет, то продолжается заполнение первого аргумента
     */
    private boolean flag = true;

    @Override
    public void onResume(CalculateView resultView) {
        this.resultView = resultView;
        if (flag) {
          if (argument01.equals("")) {
              resultView.showResult(result);
          } else {
              resultView.showResult(argument01);
          }
        } else if (argument02.equals("")) {
            if (argument01.equals("")) {
                resultView.showResult(result);
            } else {
                resultView.showResult(argument01);
            }
        } else {
            resultView.showResult(argument02);
        }
    }

    @Override
    public void onDigitClick(Integer digit) {
        if (flag) {
            argument01 += digit;
            resultView.showResult(argument01);
        } else {
            argument02 += digit;
            resultView.showResult(argument02);
        }
    }

    @Override
    public void onDotClick() {
        if (flag & !(argument01.contains("."))) {
            argument01 += ".";
        } else if ((operate != null) & !(argument02.contains("."))) {
            argument02 += ".";
        }
    }

    @Override
    public void onOperateClick(Operate operate) {
        // Проверяем, содержится ли что-то в первом аргументе :
        if (argument01.equals("")) { // Если аргумент пуст, то :
            // Проверяем, не остался ли результат с прошлых операций :
            if (result.equals("")) { // Если результат не остался, то :
                // ...продолжаем заполнять первый аргумент
                flag = true;
            } else { // Если результат остался, то :
                // ...прекращаем заполнять первый аргумент
                flag = false;
                // присваеваем первому аргументу значение результата прошлого вычисления
                argument01 = result;
            }
        } else { // Если первый аргумент не пуст, прекращаем заполнять его(будем заполнять второй)
            flag = false;
        }

        resultView.showResult(argument01);
        // Проводим вычисление :
        if ((this.operate != null) & !argument01.equals("") & !argument02.equals("")) {
            double arg01 = Double.parseDouble(argument01);
            double arg02 = Double.parseDouble(argument02);
            arg01 = calculate.calculation(arg01, arg02, this.operate);
            result = String.valueOf(arg01); // Сохраняем результат в result
            resultView.showResult(result); // Выводим результат
            argument01 = ""; // Обнуляем первый аргумент
        }
        argument02 = ""; // Всегда обнуляем второй аргумент
        this.operate = operate; // Запоминаем выбранную операцию
    }

    @Override
    public void onResetClick() {
        // Запрещаем изменение первого аргумента
        flag = false;
        // Если второй аргумент пуст :
        if (argument02.equals("")) {
            // Обнуляем первый аргумент
            argument01 = "";
            // последнюю операцию,
            this.operate = null;
            // Разрешаем изменение первого аргумента
            flag = true;
        }
        resultView.showResult(argument01);
        // Всегда обнуляем :
        // значение второго аргумента и
        argument02 = "";
        // результат последней операции
        result = "";
    }

    @Override
    public void onEqualClick() {
        // Если первый операнд пуст, то..
        if (argument01.equals("")) { // проверяем наличие результата прошлых вычислений
            if (!result.equals("")) argument01 = result;
        } else result = argument01; // Если первый операнд содержит число - копируем это число в результат
        // Проводим вычисления :
        if ((this.operate != null) & !argument01.equals("") & !argument02.equals("")) {
            double arg01 = Double.parseDouble(argument01);
            double arg02 = Double.parseDouble(argument02);
            arg01 = calculate.calculation(arg01, arg02, this.operate);
            result = String.valueOf(arg01);
            resultView.showResult(result);
        }
        flag = true; // После нажатия на равно оба операнда очищаются
        argument01 = ""; //
        argument02 = ""; //
        this.operate = null; // Очищаем операцию
    }
}
