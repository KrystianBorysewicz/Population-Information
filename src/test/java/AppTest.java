

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class AppTest
{
    static App app;

    @BeforeAll
    static void init()
    {
        app = new App();
    }

    @Test
    void printQueryResultSimple() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
        app.printQueryResult("SELECT * FROM country");
    }

    @Test
    void printQueryResultsNull() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
        app.printQueryResult(null);
    }

    @Test
    void printOptions1() {
        app.printOptions(1);
    }

    @Test
    void printOptions2() {
        app.printOptions(2);
    }

}