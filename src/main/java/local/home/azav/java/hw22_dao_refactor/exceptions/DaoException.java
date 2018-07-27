package local.home.azav.java.hw22_dao_refaktor.exceptions;

public class DaoException extends Exception {


    public DaoException(String message) {
        super(message);
    }

    public DaoException(String message, Throwable cause) {
        super(message, cause);
    }
}
