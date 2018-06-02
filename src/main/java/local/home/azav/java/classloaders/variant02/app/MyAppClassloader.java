package local.home.azav.java.classloaders.variant02.app;

import local.home.azav.java.classloaders.variant02.api.ApiClassloader;

import java.io.File;
import java.io.IOException;

/**
 * Classloader для класса MyApp
 */
public class MyAppClassloader extends ApiClassloader {
    public final String classPath;   // путь к классу

    public MyAppClassloader(String classPath) {
        super(classPath);
        //super();
        this.classPath = classPath;
        System.out.println("MyAppClassloader после вызова родителя");
    }

    protected Class findClass(String name) throws ClassNotFoundException {

        Class result;
        File fileClass = super.findFile(name.replace('.', '/'), ".class");
        if (fileClass == null) {
            // Обращаемся к системному загрузчику в случае неудачи.
            return findSystemClass(name);
        }
        try {
            byte[] classBytes = loadFileAsBytes(fileClass);
            result = defineClass(name, classBytes, 0, classBytes.length);
        } catch (IOException e) {
            throw new ClassNotFoundException("Класс не загружен " + name + ": " + e);
        } catch (ClassFormatError e) {
            throw new ClassNotFoundException("Некорректный формат класса " + name + ": " + e);
        }
        return result;
    }
}
