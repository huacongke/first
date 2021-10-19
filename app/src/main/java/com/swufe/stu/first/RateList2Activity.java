package com.swufe.stu.first;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class RateList2Activity extends AppCompatActivity implements AdapterView.OnItemLongClickListener, AdapterView.OnItemClickListener {


    Handler handler;
    private static final String TAG="RateList2Activity";
    ListView list2;
    MyAdapter myadapter;
    ArrayList<Item> rlist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate_list2);
        list2=findViewById(R.id.mylist2);

        list2.setOnItemClickListener(this);

        list2.setOnItemLongClickListener(this);
        //准备数据
        ArrayList<HashMap<String,String>> listItems=new ArrayList<HashMap<String,String>>();
        for (int i=0;i<10;i++){
            HashMap<String,String> map=new HashMap<String,String>();

            map.put("Itemtitle","Rate:"+i);
            map.put("ItemDetaie","detail:"+i);
            listItems.add(map);
        }

        //生成适配器的Item和动态数组对应的元素
        SimpleAdapter listItemAdapter=new SimpleAdapter(this,
                listItems,//数据源
                R.layout.list_item,//ListItem的XML布局实现
                new String[]{"Itemtitle","ItemDetaie"},
                new int[]{R.id.itemTitle,R.id.itemDetail}
        );
        list2.setAdapter(listItemAdapter);
        //页面数据为空时，显示no_data文本框及其内容
        list2.setEmptyView(findViewById(R.id.no_data));

        handler=new Handler(Looper.myLooper()){
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                Log.i(TAG, "handleMessage: ");
                if(msg.what==9){
//                    ArrayList<HashMap<String,String>> rlist=(ArrayList<HashMap<String,String>>)msg.obj;
                    rlist=(ArrayList<Item>)msg.obj;
                    myadapter=new MyAdapter(RateList2Activity.this,R.layout.list_item,rlist);
                    list2.setAdapter(myadapter);
                }
            }
        };

//        RateMapThread dt=new RateMapThread(handler);
        RateItemThread dt=new RateItemThread(handler);

        Thread t=new Thread(dt);
        t.start();

    }

    @Override
    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long l) {
        Log.i(TAG, "onItemLongClick: 长按操作");
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("提示")
                .setMessage("请确认删除当前数据")
                .setPositiveButton("是", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.i(TAG, "onClick: 对话框事件处理");
                        //删除数据项
                        myadapter.remove(list2.getItemAtPosition(position));


                    }
                }).setNegativeButton("否",null);
        builder.create().show();



        return false;//不区分单击事件和长按事件
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Object itemAtposition=list2.getItemAtPosition(position);
        Item item=(Item) itemAtposition;
//        HashMap<String,String> map=(HashMap<String,String>)itemAtposition;
//        String titleStr=map.get("cname");
//        String detailStr=map.get("cval");
        String titleStr=item.getCname();
        String detailStr=item.getCval();
        Log.i(TAG, "onItemClick: titleStr="+titleStr);
        Log.i(TAG, "onItemClick: detailStr="+detailStr);

        TextView title=(TextView) view.findViewById(R.id.itemTitle);
        TextView detail=(TextView) view.findViewById(R.id.itemDetail);
        String title2=String.valueOf(title.getText());
        String detail2=String.valueOf(detail.getText());

        Log.i(TAG, "onItemClick: title2="+title2);
        Log.i(TAG, "onItemClick: detail2="+detail2);
        //跳转到另一个页面
        Intent config=new Intent(this,Calactivity.class);
        config.putExtra("moneyType",titleStr);
        config.putExtra("rate",detailStr);

//        //跳转到另一个页面
//        Intent config=new Intent(this,calculateRate.class);
//        config.putExtra("moneyType",titleStr);
//        config.putExtra("rate",detailStr);

        //startActivity(config);
        startActivityForResult(config,1);//code填任意整数,相当于给不同窗口的编号


    }
}