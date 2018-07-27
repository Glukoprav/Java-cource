package local.home.azav.java.hw22_dao_refaktor.h2;

import local.home.azav.java.hw22_dao_refaktor.domen.Account;
import local.home.azav.java.hw22_dao_refaktor.exceptions.DaoException;

import java.util.List;

/**
 * Created by SBT-Pozdnyakov-AN on 02.11.2017.
 * Refactored Zavgorodniy on 26.07.2018
 */
public interface AccountDaoService {
    List<Account> getAccountByNumber(Integer personId) throws DaoException;
}
