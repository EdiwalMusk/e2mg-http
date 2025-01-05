package com.e2mg;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpURLTest {

    public static void main(String[] args) {
        // 目标 URL
        String url = "https://www.baidu.com";

        try {
            // 1. 创建 URL 对象
            URL baiduUrl = new URL(url);

            // 2. 打开连接
            HttpURLConnection connection = (HttpURLConnection) baiduUrl.openConnection();

            // 3. 设置请求方法为 GET
            connection.setRequestMethod("GET");
            connection.setDoOutput(true);

            // 4. 设置请求头，模拟浏览器行为
            connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36");
            connection.setRequestProperty("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
            connection.setRequestProperty("Connection", "keep-alive");

            OutputStream os = connection.getOutputStream();
            os.write(1);

            // 5. 发送请求并获取响应码
            int responseCode = connection.getResponseCode();
            System.out.println("Response Code: " + responseCode);

            // 6. 如果响应码是 200（HTTP_OK），则读取响应内容
            if (responseCode == HttpURLConnection.HTTP_OK) {
                try (BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"))) {
                    String inputLine;
                    StringBuilder content = new StringBuilder();

                    // 逐行读取响应内容
                    while ((inputLine = in.readLine()) != null) {
                        content.append(inputLine).append("\n");
                    }

                    // 输出响应内容
                    System.out.println("Response Content: " + content.toString());
                }
            } else {
                System.out.println("Failed to retrieve data. Response Code: " + responseCode);
            }

            // 7. 断开连接
            connection.disconnect();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}