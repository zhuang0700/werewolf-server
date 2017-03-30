package com.joyweekend;

import com.alibaba.fastjson.JSON;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by weiwenliang on 17/1/2.
 */
public class JavaTest {
    class MyObj {
        public String name;
    }

    @Test
    public void memoryTest() {
        MyObj o = new MyObj();
        o.name = "o";
        List<MyObj> a = new ArrayList<>();
        List<MyObj> b = new ArrayList<>();
        a.add(o);
        b.add(o);
        a.get(0).name = "a";
        funcTest(o);
        System.err.print(JSON.toJSONString(o));
    }

    @Test
    public void memoryTest2() {
        MyObj o = new MyObj();
        o.name = "o";
        List<Integer> a = new ArrayList<>();
        a.add(1);
        List<MyObj> b = new ArrayList<>();

        funcTest2(a);
        System.err.print(JSON.toJSONString(a));
    }

    public void funcTest(MyObj o) {
        o.name = "func";
    }

    public void funcTest2(List<Integer> list) {
        list.add(2);
        Integer i = list.get(0);
        i = 3;
    }
}
