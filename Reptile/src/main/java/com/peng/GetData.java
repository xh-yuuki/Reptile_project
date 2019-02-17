package com.peng;


import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class GetData {
    private CloseableHttpClient httpclient =null ;

    private CloseableHttpResponse response =null;
    private String response_text;
    public String data(String url){
//        生成一个Http客户端
        httpclient = HttpClients.createDefault();
//      创建一个get请求
        HttpGet httpGet =new HttpGet(url);
        try {
            //      发送请求
            response = httpclient.execute(httpGet);
            //设置超时5秒
            RequestConfig requestConfig = RequestConfig.custom()
                    .setConnectTimeout(5000).build();
            //获得响应实体
            httpGet.setConfig(requestConfig);
            HttpEntity httpEntity = response.getEntity();
            if(httpEntity != null) {
                response_text = EntityUtils.toString(httpEntity);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(httpclient!=null){
                try {
                    httpclient.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(response!=null){
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return response_text;
    }
}
