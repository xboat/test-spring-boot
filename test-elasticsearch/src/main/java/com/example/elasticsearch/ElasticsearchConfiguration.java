package com.example.elasticsearch;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.network.InetAddresses;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.net.InetSocketAddress;

/**
 * @author xboat date 2019-02-02
 */
@Component
@Configuration
public class ElasticsearchConfiguration {

    private String clusterName = "my-application";
    private String esIp = "192.168.231.130";
    private Integer esPort = 9300;

    @Bean
    public TransportClient createClient() {
        Settings esSettings = Settings.builder()
                .put("cluster.name", clusterName) //设置ES实例的名称
                .put("client.transport.sniff", true) //自动嗅探整个集群的状态，把集群中其他ES节点的ip添加到本地的客户端列表中
                //.put("network.host", esIp)
                .build();

        TransportClient client = new PreBuiltTransportClient(esSettings);
        //此步骤添加IP，至少一个，其实一个就够了，因为添加了自动嗅探配置
        client.addTransportAddress(new InetSocketTransportAddress(
                new InetSocketAddress(InetAddresses.forString(esIp), esPort)));
        return client;
    }
}
