package collection.thread.safety;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;

//集合线程安全
public class HashMapMain {

    public static void main(String[] args) {

        //Map<String,String> list = new HashMap<>();

        Map<String,String> list = new ConcurrentHashMap<>();
        for (int i=0 ; i<=30; i++){
            String key = String.valueOf(i);
            new Thread(()->{
                list.put(key,UUID.randomUUID().toString().substring(0,8));

                System.out.println(list);
            },String.valueOf(i)).start();
        }

    }

}
