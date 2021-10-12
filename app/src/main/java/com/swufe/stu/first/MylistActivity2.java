package com.swufe.stu.first;

import android.app.ListActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;

import androidx.annotation.NonNull;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MylistActivity2 extends ListActivity{
    Handler handler;
    Bundle bundle;
    private static final String TAG="ListActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        List<String> list1=new ArrayList<String>();
        for(int i=0;i<100;i++){
            list1.add("item"+i);

        }
        handler=new Handler(Looper.myLooper()){
            @Override
            public void handleMessage(@NonNull Message msg) {
                if(msg.what==7){
                    ArrayList<String> rlist=(ArrayList<String>)msg.obj;
                    //List<String> list2=(List<String>) msg.obj;
                    ListAdapter adapter=new ArrayAdapter<String>(MylistActivity2.this, android.R.layout.simple_list_item_1,rlist);
                    setListAdapter(adapter);
                }

                super.handleMessage(msg);
            }
        };
        //开启线程
//        MyThrea t=new MyThrea();
//        t.setHandler(handler);
//        Thread td=new Thread(t);
//        td.start();
//
//        RateThread dt=new RateThread(handler);
//        Thread t=new Thread(dt);
//        t.start();

        MainActivity rt=new MainActivity();
        rt.setHandler(handler);
        Thread t=new Thread(rt);
        t.start();


//        String[] list_data={"one","two","three","four"};
//        ListAdapter adapter=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,list_data);
//        setListAdapter(adapter);
    }
//

}