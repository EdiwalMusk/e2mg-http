package com.e2mg;


import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class OkHttpTest {

    public static void main(String[] args) {
        // 1. 创建 OkHttpClient 实例
        OkHttpClient client = new OkHttpClient();

        // 2. 构建请求
        Request request = new Request.Builder().url("https://www.baidu.com")  // 目标 URL
                .get()  // 设置请求方法为 GET
                .addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36")  // 模拟浏览器的 User-Agent
                .build();

        // 3. 发送同步请求并获取响应
        try (Response response = client.newCall(request).execute()) {
            // 4. 检查响应是否成功
            if (response.isSuccessful()) {
                // 5. 读取并输出响应内容
                String responseBody = response.body().string();
                System.out.println("Response Body: " + responseBody);
            } else {
                // 6. 如果请求失败，输出错误信息
                System.out.println("Request failed. Response Code: " + response.code());
            }
        } catch (IOException e) {
            // 7. 处理网络异常
            e.printStackTrace();
        }
    }
}
