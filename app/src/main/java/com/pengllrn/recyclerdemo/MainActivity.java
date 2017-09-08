package com.pengllrn.recyclerdemo;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    private List<Fruits> listFruits=new ArrayList<>();
    private String path = "http://192.168.1.103:9999/server_login/fruit/";
    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            // TODO Auto-generated method stub
            switch (msg.what) {
                case GetAndParseJson.PARSEOK:
                    listFruits=(List<Fruits>) msg.obj;
                    Toast.makeText(MainActivity.this,"水果已成功找到", Toast.LENGTH_SHORT).show();
                    showOnRecycler(listFruits);
                    break;
                case 0x22:
                    Fruits fruits = new Fruits("1","apple");
                    listFruits.add(fruits);
                    Toast.makeText(MainActivity.this,"水果未找到", Toast.LENGTH_SHORT).show();
            }
            super.handleMessage(msg);
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        GetAndParseJson getAndParseJson = new GetAndParseJson(mHandler);
        getAndParseJson.getJsonFromInternet(path);

    }

    public void showOnRecycler(List<Fruits> listFruits){
        //设置recycler显示的内容---
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        ItemAdapter adapter = new ItemAdapter(listFruits);
        recyclerView.setAdapter(adapter);
        //-----设置recycler显示的内容
    }

}
