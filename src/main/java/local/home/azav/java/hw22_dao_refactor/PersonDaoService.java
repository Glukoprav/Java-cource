package local.home.azav.java.hw22_dao_refaktor;

import local.home.azav.java.hw22_dao_refaktor.domen.Person;
import local.home.azav.java.hw22_dao_refaktor.exceptions.DaoException;
import local.home.azav.java.hw22_dao_refaktor.h2.PersonDaoException;

/**
 * Created by SBT-Pozdnyakov-AN on 02.11.2017.
 */
public interface PersonDaoService {
    Person getPersonById(Integer id) throws DaoException;

    Person save(Person person) throws PersonDaoException;
}
