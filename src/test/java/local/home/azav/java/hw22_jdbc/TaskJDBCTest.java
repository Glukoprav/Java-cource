package local.home.azav.java.hw22_jdbc;

import org.junit.Before;
import org.junit.Test;

import java.sql.*;

import static org.junit.Assert.*;

public class TaskJDBCTest {
    private TaskJDBC taskJDBC;
//    private final String STRCONN = "jdbc:h2:C:/Documents and Settings/andreyz/IdeaProjects/firstproject/src/test/java/local/home/azav/java/hw22_jdbc/test";
    private final String STRCONN = "jdbc:h2:c:/Users/Azav/IdeaProjects/Java-cource/src/test/java/local/home/azav/java/hw22_jdbc/test";
//    private final String STRCONN = "jdbc:h2:test";

    @Before
    public void setUp() {
        taskJDBC = new TaskJDBC();
    }

    @Test
    public void testForNameH2() {
        assertNotNull(taskJDBC.forNameH2());
    }

    @Test
    public void testPrintUserOrderItem() {
        try (Connection connection = DriverManager.getConnection(STRCONN, "sa", "");
//        try (Connection connection = DriverManager.getConnection(, "sa", "");
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("select * from user_order_item order by date_order");) {
            assertEquals(20, taskJDBC.printUserOrderItem(resultSet));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testConnectAndQuery() {
        try (Connection connection = DriverManager.getConnection(STRCONN, "sa", "")) {
            assertEquals(20, taskJDBC.connectAndQuery(connection));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testConnectPreparedAndQuery() {
        try (Connection connection = DriverManager.getConnection(STRCONN, "sa", "")) {
            assertEquals(7, taskJDBC.connectPreparedAndQuery(connection));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void main() {
        String[] arg = new String[]{};
        TaskJDBC.main(arg);
    }
}