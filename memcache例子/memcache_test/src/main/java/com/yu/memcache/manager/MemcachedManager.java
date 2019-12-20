package com.yu.memcache.manager;

import net.spy.memcached.MemcachedClient;
import net.spy.memcached.internal.OperationFuture;
import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * @className: MemcachedManager
 * @author: yu.liu
 * @date: 2019/8/7 10:55
 * @description: 缓存管理器
 *  服务端用C语言写的
 *  telnet 连接memcache服务端 stats 查看状态
 *
 */
public class MemcachedManager {

    @Value("${memcached.ip}")
    private String memcachedIp;

    @Value("${memcached.port}")
    private Integer memcacahedPort;

    public final static int DEFAULT_TIMEOUT = 5;

    public final static TimeUnit timeUnitSeconds = TimeUnit.SECONDS;

    private MemcachedClient memcachedClient;

    /**
     * 初始化方法
     */
    public void init(){
        try {
            memcachedClient = new MemcachedClient(new InetSocketAddress(memcachedIp, memcacahedPort));
            System.out.println("连接成功");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public  Boolean set(String key,int expire, Object value){
        OperationFuture<Boolean> result = memcachedClient.set(key, expire, value);
        return getResult(result);
    }

    public Object get(String key) {
        return memcachedClient.get(key);
    }

    public Object ascynGet(String key) {
        Future<Object> objectFuture = memcachedClient.asyncGet(key);
        return getResult(objectFuture);
    }


    public Boolean add(String key, Object value, int expire) {
        Future<Boolean> f = memcachedClient.add(key, expire, value);
        return getResult(f);
    }

    public Boolean replace(String key, Object value, int expire) {
        Future<Boolean> f = memcachedClient.replace(key, expire, value);
        return getResult(f);
    }

    public Boolean delete(String key) {
        Future<Boolean> f = memcachedClient.delete(key);
        return getResult(f);
    }

    /**
     * 从所有服务器清除所有缓存
     * @return
     */
    public Boolean flush() {
        Future<Boolean> f = memcachedClient.flush();
        return getResult(f);
    }

    /**
     * 从缓存中获取多个键值
     * @param keys
     * @return
     */
    public Map<String, Object> getMulti(Collection<String> keys) {
        return memcachedClient.getBulk(keys);
    }

    /**
     *  从缓存中获取多个键值
     * @param keys
     * @return
     */
    public Map<String, Object> getMulti(String[] keys) {
        return memcachedClient.getBulk(keys);
    }

    /**
     *  异步地从缓存中获取一组对象并使用它们进行解码
     * @param keys
     * @return
     */
    public Map<String, Object> asyncGetMulti(Collection<String> keys) {
        Map<String, Object> map = null;
        Future<Map<String, Object>> f = memcachedClient.asyncGetBulk(keys);
        try {
            map = getResult(f);
        } catch (Exception e) {
            f.cancel(false);
        }
        return map;
    }

    /**
     * 增加给定的计数器，返回新值。
     *
     * @param key          the key
     * @param by           the 增值
     * @param defaultValue the 默认值(如计时器不存在)，如该key没值，则取默认值
     * @param expire       the 过期时间
     * @return the long
     */
    public long increment(String key, int by, long defaultValue, int expire) {
        return memcachedClient.incr(key, by, defaultValue, expire);
    }


    /**
     * 以给定的数量增加给定的键。
     *
     * @param key the key
     * @param by  the 增值
     * @return the long
     */
    public long increment(String key, int by) {
        return memcachedClient.incr(key, by);
    }


    public <T> T getResult(Future<T> future) {
        try {
            return future.get(DEFAULT_TIMEOUT, timeUnitSeconds);
        } catch (Exception e) {
             e.printStackTrace();
        }
        return null;
    }


    /**
     * 关闭连接
     */
    public void disConnect() {
        if (memcachedClient == null) {
            return;
        }
        memcachedClient.shutdown();
    }

}
