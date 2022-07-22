package lecture0722.step4;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {

    private ConnectionMaker connectionMaker;
    public UserDAO(){
        connectionMaker = new NUserDAO(); // 구현체 클래스에 아직 구현하지도 않은 클래스의 이름이 들어가있다.(interface 를 제공하주면
        // 회사에서 구현체인 NUserDAO() 를 구현해야하는데 우리 로직에는 이미 구현하기도 전인 이 클래스의 내용이 들어가있다. -> 뭔가 이상함
    }
    public void insert(User user) throws ClassNotFoundException, SQLException { // 새로운 사용자를 테이블에 추가

        // throws 를 사용함으로서 try-catch 문을 여기서 사용하지 않아도 됨
        Connection con = connectionMaker.makeConnection();

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
        Connection con = connectionMaker.makeConnection();

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
