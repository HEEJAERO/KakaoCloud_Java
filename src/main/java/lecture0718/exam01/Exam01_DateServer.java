package lecture0718.exam01;


import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

//Server Process
public class Exam01_DateServer {

    public static void main(String[] args) {

        try {
            ServerSocket serverSocket = new ServerSocket(5678);
            System.out.println("Date Server 기동 - 포트번호(5678)");
            Socket socket = serverSocket.accept(); // blocking method  => 여기서 기다림 -> 클라이언트에서 요청이 오면
            // 통신가능한 socket을 만들어서 리턴해줌

            // 여기까지 오면 접속이 성공
            // 소켓을 이용해서 output stream 을 만들어야함
            PrintWriter pw = new PrintWriter(socket.getOutputStream());
            // 현재날짜를 구함
            Date date = new Date();
            pw.println(date.toLocaleString());
            // 버퍼안에 들어가 있는 데이터를 스트림을 통해 내보내는 시점은
            // 1. 스트림이 강제로 종료될 경우(close)
            // 2. 버퍼의 공간이 다 찰 경우
            // 3. method 를 이용해서 flush() 시킬 경우
            pw.flush();

            pw.close();
            socket.close();
            serverSocket.close();
            // 사용된 자원들을 모두 반납해야지 프로그램이 제대로 동작한다..
            System.out.println("Date Server 종료");
        } catch (IOException e) {

        }

    }
}
