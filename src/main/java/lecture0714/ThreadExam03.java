package lecture0714;
class ThreadEx_03_1 extends Thread{
    @Override
    public void run() {
        try{
            Thread.sleep(2000);
        }catch(Exception e){

        }
        for (int i = 0; i <300; i++) {
            System.out.print("-");
        }
        System.out.println("<<Thread1 종료>>");
    }
}
class ThreadEx_03_2 extends Thread{
    @Override
    public void run() {
        for (int i = 0; i <300; i++) {
            System.out.print("|");
        }
        System.out.println("<<Thread2 종료>>");
    }
}

public class ThreadExam03 {
    public static void main(String[] args) {
        ThreadEx_03_1 t1 = new ThreadEx_03_1();
        ThreadEx_03_2 t2 = new ThreadEx_03_2();

        t1.start();
        t2.start();
        System.out.println("<<main Thread 종료>>");
    }
}
