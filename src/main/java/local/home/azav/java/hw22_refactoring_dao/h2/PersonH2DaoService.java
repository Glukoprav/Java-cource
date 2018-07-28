package local.home.azav.java.hw22_refactoring_dao.h2;

import local.home.azav.java.hw22_refactoring_dao.PersonDaoService;
import local.home.azav.java.hw22_refactoring_dao.domen.Person;
import local.home.azav.java.hw22_refactoring_dao.exceptions.DaoException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by SBT-Pozdnyakov-AN on 02.11.2017.
 * Refactored Zavgorodniy on 26.07.2018
 */
public class PersonH2DaoService extends AbstractH2DaoService implements PersonDaoService {
    public static final String CONNECT_URL = "jdbc:local.home.azav.java.hw22_refactoring_dao.h2:tcp://localhost/C:\\TEMP\\test.db";


    public Person getPersonById(Integer id) throws DaoException {
        try (Connection connection = getConnection(CONNECT_URL);
             PreparedStatement preparedStatement =
                     connection.prepareStatement("SELECT * FROM BANK.PERSON WHERE id=?");) {

            preparedStatement.setInt(1, id);
            Person person;
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                int i = 0;
                person = null;
                while (resultSet.next()) {
                    i++;
                    if (i > 1) {
                        throw new PersonDaoException("More than one row");
                    }
                    person = new Person();
                    person.setId(resultSet.getInt("id"));
                    person.setFirstName(resultSet.getString("first_name"));
                    person.setLastName(resultSet.getString("last_name"));
                    person.setAge(resultSet.getInt("age"));
                }
                if (person == null) {
                    throw new DaoException("Person not found id=" + id);
                }
                if (!resultSet.isClosed()) {
                    resultSet.close();
                }
            }
            return person;

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException("Get person error", e);
        }
    }

    // сохранение персоны в БД
    public Person save(Person person) throws PersonDaoException {
        if (person == null) {
            throw new PersonDaoException("Person not found!");
        }
        try (Connection connection = getConnection(CONNECT_URL);
             PreparedStatement preparedStatement =
                     connection.prepareStatement("SELECT * FROM BANK.PERSON WHERE id=?");) {
            // проверяем наличие персоны с данным идентом в базе
            int personId = person.getId();
            preparedStatement.setInt(1, personId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                int i = 0;
                while (resultSet.next()) {
                    i++;
                    if (i > 1) {
                        throw new PersonDaoException("More than one row");
                    }
                    person = new Person();
                    person.setId(resultSet.getInt("id"));
                    person.setFirstName(resultSet.getString("first_name"));
                    person.setLastName(resultSet.getString("last_name"));
                    person.setAge(resultSet.getInt("age"));
                }
                if (person == null) {
                    //throw new DaoException("Person not found id=" + personId);
                    // если нет, то сохраняем

                } else {
                    // если есть, то update

                }
                if (!resultSet.isClosed()) {
                    resultSet.close();
                }
            }
            return person;

        } catch (SQLException e) {
            e.printStackTrace();
            throw new PersonDaoException("Save person error", e);
        } catch (DaoException e) {
            e.printStackTrace();
        }
        return person;
    }
}
