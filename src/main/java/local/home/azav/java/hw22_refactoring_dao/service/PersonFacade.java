package local.home.azav.java.hw22_refactoring_dao.service;

import local.home.azav.java.hw22_refactoring_dao.PersonDaoService;
import local.home.azav.java.hw22_refactoring_dao.domen.Account;
import local.home.azav.java.hw22_refactoring_dao.domen.Person;
import local.home.azav.java.hw22_refactoring_dao.exceptions.DaoException;
import local.home.azav.java.hw22_refactoring_dao.h2.AccountDaoService;
import local.home.azav.java.hw22_refactoring_dao.h2.AccountH2DaoService;
import local.home.azav.java.hw22_refactoring_dao.h2.PersonH2DaoService;

import java.util.List;

/**
 * Created by SBT-Pozdnyakov-AN on 02.11.2017.
 * Refactored Zavgorodniy on 26.07.2018
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
