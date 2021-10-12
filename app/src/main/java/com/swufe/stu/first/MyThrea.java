package com.swufe.stu.first;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class MyThrea implements Runnable{
    private static final String TAG="MyThread";
    private Handler handler;
    Bundle bundle;
    List<String> retlist=new ArrayList<String>();



    public void setHandler(Handler handler){
        this.handler=handler;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(3000);
        }catch (InterruptedException e) {
            e.printStackTrace();
        }
        Log.i(TAG, "run: run()");

        //获取网络数据
        URL url=null;
        String d_rate_hs="",e_rate_hs="",w_rate_hs="";
        try {
//            url=new URL("https://www.usd-cny.com/bankofchina.htm");
//            HttpURLConnection http=(HttpURLConnection) url.openConnection();
//            InputStream in=http.getInputStream();
//            String html=inputStream2String(in);
//            Log.i(TAG, "run: html="+html);

            //获得document对象
            Document doc= Jsoup.connect("https://www.usd-cny.com/bankofchina.htm").get();
            //获得页面元素
            Log.i(TAG, "run: title="+doc.title());

            Elements h4s=doc.getElementsByTag("h4");
            for(Element h4:h4s){
                Log.i(TAG, "run: h4="+h4.text());
            }

            //获得表格内容
            Elements tables=doc.getElementsByTag("table");//集合对象
            Element table1=tables.first();
            Log.i(TAG, "run: table="+table1);

//            Elements trs=table1.getElementsByTag("tr");
//            for(Element tr:trs){
//                Log.i(TAG, "run: tr="+tr);
//            }

            Elements hrefs=table1.getElementsByTag("a");
            for(Element href:hrefs){
                Log.i(TAG, "run: href="+href.text());
            }
            bundle=new Bundle();

            Elements trs=table1.getElementsByTag("tr");
            trs.remove(0);
            for(Element tr:trs){
                Elements tds=tr.getElementsByTag("td");
                String cname=tds.get(0).text();
                String cval=tds.get(5).text();
                Log.i(TAG, "run: "+cname+"==>"+cval);
                retlist.add(cname+"==>"+cval);


//                if("美元".equals(cname)){
//                    bundle.putFloat("r1",100f/Float.parseFloat(cval));
//                }else if("欧元".equals(cname)){
//                    bundle.putFloat("r2",100f/Float.parseFloat(cval));
//
//                }else if("韩元".equals(cname)){
//                    bundle.putFloat("r3",Float.parseFloat(cval));
//                }
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }



        //发送消息给主线程
        Message msg=handler.obtainMessage();
        msg.what=7;
        //msg.obj="Hello from run";
        msg.obj=retlist;


        handler.sendMessage(msg);

        Log.i(TAG, "run: 消息已发送");
    }

}
