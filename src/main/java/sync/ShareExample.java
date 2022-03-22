package sync;


import java.util.concurrent.locks.ReentrantLock;

class LTicket{

    private int number=30;

    //Ĭ��Ϊ�ǹ�ƽ�������ʹ�� new ReentrantLock(true) Ϊ��ƽ��
    //��ƽ����Էǹ�ƽ��Ч��Ҫ��һЩ
    private final ReentrantLock lock = new ReentrantLock(true);

    public void sale(){
        lock.lock();
        try{
            if(number>0){
                System.out.println(Thread.currentThread().getName() + " :����" + (number--) + " ʣ�ࣺ"+number);
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
        },"�߳�1").start();

        new Thread(()->{
            for (int i=0;i<30;i++){
                try {
                    lTicket.sale();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        },"�߳�2").start();
    }

}
