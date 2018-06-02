package local.home.azav.java.classloaders.variant02.api;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public abstract class ApiClassloader extends ClassLoader {

    public final String classPath;   // путь к классу

    public ApiClassloader(String classPath) {
        this.classPath = classPath;
        System.out.println("ApiClassloader конструктор");
    }

    /**
     * Преобразовываем имя, ищем файл класса, считываем файл и устанавливаем класс
     * @param name - строка пути к файлу в формате пакета (разделитель - точка)
     * @return установленный класс
     */
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
    public static byte[] loadFileAsBytes(File file) throws IOException {
        byte[] result = new byte[(int) file.length()];
        FileInputStream f = new FileInputStream(file);
        try {
            f.read(result, 0, result.length);
        } finally {
            try {
                f.close();
            } catch (Exception e) {
                // Об исключениях, возникших при вызове close сообщим
                System.out.println("Ошибка при закрытии файла " + file);
            }
        }
        return result;
    }
}
