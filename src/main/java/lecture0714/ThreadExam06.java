package lecture0714;

class ThreadEx_06 implements Runnable {
    volatile boolean suspended = false;
    volatile boolean stopped = false;
    // 멀티 코어 환경에서 catch 에서 데이터를 가져오는것이 아닌 메모리에 직접 접근하여 데이터를 가져오도록 설정해주는
    // volatile 키워드를 설정해줘야한다.ㄴ
    @Override
    public void run() {
        while(!stopped){
            if(!suspended){
                System.out.println(Thread.currentThread().getName());
                try{
                    Thread.sleep(1000);
                }catch(Exception e){

                }
            }else{
                Thread.yield();
            }
        }
    }
    public void suspend(){
        suspended = true;
    }
    public void stop(){
        stopped = true;
    }
    public void resume(){
        suspended = false;
    }
}
public class ThreadExam06 {
    public static void main(String[] args) {
        ThreadEx_06 r1 = new ThreadEx_06();
        ThreadEx_06 r2 = new ThreadEx_06();
        ThreadEx_06 r3 = new ThreadEx_06();

        Thread t1 = new Thread(r1,"*"); // 두번째 인자 - thread 의 이름
        Thread t2 = new Thread(r2,"**");
        Thread t3 = new Thread(r3,"***");

        t1.start();
        t2.start();
        t3.start();

        try {
            Thread.sleep(2000);
            r1.suspend();//t1을 일시중지
            Thread.sleep(2000);
            r2.suspend(); // t2 를 일시중지
            Thread.sleep(2000);
            r1.resume(); // t1 을 다시 동작하도록 동작
            Thread.sleep(2000);
            r1.stop();
            r2.stop();
            Thread.sleep(2000);
            r3.stop();

        }catch (Exception e){

        }
    }
}
