package local.home.azav.java.hw22_jdbc;

import org.junit.Before;
import org.junit.Test;

import java.sql.*;

import static org.junit.Assert.*;

public class TaskJDBCTest {
    private TaskJDBC taskJDBC;

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
        try (Connection connection = DriverManager.getConnection("jdbc:h2:~/test", "sa", "");
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("select * from user_order_item order by date_order");) {
            assertEquals(20, taskJDBC.printUserOrderItem(resultSet));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testConnectAndQuery() {
        assertEquals(7, taskJDBC.connectAndQuery(taskJDBC));
    }
}