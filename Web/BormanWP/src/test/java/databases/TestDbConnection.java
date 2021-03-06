package databases;

import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import static java.sql.DriverManager.getConnection;

public class TestDbConnection {

    @Test
    public void testDbConnection() throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
//        Class.forName("org.firebirdsql.jdbc.FBDriver").newInstance();
//        DriverManager.registerDriver(new org.firebirdsql.jdbc.FBDriver());
//        Connection connection = DriverManager.getConnection(
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
