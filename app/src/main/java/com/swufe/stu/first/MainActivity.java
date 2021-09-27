package com.swufe.stu.first;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.time.LocalDate;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "wwww";
    int score,score1,score2;
    //TextView output,output1,output2;
    double dollarRate=0.1548;
    double euroRate=0.1324;
    double wonRate=182.69;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.money);


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
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    //设置菜单
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mymenu,menu);

        return true;//当前有菜单项
    }

    //点击菜单可跳转
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.menu_setting){
            config();
        }
        return super.onOptionsItemSelected(item);
    }
}