package local.home.azav.java.hw22_dao_refaktor.service;

import local.home.azav.java.hw22_dao_refaktor.PersonDaoService;
import local.home.azav.java.hw22_dao_refaktor.domen.Account;
import local.home.azav.java.hw22_dao_refaktor.domen.Person;
import local.home.azav.java.hw22_dao_refaktor.exceptions.DaoException;
import local.home.azav.java.hw22_dao_refaktor.h2.AccountDaoService;
import local.home.azav.java.hw22_dao_refaktor.h2.AccountH2DaoService;
import local.home.azav.java.hw22_dao_refaktor.h2.PersonH2DaoService;

import java.util.List;

/**
 * Created by SBT-Pozdnyakov-AN on 02.11.2017.
 */
public class PersonFacade {

    public Person getPersonById(Integer id) throws DaoException {
        PersonDaoService personDaoService = new PersonH2DaoService();
        AccountDaoService accountDaoService = new AccountH2DaoService();
        Person person = personDaoService.getPersonById(id);
        List<Account> accountList = accountDaoService.getAccountByNumber(person.getId());
        person.setAccountList(accountList);
        return person;
    }
}
