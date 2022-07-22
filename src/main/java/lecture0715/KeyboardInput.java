package lecture0715;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class KeyboardInput {
    public static void main(String[] args) {
        System.out.println("키보드로 한줄을 입력하세요");

        // 키보드(System.in)를 통해 데이터를 1줄 입력받자
        // System.in 이라는 제공된 Stream 은 사용하기 너무 힘듬 -byte 단위 입력
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);

        try {
            String input = br.readLine();
            System.out.println(input);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
