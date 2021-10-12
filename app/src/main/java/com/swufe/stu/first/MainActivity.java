package com.swufe.stu.first;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
// implements  Runnable
public class MainActivity extends AppCompatActivity  implements  Runnable{

    private static final String TAG = "wwww";
    int score,score1,score2;
    TextView output,output1,output2;
    double dollarRate=0.1548;
    double euroRate=0.1324;
    double wonRate=182.69;
    long TIMEOUT_MILLS = 5 * 60 * 1000L;


    Handler handler;//全局变量
    Bundle bundle;


    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.money);
        List<String> list1=new ArrayList<String>();

        //从xml文件中读取数据
        SharedPreferences sharedPreferences=getSharedPreferences("myrate",Activity.MODE_PRIVATE);
        dollarRate=(double) sharedPreferences.getFloat("d_rate",0.1f);
        euroRate=(double) sharedPreferences.getFloat("e_rate",0.2f);
        wonRate=(double) sharedPreferences.getFloat("w_rate",0.3f);



        handler=new Handler(Looper.myLooper()){
            //接收消息
            @Override
            public void handleMessage(@NonNull Message msg) {

                //接收消息
                if(msg.what==6){
                    Log.i(TAG, "handleMessage: 消息收到");
                    Bundle bdl=(Bundle) msg.obj;
                    dollarRate=(double) bdl.getFloat("r1");
                    euroRate=(double) bdl.getFloat("r2");
                    wonRate=(double) bdl.getFloat("r3");
                    Log.i(TAG, "handleMessage: dollarRate="+dollarRate);
                    Log.i(TAG, "handleMessage: euroRate="+euroRate);
                    Log.i(TAG, "handleMessage: wonRate="+wonRate);

                    Toast.makeText(MainActivity.this, "数据已更新", Toast.LENGTH_SHORT).show();


//                    String rates[]=str.split(";");
//                    if(rates[0].length()>0)
//                        dollarRate=100/Double.parseDouble(rates[0]);
//                    euroRate=100/Double.parseDouble(rates[1]);
//                    wonRate=100/Double.parseDouble(rates[2]);
//                    Log.i(TAG, "handleMessage: dollarTate="+String.valueOf(dollarRate));
//                    Log.i(TAG, "handleMessage: euroTate="+String.valueOf(euroRate));
//                    Log.i(TAG, "handleMessage: wonTate="+String.valueOf(wonRate));
//                    Log.i(TAG, "handleMessage: str="+str);

                    //把新数据保存在文件
                     SharedPreferences sp=getSharedPreferences("myrate", Activity.MODE_PRIVATE);
                     SharedPreferences.Editor editor=sp.edit();
                     editor.putFloat("d_rate",(float) dollarRate);
                     editor.putFloat("e_rate",(float) euroRate);
                     editor.putFloat("w_rate",(float) wonRate);
                     editor.apply();

                }
                super.handleMessage(msg);
            }
        };
        //开启线程
//        MyThrea td=new MyThrea();
//        td.setHandler(handler);
        //Thread t=new Thread();
        Thread t=new Thread(this);
        t.start();

//        output.setText("time"+java.time.LocalTime.now());
        Log.i(TAG, "onCreate: 开始运行了");
//        Button btn=findViewById(R.id.button6);
//        //给按钮设置监听
//        btn.setOnClickListener(this);//this表示当前窗口
//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                EditText edit=findViewById(R.id.editTextTextPersonName);
//                String uname=edit.getText().toString();
//                //显示输出到控件
//                TextView output=findViewById(R.id.textView3);
//                output.setText("hi~"+uname);
//
////                output.setText("hi~android");
//            }
//        });





//        Log.i(tag:"11111")
//        output.setText("Hello Swufe");
    }
