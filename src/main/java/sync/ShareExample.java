package sync;


import java.util.concurrent.locks.ReentrantLock;

class LTicket{

    private int number=30;

    //默认为非公平锁，如果使用 new ReentrantLock(true) 为公平锁
    //公平锁相对非公平锁效率要低一些
    private final ReentrantLock lock = new ReentrantLock(true);

    public void sale(){
        lock.lock();
        try{
            if(number>0){
                System.out.println(Thread.currentThread().getName() + " :卖出" + (number--) + " 剩余："+number);
            }
        }
        finally {
            lock.unlock();
        }
    }




}

public class ShareExample {

    public static void main(String[] args)  {
        LTicket lTicket = new LTicket();
        new Thread(()->{
            for (int i=0;i<30;i++){
                try {
                    lTicket.sale();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        },"线程1").start();

        new Thread(()->{
            for (int i=0;i<30;i++){
                try {
                    lTicket.sale();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        },"线程2").start();
    }

}
