package local.home.azav.java.hw22_jdbc;

import java.sql.*;

/**
 * Класс с запросами через JDBC к БД H2, наполнение которой сделано
 * скриптом user_order.sql из пакета hw21_sql_database
 */
public class TaskJDBC {

    Object forNameH2() {
        try {
            return Class.forName("org.h2.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    int printUserOrderItem(ResultSet resultSet) throws SQLException {
        int countResult = 0;
        while (resultSet.next()) {
            System.out.println("User: " + resultSet.getString("user_name")
                    + " Date: " + resultSet.getDate("date_order")
                    + " Item: " + resultSet.getString("item_name")
                    + " Value: " + resultSet.getInt("value")
                    + " Price: " + resultSet.getBigDecimal("price")
                    + " Order_cost: " + resultSet.getBigDecimal("order_cost"));
            countResult++;
        }
        return countResult;
    }

    int connectAndQuery(TaskJDBC taskJDBC) {
        int res = 0;
        try (Connection connection = DriverManager.getConnection("jdbc:h2:C:/Documents and Settings/andreyz/IdeaProjects/firstproject/src/main/java/local/home/azav/java/hw22_jdbc/test", "sa", "");
//        try (Connection connection = DriverManager.getConnection("jdbc:h2:C:/Users/Azav/IdeaProjects/Java-cource/src/main/java/local/home/azav/java/hw22_jdbc/test", "sa", "");
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("select * from user_order_item order by date_order");) {
            res = taskJDBC.printUserOrderItem(resultSet);
            System.out.println(res + " selected string.");
            System.out.println("----------------------");
            System.out.println("Petrov");
            try (PreparedStatement preparedStatement = connection.prepareStatement("select * from user_order_item where user_id = ? order by date_order");
            ) {
                preparedStatement.setInt(1, 2);
                try (ResultSet resultSet2 = preparedStatement.executeQuery()) {
                    res = taskJDBC.printUserOrderItem(resultSet2);
                    System.out.println(res + " selected string.");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return res;
        } catch (SQLException e) {
            e.printStackTrace();
            return res;

        }
    }

    public static void main(String[] args) {
        TaskJDBC taskJDBC = new TaskJDBC();
        if (taskJDBC.forNameH2() == null) {
            return;
        }
        taskJDBC.connectAndQuery(taskJDBC);
    }
}
