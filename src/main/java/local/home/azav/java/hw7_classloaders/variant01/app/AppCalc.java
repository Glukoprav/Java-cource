package local.home.azav.java.hw7_classloaders.variant01.app;

import local.home.azav.java.hw7_classloaders.variant01.impl.CalculatorImpl;
import local.home.azav.java.hw7_classloaders.variant01.impl.ImplClassloader;
import local.home.azav.java.hw7_classloaders.variant01.api.ICalculator;

import java.util.logging.Level;
import java.util.logging.Logger;


public class AppCalc extends CalculatorImpl {
    private static final Logger LOG = Logger.getLogger(AppCalc.class.getName());

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {

        // грузим класс AppCalc с помощью AppClassloader
        ICalculator appCalc = (ICalculator) new AppClassloader()
                .loadClass()
                .newInstance();
        LOG.log(Level.INFO, String.valueOf(appCalc.getClass().getClassLoader()));
        LOG.log(Level.INFO, String.valueOf(appCalc.addInt(4,7)));

        // грузим класс CalculatorImpl с помощью ImplClassloader
        ICalculator implCalc = (ICalculator) new ImplClassloader()
                .loadClass()
                .newInstance();
        LOG.log(Level.INFO, String.valueOf(implCalc.getClass().getClassLoader()));
        LOG.log(Level.INFO, String.valueOf(implCalc.addInt(3, 4)));

        // грузим класс MyApp с помощью MyAppClassloader
        MyApp myApp = (MyApp)new MyAppClassloader()
                .loadClass()
                .newInstance();
        LOG.log(Level.INFO, String.valueOf(myApp.getClass().getClassLoader()));
        LOG.log(Level.INFO, String.valueOf(myApp.addInt(1,2)));

        // устанавливаем у объекта класса MyApp значение типа CalculatorImpl
        // из имеющегося объекта класса CalculatorImpl
        myApp.setCalcImpl((CalculatorImpl) implCalc);
        LOG.log(Level.INFO, String.valueOf(myApp.getCalcImpl().addInt(6,7)));
    }
}
