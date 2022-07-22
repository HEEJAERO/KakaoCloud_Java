package lecture0722.step1;

import java.sql.*;

public class UserDAO {

    public void insert(User user) throws ClassNotFoundException, SQLException { // 새로운 사용자를 테이블에 추가
        // throws 를 사용함으로서 try-catch 문을 여기서 사용하지 않아도 됨
        Class.forName("com.mysql.cj.jdbc.Driver");
        String jdbc_URL = "jdbc:mysql://localhost:3306/sqldb?characterEncoding=UTF-8&serverTimeZone=UTC&useSSL=false";
        Connection con = DriverManager.getConnection(jdbc_URL, "root", "test1234");

        String sql = "INSERT INTO users VALUES (?,?,?)";
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setString(1,user.getId());
        pstmt.setString(2, user.getName());
        pstmt.setString(3, user.getPassword());

        pstmt.executeUpdate();
        pstmt.close();
        con.close();
    }

    public User select(String id) throws ClassNotFoundException, SQLException {
        // user 의 id 를 이용해 user 정보 조회
        Class.forName("com.mysql.cj.jdbc.Driver");
        String jdbc_URL = "jdbc:mysql://localhost:3306/sqldb?characterEncoding=UTF-8&serverTimeZone=UTC&useSSL=false";
        Connection con = DriverManager.getConnection(jdbc_URL, "root", "test1234");

        String sql = "SELECT * FROM users WHERE id = ?";
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setString(1, id);

        ResultSet rs = pstmt.executeQuery();
        rs.next();
        User user = new User();
        user.setId(rs.getString("id"));
        user.setName(rs.getString("name"));
        user.setPassword(rs.getString("password"));

        rs.close();
        pstmt.close();
        con.close();

        return user;
    }

}
