package com.yu.memcache;

import com.yu.memcache.manager.MemcachedManager;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MemcacheTestApplicationTests {

    @Resource
    private MemcachedManager memcachedManager;

    @Test
    public void testSetGet() {
        Boolean result = memcachedManager.set("someKey", 10000, "AAAAA");
        if (result) {
            System.out.println("***********  " + memcachedManager.get("someKey").toString());
            return;
        }
        System.out.println("***********  操作失败!  ***********");
    }

    @Test
    public void testAsyncGet2() {
        //获取值，如果在5秒内没有返回值，将取消
        Object myObj = null;
        Object result = memcachedManager.ascynGet("someKey");
        System.out.println(result);
    }

    @Test
    public void testReplace() {
        Boolean flag = memcachedManager.replace("someKey", "BBBBB", 10000);
        if (flag) {
            System.out.println("更新替换键值成功!");
            System.out.println("最终结果为:" + memcachedManager.get("someKey").toString());
            return;
        }
        System.out.println("更新键值失败!");
    }

    @Test
    public void testAdd() {
        Boolean flag = memcachedManager.add("someKey", "CCCCCC", 10000);
        if (flag) {
            System.out.println("最终结果为:" + memcachedManager.get("someKey").toString());
            return;
        }
        System.out.println("添加键值失败!");
    }

    @Test
    public void delete() {
        Boolean f = memcachedManager.delete("someKey");
        System.out.println("删除" + (f ? "成功!" : "失败!"));
    }

    @Test
    public void incrementTest() {
        long result = memcachedManager.increment("increment", 5, 20, 10000);
        System.out.println(result);
    }


    @Test
    public void flushTest() {
        memcachedManager.flush();
        Object result = memcachedManager.get("aa");
        System.out.println(result);
    }

}
