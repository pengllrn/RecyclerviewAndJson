package com.pengllrn.recyclerdemo;

import android.os.Handler;//别导错包
import android.os.Message;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * PARSOK 转换成功后的返回码
 */
public class GetAndParseJson {
    public static final int PARSEOK = 0x2017;
    private Handler handler;
    public GetAndParseJson(Handler handler){
        this.handler = handler;
    }

    public void getJsonFromInternet(final String path){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL(path);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setConnectTimeout(3000);
                    conn.setRequestMethod("GET");
                    System.out.println(conn.getResponseCode());
                    if(conn.getResponseCode()==200){
                        InputStream inputStream = conn.getInputStream();
                        List<Fruits> listNews=parseJson(inputStream);
                        if(listNews.size()>0){
                            Message msg = new Message();
                            msg.what = PARSEOK;//通知UI线程Json解析完成
                            msg.obj = listNews;//将解析的数据传递给UI线程
                            handler.sendMessage(msg);
                        }
                    }
                    else {
                        Message msg = new Message();
                        msg.what = 0x22;
                        handler.sendMessage(msg);
                    }
                } catch (IOException e) {
                    Message msg = new Message();
                    msg.what = 0x22;
                    handler.sendMessage(msg);
                    e.printStackTrace();
                }
            }
        }).start();
    }


    private List<Fruits> parseJson(InputStream inputStream) {
        List<Fruits> listFruits = new ArrayList<Fruits>();
        byte[] jsonBytes = convertIsToByteArray(inputStream);
        String json = new String(jsonBytes);
        try {
            JSONArray jsonArray = new JSONArray(json);
            for(int i=0;i<jsonArray.length();i++){
                JSONObject jObject=jsonArray.getJSONObject(i);
                String id=jObject.getString("id");
                String fruitname=jObject.getString("fruitname");
                Fruits fruit = new Fruits(id,fruitname);
                listFruits.add(fruit);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return listFruits;
    }

    //将输入流转换成Byte字节流，类似于streamtool
    private byte[] convertIsToByteArray(InputStream inputStream) {
        // TODO Auto-generated method stub
        ByteArrayOutputStream baos=new ByteArrayOutputStream();
        byte buffer[]=new byte[1024];
        int len=0;
        try {
            while ((len=inputStream.read(buffer))!=-1) {
                baos.write(buffer, 0, len);
            }
            inputStream.close();
            baos.flush();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return baos.toByteArray();
    }
}
