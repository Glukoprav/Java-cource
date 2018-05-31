package local.home.azav.java.classloaders.api;

public abstract class ApiClassloader extends ClassLoader {

    public ApiClassloader() {
        super();
        System.out.println("ApiClassloader после вызова родителя");
    }

    public Class<?> loadClass() throws ClassNotFoundException {
        return super.loadClass("local.home.azav.java.classloaders.api.ICalculator");
    }
}
