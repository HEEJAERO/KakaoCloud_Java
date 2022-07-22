package lecture0722.step5;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        //Service 없이 main 에서 직접 로직 처리
        ConnectionMaker connectionMaker = new NUserDAO(); // 여기서 인터페이스의 구현체를 생성하여 주고

        UserDAO dao = new UserDAO(connectionMaker); // 여기서 구현체를 인자로 넘겨준다.
        // 이렇게 되면 우리가 제공한 코드들 자체들은(클래스의 의존성) 클래스의 연관관계는 낮아지게 된다.
        // Java 프로그램은 interface 기반으로 구현하여 DI 를 통해 클래스의 의존성(연관관계)을 낮추는 방향으로 코드를 작성하는것이 좋다.
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
