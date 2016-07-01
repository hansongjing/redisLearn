package com.qm.redis;


import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.Iterator;

/**
 * Created by hanhansongjiang on 16/7/1.
 */
public class Example1 {
    private Jedis jedis;

    @Before
    public void setup() {

        jedis = new Jedis("127.0.0.1", 6379);
    }

    @Test
    public void testString() {
        //-----添加数据----------
        jedis.set("name", "小二");//向key-->name中放入了value-->xinxin
        System.out.println(jedis.get("name"));//执行结果：xinxin

        jedis.append("name", " " +
                "来壶上等好久"); //拼接
        System.out.println(jedis.get("name"));

        jedis.del("name");  //删除某个键
        System.out.println(jedis.get("name"));
        //设置多个键值对
        jedis.mset("name", "liuling", "age", "23", "qq", "476777XXX");


    }

    @Test
    public void TestMap() {
        HashMap<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("name", "xiaoer");
        hashMap.put("age", "23");
        hashMap.put("sex", "male");

        //set hashmap
        jedis.hmset("people", hashMap);

        //获取map中的对应的key params: key field1 field2


        System.out.println(jedis.hmget("people", "name", "age"));
        System.out.println(jedis.hmget("people", "name"));
        System.out.println(jedis.hlen("people"));//
        System.out.println(jedis.hkeys("people"));//
        System.out.println(jedis.hvals("people"));

        Iterator<String> iterator = jedis.hkeys("people").iterator();
        while (iterator.hasNext()) {


            String s = iterator.next();

            System.out.println(jedis.hmget("people", s));


        }


    }

  /*
   测试List的相关操作
    lrage(key,start,end),表示list的范围
    lpush加在头上  //类似栈
    rpush加在尾巴上  //类似队列
   */
    @Test
    public void testList(){
        //如果存在key则删除
        //加在头上
         jedis.del("nanjing");
        jedis.lpush("nanjing","9");
        jedis.lpush("nanjing","6");
        jedis.lpush("nanjing","7");
       System.out.println("排序前"+jedis.lrange("nanjing",0,-1));

       System.out.println("排序后"+jedis.sort("nanjing"));

        //Just test

        //lpush尾巴插入,所以输出顺序和加入顺序一致
        jedis.del("nanjing");
        jedis.rpush("nanjing","9");
        jedis.rpush("nanjing","6");
        jedis.rpush("nanjing","7");
        System.out.println("排序前"+jedis.lrange("nanjing",0,-1));









    }




}
