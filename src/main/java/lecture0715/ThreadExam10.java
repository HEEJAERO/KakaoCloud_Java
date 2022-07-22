package lecture0715;
class Shared{
    public synchronized void printNum(){
        System.out.println(Thread.currentThread().getName());
        notify();
        try{
            Thread.sleep(1000);
            wait();
        }catch(Exception e){

        }
    }
}
class ThreadExam_10 implements Runnable {
    Shared s = new Shared();
    @Override
    public void run() {
            while(true) {
                s.printNum();
            }
    }
}
public class ThreadExam10 {
    public static void main(String[] args) {
        ThreadExam_10 r = new ThreadExam_10();
        Thread t1 = new Thread(r,"첫번째");
        Thread t2 = new Thread(r,"두번쨰");
       // Thread t3 = new Thread(r,"세번쨰");

        t1.start();
        t2.start();
        //t3.start();
    }
}
