package com.e2mg;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
     * 执行Get请求线程
     */
    public class HttpThread extends Thread{
        private final CloseableHttpClient httpClient;
        private final HttpContext context;
        private final HttpGet httpGet;
        public HttpThread(CloseableHttpClient httpClient, HttpGet httpGet, String s) {
            this.httpClient = httpClient;
            this.context = HttpClientContext.create();
            this.httpGet = httpGet;
        }
        @Override
        public void run() {
            try {
                CloseableHttpResponse response = httpClient.execute(httpGet, context);
                try {
                    HttpEntity entity = response.getEntity();
                    System.out.println(IOUtils.toString(entity.getContent()));
                }finally {
                    EntityUtils.consume(response.getEntity());
                    response.close();
                }
            }catch (ClientProtocolException ex){
                //处理客户端协议异常
            }catch (IOException ex){
                //处理客户端IO异常
                ex.printStackTrace();
            }
        }
    }