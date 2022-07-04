package ru.geekbrains.androidlessontwo.model;

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
                if (arg02 == 0) return 0.0;
                return arg01 / arg02;
            default:
                return 0.0;
        }
    }
}
