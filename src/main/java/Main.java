import services.*;
import ui.HomePage;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {

        CreateTables.CreateTablesInDatabase();
        new HomePage();


    }
}
