package local.home.azav.java.hw7_classloaders.variant02.impl;

import local.home.azav.java.hw7_classloaders.variant02.api.ICalculator;

/* Класс реализации калькулятора */
public class CalculatorImpl implements ICalculator {
    private int p1;
    private int p2;

    public int getP1() {
        return p1;
    }

    public void setP1(int p1) {
        this.p1 = p1;
    }

    public int getP2() {
        return p2;
    }

    public void setP2(int p2) {
        this.p2 = p2;
    }

    public CalculatorImpl() {
        this.p1 = 0;
        this.p2 = 0;
    }

    public CalculatorImpl(int p1, int p2) {
        this.p1 = p1;
        this.p2 = p2;
    }

    public int addInt() {
        return getP1() + getP2();
    }

    @Override
    public int addInt(int fp1, int fp2) {
        return fp1 + fp2;
    }

    public int mulInt() {
        return getP1() * getP2();
    }

    @Override
    public int mulInt(int fp1, int fp2) {
        return fp1 * fp2;
    }
}
