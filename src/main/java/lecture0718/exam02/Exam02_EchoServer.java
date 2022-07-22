package lecture0718.exam02;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Exam02_EchoServer {

    public static void main(String[] args) {
        ServerSocket serverSocket = null;
        Socket socket = null;
        PrintWriter pw = null;
        BufferedReader br = null;
        try {
            serverSocket = new ServerSocket(5678);
            System.out.println("Echo Server 기동 - 클라이언트 접속 대기");
            socket = serverSocket.accept();
            br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            pw = new PrintWriter(socket.getOutputStream());
            String receive = null;
            while(true) {

                receive= br.readLine();
                System.out.println(receive);

                if(receive.equals("/exit")||receive==null) break;
                pw.println(receive);
                pw.flush();
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }finally {
            // 사용된 resource 해제
            try{
                if(br!=null) br.close();
                if(pw!=null) pw.close();
                if(socket!=null) socket.close();
                if(serverSocket!=null) serverSocket.close();
            }catch(Exception e){

            }

        }
    }






}