//    public void mylick(View v){
//        Log.i(TAG, "onClick: AAAAAAA");
//        //获取用户输入数据
//        EditText edit1=findViewById(R.id.editTextTextPersonName);//身高
//        String height=edit1.getText().toString();
//        EditText edit2=findViewById(R.id.editTextTextPersonName3);//体重
//        String weight=edit2.getText().toString();
//
//        double h,w;
//        h=Double.parseDouble(height);
//        w=Double.parseDouble(weight);
//        double bmi;
//        bmi=w/(h*h);
//        DecimalFormat df   = new DecimalFormat("######0.00");
//
//        //显示输出到控件
//        TextView output=findViewById(R.id.textView4);
//        String pro;//保存建议
//        if(bmi<18.5)
//            pro="您的体重过轻，请增加营养摄入！";
//        else if(bmi<23.9)
//            pro="您的BMI指数正常，请继续保持！";
//        else if(bmi<27.9)
//            pro="您的体重超重，请增加锻炼！";
//        else
//            pro="您的体重已属于肥胖范畴，请增加锻炼，合理饮食，防止危害健康！";
//        output.setText("您的BMI指数为："+String.valueOf(df.format(bmi))+'\n'+pro);
//    }
//    public void myclick1(View v){
//        //分数显示
//        TextView output=findViewById(R.id.textView7);//java中寻找控件
//
//        String s_points=output.getText().toString();
//        int points;
//        points=Integer.parseInt(s_points);
//        points=points+1;
//        output.setText(String.valueOf(points));

//
//    }
//
//    public void myclick2(View v){
//        //分数显示
//        TextView output=findViewById(R.id.textView7);//java中寻找控件
//
//        String s_points=output.getText().toString();
//        int points;
//        points=Integer.parseInt(s_points);
//        points=points+2;
//        output.setText(String.valueOf(points));
//
//    }
//
//    public void myclick3(View v){
//        //分数显示
//        TextView output=findViewById(R.id.textView7);//java中寻找控件
//
//        String s_points=output.getText().toString();
//        int points;
//        points=Integer.parseInt(s_points);
//        points=points+3;
//        output.setText(String.valueOf(points));
//
//    }
//
//    public void myclick0(View v){
//        //分数显示
//        TextView output=findViewById(R.id.textView7);//java中寻找控件
//
//
//        output.setText(String.valueOf(0));
//
//    }

//    public void myclick(View btn){
//        output=findViewById(R.id.textView7);
//        score=Integer.parseInt(output.getText().toString());
//
//        if(btn.getId()==R.id.btn1){
//            score++;
//        }
//        else if(btn.getId()==R.id.btn2){
//            score=score+2;
//        }
//        else if(btn.getId()==R.id.btn3){
//            score=score+3;
//        }
//        else {
//            score=0;
//        }
//        output.setText(String.valueOf(score));
//
//    }
//
//    public void myclicka(View btn1){
//        output1=findViewById(R.id.textView20);
//        score1=Integer.parseInt(output1.getText().toString());
//
//        if(btn1.getId()==R.id.btna1){
//            score1++;
//        }else if(btn1.getId()==R.id.btna2){
//            score1=score1+2;
//        }else if(btn1.getId()==R.id.btna3){
//            score1=score1+3;
//        }
//        output1.setText(String.valueOf(score1));
//    }
//
//    public void myclickb(View btn2){
//        output2=findViewById(R.id.textView22);
//        score2=Integer.parseInt(output2.getText().toString());
//
//        if(btn2.getId()==R.id.btnb1){
//            score2++;
//        }else if(btn2.getId()==R.id.btnb2){
//            score2=score2+2;
//        }else if(btn2.getId()==R.id.btnb3){
//            score2=score2+3;
//        }
//        output2.setText(String.valueOf(score2));
//    }
//
//    public void myclick0(View btn){
//        output1=findViewById(R.id.textView20);
//        output2=findViewById(R.id.textView22);
//        score=0;
//        output1.setText(String.valueOf(0));
//        output2.setText(String.valueOf(0));
//
//    }


