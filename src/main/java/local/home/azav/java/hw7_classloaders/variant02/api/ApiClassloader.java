package local.home.azav.java.hw7_classloaders.variant02.api;


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

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ApiClassloader extends ClassLoader {
    private static final Logger LOG = Logger.getLogger(ApiClassloader.class.getName());

    public final String classPath;   // путь к классу

    public ApiClassloader(String classPath) {
        this.classPath = classPath;
        LOG.log(Level.INFO,"ApiClassloader конструктор");
    }

    @Override
    public Class loadClass(String name,boolean resolve) throws ClassNotFoundException
    {
        Class result= findClass(name);
        if (resolve)
            resolveClass(result);
        return result;
    }

    /**
     * Преобразовываем имя, ищем файл класса, считываем файл и устанавливаем класс
     * @param name - строка пути к файлу в формате пакета (разделитель - точка)
     * @return установленный класс
     */
    @Override
    protected Class findClass(String name) throws ClassNotFoundException {
        Class result;
        File fileClass = findFile(name.replace('.', '/'), ".class");
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

    /**
     * Ищем файл класса
     * @param name - строка пути к классу
     * @param extension - строка расширения файла
     * @return Возвращаем найденный файл, иначе null
     */
    private File findFile(String name, String extension) {
        File f = new File((new File(classPath)).getPath() +
                File.separatorChar + name.replace('/', File.separatorChar) + extension);
        if (f.exists())
            return f;
        return null;
    }

    /**
     * Считываем файл
     * @param file - найденный файл класа
     * @return Возвращаем байтовый массив класса
     */
    private static byte[] loadFileAsBytes(File file) throws IOException {
        byte[] result = new byte[(int) file.length()];
        FileInputStream f = new FileInputStream(file);
        try {
            f.read(result, 0, result.length);
        } finally {
            try {
                f.close();
            } catch (Exception e) {
                // Об исключениях, возникших при вызове close сообщим
                LOG.log(Level.SEVERE, "Exception: ", e);
            }
        }
        return result;
    }
}
