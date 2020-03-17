import java.io.Console;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {

        Scanner scn = new Scanner(System.in);
        Boolean exit = false;

        // Main program loop
        while(!exit) {
            printOptions(1);

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
                    System.out.println("Input the number of countries you want to print");
                    String q6a = scn.nextLine();
                    System.out.println("Input the region");
                    String q6b = scn.nextLine();
                    printQueryResult(String.format("SELECT * FROM country WHERE Region = '%s' ORDER BY Population DESC LIMIT %s", q6b, q6a));
                    break;
                case 7:
                    printQueryResult("SELECT Name as City, Population FROM city ORDER BY Population DESC");
                    break;
                case 8:
                    System.out.println("Input the name of the Continent:");
                    String q8 = scn.nextLine();
                    printQueryResult(String.format("SELECT City, Continent, Population FROM (\n" +
                            "SELECT city.ID, city.Name as City, country.Continent, city.Population FROM country LEFT JOIN city ON country.Code = city.CountryCode\n" +
                            "WHERE ID IS NOT NULL\n" +
                            "UNION\n" +
                            "SELECT city.ID, city.Name as City, country.Continent, city.Population FROM country RIGHT JOIN city ON country.Code = city.CountryCode) as A\n" +
                            "WHERE Continent = '%s'\n" +
                            "ORDER BY Population DESC", q8));
                    break;
                case 9:
                    System.out.println("Input the name of the Region:");
                    String q9 = scn.nextLine();
                    printQueryResult(String.format("SELECT City, Region, Population FROM (\n" +
                            "SELECT city.ID, city.Name as City, country.Region, city.Population FROM country LEFT JOIN city ON country.Code = city.CountryCode\n" +
                            "WHERE ID IS NOT NULL\n" +
                            "UNION\n" +
                            "SELECT city.ID, city.Name as City, country.Region, city.Population FROM country RIGHT JOIN city ON country.Code = city.CountryCode) as A\n" +
                            "WHERE Region = '%s'\n" +
                            "ORDER BY Population DESC", q9));
                    break;
                case 10:
                    System.out.println("Input the name of the Country:");
                    String q10 = scn.nextLine();
                    printQueryResult(String.format("SELECT City, Country, Population FROM (\n" +
                            "SELECT city.ID, city.Name as City, country.Name as Country, city.Population FROM country LEFT JOIN city ON country.Code = city.CountryCode\n" +
                            "WHERE ID IS NOT NULL\n" +
                            "UNION\n" +
                            "SELECT city.ID, city.Name as City, country.Name as Country, city.Population FROM country RIGHT JOIN city ON country.Code = city.CountryCode) as A\n" +
                            "WHERE Country = '%s'\n" +
                            "ORDER BY Population DESC", q10));
                    break;
                case 11:
                    System.out.println("Input the name of the District:");
                    String q11 = scn.nextLine();
                    printQueryResult(String.format("SELECT Name as City, District, Population FROM city WHERE District = '%s' ORDER BY Population DESC", q11));
                    break;
                case 12:
                    System.out.println("Input the number of Cities you want to print:");
                    String q12 = scn.nextLine();
                    printQueryResult(String.format("SELECT Name as City, Population FROM city ORDER BY Population DESC LIMIT %s", q12));
                    break;
                case 13:
                    System.out.println("Input the name of the Continent:");
                    String q13a = scn.nextLine();
                    System.out.println("Input the number of Cities you want to print:");
                    String q13b = scn.nextLine();
                    printQueryResult(String.format("SELECT City, Continent, Population FROM (\n" +
                            "SELECT city.ID, city.Name as City, country.Continent, city.Population FROM country LEFT JOIN city ON country.Code = city.CountryCode\n" +
                            "WHERE ID IS NOT NULL\n" +
                            "UNION\n" +
                            "SELECT city.ID, city.Name as City, country.Continent, city.Population FROM country RIGHT JOIN city ON country.Code = city.CountryCode ) as A\n" +
                            "WHERE Continent = '%s'\n" +
                            "ORDER BY Population DESC\n" +
                            "LIMIT %s", q13a, q13b));
                    break;
                case 14:
                    System.out.println("Input the name of the Region:");
                    String q14a = scn.nextLine();
                    System.out.println("Input the number of Cities you want to print:");
                    String q14b = scn.nextLine();
                    printQueryResult(String.format("SELECT City, Region, Population FROM (\n" +
                            "SELECT city.ID, city.Name as City, country.Region, city.Population FROM country LEFT JOIN city ON country.Code = city.CountryCode\n" +
                            "WHERE ID IS NOT NULL\n" +
                            "UNION\n" +
                            "SELECT city.ID, city.Name as City, country.Region, city.Population FROM country RIGHT JOIN city ON country.Code = city.CountryCode) as A\n" +
                            "WHERE Region = '%s'\n" +
                            "ORDER BY Population DESC\n" +
                            "LIMIT %s", q14a, q14b));
                    break;
                case 15:
                    System.out.println("Input the name of the Country:");
                    String q15a = scn.nextLine();
                    System.out.println("Input the number of Cities you want to print:");
                    String q15b = scn.nextLine();
                    printQueryResult(String.format("SELECT City, Country, Population FROM (\n" +
                            "SELECT city.ID, city.Name as City, country.Name as Country, city.Population FROM country LEFT JOIN city ON country.Code = city.CountryCode\n" +
                            "WHERE ID IS NOT NULL\n" +
                            "UNION\n" +
                            "SELECT city.ID, city.Name as City, country.Name as Country, city.Population FROM country RIGHT JOIN city ON country.Code = city.CountryCode) as A\n" +
                            "WHERE Country = '%s'\n" +
                            "ORDER BY Population DESC\n" +
                            "limit %s", q15a, q15b));
                    break;
                case 16:
                    System.out.println("Input the name of the District:");
                    String q16a = scn.nextLine();
                    System.out.println("Input the number of Cities you want to print:");
                    String q16b = scn.nextLine();
                    printQueryResult(String.format("SELECT Name as City, District, Population FROM city WHERE Population IS NOT NULL AND District = '%s' ORDER BY Population desc LIMIT %s", q16a, q16b));
                    break;
                case 17:
                    printQueryResult("SELECT city.Name as City, city.Population from country LEFT JOIN city on country.Capital = city.ID WHERE Capital IS NOT NULL ORDER BY Population DESC");
                    break;
                case 18:
                    System.out.println("Input the name of the Continent:");
                    String q18 = scn.nextLine();
                    printQueryResult(String.format("SELECT city.Name as City, country.Continent, city.Population from country LEFT JOIN city ON country.Capital = city.ID \n" +
                            "WHERE Capital IS NOT NULL AND Continent = '%s' ORDER BY Population DESC", q18));
                    break;
                case 19:
                    System.out.println("Input the name of the Region:");
                    String q19 = scn.nextLine();
                    printQueryResult(String.format("SELECT city.Name as City, city.Population FROM country LEFT JOIN city ON country.Capital = city.ID\n" +
                            "WHERE Capital IS NOT NULL AND Region = '%s' ORDER BY Population desc", q19));
                    break;
                case 20:
                    System.out.println("Input the number of countries you want to print");
                    printQueryResult(String.format("SELECT * FROM city JOIN country ON country.Capital = city.ID ORDER BY city.Population DESC LIMIT %s", scn.nextLine()));
                    break;
                case 21:
                    System.out.println("Input the number of countries you want to print");
                    String n22 = scn.nextLine();
                    System.out.println("Input the continent");
                    String continent22 = scn.nextLine();
                    printQueryResult(String.format("SELECT * FROM city JOIN country ON country.Capital = city.ID WHERE Continent = '%s' ORDER BY city.Population DESC LIMIT %s;\n", continent22, n22));
                    break;
                case 22:
                    System.out.println("Input the number of countries you want to print");
                    String n23 = scn.nextLine();
                    System.out.println("Input the region");
                    String region23 = scn.nextLine();
                    printQueryResult(String.format("SELECT * FROM city JOIN country ON country.Capital = city.ID WHERE Region = '%s' ORDER BY city.Population DESC LIMIT %s;\n", region23, n23));
                    break;
                case 23:
                    printQueryResult("SELECT SUM(DISTINCT country.Population) AS Total, SUM(city.Population) AS Cities, (SUM(DISTINCT country.Population) - SUM(city.Population)) AS \"Non-cities\", country.Continent\n" +
                            "FROM country JOIN city ON country.Code = city.CountryCode\n" +
                            "GROUP BY country.Continent;\n");
                    break;
                case 24:
                    printQueryResult("SELECT SUM(DISTINCT country.Population) AS Total, SUM(city.Population) AS Cities, (SUM(DISTINCT country.Population) - SUM(city.Population)) AS \"Non-cities\", country.Region\n" +
                            "FROM country JOIN city ON country.Code = city.CountryCode\n" +
                            "GROUP BY country.Region;\n");
                    break;
                case 25:
                    printQueryResult("SELECT SUM(DISTINCT country.Population) AS Total, SUM(city.Population) AS Cities, (SUM(DISTINCT country.Population) - SUM(city.Population)) AS Rural, country.Name\n" +
                            "FROM country JOIN city ON country.Code = city.CountryCode\n" +
                            "GROUP BY country.Name;\n");
                    break;
                case 26:
                    printOptions(2);
                    inputOption = Integer.parseInt(scn.nextLine());
                    switch(inputOption) {
                        case 1:
                            printQueryResult("SELECT SUM(population) as 'Total World Population' from country");
                            break;
                        case 2:
                            System.out.println("Input the name of the Continent:");
                            String a26 = scn.nextLine();
                            printQueryResult(String.format("SELECT continent, SUM(population) as 'Total Continent Population' from country where continent = '%s'", a26));
                            break;
                        case 3:
                            System.out.println("Input the name of the Region:");
                            String b26 = scn.nextLine();
                            printQueryResult(String.format("SELECT SUM(population) from country where region = '%s'", b26));
                            break;
                        case 4:
                            System.out.println("Input the name of the Country:");
                            String c26 = scn.nextLine();
                            printQueryResult(String.format("SELECT population from country where Name = '%s'", c26));
                            break;
                        case 5:
                            System.out.println("Input the name of the District:");
                            String d26 = scn.nextLine();
                            printQueryResult(String.format("SELECT sum(population) from city where district = '%s'", d26));
                            break;
                        case 6:
                            System.out.println("Input the name of the City:");
                            String e26 = scn.nextLine();
                            printQueryResult(String.format("SELECT population from city where name = '%s'", e26));
                            break;
                    }
                    break;

            }
            if(!exit) {
                System.out.println("Do you wish to generate another report? 1 = YES / 0 = NO");
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

        if (query == null){
            System.out.println("Query is empty");
            return;
        }

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
    public static void printOptions(int opt) {
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
        // Options for the "Special reports"
        List<String> options2 = Arrays.asList(
                "1. Population of the world.",
                "2. Population of a continent.",
                "3. Population of a region.",
                "4. Population of a country.",
                "5. Population of a district.",
                "6. Population of a city.",
                "7. Population of the world speaking: Chinese, English, Hindi, Spanish and Arabic"
        );

        if(opt == 1) {
            for(int i = 0; i < options.size(); i++)
                System.out.println(options.get(i));
        }
        else if (opt == 2) {
            for(int i = 0; i < options2.size(); i++)
                System.out.println(options2.get(i));
        }
    }
}