package lecture0722.step2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class NUserDAO extends UserDAO{
    @Override
    protected Connection getConnection() throws ClassNotFoundException, SQLException {
        // 각 회사 나름대로의 connection 연결방법에 대한 구현이 나오면 된다.
        Class.forName("com.mysql.cj.jdbc.Driver");
        String jdbc_URL = "jdbc:mysql://localhost:3306/sqldb?characterEncoding=UTF-8&serverTimeZone=UTC&useSSL=false";
        Connection con = DriverManager.getConnection(jdbc_URL, "root", "test1234");
        return con;
    }
}
