package lecture0722.simplebookesearch.dao;

import lecture0722.simplebookesearch.vo.BookVo;
import org.apache.commons.dbcp2.BasicDataSource;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

public class BookDAO { //데이터 베이스 처리
    private BasicDataSource basicDs;
    public BookDAO(ConnectionPoolMaker connectionPoolMaker) throws IOException {
        this.basicDs = connectionPoolMaker.makePool();
    }

    public ArrayList<BookVo> select(String keyword){
        // DB  를 연결하여 select 하는 작업이 여기서 일어난다.

        ArrayList<BookVo> list = new ArrayList<>();

        try{
            Connection con = basicDs.getConnection();

            String sql = "SELECT bisbn, btitle,bdate,bprice FROM BOOK WHERE btitle LIKE '%"+keyword+"%'";
            System.out.println("select 실행2");
            PreparedStatement pstmt = con.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                list.add(new BookVo(rs.getString("bisbn")
                        , rs.getString("btitle")
                        , rs.getString("bdate")
                        , rs.getInt("bprice")));
            }
            con.close(); // 여기서는 connection 이 닫히는게 아닌 connection pool 로 돌아가게 된다.
        }catch (Exception e1){
            e1.printStackTrace();

        }
        return list;
    }



    public String delete(String bisbn) {
        String s = null;

        try {
            Connection con = basicDs.getConnection();

            String sql = "DELETE FROM BOOK WHERE bisbn=" + "'" + bisbn + "'";
            System.out.println(sql);
            PreparedStatement pstmt = con.prepareStatement(sql);
            int i = pstmt.executeUpdate();

            s = i + "개가 삭제되었습니다\n ";
            System.out.println("delete 실행");
            con.close(); // 여기서는 connection 이 닫히는게 아닌 connection pool 로 돌아가게 된다.
        }catch(Exception e){

        }
        return s;
    }
}
