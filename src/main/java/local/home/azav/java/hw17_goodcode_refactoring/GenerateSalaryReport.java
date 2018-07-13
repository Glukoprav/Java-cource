package local.home.azav.java.hw17_goodcode_refactoring;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

class GenerateSalaryReport {
    private final Connection connection;

    GenerateSalaryReport(Connection databaseConnection) {
        connection = databaseConnection;
    }

    // Делаем запрос и возвращаем результат в виде ResultSet
    ResultSet generateSalaryResultSet(String departmentId, LocalDate dateFrom, LocalDate dateTo) {
        // prepare statement with sql
        ResultSet results = null;
        try (PreparedStatement ps = connection.prepareStatement(
                "select emp.id as emp_id, emp.name as amp_name, sum(salary) as salary" +
                        "from employee emp left join salary_payments sp on emp.id = sp.employee_id" +
                        "where emp.department_id = ? and sp.date >= ? and sp.date <= ?" +
                        "group by emp.id, emp.name")) {
            // inject parameters to sql
            ps.setString(1, departmentId);
            ps.setDate(2, new java.sql.Date(dateFrom.toEpochDay()));
            ps.setDate(3, new java.sql.Date(dateTo.toEpochDay()));
            // execute query and get the results
            results = ps.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return results;
    }

    // Результат запроса конвертируем в HTML, возвращаем в виде StringBuilder
    StringBuilder convertResultSetToHTML(ResultSet results) {
        if (results == null) {
            throw new NullPointerException("The query result is empty!");
        }
        // create a StringBuilder holding a resulting html
        StringBuilder resultingHtml = new StringBuilder();
        resultingHtml.append("<html><body><table><tr><td>Employee</td><td>Salary</td></tr>");
        double totals = 0;
        try {
            while (results.next()) {
                // process each row of query results
                resultingHtml.append("<tr>"); // add row start tag
                resultingHtml.append("<td>").append(results.getString("emp_name")).append("</td>"); // appending employee name
                resultingHtml.append("<td>").append(results.getDouble("salary")).append("</td>"); // appending employee salary for period
                resultingHtml.append("</tr>"); // add row end tag
                totals += results.getDouble("salary"); // add salary to totals
            }
            resultingHtml.append("<tr><td>Total</td><td>").append(totals).append("</td></tr>");
            resultingHtml.append("</table></body></html>");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultingHtml;
    }
}
