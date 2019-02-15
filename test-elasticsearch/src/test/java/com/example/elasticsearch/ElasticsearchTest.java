package com.example.elasticsearch;

import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.Date;

import static org.elasticsearch.common.xcontent.XContentFactory.jsonBuilder;

@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@RunWith(SpringRunner.class)
@SpringBootTest
@Import(ElasticsearchApplication.class)
public class ElasticsearchTest {

    @Autowired
    private TransportClient client;

    @Test
    public void create() throws IOException {
        XContentBuilder builder = XContentFactory.jsonBuilder()
                .startObject()
                .field("user", "test")
                .field("postDate", new Date())
                .field("message", "this is Elasticsearch")
                .endObject();
        IndexResponse response = client.prepareIndex("fendo", "fendodate","1")
                .setSource(builder)
                .get();
        System.out.println(response.getResult());
    }

    @Test
    public void get(){
        //operationThreaded 设置为 true 是在不同的线程里执行此次操作
        GetResponse response = client.prepareGet("fendo", "fendodate","1")
                .setOperationThreaded(false)
                .get();
        System.out.println(response.getSource());
    }


    @Test
    public void update() throws IOException {
        UpdateRequest request = new UpdateRequest();
        request.index("fendo");
        request.type("fendodate");
        request.id("1");
        request.doc(jsonBuilder()
                .startObject()
                .field("user", "fendo")
                .field("postDate",  new Date())
                .field("message", "Hell word")
                .endObject());
        UpdateResponse response =client.update(request).actionGet();
        System.out.println(response.getResult());
    }

    @Test
    public void delete(){
        DeleteResponse response = client.prepareDelete("fendo", "fendodate", "AWjvVyQIhlzz7nlIUWH7").get();
        System.out.println(response.getResult());
    }
}
