package local.home.azav.java.classloaders.app;

import local.home.azav.java.classloaders.api.ICalculator;
import local.home.azav.java.classloaders.impl.CalculatorImpl;
import local.home.azav.java.classloaders.impl.ImplClassloader;


public class AppCalc extends CalculatorImpl {

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {

        // грузим класс AppCalc с помощью AppClassloader
        ICalculator appCalc = (ICalculator) new AppClassloader()
                .loadClass()
                .newInstance();
        System.out.println(appCalc.addInt(4,7));

        // грузим класс CalculatorImpl с помощью ImplClassloader
        ICalculator implCalc = (ICalculator) new ImplClassloader()
                .loadClass()
                .newInstance();
        System.out.println(implCalc.addInt(3, 4));

        // грузим класс MyApp с помощью MyAppClassloader
        MyApp myApp = (MyApp)new MyAppClassloader()
                .loadClass()
                .newInstance();
        System.out.println(myApp.addInt(1,2));

        // устанавливаем у объекта класса MyApp значение типа CalculatorImpl
        // из имеющегося объекта класса CalculatorImpl
        myApp.setCalcImpl((CalculatorImpl) implCalc);
        System.out.println(myApp.getCalcImpl().addInt(6,7));
    }
}
