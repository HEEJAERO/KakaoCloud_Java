package lecture0714;

import javax.swing.*;

class ThreadEx_04 extends Thread{
    @Override
    public void run() {
        int i = 10;
        while (i != 0 && !isInterrupted()) {
            System.out.println(--i);
            //for(long k =0;k<Integer.MAX_VALUE;k++);
            try{
                Thread.sleep(3000);
            }catch(Exception e){
                interrupt(); // 여기서 다시 interrupt 호출
            }
            // sleep 상태의 thread 가 interrupted 당하면
            //sleep 상태에서 Runnable 상태로 변하면서 interrupte 상태가 false로 바뀌게 됨
            // 따라서 sleep 의 catch 문에서 다시 interrupt() 를 호출해야 sleep 상태에서도 thread 가 종료되도록 할 수 있다.
        }
        System.out.println(" 카운트 종료");
    }
}
public class ThreadExam04 {
    public static void main(String[] args) {
        Thread t = new ThreadEx_04();

        t.start();

        String input = JOptionPane.showInputDialog("값을 입력하세요");
        System.out.println(input);

        t.interrupt();
        //  t thread 의 interrupted 상태가 true 가 된다.
        System.out.println("Thread 상태값은 : " + t.isInterrupted());

    }
}
