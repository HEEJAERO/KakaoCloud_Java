package lecture0722.step4;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        //Service 없이 main 에서 직접 로직 처리
        UserDAO dao = new UserDAO();
        User user = new User();
        user.setId("1");
        user.setName("홍길동");
        user.setPassword("test1234");


        dao.insert(user);

        System.out.println("사용자 등록");


        User user2 = dao.select("1");

        System.out.println(user2.getName() + "," + user2.getPassword());

    }
}
