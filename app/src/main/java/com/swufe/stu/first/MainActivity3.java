package com.swufe.stu.first;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity3 extends AppCompatActivity {

    double dollar2;
    double euro2;
    double won2;
    TextView dollar_input,euro_input,won_input;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rate);
        Intent intent=getIntent();
        dollar2=intent.getDoubleExtra("dollar_key",0.0);
        System.out.println(dollar2);
        euro2=intent.getDoubleExtra("euro_key",0.0);
        System.out.println(euro2);
        won2=intent.getDoubleExtra("won_key",0.0);
        System.out.println(won2);

        dollar_input=findViewById(R.id.editTextDollar);
        euro_input=findViewById(R.id.editTextEuro);
        won_input=findViewById(R.id.editTextWon);
        //将汇率放到控件中
        dollar_input.setText(String.valueOf(dollar2));
        euro_input.setText(String.valueOf(euro2));
        won_input.setText(String.valueOf(won2));



    }
    public void getRate(View btn){
        dollar_input=findViewById(R.id.editTextDollar);
        euro_input=findViewById(R.id.editTextEuro);
        won_input=findViewById(R.id.editTextWon);
        dollar2=Double.parseDouble(dollar_input.getText().toString());
        euro2=Double.parseDouble(euro_input.getText().toString());
        won2=Double.parseDouble(won_input.getText().toString());

        //带着数据回到之前窗口
        Intent intent1=getIntent();
        intent1.putExtra("new_dollar",dollar2);
        intent1.putExtra("new_euro",euro2);
        intent1.putExtra("new_won",won2);
        setResult(3,intent1);//code填任意整数，确认不同的返回
        finish();//回到打开他的地方



    }



}