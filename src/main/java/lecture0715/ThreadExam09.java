package lecture0715;
class Account{
    private int balance = 1000;

    public int getBalance() {
        return balance;
    }
    // 출금하는 method -> 동기화 처리 -> 이 부분은 critical section -> 하나의 쓰레드가 실행하고 있다면 다른 쓰레드는 접근불가
    // 이렇게 메소드 전체를 동기화 처리하는것은 메소드 실행시간이 길어지면 비효율적인 코드가 된다.
    public void withdraw(int money){
        // 동기화 블럭 - 해당 블럭을 동기화 처리 -> 이것이 메소드 전체를 동기화 처리하는것보다는 더 효율적
        synchronized (this){ // 현재 객체에 대하여 동기화 처리
            if(balance>=money){
                try{
                    Thread.sleep(1000);
                }catch(Exception e){

                }
                balance -= money;
            }
        }
    }
}

class ThreadEx_09 implements Runnable {
    Account acc = new Account();

    @Override
    public void run() {
        while(acc.getBalance()>0){
            int money = (int) (Math.random() * 3 + 1) * 100; // 100~400
            acc.withdraw(money);
            System.out.println("남은 잔액은 : " + acc.getBalance());
        }
    }
}
public class ThreadExam09 {
    public static void main(String[] args) {
        ThreadEx_09 r = new ThreadEx_09();
        Thread t1 = new Thread(r);
        Thread t2 = new Thread(r);

        t1.start();
        t2.start();

    }
}
