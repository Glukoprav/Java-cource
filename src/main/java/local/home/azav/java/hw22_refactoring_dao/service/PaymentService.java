package local.home.azav.java.hw22_refactoring_dao.service;

import local.home.azav.java.hw22_refactoring_dao.domen.Account;
import local.home.azav.java.hw22_refactoring_dao.h2.TrasferH2DaoService;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by SBT-Pozdnyakov-AN on 02.11.2017.
 * Refactored Zavgorodniy on 26.07.2018
 */
public class PaymentService {
    private static final Logger LOG = Logger.getLogger(PaymentService.class.getName());

    public boolean createPayment(Account from, Account to, BigDecimal amount) {
        TrasferH2DaoService daoService = new TrasferH2DaoService();
        try {
            daoService.createTrasfer(from.getNumber(), to.getNumber(), amount);
        } catch (SQLException e) {
            LOG.log(Level.SEVERE, "Exception: ", e);
            return false;
        }
        return true;
    }
}
