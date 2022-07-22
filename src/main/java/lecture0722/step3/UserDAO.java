package lecture0722.step3;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {

    private SimpleConnectionMaker simpleConnectionMaker;
    public UserDAO(){
        simpleConnectionMaker = new SimpleConnectionMaker();
    }
    public void insert(User user) throws ClassNotFoundException, SQLException {
        Connection con = simpleConnectionMaker.makeNewConnection();

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
        Connection con = simpleConnectionMaker.makeNewConnection();
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
