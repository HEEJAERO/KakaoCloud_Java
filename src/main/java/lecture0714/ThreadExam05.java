package lecture0714;

class ThreadEx_05 implements Runnable {

    @Override
    public void run() {
         while(true){
             System.out.println(Thread.currentThread().getName()); // 현재 사용중인 Thread 의 이름 출력
             try{
                 Thread.sleep(1000);
             } catch (InterruptedException e) {

             }
         }
    }
}
public class ThreadExam05 {
    public static void main(String[] args) {
        ThreadEx_05 r = new ThreadEx_05();
        Thread t1 = new Thread(r,"*"); // 두번째 인자 - thread 의 이름
        Thread t2 = new Thread(r,"**"); // 두번째 인자 - thread 의 이름
        Thread t3 = new Thread(r,"***"); // 두번째 인자 - thread 의 이름

        t1.start();
        t2.start();
        t3.start();

        try {
            Thread.sleep(2000);
            t1.suspend();//t1을 일시중지
            Thread.sleep(2000);
            t2.suspend(); // t2 를 일시중지
            Thread.sleep(2000);
            t1.resume(); // t1 을 다시 동작하도록 동작
            Thread.sleep(2000);
            t1.stop();
            t2.stop();
            Thread.sleep(2000);
            t3.stop();

        }catch (Exception e){

        }
    }
}
