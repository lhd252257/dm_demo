package com.bifangan.dmDemo.utils;

import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Component;

/**
 * Created by
 * 王尚飞
 * on 2020/7/10 11:38
 */
@Component
public class HttpGetRequestUtil {
    /**
     * 发送POST请求
     * @param url 请求url
     * @param data 请求数据
     * @return 结果
     */
    @SuppressWarnings("deprecation")
    public static String doGet(String url, String data) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);
        RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(10000).setConnectTimeout(20000).setConnectionRequestTimeout(10000).build();
        httpGet.setConfig(requestConfig);

        // 设置回调接口接收的消息头
        httpGet.addHeader("Content-Type", "application/json");
        CloseableHttpResponse response = null;
        String context = null;
        try {
            response = httpClient.execute(httpGet);
            HttpEntity entity = response.getEntity();
            context = EntityUtils.toString(entity, HTTP.UTF_8);
        } catch (Exception e) {
            e.getStackTrace();
        } finally {
            try {
                response.close();
                httpGet.abort();
                httpClient.close();
            } catch (Exception e) {
                e.getStackTrace();
            }
        }
        return context;
    }

}
