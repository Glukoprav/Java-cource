package local.home.azav.java.classloaders.variant02.app;

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

import local.home.azav.java.classloaders.variant02.api.ICalculator;
import local.home.azav.java.classloaders.variant02.impl.CalculatorImpl;
import local.home.azav.java.classloaders.variant02.impl.ImplClassloader;

public class AppCalc extends CalculatorImpl {

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {

        // грузим класс AppCalc с помощью AppClassloader
        String strApp = "local.home.azav.java.classloaders.variant02.app.AppCalc";
        ClassLoader appCalcLoader = new AppClassloader(strApp);
        Class clazzApp = Class.forName(strApp, true, appCalcLoader);
        ICalculator appCalc = (ICalculator) clazzApp.newInstance();
        System.out.println(appCalc.getClass().getClassLoader());
        System.out.println(appCalc.addInt(4, 7));

        // грузим класс CalculatorImpl с помощью ImplClassloader
        String strCalc = "local.home.azav.java.classloaders.variant02.impl.CalculatorImpl";
        ClassLoader implCalcLoader = new ImplClassloader(strCalc);
        Class clazzCalc = Class.forName(strCalc, true, implCalcLoader);
        ICalculator implCalc = (ICalculator) clazzCalc.newInstance();
        System.out.println(implCalc.getClass().getClassLoader());
        System.out.println(implCalc.addInt(3, 4));

        // грузим класс MyApp с помощью MyAppClassloader
        String strMyApp = "local.home.azav.java.classloaders.variant02.app.MyApp";
        ClassLoader myAppLoader = new MyAppClassloader(strMyApp);
        Class clazzMyApp = Class.forName(strMyApp, true, myAppLoader);
        MyApp myApp = (MyApp) clazzMyApp.newInstance();
        System.out.println(myApp.getClass().getClassLoader());
        System.out.println(myApp.addInt(1, 2));

        // устанавливаем у объекта класса MyApp значение типа CalculatorImpl
        // из имеющегося объекта класса CalculatorImpl
        myApp.setCalcImpl((CalculatorImpl) implCalc);
        System.out.println(myApp.getCalcImpl().addInt(6, 7));
    }
}
