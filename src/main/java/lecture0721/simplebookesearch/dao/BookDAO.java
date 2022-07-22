package lecture0721.simplebookesearch.dao;

import lecture0721.simplebookesearch.vo.BookVo;
import org.apache.commons.dbcp2.BasicDataSource;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Properties;

public class BookDAO { //데이터 베이스 처리
    private static BasicDataSource basicDs;
    public BookDAO() {

        try {
            basicDs = new BasicDataSource();
            Properties properties = new Properties();
            InputStream is = new FileInputStream("/Users/heejae/Desktop/KakaoCloud_JavaFx/src/main/resources/db.properties");
            properties.load(is);

            basicDs.setDriverClassName(properties.getProperty("JDBC_DRIVER"));
            basicDs.setUrl(properties.getProperty("JDBC_URL"));
            basicDs.setUsername(properties.getProperty("DB_USER"));
            basicDs.setPassword(properties.getProperty("DB_PASSWORD"));

            // 어떻게 설정해야 하나? => 서비스에 따라 다르다
            basicDs.setInitialSize(10);
            basicDs.setMaxTotal(10);

        } catch (Exception e) {

        }


    }
    public static DataSource getDataSource(){
        return basicDs;
    }

    public ArrayList<BookVo> select(String keyword){
        // DB  를 연결하여 select 하는 작업이 여기서 일어난다.

        ArrayList<BookVo> list = new ArrayList<>();
        Connection con = null;
        DataSource ds = getDataSource(); // 만들어진 connection pool 을 가져옴
        try{
            System.out.println("select 실행1");
            con = ds.getConnection(); // pool 에서 사용가능한 connection 을 하나 리턴해준다.

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
        Connection con = null;
        DataSource ds = getDataSource(); // 만들어진 connection pool 을 가져옴
        try {
            con = ds.getConnection(); // pool 에서 사용가능한 connection 을 하나 리턴해준다.

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
