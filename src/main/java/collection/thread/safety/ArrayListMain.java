package collection.thread.safety;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

//集合线程安全
public class ArrayListMain {

    public static void main(String[] args) {

        //List<String> list = new ArrayList<>();
        //解决方案一  使用 Vector
        //List<String> list = new Vector<>();

        //解决方案二 使用 Collections.synchronizedList
        // List<String> list = Collections.synchronizedList(new ArrayList<>());

        //解决方案三 使用 CopyOnWriteArrayList
        List<String> list = new CopyOnWriteArrayList<>();

        for (int i=0 ; i<=30; i++){
            new Thread(()->{
                list.add(UUID.randomUUID().toString().substring(0,8));

                System.out.println(list);
            },String.valueOf(i)).start();
        }

    }

}
