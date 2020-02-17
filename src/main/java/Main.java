import java.io.Console;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {

        Scanner scn = new Scanner(System.in);
        Boolean exit = false;

        while(!exit) {
            printOptions();

            int inputOption = Integer.parseInt(scn.nextLine());

            // Handle user input
            switch(inputOption) {
                case 0:
                    exit = true;
                    break;
                case 1:
                    printQueryResult("SELECT * FROM country ORDER BY Population DESC");
                    break;
                case 2:
                    System.out.println("Input the continent: ");
                    printQueryResult(String.format("SELECT * FROM country WHERE continent = '%s' ORDER BY Population DESC", scn.nextLine()));
                    break;
                case 3:
                    System.out.println("Input the region: ");
                    printQueryResult(String.format("SELECT * FROM country WHERE region = '%s' ORDER BY Population DESC", scn.nextLine()));
                    break;
                case 4:
                    System.out.println("Input the number of countries you want to print");
                    printQueryResult(String.format("SELECT * FROM country ORDER BY Population DESC LIMIT %s", scn.nextLine()));
                    break;
                case 5:
                    System.out.println("Input the number of countries you want to print");
                    String s = scn.nextLine();
                    System.out.println("Input the continent");
                    String x = scn.nextLine();
                    printQueryResult(String.format("SELECT * FROM country WHERE Continent = '%s' ORDER BY Population DESC LIMIT %s", x, s));
                    break;
                case 6:
                    break;
                case 7:
                    break;
                case 8:
                    break;
                case 9:
                    break;
                case 10:
                    break;
                case 11:
                    break;
                case 12:
                    break;
                case 13:
                    break;
                case 14:
                    break;
                case 15:
                    break;
                case 16:
                    break;
                case 17:
                    break;
                case 18:
                    break;
                case 19:
                    break;
                case 20:
                    break;
                case 21:
                    System.out.println("Input the number of countries you want to print");
                    printQueryResult(String.format("SELECT * FROM city JOIN country ON country.Capital = city.ID ORDER BY city.Population DESC LIMIT %s", scn.nextLine()));
                    break;
                case 22:
                    System.out.println("Input the number of countries you want to print");
                    String n22 = scn.nextLine();
                    System.out.println("Input the continent");
                    String continent22 = scn.nextLine();
                    printQueryResult(String.format("SELECT * FROM city JOIN country ON country.Capital = city.ID WHERE Continent = '%s' ORDER BY city.Population DESC LIMIT %s;\n", continent22, n22));
                    break;
                case 23:
                    System.out.println("Input the number of countries you want to print");
                    String n23 = scn.nextLine();
                    System.out.println("Input the region");
                    String region23 = scn.nextLine();
                    printQueryResult(String.format("SELECT * FROM city JOIN country ON country.Capital = city.ID WHERE Region = '%s' ORDER BY city.Population DESC LIMIT %s;\n", region23, n23));
                    break;
                case 24:
                    printQueryResult("SELECT SUM(DISTINCT country.Population) AS Total, SUM(city.Population) AS Cities, (SUM(DISTINCT country.Population) - SUM(city.Population)) AS \"Non-cities\", country.Continent\n" +
                            "FROM country JOIN city ON country.Code = city.CountryCode\n" +
                            "GROUP BY country.Continent;\n");
                    break;
                case 25:
                    printQueryResult("SELECT SUM(DISTINCT country.Population) AS Total, SUM(city.Population) AS Cities, (SUM(DISTINCT country.Population) - SUM(city.Population)) AS \"Non-cities\", country.Region\n" +
                            "FROM country JOIN city ON country.Code = city.CountryCode\n" +
                            "GROUP BY country.Region;\n");
                    break;
                case 26:
                    printQueryResult("SELECT SUM(DISTINCT country.Population) AS Total, SUM(city.Population) AS Cities, (SUM(DISTINCT country.Population) - SUM(city.Population)) AS Rural, country.Name\n" +
                            "FROM country JOIN city ON country.Code = city.CountryCode\n" +
                            "GROUP BY country.Name;\n");
                    break;

            }
            if(!exit) {
                System.out.println("Do you wish to pull another report? 1 = YES / 0 = NO");
                inputOption = Integer.parseInt(scn.nextLine());
                if(inputOption == 0)
                    exit = true;
            }

        }
    }

    /// <summary>
    /// Prints all the results of a sql query to the console
    /// </summary>
    /// <param name="query">The sql query to be run against the database</param>
    public static void printQueryResult(String query) throws ClassNotFoundException, IllegalAccessException, InstantiationException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/world?serverTimezone=UTC",
                "root","password123");
        Statement st = conn.createStatement();

        ResultSet rs = st.executeQuery(query);
        ResultSetMetaData rsmd = rs.getMetaData();
        int columnsNumber = rsmd.getColumnCount();
        //Loop through query result and print to console
        while (rs.next()) {
            String s = "";
            for (int i = 1; i <= columnsNumber; i++) {
                String columnValue = rs.getString(i);
                s += (rsmd.getColumnName(i) + ": " + columnValue + ", ");
            }
            System.out.println(s);
        }
        st.close();
        return;
    }

    /// <summary>
    /// Prints all the user options to the console
    /// </summary>
    public static void printOptions() {
        List<String> options = Arrays.asList(
                "1. All the countries in the world organised by largest population to smallest.",
                "2. All the countries in a continent organised by largest population to smallest.",
                "3. All the countries in a region organised by largest population to smallest.",
                "4. The top N populated countries in the world.",
                "5. The top N populated countries in a continent.",
                "6. The top N populated countries in a region.",
                "7. All the cities in the world organised by largest population to smallest.",
                "8. All the cities in a continent organised by largest population to smallest.",
                "9. All the cities in a region organised by largest population to smallest.",
                "10. All the cities in a country organised by largest population to smallest.",
                "11. All the cities in a district organised by largest population to smallest.",
                "12. The top N populated cities in the world.",
                "13. The top N populated cities in a continent.",
                "14. The top N populated cities in a region.",
                "15. The top N populated cities in a country.",
                "16. The top N populated cities in a district.",
                "17. All the capital cities in the world organised by largest population to smallest.",
                "18. All the capital cities in a continent organised by largest population to smallest.",
                "19. All the capital cities in a region organised by largest to smallest.",
                "20. The top N populated capital cities in the world.",
                "21. The top N populated capital cities in a continent.",
                "22. The top N populated capital cities in a region.",
                "23. The population of people, people living in cities, and people not living in cities in each continent.",
                "24. The population of people, people living in cities, and people not living in cities in each region.",
                "25. The population of people, people living in cities, and people not living in cities in each country.",
                "26. Special reports",
                "0. Exit",
                "Chose your option by typing the option number and hitting ENTER"
        );
        for(int i = 0; i < options.size(); i++)
            System.out.println(options.get(i));
    }
}