package lab10;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class Api {
    public static void main(String[] args) {
        String SUBMIT_METHOD_GET = "GET";  // 一定要是大写，否则请求无效

        String urlStr = "https://pokeapi.co/api/v2/pokemon/pikachu";  // 请求http地址


        HttpURLConnection connection = null;
        InputStream is = null;
        BufferedReader br = null;
        String result = null;  // 返回结果字符串
        try {
            // 创建远程url连接对象
            URL url = new URL(urlStr);
            // 通过远程url连接对象打开一个连接，强转成httpURLConnection类
            connection = (HttpURLConnection) url.openConnection();
            // 设置连接方式：GET
            connection.setRequestMethod(SUBMIT_METHOD_GET);
            // 设置连接主机服务器的超时时间：15000毫秒
            connection.setConnectTimeout(15000);
            // 设置读取远程返回的数据时间：60000毫秒
            connection.setReadTimeout(60000);
            // 发送请求
            connection.connect();
            // 通过connection连接，请求成功后获取输入流
            if (connection.getResponseCode() == 200) {
                is = connection.getInputStream();
                // 封装输入流is，并指定字符集
                br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
                // 存放数据
                StringBuffer sbf = new StringBuffer();
                String temp = null;
                while ((temp = br.readLine()) != null) {
                    sbf.append(temp);
                }
                result = sbf.toString();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 释放资源
            if (null != br) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null != is) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            connection.disconnect();  // 关闭远程连接
        }
//        System.out.println("Successfully：" + result);
        System.out.println("Name: pikachu");
        JSONObject jsonObject = JSONObject.parseObject(result);
        System.out.println("Height: " + jsonObject.get("height"));
        System.out.println("Weight: " + jsonObject.get("weight"));
        JSONArray abilities = jsonObject.getJSONArray("abilities");
        ArrayList<String> arrayList = new ArrayList<>();
        for (int i = 0; i < abilities.size(); i++) {
            JSONObject tmp = (JSONObject) abilities.get(i);
            JSONObject tmp1 = (JSONObject) tmp.get("ability");
            arrayList.add((String) tmp1.get("name"));
        }
//        System.out.println(abilities);
        System.out.println("Abilities: " + arrayList.toString());
    }
}
