package collection.thread.safety;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

//集合线程安全
public class HashSetMain {

    public static void main(String[] args) {

        //Set<String> list = new HashSet<>();
        Set<String> list = new CopyOnWriteArraySet<>();
        for (int i=0 ; i<=30; i++){
            new Thread(()->{
                list.add(UUID.randomUUID().toString().substring(0,8));

                System.out.println(list);
            },String.valueOf(i)).start();
        }

    }

}