//    @Override
//    protected void onSaveInstanceState(@NonNull Bundle outState) {
//        super.onSaveInstanceState(outState);
//    }

    public void myclick(View btn){
        double rmb,output_rmb=0;
        TextView input,output;
        String rmb_input;
        input=findViewById(R.id.editTextTextPersonName2);
        output=findViewById(R.id.textView8);
        rmb_input=input.getText().toString();

        if(rmb_input.length()==0){
            output.setText("Hello");
            Toast.makeText(this, "您的输入为空！", Toast.LENGTH_SHORT).show();
        }
        else{
            rmb=Double.parseDouble(rmb_input);
            if(btn.getId()==R.id.btn7){
                //美元
                output_rmb=rmb*dollarRate;
            }else if(btn.getId()==R.id.btn8){
                //欧元
                output_rmb=rmb*euroRate;

            }else if(btn.getId()==R.id.btn9){
                //韩元
                output_rmb=rmb*wonRate;

            }
            output.setText(String.valueOf(output_rmb));
        }




    }
    //跳转到其他窗口
    public void openOne(View btn){
        //打开新窗口
        config();
    }

    private void config() {
        Intent config=new Intent(this,MainActivity3.class);
        config.putExtra("dollar_key",dollarRate);
        config.putExtra("euro_key",euroRate);
        config.putExtra("won_key",wonRate);

        //startActivity(config);
        startActivityForResult(config,1);//code填任意整数,相当于给不同窗口的编号
    }

    //从跳转过去的页面接收数据
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if(requestCode==1&&resultCode==3){
            dollarRate=data.getDoubleExtra("new_dollar",0.1);
            euroRate=data.getDoubleExtra("new_euro",0.1);
            wonRate=data.getDoubleExtra("new_won",0.1);

            //保存数据到xml文件
            SharedPreferences sp=getSharedPreferences("myrate",Activity.MODE_PRIVATE);
            SharedPreferences.Editor editor=sp.edit();
            editor.putFloat("d_rate",(float) dollarRate);
            editor.putFloat("e_rate",(float) euroRate);
            editor.putFloat("w_rate",(float) wonRate);
            editor.apply();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }



    //设置菜单，在当前页面启用菜单
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mymenu,menu);

        return true;//当前有菜单项
    }

    //点击菜单可跳转到指定页面
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.menu_setting){
            config();
        }
        return super.onOptionsItemSelected(item);
    }

    Timer timer=new Timer(true);

    //设置任务
    public TimerTask timeoutTask=new TimerTask() {
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
                Document doc=Jsoup.connect("https://www.usd-cny.com/bankofchina.htm").get();
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

                    if("美元".equals(cname)){
                        bundle.putFloat("r1",100f/Float.parseFloat(cval));
                    }else if("欧元".equals(cname)){
                        bundle.putFloat("r2",100f/Float.parseFloat(cval));

                    }else if("韩元".equals(cname)){
                        bundle.putFloat("r3",Float.parseFloat(cval));
                    }
                }


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }



            //发送消息给主线程
            Message msg=handler.obtainMessage();
            msg.what=6;
            //msg.obj="Hello from run";
            msg.obj=bundle;


            handler.sendMessage(msg);

            Log.i(TAG, "run: 消息已发送");
        }
    };
    public void scheduleTimeout(){
        timer.schedule(timeoutTask,TIMEOUT_MILLS);
    }

    public void cancel(){
        //timer cancel后不能再次调用schedule方法，需要重新创建，所以可以调用task.cancel方法取消任务
        //timer.cancel();
        timeoutTask.cancel();
    }

//        @Override
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
                Document doc=Jsoup.connect("https://www.usd-cny.com/bankofchina.htm").get();
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

                    if("美元".equals(cname)){
                        bundle.putFloat("r1",100f/Float.parseFloat(cval));
                    }else if("欧元".equals(cname)){
                        bundle.putFloat("r2",100f/Float.parseFloat(cval));

                    }else if("韩元".equals(cname)){
                        bundle.putFloat("r3",Float.parseFloat(cval));
                    }
                }



            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }



            //发送消息给主线程
            Message msg=handler.obtainMessage();
            msg.what=6;
            //msg.obj="Hello from run";
            msg.obj=bundle;


            handler.sendMessage(msg);

            Log.i(TAG, "run: 消息已发送");
        }



