package local.home.azav.java.classloaders.app;

import local.home.azav.java.classloaders.impl.CalculatorImpl;

public class MyApp extends AppCalc {

    private CalculatorImpl calcImpl;

    public CalculatorImpl getCalcImpl() {
        return calcImpl;
    }

    public void setCalcImpl(CalculatorImpl calcImpl) {
        this.calcImpl = calcImpl;
    }
}
