package databases;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import static java.sql.DriverManager.getConnection;

public class Main {
    public /*static*/ void main(String[] args) throws SQLException {
        Connection connection = getConnection(
                "jdbc:firebirdsql://localhost:3050/c:\\DB\\DBWS.fdb",
                "SYSDBA",
                "masterkey"
        );
        Statement stat = connection.createStatement();
        System.out.println("connection = " + connection);
        System.out.println("stat       = " + stat);

    }
}
