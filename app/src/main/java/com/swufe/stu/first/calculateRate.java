package com.swufe.stu.first;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class calculateRate extends AppCompatActivity {
    String name,crate;
    double resultMoney;
    TextView input1,input2,result;
    private static final String TAG="calculateRate";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculate_rate);
        input1=findViewById(R.id.nameText);
        input2=findViewById(R.id.rmbText);
        result=findViewById(R.id.resultText);
        //获得上一个页面的数据
        Intent intent=getIntent();
        name=intent.getStringExtra("moneyType");
        crate=intent.getStringExtra("rate");
        input1.setText(String.valueOf(name));
    }

    public void clickRate(View btn){
        //获得用户输入的人民币
        String rmb=input2.getText().toString();
        Double drate=Double.parseDouble(crate);
        if(rmb.length()>0){
            Double r=Double.parseDouble(rmb);
            resultMoney=r*(100/drate);
            result.setText(String.valueOf(resultMoney));
        }else{
            Toast.makeText(calculateRate.this, "please input rmb", Toast.LENGTH_SHORT).show();
        }
    }
}