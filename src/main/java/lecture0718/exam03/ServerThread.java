package lecture0718.exam03;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ServerThread implements Runnable {
    static List<Socket> sockets = new ArrayList<>();

    private Socket mySocket;

    ServerThread(Socket socket){
        this.mySocket = socket;
    }
    @Override
    public void run() {
        PrintWriter pw = null;
        BufferedReader br = null;
        sockets.add(mySocket);
        try{
            br = new BufferedReader(new InputStreamReader(mySocket.getInputStream()));
            while(true) {
                String receive = null;
                receive= br.readLine();
                System.out.println(receive);
                if(receive.equals("/exit")||receive==null) break;
                synchronized (this) {
                    for (Socket s : sockets) {
                        pw = new PrintWriter(s.getOutputStream());
                        pw.println(receive);
                        pw.flush();
                    }
                }
            }
        }catch(Exception e){

        }


    }
}
