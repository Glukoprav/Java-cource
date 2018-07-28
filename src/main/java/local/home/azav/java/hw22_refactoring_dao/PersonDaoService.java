package local.home.azav.java.hw22_refactoring_dao;

import local.home.azav.java.hw22_refactoring_dao.domen.Person;
import local.home.azav.java.hw22_refactoring_dao.exceptions.DaoException;
import local.home.azav.java.hw22_refactoring_dao.h2.PersonDaoException;

/**
 * Created by SBT-Pozdnyakov-AN on 02.11.2017.
 */
public interface PersonDaoService {
    Person getPersonById(Integer id) throws DaoException;

    Person save(Person person) throws PersonDaoException;
}
