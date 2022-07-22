package lecture0721;

import java.sql.*;

public class Main {
    public static void main(String[] args) {
        Connection con = null;
        //Statement stmt = null;
        PreparedStatement pstmt = null;
        //ResultSet rs1 = null;
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


            String sql = "SELECT userID, name, addr FROM usertbl";
            // 3.statement 생성
          //  stmt = con.createStatement(); // 일반 statement 는 실행할 때 SQL을 넣는다.
            // Prepared Statement => 생성할 때 SQL을 넣는다.
            // => 데이터베이스가 알아먹을 수 있도록 JavaString 을 미리 인코딩해서 가지고 있음 => 성능상 이점
            pstmt = con.prepareStatement(sql);

            // 4. 실행
            // rs1 = stmt.executeQuery(sql);
             rs = pstmt.executeQuery(); // 이미 전처리된 SQL 문장을 가지고 있기 때문에 parameter 를 안넣어도 됨

            // 5. 결과처리
            // rs 의 초기값은 컬럼명을 가르킴
            // rs.next() == true 이면 다음으로 커서를 이동하였을떄 거기에 값이 존재
            // rs.next()== false 이면 커서를 이동하였는데 값이 없을때(즉, 더이상 데이터가 존재하지 않을때)
            while(rs.next()){
                String id = rs.getString(1);
                String name = rs.getString(2);
                String addr = rs.getString(3);
                System.out.println(id + "," + name + "," + addr);
            }
            // 6. 사용한 자원을 해제 -> 사용된 리소스의 역순으로 다 해제하도록 하자(최상위인 con 만 해제해도 되긴하지만 위험)

        } catch (ClassNotFoundException e1) {
            System.out.println(e1);
        } catch (SQLException e2) {
            System.out.println(e2);
        }finally {
            try {
                //if(rs1!=null) rs1.close();
                if(rs!=null) rs.close();
                if(pstmt!=null) pstmt.close();
                //if(stmt!=null) stmt.close();
                if(con!=null) con.close();
                } catch (SQLException e) {

                }


        }


    }
}
