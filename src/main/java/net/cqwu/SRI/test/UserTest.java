package net.cqwu.SRI.test;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Collections;


public class UserTest {

    @Test
    public void getUsers(){
        Map<String, Integer> map = new HashMap<>();
        map.put("20000001", 1+1);
        map.put("20000002", 2);
        map.put("20000003", 3);
        map.put("20000005", 5);
        map.put("20000004", 4);
        List<String> keyList = new ArrayList<>(map.keySet());
        List<Integer> valueList = new ArrayList<>(map.values());
        Collections.sort(valueList);
        valueList.forEach(System.out::println);
        keyList.forEach(System.out::println);
        System.out.println(map);
    }

}
