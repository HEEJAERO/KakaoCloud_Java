package lecture0722.step2;

import java.sql.*;

public abstract class UserDAO {

    public void insert(User user) throws ClassNotFoundException, SQLException { // 새로운 사용자를 테이블에 추가
        // throws 를 사용함으로서 try-catch 문을 여기서 사용하지 않아도 됨
        Connection con = getConnection();

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
        Connection con = getConnection();

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

    //private Connection getConnection() throws ClassNotFoundException, SQLException {
        // 메서드를 추출하여 유지보수성을 높인것은 좋았으나. getConnection 이라는 메서드는 우리 시스템에서밖에 사용할 수 있다.
        // 재사용성에 문제 !!
        // => 이 부분을 추상메서드로 만들어 select 와 insert 는 그대로 사용하고
        // connection 부분만을 각자의 시스템에 맞게 구현하여 사용할 수 있도록 하자
    protected abstract Connection getConnection() throws ClassNotFoundException, SQLException ;



}
