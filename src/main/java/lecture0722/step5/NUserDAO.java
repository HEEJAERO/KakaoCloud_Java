package lecture0722.step5;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class NUserDAO implements ConnectionMaker {
    @Override
    public Connection makeConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String jdbc_URL = "jdbc:mysql://localhost:3306/sqldb?characterEncoding=UTF-8&serverTimeZone=UTC&useSSL=false";
        Connection con = DriverManager.getConnection(jdbc_URL, "root", "test1234");
        return con;
    }
}
