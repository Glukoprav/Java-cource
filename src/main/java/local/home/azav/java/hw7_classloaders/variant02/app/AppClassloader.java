package local.home.azav.java.hw7_classloaders.variant02.app;

import local.home.azav.java.hw7_classloaders.variant02.api.ApiClassloader;

/**
 * Classloader для класса AppCalc
 */
public class AppClassloader extends ApiClassloader {
    public final String classPath;   // путь к классу

    public AppClassloader(String classPath) {
        super(classPath);
        //super();
        this.classPath = classPath;
        System.out.println("AppClassloader после вызова родителя");
    }

    @Override
    public Class loadClass(String name,boolean resolve) throws ClassNotFoundException
    {
        Class result= findClass(name);
        if (resolve)
            resolveClass(result);
        return result;
    }
    /*protected Class findClass(String name) throws ClassNotFoundException {

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
    }*/
}
