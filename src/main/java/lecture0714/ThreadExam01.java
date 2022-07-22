package lecture0714;

class ThreadEx_01_1 extends Thread{
    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println(getName());
        }
    }
}
class ThreadEx_01_2 implements Runnable{
    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println(Thread.currentThread().getName());
            // 현재 실행되고 있는 Thread => 즉 이코드를 실행시키는 Thread 를 출력(이 class 를 사용하는 thread 출력)
        }
    }
}
public class ThreadExam01 {
    public static void main(String[] args) {
        ThreadEx_01_1 t1 = new ThreadEx_01_1();
        Thread t2 = new Thread(new ThreadEx_01_2());
        //t1.setPriority(10); // multi-core 환경에서는 우선순위의 의미가 없다 -> 굳이 사용할 이유가 없음
        t1.start();
        t2.start();

        System.out.println("main Thread 종료");

        // 어떤 thread 가 먼저 실행되는지 알 수 없다.
    }
}
