package lecture0718.exam03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientThread implements Runnable {
    private Socket socket;
    ClientThread(Socket socket) {
        this.socket = socket;
    }
    @Override
    public void run() {
        while(true){
            try {
                BufferedReader br = new BufferedReader( new InputStreamReader(socket.getInputStream()));
                if (br.readLine() != null) {
                    wait();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
