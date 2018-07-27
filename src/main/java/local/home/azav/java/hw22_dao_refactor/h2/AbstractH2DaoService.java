package local.home.azav.java.hw22_dao_refaktor.h2;

import local.home.azav.java.hw22_dao_refaktor.exceptions.DaoException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by SBT-Pozdnyakov-AN on 02.11.2017.
 * Refactored Zavgorodniy on 26.07.2018
 */
public class AbstractH2DaoService {
    public static final String CONNECT_URL = "jdbc:h2:tcp://localhost/C:\\TEMP\\test.db";

    protected Connection getConnection(String url) throws DaoException {
        try {
            return DriverManager.getConnection(url, "sa", null);
        } catch (SQLException e) {
            System.out.println("Can't create connection");
            e.printStackTrace();
            throw new DaoException("Can't create connection", e);
        }
    }
}
