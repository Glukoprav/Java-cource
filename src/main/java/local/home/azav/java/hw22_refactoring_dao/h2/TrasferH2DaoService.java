package local.home.azav.java.hw22_refactoring_dao.h2;


import local.home.azav.java.hw22_refactoring_dao.exceptions.DaoException;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by SBT-Pozdnyakov-AN on 02.11.2017.
 * Refactored Zavgorodniy on 26.07.2018
 */
public class TrasferH2DaoService extends AbstractH2DaoService {
    private static final Logger LOG = Logger.getLogger(TrasferH2DaoService.class.getName());

    public void createTrasfer(String accFrom, String accTo, BigDecimal amount) throws SQLException {
        Connection connection = null;
        try {
            connection = getConnection(AbstractH2DaoService.CONNECT_URL);
            connection.setAutoCommit(false);
            changeBalance(connection, accFrom, amount.negate());
            changeBalance(connection, accTo, amount);
            connection.commit();
            //finally is here
            connection.close();
        } catch (SQLException | DaoException e) {
            LOG.log(Level.SEVERE, "ExceptionSQL: ", e);
        } catch (RuntimeException ex) {
            if (connection != null) {
                connection.rollback();
            }
        }
    }

    private void changeBalance(final Connection connection, String account, BigDecimal amount) throws SQLException {
        try (PreparedStatement pstmt = connection.prepareStatement("SELECT balance from BANK.ACCOUNT where number=?",
                ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);) {
            pstmt.setString(1, account);
            try (ResultSet rset = pstmt.executeQuery();) {
                rset.next();
                final BigDecimal balance = rset.getBigDecimal("balance");
                rset.updateBigDecimal("balance", balance.add(amount));
                rset.updateRow();
            }
        } catch (SQLException e) {
            LOG.log(Level.SEVERE, "Exception: ", e);
        }
    }
}
