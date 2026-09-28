package prueba2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {
    public static Connection getConnection() throws SQLException {
        final String USER = "root";
        final String PASS = "1234";
        final String DB_NAME = "nomina_bd";
        final String CONN_URL = "jdbc:mariadb://localhost:3306/" + DB_NAME;

        return DriverManager.getConnection(CONN_URL, USER, PASS);
    }
}
