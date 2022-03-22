package sync;


import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


class Share2{

    private int number=0;

    private Lock lock = new ReentrantLock();

    private Condition condition = lock.newCondition();

    public void incr() throws InterruptedException {
        lock.lock();
        try{
            while (number!=0){
                condition.await();
            }
            number++;
            System.out.println(Thread.currentThread().getName() + ":" + number);
            condition.signalAll();
        }
        finally {
            lock.unlock();
        }
    }

    public void decr() throws InterruptedException{
        lock.lock();
        try{
            while (number==0){
                condition.await();
            }
            number--;
            System.out.println(Thread.currentThread().getName() + ":" + number);
            condition.signalAll();
        }
        finally {
            lock.unlock();
        }
    }

}

public class ShareExample2 {

    public static void main(String[] args) throws Exception  {
        Share2 share = new Share2();
        new Thread(()->{
            for (int i=0;i<11;i++){
                try {
                    share.incr();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"线程1").start();

        new Thread(()->{
            for (int i=0;i<11;i++){
                try {
                    share.decr();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"线程2").start();
    }

}
