package local.home.azav.java.hw7_classloaders.variant02.app;

import local.home.azav.java.hw7_classloaders.variant02.impl.CalculatorImpl;

/* Класс MyApp extends App, у которого есть поле с типом CalculatorImpl */
public class MyApp extends AppCalc {

    private CalculatorImpl calcImpl;

    public CalculatorImpl getCalcImpl() {
        return calcImpl;
    }

    public void setCalcImpl(CalculatorImpl calcImpl) {
        this.calcImpl = calcImpl;
    }
}
