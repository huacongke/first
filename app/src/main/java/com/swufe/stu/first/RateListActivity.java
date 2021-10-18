package com.swufe.stu.first;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;

import java.util.ArrayList;

public class RateListActivity extends AppCompatActivity {
    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate_list);
        ListView listView=findViewById(R.id.mylist1);
        ProgressBar progressBar=findViewById(R.id.progressBar2);


        String[] list_data={"one","two","three","four"};
        ListAdapter adapter=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,list_data);
//        listView.setListAdapter(adapter);


        handler=new Handler(Looper.myLooper()){
            @Override
            public void handleMessage(@NonNull Message msg) {
                if(msg.what==7){
                    ArrayList<String> rlist=(ArrayList<String>)msg.obj;
                    //List<String> list2=(List<String>) msg.obj;
                    ListAdapter adapter=new ArrayAdapter<String>(RateListActivity.this, android.R.layout.simple_list_item_1,rlist);
                    listView.setAdapter(adapter);
                }

                //调整控件显示状态
                progressBar.setVisibility(View.GONE);
                listView.setVisibility(View.VISIBLE);

                super.handleMessage(msg);
            }
        };
        //开启线程
//        MyThrea t=new MyThrea();
//        t.setHandler(handler);
//        Thread td=new Thread(t);
//        td.start();

        RateThread dt=new RateThread(handler);
        Thread t=new Thread(dt);
        t.start();
    }
}