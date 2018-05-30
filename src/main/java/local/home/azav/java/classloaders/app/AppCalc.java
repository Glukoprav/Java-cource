package local.home.azav.java.classloaders.app;

import local.home.azav.java.classloaders.api.ApiClassloader;
import local.home.azav.java.classloaders.api.ICalculator;
import local.home.azav.java.classloaders.impl.CalculatorImpl;
import local.home.azav.java.classloaders.impl.ImplClassloader;

import java.net.URL;

public class AppCalc {

    int doCalcAdd(CalculatorImpl calcImpl) {
        return calcImpl.addInt();
    }

    int doCalcMul(CalculatorImpl calcImpl) {
        return calcImpl.mulInt();
    }

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        ICalculator doCalc = (ICalculator) new AppClassloader()
                .loadClass("local.home.azav.java.classloaders.app.AppCalc")
                .newInstance();
        ICalculator implCalc = (ICalculator) new ImplClassloader()
                .loadClass("local.home.azav.java.classloaders.impl.CalculatorImpl")
                .newInstance();
        System.out.println(doCalc.addInt(3, 4));
    }
}
