package local.home.azav.java.hw7_classloaders.variant02.app;

/*
    Задание:

У Вас будут интерфейс Calculator, классы CalculatorImpl и App.
Располагаться они должны в разных директориях(= package-ах) - api, impl и app соответственно.
Необходимо создать три класслоадера: ApiClassloader, ImplClassloader и AppClassloader.
ApiClassloader должен уметь загружать классы из api, ImplClassloader из impl, AppClassloader из app.
Apiclassloader должен быть родителем как для ImplClassloader, так и для AppClassloader.

С помощью ImplClassloader загрузить класс CalculatorImpl. С помощью AppClassloader загрузить класс App.
1) Убедиться, что из AppClassloader недоступна загрузка CalculatorImpl.
2) Создать класслоадер, с помощью которого возможно будет загрузить класс MyApp extends App,
у которого будет поле с типом CalculatorImpl
*/

import local.home.azav.java.hw7_classloaders.variant02.api.ICalculator;
import local.home.azav.java.hw7_classloaders.variant02.impl.CalculatorImpl;
import local.home.azav.java.hw7_classloaders.variant02.impl.ImplClassloader;

import java.util.logging.Level;
import java.util.logging.Logger;

public class AppCalc extends CalculatorImpl {
    private static final Logger LOG = Logger.getLogger(AppCalc.class.getName());

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {

        // грузим класс AppCalc с помощью AppClassloader
        String strApp = "local.home.azav.java.hw7_classloaders.variant02.app.AppCalc";
        ClassLoader appCalcLoader = new AppClassloader(strApp);
        ICalculator appCalc = (ICalculator) ((AppClassloader) appCalcLoader).loadClass(strApp, true).newInstance();
        LOG.log(Level.INFO, String.valueOf(appCalc.getClass().getClassLoader()));
        LOG.log(Level.INFO, String.valueOf(appCalc.addInt(4, 7)));

        // грузим класс CalculatorImpl с помощью ImplClassloader
        String strCalc = "local.home.azav.java.hw7_classloaders.variant02.impl.CalculatorImpl";
        ClassLoader implCalcLoader = new ImplClassloader(strCalc);
        ICalculator implCalc =  (ICalculator) ((ImplClassloader) implCalcLoader).loadClass(strCalc, true).newInstance();
        LOG.log(Level.INFO, String.valueOf(implCalc.getClass().getClassLoader()));
        LOG.log(Level.INFO, String.valueOf(implCalc.addInt(3, 4)));

        // грузим класс MyApp с помощью MyAppClassloader
        String strMyApp = "local.home.azav.java.hw7_classloaders.variant02.app.MyApp";
        ClassLoader myAppLoader = new MyAppClassloader(strMyApp);
        MyApp myApp =  (MyApp) ((MyAppClassloader) myAppLoader).loadClass(strMyApp, true).newInstance();
        LOG.log(Level.INFO, String.valueOf(myApp.getClass().getClassLoader()));
        LOG.log(Level.INFO, String.valueOf(myApp.addInt(1, 2)));

        // устанавливаем у объекта класса MyApp значение типа CalculatorImpl
        // из имеющегося объекта класса CalculatorImpl
        myApp.setCalcImpl((CalculatorImpl) implCalc);
        LOG.log(Level.INFO, String.valueOf(myApp.getCalcImpl().addInt(6, 7)));
    }
}
