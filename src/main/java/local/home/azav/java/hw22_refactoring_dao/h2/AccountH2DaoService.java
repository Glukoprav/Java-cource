package local.home.azav.java.hw22_refactoring_dao.h2;

import local.home.azav.java.hw22_refactoring_dao.domen.Account;
import local.home.azav.java.hw22_refactoring_dao.exceptions.DaoException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by SBT-Pozdnyakov-AN on 02.11.2017.
 * Refactored Zavgorodniy on 26.07.2018
 */
public class AccountH2DaoService extends AbstractH2DaoService implements AccountDaoService {
    @Override
    public List<Account> getAccountByNumber(Integer personId) throws DaoException {
        try (Connection connection = getConnection(CONNECT_URL);
             Statement statement = connection.createStatement()) {
            List<Account> accounts;
            try (ResultSet rs = statement.executeQuery("SELECT * FROM BANK.ACCOUNT WHERE person_id=" + personId)) {
                accounts = new ArrayList<>();
                while (rs.next()) {
                    Account account = new Account();
                    account.setNumber(rs.getString("number"));
                    account.setName(rs.getString("name"));
                    account.setBalance(rs.getBigDecimal("balance"));
                    accounts.add(account);
                }
            }
            return accounts;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException("Get account error", e);
        }
    }
}
