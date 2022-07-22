package lecture0718.exam03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ChattingServer{
    public static void main(String[] args) {
        List<ServerThread> threads = new ArrayList<>();
        ServerSocket serverSocket = null;
        Socket socket = null;
        BufferedReader br = null;
        try {
            serverSocket = new ServerSocket(5678);
            System.out.println("Echo Server 기동 - 클라이언트 접속 대기");
            while(true) {
                socket = serverSocket.accept();
                Thread thread = new Thread(new ServerThread(socket));
                thread.setDaemon(true);
                thread.start();
                br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String receive = null;
                receive= br.readLine();
                System.out.println(receive);
                if(receive.equals("/exit")||receive==null) break;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }finally {
            // 사용된 resource 해제
            try{
                if(br!=null) br.close();
                if(socket!=null) socket.close();
                if(serverSocket!=null) serverSocket.close();
            }catch(Exception e){

            }

        }
    }
}
