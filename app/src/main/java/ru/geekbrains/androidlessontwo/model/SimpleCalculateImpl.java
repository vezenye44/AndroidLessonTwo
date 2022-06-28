package ru.geekbrains.androidlessontwo.model;

import ru.geekbrains.androidlessontwo.Operate;

public class SimpleCalculateImpl implements SimpleCalculate{
    @Override
    public double calculation(double arg01, double arg02, Operate operate) {
        switch (operate){
            case ADD:
                return arg01 + arg02;
            case SUB:
                return arg01 - arg02;
            case MUL:
                return arg01 * arg02;
            case DIV:
                return arg01 / arg02;
        }
        return 0.0;
    }
}
