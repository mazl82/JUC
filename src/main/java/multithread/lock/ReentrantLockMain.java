package multithread.lock;


import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Share3{

    private int number=0;

    private ReentrantLock lock = new ReentrantLock();

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

public class ReentrantLockMain {

    public static void main(String[] args) {
        Share3 share = new Share3();
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
