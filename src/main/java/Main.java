import java.io.Console;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, SQLException {
        printQueryResult("SELECT * FROM country order by Population desc limit 5");
        printQueryResult("SELECT * FROM city limit 3");



    }
    public static void printQueryResult(String query) throws ClassNotFoundException, IllegalAccessException, InstantiationException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/world?serverTimezone=UTC",
                "root","password123");
        Statement st = conn.createStatement();


        ResultSet rs = st.executeQuery(query);
        ResultSetMetaData rsmd = rs.getMetaData();
        int columnsNumber = rsmd.getColumnCount();
        while (rs.next()) {
            String s = "";
            for (int i = 1; i <= columnsNumber; i++) {
                String columnValue = rs.getString(i);
                s += (rsmd.getColumnName(i) + ": " + columnValue + " ");
            }
            System.out.println(s);
        }
        st.close();

        return;
    }
}