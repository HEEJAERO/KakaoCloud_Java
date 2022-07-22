package lecture0715;
class ThreadEx_08_1 extends Thread{
    final static int MAX_MEMORY = 1000;
    int usedMemory = 0;
    @Override
    public void run() {
        while(true){
            try{
                Thread.sleep(10000); // 10초 동안 sleep
            }catch(Exception e){
                System.out.println("interrupt()에 의해 꺠어남");
            }
            gc();
            System.out.println("메모리 청소완료... 현재 사용 가능한 메모리 량은 "+ freeMemory());
        }
    }
    private void gc(){ // 내부에서 사용되는 로직용이므로 private
        usedMemory -= 300;
        if(usedMemory<0) {
            usedMemory = 0;
        }
    }
    public int totalMemory(){
        return MAX_MEMORY;
    }
    public int freeMemory(){
        return MAX_MEMORY - usedMemory;
    }
}
public class ThreadExam08 {
    public static void main(String[] args) {
        ThreadEx_08_1 t = new ThreadEx_08_1();
        t.setDaemon(true);
        t.start();

        int requiredMemory = 0;
        for (int i = 0; i < 20; i++) {
            requiredMemory = (int)(Math.random()*10)*20;
                            // 0보다 같거나 크고 200보다 작은 정수
            // 필요한 메모리가 사용할수 있는 양보다 크거나
            // 현재 전체 메모리양의 60% 이상을 사용하고 있는때 gc 를 실행
            if (requiredMemory > t.freeMemory() || t.freeMemory() < t.totalMemory() * 0.4) {

                t.interrupt(); // interrupt 로 깨워 메모리 청소를 하도록...
                // 하지만 interrupt 만 걸고 gc() 가 수행되는것을 기다리지 않는다.
                try{
                    t.join(100); // join()을 호출해 줘야 main thread 가 gc가 실행되는것을 기다린다.
                    // 시간설정을 하지않으면 run의 로직이 무한루프이기때문에 시간을 걸어줘야함
                }catch(InterruptedException e){

                }
            }
            t.usedMemory += requiredMemory;
            System.out.println("사용된 메모리 량: " + t.usedMemory);
        }
    }
}
