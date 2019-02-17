package com.peng;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

class JsonData {

    private String url = "https://www.sojson.com/api/qqmusic/8446666";

    StringBuffer sbf = new StringBuffer();

    String result;

    public String  connect() throws IOException {
              URL Url =new URL(url);
              //获得一个connection
              HttpURLConnection connection = (HttpURLConnection)Url.openConnection();
              //设置请求参数
              connection.setConnectTimeout(10000);
              connection.setRequestMethod("GET");
              //建立连接
              connection.connect();
              //拿到响应码
              int responseCode =connection.getResponseCode();
              System.out.print(responseCode);
              String response=connection.getResponseMessage();
              System.out.println(response);
              if (responseCode == 200){
                  //拿到流
                  InputStream inputStream = connection.getInputStream();
                  //将流转换成字符串
                  BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                  String strRead = null;
                  while ((strRead = reader.readLine()) != null) {
                      sbf.append(strRead);
                      sbf.append("\r\n");
                  }
                  reader.close();
                  result = sbf.toString();   //将流转换为字符串。
                  //String response=connection.getResponseMessage();

              }
               return result;
    }
}
