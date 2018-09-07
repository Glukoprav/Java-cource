package local.home.azav.java.hw22_refactoring_dao;

import local.home.azav.java.hw22_refactoring_dao.domen.Person;
import local.home.azav.java.hw22_refactoring_dao.exceptions.DaoException;
import local.home.azav.java.hw22_refactoring_dao.h2.TrasferH2DaoService;
import local.home.azav.java.hw22_refactoring_dao.service.PersonFacade;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by SBT-Pozdnyakov-AN on 02.11.2017.
 * Refactored Zavgorodniy on 26.07.2018
 */
public class MainDao {
    private static final Logger LOG = Logger.getLogger(MainDao.class.getName());
    public static final String CONNECT_URL = "jdbc:local.home.azav.java.hw22_refactoring_dao.h2:tcp://localhost/C:\\TEST\\test.db";
    public static final String GET_PERSON_QUERY = "SELECT * FROM BANK.PERSON";

    public static void main(String[] args) throws SQLException {

        try {
            Person person = new PersonFacade().getPersonById(2);
            LOG.log(Level.INFO,"Person > {0}",person);
        } catch (DaoException e) {
            LOG.log(Level.SEVERE, "Exception: ", e);
        }
        TrasferH2DaoService daoService = new TrasferH2DaoService();
        daoService.createTrasfer("1234", "4321", new BigDecimal(300));


    }
}
