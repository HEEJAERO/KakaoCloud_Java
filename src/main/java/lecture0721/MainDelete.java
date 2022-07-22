package lecture0721;

import java.sql.*;

public class MainDelete {
    public static void main(String[] args) {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        // 1. JDBC Driver Loading
        //
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("드라이버 로딩 성공!");
            // 2.DBMS 와 연결
            String jdbcURL = "jdbc:mysql://localhost:3306/sqldb?" +
                    "characterEncoding=UTF-8&serverTimeZone=UTC&useSSL=false";
            con = DriverManager.getConnection(jdbcURL,"root","jae576792@");
            System.out.println("데이터 베이스 연결 성공!!");
            // 기본적으로는 auto commit 모드로 동작
            //별도로 설정 가능
            con.setAutoCommit(false); // transaction 의 시작
            // Prepared statement 는 In Parameter 를 사용할 수 있다.(값이 맵핑되는 부분만 가능- 예약어 불가능)
            String sql = "DELETE FROM buytbl WHERE userid = ?";
            // 3.statement 생성
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, "BBK"); //DBMS 표준은 1부터...
            // 4. 실행
            int result  = pstmt.executeUpdate();
            // 5. 결과처리
            System.out.println("총 " + result + " 행이 삭제되었습니다");

            con.rollback();// rollback 후 transaction 이 종료
            // 명시적으로 커밋 or 롤백하지않으면 connection 이 닫히는 순간 commit 된다.

        } catch (ClassNotFoundException e1) {
            System.out.println(e1);
        } catch (SQLException e2) {
            System.out.println(e2);
        }finally {
            try {
                //if(rs1!=null) rs1.close();
                // 6. 사용한 자원을 해제 -> 사용된 리소스의 역순으로 다 해제하도록 하자(최상위인 con 만 해제해도 되긴하지만 위험)
                if(rs!=null) rs.close();
                if(pstmt!=null) pstmt.close();
                //if(stmt!=null) stmt.close();
                if(con!=null) con.close();
            } catch (SQLException e) {

            }


        }


    }
}
