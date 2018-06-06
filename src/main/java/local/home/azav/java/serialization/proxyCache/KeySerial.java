package local.home.azav.java.serialization.proxyCache;

import java.io.Serializable;

/**
 * Класс для сохранения результатов в файловый кэш
 * с реализацией Serializable Proxy
 */
class KeySerial implements Serializable {
    private static final long serialVersionUID = 8642553321654929147L;
    private String method;
    private Object result;

    KeySerial(String method, Object result) {
        this.method = method;
        this.result = result;
    }

    public String getMethod() {
        return method;
    }

    public Object getResult() {
        return result;
    }
//    // no-arg Constructor
//    KeySerial() {
//        System.out.println("no-arg constructor");
//    }

    /**
     * writeReplace - метод для proxy pattern
     *
     * @return Object = KeyProxy с полями из KeySerial
     */
    private Object writeReplace() {
        System.out.println("In writeReplace() method");
        return new KeyProxy(this);
    }

    // Приватный класс для сериализации
    private static class KeyProxy implements Serializable {
        private static final long serialVersionUID = -2899736491170223339L;
        private String method;
        private Object result;

        KeyProxy(KeySerial key) {
            this.method = key.method;
            this.result = key.result;
        }

        // readResolve метод для KeySerial.KeyProxy
        private Object readResolve() {
            System.out.println("In readResolve() method");
            return new KeySerial(method, result);
        }
    }
}