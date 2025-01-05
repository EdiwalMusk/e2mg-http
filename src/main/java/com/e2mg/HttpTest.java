package com.e2mg;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class HttpTest {

    public static void main(String[] args) throws InterruptedException, IOException {
        PoolingHttpClientConnectionManager poolingHttpClientConnectionManager = new PoolingHttpClientConnectionManager();
        poolingHttpClientConnectionManager.setMaxTotal(1);             //连接池最多连接数
        poolingHttpClientConnectionManager.setDefaultMaxPerRoute(1);    //每个域名的最大连接数,
        CloseableHttpClient httpClient = HttpClients.custom()
                .setConnectionManager(poolingHttpClientConnectionManager)
                .build();
        for (int i = 0; i < 10; i++) {
            HttpContext context = HttpClientContext.create();
            HttpGet httpGet = new HttpGet("http://www.baidu.com");
            CloseableHttpResponse response = httpClient.execute(httpGet, context);
            try {
                HttpEntity entity = response.getEntity();
                System.out.println(IOUtils.toString(entity.getContent()));
            } finally {
                EntityUtils.consume(response.getEntity());
                response.close();
            }
        }
        httpClient.close();
    }
}
