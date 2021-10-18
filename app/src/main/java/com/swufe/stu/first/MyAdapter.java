package com.swufe.stu.first;

import android.content.Context;
import android.nfc.Tag;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MyAdapter extends ArrayAdapter {

    public MyAdapter(Context context, int resource, ArrayList<HashMap<String,String>> data){
        super(context,resource,data);
    }
    public View getView(int position, View convertView, ViewGroup parent){
        View itemView=convertView;
        if(itemView==null){
            itemView= LayoutInflater.from(getContext()).inflate(R.layout.list_item,parent,false);

        }
        Map<String,String> map=(Map<String,String>)getItem(position);
        TextView title=(TextView) itemView.findViewById(R.id.itemTitle);
        TextView detail=(TextView) itemView.findViewById(R.id.itemDetail);
        title.setText("货币:"+map.get("cname"));
        detail.setText("汇率:"+map.get("cval"));

        return itemView;
    }

}
