package collection.thread.safety;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

//�����̰߳�ȫ
public class ArrayListMain {

    public static void main(String[] args) {

        //List<String> list = new ArrayList<>();
        //�������һ  ʹ�� Vector
        //List<String> list = new Vector<>();

        //��������� ʹ�� Collections.synchronizedList
        // List<String> list = Collections.synchronizedList(new ArrayList<>());

        //��������� ʹ�� CopyOnWriteArrayList
        List<String> list = new CopyOnWriteArrayList<>();

        for (int i=0 ; i<=30; i++){
            new Thread(()->{
                list.add(UUID.randomUUID().toString().substring(0,8));

                System.out.println(list);
            },String.valueOf(i)).start();
        }

    }

}
