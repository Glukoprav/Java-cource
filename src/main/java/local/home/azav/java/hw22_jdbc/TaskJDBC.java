package local.home.azav.java.hw22_jdbc;

import java.sql.*;

public class TaskJDBC {
    public static void main(String[] args) {
        try {
            Class.forName("org.h2.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        // jdbc:h2:~/testdb
        // jdbc:h2:~/test
        // jdbc:h2:tcp://localhost/~/test
        try(Connection connection = DriverManager.getConnection("jdbc:h2:~/test","sa","");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from user_order_item");) {
            while (resultSet.next()){
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
//            PreparedStatement preparedStatement = connection.prepareStatement("select* from songs where id =?");
//            preparedStatement.setString(1,"25");
//            resultSet = preparedStatement.executeQuery();
//            while (resultSet.next()){
//                System.out.println(" name: "+ resultSet.getString("NAME") + " Time: "+ resultSet.getBigDecimal(4));
//            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
