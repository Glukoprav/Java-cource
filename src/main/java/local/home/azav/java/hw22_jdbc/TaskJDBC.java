package local.home.azav.java.hw22_jdbc;

import java.sql.*;

/**
 * Класс с запросами через JDBC к БД H2, наполнение которой сделано
 * скриптом user_order.sql из пакета hw21_sql_database
 */
public class TaskJDBC {
    public static void main(String[] args) {
        try {
            //Class.forName("oracle.jdbc.driver.OracleDriver");
            Class.forName("org.h2.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        // jdbc:local.home.azav.java.hw22_refactoring_dao.h2:~/testdb
        // jdbc:local.home.azav.java.hw22_refactoring_dao.h2:~/test
        // jdbc:local.home.azav.java.hw22_refactoring_dao.h2:tcp://localhost/~/test
        //try(Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:od","HR","qwerty");
        try (Connection connection = DriverManager.getConnection("jdbc:h2:~/test", "sa", "");
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("select * from user_order_item order by date_order");) {
            while (resultSet.next()) {
//                u.id user_id,
//                u.name user_name,
//                o.order_id order_id,
//                o.date_order date_order,
//                i.name item_name,
//                o.value value,
//                i.price price,
//                o.value * i.price order_cost
                System.out.println("User: " + resultSet.getString("user_name")
                        + " Date: " + resultSet.getDate("date_order")
                        + " Item: " + resultSet.getString("item_name")
                        + " Value: " + resultSet.getInt("value")
                        + " Price: " + resultSet.getBigDecimal("price")
                        + " Order_cost: " + resultSet.getBigDecimal("order_cost"));
            }
            System.out.println("----------------------");
            System.out.println("Petrov");
            try (PreparedStatement preparedStatement = connection.prepareStatement("select * from user_order_item where user_id = ? order by date_order");
            ) {
                preparedStatement.setInt(1, 2);
                try (ResultSet resultSet2 = preparedStatement.executeQuery()) {
                    while (resultSet2.next()) {
                        System.out.println(" Date: " + resultSet2.getDate("date_order")
                                + " Item: " + resultSet2.getString("item_name")
                                + " Value: " + resultSet2.getInt("value")
                                + " Price: " + resultSet2.getBigDecimal("price")
                                + " Order_cost: " + resultSet2.getBigDecimal("order_cost"));
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
