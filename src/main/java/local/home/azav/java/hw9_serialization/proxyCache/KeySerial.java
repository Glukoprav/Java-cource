package local.home.azav.java.hw9_serialization.proxyCache;

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

    /**
     * Записываем проксированный результат
     * writeReplace - метод для proxy pattern
     *
     * @return Object = KeyProxy с полями из KeySerial
     */
    private Object writeReplace() {
        System.out.println("Пишем в сериализованную прокси");
        return new KeyProxy(this);
    }

    /**
     * Приватный класс для сериализованного прокси.
     */
    private static class KeyProxy implements Serializable {
        private static final long serialVersionUID = -2899736491170223339L;
        private String method;
        private Object result;

        KeyProxy(KeySerial key) {
            this.method = key.method;
            this.result = key.result;
        }

        /**
         * Возвращаем кэшированный результат через вызов конструктора родителя прокси.
         * readResolve метод для proxy pattern - KeySerial.KeyProxy.
         */
        private Object readResolve() {
            System.out.println("Читаем из сериализованной прокси");
            return new KeySerial(method, result);
        }
    }
}