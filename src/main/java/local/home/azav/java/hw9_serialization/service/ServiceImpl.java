package local.home.azav.java.hw9_serialization.service;

/**
 * Класс, у которого будем кэшировать результаты выполнения разных методов
 */
public class ServiceImpl implements Service {

    @Override
    public long doHardWork01(long t) {
        if (t > 0) {
            return t;
        } else {
            return 111;
        }

    }

    @Override
    public long doHardWork02(long t) {
        if (t > 0) {
            return t;
        } else {
            return 222;
        }
    }

    @Override
    public long doHardWork03(long t) {
        if (t > 0) {
            return t;
        } else {
            return 333;
        }
    }

    @Override
    public long doHardWork04(long t) {
        if (t > 0) {
            return t;
        } else {
            return 444;
        }
    }
}
