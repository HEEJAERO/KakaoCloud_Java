package lecture0721;

import org.apache.commons.dbcp2.BasicDataSource;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Properties;

public class MainDBCP {
// connection pool 로 만들기
    private static BasicDataSource basicDs;
    static{
        try{
            basicDs = new BasicDataSource();
            Properties properties = new Properties();
            InputStream is = new FileInputStream("resources /db.properties");
            properties.load(is);

            basicDs.setDriverClassName(properties.getProperty("JDBC_DRIVER"));
            basicDs.setUrl(properties.getProperty("JDBC_URL"));
            basicDs.setUsername(properties.getProperty("DB_USER"));
            basicDs.setPassword(properties.getProperty("DB_PASSWORD"));

            // 어떻게 설정해야 하나? => 서비스에 따라 다르다
            basicDs.setInitialSize(10);
            basicDs.setMaxTotal(10);

        }catch (Exception e){

        }
    }
    public static DataSource getDataSource(){
        return basicDs;
    }
    public static void main(String[] args) {
        Connection con = null;
        DataSource ds = getDataSource(); // 만들어진 connection pool 을 가져옴
        try{
            con = ds.getConnection(); // pool 에서 사용가능한 connection 을 하나 리턴해준다.
            con.setAutoCommit(false);

            String sql = "DELETE FROM buytbl";
            PreparedStatement pstmt = con.prepareStatement(sql);

            int result = pstmt.executeUpdate();

            con.commit();

            con.close(); // 여기서는 connection 이 닫히는게 아닌 connection pool 로 돌아가게 된다.
        }catch (Exception e){

        }

    }
}
