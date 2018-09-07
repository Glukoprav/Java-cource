package local.home.azav.java.hw22_jdbc;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Класс с запросами через JDBC к БД H2, наполнение которой сделано
 * скриптом user_order.sql из пакета hw21_sql_database
 */
public class TaskJDBC {
    private static final Logger LOG = Logger.getLogger(TaskJDBC.class.getName());

    private static String getPassv(String passv) {
        String passvord = passv + "a";
        return passvord;
    }

    Object forNameH2() {
        try {
            return Class.forName("org.h2.Driver");
        } catch (ClassNotFoundException e) {
            LOG.log(Level.SEVERE, "Exception on create driver DB: ", e);
            return null;
        }
    }

    int printUserOrderItem(ResultSet resultSet) throws SQLException {
        int countResult = 0;
        while (resultSet.next()) {
            LOG.log(Level.INFO, "User: {0} Date: {1} Item: {2} Value: {3} Price: {4} Order_cost: {5}",
                    new Object[]{resultSet.getString("user_name"),
                            resultSet.getDate("date_order"),
                            resultSet.getString("item_name"),
                            resultSet.getInt("value"),
                            resultSet.getBigDecimal("price"),
                            resultSet.getBigDecimal("order_cost")});
            countResult++;
        }
        return countResult;
    }

    int connectAndQuery(Connection connection) {
        int res = 0;
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("select * from user_order_item order by date_order");
            res = printUserOrderItem(resultSet);
            LOG.log(Level.INFO, "{0} selected string.", res);
            LOG.log(Level.INFO, "----------------------");
            return res;
        } catch (SQLException e) {
            LOG.log(Level.SEVERE, "Exception on connectAndQuery: ", e);
            return res;

        }
    }

    int connectPreparedAndQuery(Connection connection) {
        int res = 0;
        try (PreparedStatement preparedStatement = connection.prepareStatement("select * from user_order_item where user_id = ? order by date_order");) {
            preparedStatement.setInt(1, 2);
            ResultSet resultSet2 = preparedStatement.executeQuery();
            res = printUserOrderItem(resultSet2);
            LOG.log(Level.INFO, "{0} selected string.", res);
            return res;
        } catch (SQLException e) {
            LOG.log(Level.SEVERE, "Exception on connectPreparedAndQuery: ", e);
            return res;
        }
    }

    public static void main(String[] args) {
        String userDB = "sa";
        String passv = "aa";
        TaskJDBC taskJDBC = new TaskJDBC();
        if (taskJDBC.forNameH2() == null) {
            return;
        }
//  --      try (Connection connection = DriverManager.getConnection("jdbc:h2:C:/Documents and Settings/andreyz/IdeaProjects/firstproject/src/main/java/local/home/azav/java/hw22_jdbc/test", userDB, getPassv(passv))) {
        try (Connection connection = DriverManager.getConnection("jdbc:h2:C:/Users/Azav/IdeaProjects/Java-cource/src/main/java/local/home/azav/java/hw22_jdbc/test", userDB, getPassv(passv))) {
            taskJDBC.connectAndQuery(connection);
            taskJDBC.connectPreparedAndQuery(connection);
        } catch (SQLException e) {
            LOG.log(Level.SEVERE, "Exception: ", e);
        }
    }
}