//    public void run() {
//        try {
//            Thread.sleep(3000);
//        }catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        Log.i(TAG, "run: run()");
//
//        //获取网络数据
//        URL url=null;
//        String d_rate_hs="",e_rate_hs="",w_rate_hs="";
//        try {
////            url=new URL("https://www.usd-cny.com/bankofchina.htm");
////            HttpURLConnection http=(HttpURLConnection) url.openConnection();
////            InputStream in=http.getInputStream();
////            String html=inputStream2String(in);
////            Log.i(TAG, "run: html="+html);
//
//            //获得document对象
//            Document doc=Jsoup.connect("https://www.usd-cny.com/bankofchina.htm").get();
//            //获得页面元素
//            Log.i(TAG, "run: title="+doc.title());
//
//            Elements h4s=doc.getElementsByTag("h4");
//            for(Element h4:h4s){
//                Log.i(TAG, "run: h4="+h4.text());
//            }
//
//            //获得表格内容
//            Elements tables=doc.getElementsByTag("table");//集合对象
//            Element table1=tables.first();
//            Log.i(TAG, "run: table="+table1);
//
////            Elements trs=table1.getElementsByTag("tr");
////            for(Element tr:trs){
////                Log.i(TAG, "run: tr="+tr);
////            }
//
//            Elements hrefs=table1.getElementsByTag("a");
//            for(Element href:hrefs){
//                Log.i(TAG, "run: href="+href.text());
//            }
//            bundle=new Bundle();
//
//            Elements trs=table1.getElementsByTag("tr");
//            trs.remove(0);
//            for(Element tr:trs){
//                Elements tds=tr.getElementsByTag("td");
//                String cname=tds.get(0).text();
//                String cval=tds.get(5).text();
//
//                if("美元".equals(cname)){
//                    bundle.putFloat("r1",100f/Float.parseFloat(cval));
//                }else if("欧元".equals(cname)){
//                    bundle.putFloat("r2",100f/Float.parseFloat(cval));
//
//                }else if("韩元".equals(cname)){
//                    bundle.putFloat("r3",Float.parseFloat(cval));
//                }
//            }
//
//            //获取td中的内容
////            Elements tds=table1.getElementsByTag("td");
////
////            for(int i=0;i< tds.size();i+=6){
////                Element td1=tds.get(i);
////                Element td2=tds.get(i+5);
////                String str1=td1.text();
////                String val=td2.text();
////                if(str1=="美元"){
////                    d_rate_hs =val;
////                    double d_rate_h=100f/Double.parseDouble(val);
////                }
////                if(str1=="欧元"){
////                    e_rate_hs=val;
////                    Double e_rate_h=100f/Double.parseDouble(val);
////                }
////                if(str1=="韩元"){
////                    w_rate_hs=val;
////                    Double w_rate_h=100f/Double.parseDouble(val);
////                }
////
////                Log.i(TAG, "run: "+str1+"==>"+val);
////                float temp=Float.parseFloat(val);
////                float v=100f/temp;
////            }
//
//
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//
//
//        //发送消息给主线程
//        Message msg=handler.obtainMessage();
//        msg.what=6;
//        //msg.obj="Hello from run";
//        msg.obj=bundle;
//
//
//        handler.sendMessage(msg);
//
//        Log.i(TAG, "run: 消息已发送");
//    }

    private String inputStream2String(InputStream inputStream)
        throws IOException {
        final int buffersize = 1024;
        final char[] buffer=new char[buffersize];
        final  StringBuilder out=new StringBuilder();
        Reader in=new InputStreamReader(inputStream,"gb2312");
        while (true){
            int rsz=in.read(buffer,0,buffer.length);
            if(rsz<0)
                break;
            out.append(buffer,0,rsz);

        }
        return out.toString();

    }


}