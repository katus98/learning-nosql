package com.katus.demo;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.junit.Test;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

import java.util.HashSet;
import java.util.Set;

/**
 * Jedis连接Redis集群
 *
 * @author SUN Katus
 * @version 1.0, 2021-08-12
 */
public class Demo6 {
    /**
     * 连接Redis集群
     */
    @Test
    public void test() {
        // 构建节点集合
        Set<HostAndPort> nodes = new HashSet<>();
        nodes.add(new HostAndPort("10.79.231.86", 7001));
        nodes.add(new HostAndPort("10.79.231.86", 7002));
        nodes.add(new HostAndPort("10.79.231.86", 7003));
        nodes.add(new HostAndPort("10.79.231.86", 7004));
        nodes.add(new HostAndPort("10.79.231.86", 7005));
        nodes.add(new HostAndPort("10.79.231.86", 7006));

        // 构建连接池配置
        GenericObjectPoolConfig<?> poolConfig = new GenericObjectPoolConfig<>();
        // 最大活跃数
        poolConfig.setMaxTotal(100);
        // 最大空闲数
        poolConfig.setMaxIdle(10);
        // 最小空闲数
        poolConfig.setMinIdle(5);
        // 当连接池空了之后, 多久没获取到Jedis对象则超时
        poolConfig.setMaxWaitMillis(3000);

        // 构建Redis集群访问对象
        JedisCluster cluster = new JedisCluster(nodes, 30000, 10000, 10, "skrv587", poolConfig);
        String name = cluster.get("name");
        System.out.println(name);
    }
}
