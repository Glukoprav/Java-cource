package local.home.azav.java.hw22_dao_refaktor.h2;

import local.home.azav.java.hw22_dao_refaktor.exceptions.DaoException;

/**
 * Created by SBT-Pozdnyakov-AN on 02.11.2017.
 * Refactored Zavgorodniy on 26.07.2018
 */
public class PersonDaoException extends DaoException {
    public PersonDaoException(String message) {
        super(message);
    }

    public PersonDaoException(String message, Throwable cause) {
        super(message, cause);
    }
}
