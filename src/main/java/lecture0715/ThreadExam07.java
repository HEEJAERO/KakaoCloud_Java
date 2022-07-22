package lecture0715;
class ThreadEx_07_1 extends Thread{
    @Override
    public void run() {
        for (int i = 0; i < 300; i++) {
            System.out.print("-");
        }
    }
}
class ThreadEx_07_2 extends Thread{
    @Override
    public void run() {
        for (int i = 0; i < 300; i++) {
            System.out.print("|");
        }
    }
}

public class ThreadExam07 {
    public static void main(String[] args) {
        Thread t1 = new ThreadEx_07_1();
        Thread t2 = new ThreadEx_07_2();

        t1.start();
        t2.start();
        try{
            t1.join(); // main thread(현재 이 코드를 실행시킨 쓰레드) 를 잠시 멈추고 t1 실행 (main thread 는 block 상태가 된다)
            t2.join();
            //이렇게 하면 main thread 의 실행순서가 제일 뒤로 ...
        }catch (Exception e){ //interrupt 처리를 위한 try-catch 문

        }
        System.out.println("<<main>> 종료>>");
    }
}
