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
            }
            return person;

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException("Get person error", e);
        }
    }

    /**
     * Задание к лекции 22:
     * реализовать метод save - сохранение данных персоны в БД
     */
    public Person save(Person person) throws PersonDaoException {
        if (person == null) {
            throw new PersonDaoException("Person not found!");
        }
        try (Connection connection = getConnection(CONNECT_URL);
             PreparedStatement preparedStatement =
                     connection.prepareStatement(
                             "MERGE INTO BANK.PERSON (id, first_name, last_name, age) KEY (id) VALUES (?, ?, ?, ?)");) {
            preparedStatement.setInt(1, person.getId());
            preparedStatement.setString(2, person.getFirstName());
            preparedStatement.setString(3, person.getLastName());
            preparedStatement.setInt(4, person.getAge());
            int resultInt = preparedStatement.executeUpdate();
            if (resultInt != 1) {
                throw new PersonDaoException("Not valid save person!");
            }
            connection.commit();
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
